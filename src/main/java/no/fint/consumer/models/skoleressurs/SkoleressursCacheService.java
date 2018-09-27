package no.fint.consumer.models.skoleressurs;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.relations.FintResourceCompatibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import no.fint.model.utdanning.elev.Skoleressurs;
import no.fint.model.resource.utdanning.elev.SkoleressursResource;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@Service
public class SkoleressursCacheService extends CacheService<SkoleressursResource> {

    public static final String MODEL = Skoleressurs.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private SkoleressursLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public SkoleressursCacheService() {
        super(MODEL, ElevActions.GET_ALL_SKOLERESSURS, ElevActions.UPDATE_SKOLERESSURS);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, SkoleressursResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_SKOLERESSURS, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_SKOLERESSURS)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Skoleressurs cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_SKOLERESSURS, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<SkoleressursResource> getSkoleressursByFeidenavn(String orgId, String feidenavn) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(SkoleressursResource::getFeidenavn)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(feidenavn))
                .orElse(false));
    }

    public Optional<SkoleressursResource> getSkoleressursBySystemId(String orgId, String systemId) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(SkoleressursResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(systemId))
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<SkoleressursResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<SkoleressursResource> to SkoleressursResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), SkoleressursResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(linker::mapLinks);
        if (ElevActions.valueOf(event.getAction()) == ElevActions.UPDATE_SKOLERESSURS) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                add(event.getOrgId(), data);
                log.info("Added {} elements to cache for {}", data.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            update(event.getOrgId(), data);
            log.info("Updated cache for {} with {} elements", event.getOrgId(), data.size());
        }
    }
}