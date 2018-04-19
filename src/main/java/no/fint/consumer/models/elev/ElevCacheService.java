package no.fint.consumer.models.elev;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
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

import no.fint.model.utdanning.elev.Elev;
import no.fint.model.resource.utdanning.elev.ElevResource;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@Service
public class ElevCacheService extends CacheService<ElevResource> {

    public static final String MODEL = Elev.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private ElevLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public ElevCacheService() {
        super(MODEL, ElevActions.GET_ALL_ELEV, ElevActions.UPDATE_ELEV);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, ElevResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_ELEV, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_ELEV)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Elev cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_ELEV, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<ElevResource> getElevByBrukernavn(String orgId, String brukernavn) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getBrukernavn)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(brukernavn))
                .orElse(false));
    }

    public Optional<ElevResource> getElevByElevnummer(String orgId, String elevnummer) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getElevnummer)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(elevnummer))
                .orElse(false));
    }

    public Optional<ElevResource> getElevByFeidenavn(String orgId, String feidenavn) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getFeidenavn)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(feidenavn))
                .orElse(false));
    }

    public Optional<ElevResource> getElevBySystemId(String orgId, String systemId) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(systemId))
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<ElevResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<ElevResource> to ElevResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), ElevResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(linker::toResource);
        if (ElevActions.valueOf(event.getAction()) == ElevActions.UPDATE_ELEV) {
            add(event.getOrgId(), data);
            log.info("Added {} elements to cache for {}", data.size(), event.getOrgId());
        } else {
            update(event.getOrgId(), data);
            log.info("Updated cache for {} with {} elements", event.getOrgId(), data.size());
        }
    }
}
