package no.fint.consumer.models.medlemskap;

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

import no.fint.model.utdanning.elev.Medlemskap;
import no.fint.model.resource.utdanning.elev.MedlemskapResource;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@Service
public class MedlemskapCacheService extends CacheService<MedlemskapResource> {

    public static final String MODEL = Medlemskap.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private MedlemskapLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public MedlemskapCacheService() {
        super(MODEL, ElevActions.GET_ALL_MEDLEMSKAP);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, MedlemskapResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_MEDLEMSKAP, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_MEDLEMSKAP)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Medlemskap cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_MEDLEMSKAP, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<MedlemskapResource> getMedlemskapBySystemId(String orgId, String systemId) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(MedlemskapResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(systemId))
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<MedlemskapResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<MedlemskapResource> to MedlemskapResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), MedlemskapResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(linker::toResource);
        update(event.getOrgId(), data);
        log.info("Updated cache for {} with {} elements", event.getOrgId(), data.size());
    }
}
