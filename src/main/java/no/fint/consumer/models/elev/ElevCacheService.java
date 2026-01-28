package no.fint.consumer.models.elev;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.cache.model.CacheObject;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;
import no.fint.relations.FintResourceCompatibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.novari.fint.model.utdanning.elev.Elev;
import no.novari.fint.model.resource.utdanning.elev.ElevResource;
import no.novari.fint.model.utdanning.elev.ElevActions;
import no.novari.fint.model.felles.kompleksedatatyper.Identifikator;

@Slf4j
@Service
@ConditionalOnProperty(name = "fint.consumer.cache.disabled.elev", havingValue = "false", matchIfMissing = true)
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
        props.getAssets().forEach(this::createCache);
    }

    @Scheduled(initialDelayString = Constants.CACHE_INITIALDELAY_ELEV, fixedRateString = Constants.CACHE_FIXEDRATE_ELEV)
    public void populateCacheAll() {
        props.getAssets().forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    @Override
    public void populateCache(String orgId) {
		log.info("Populating Elev cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_ELEV, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<ElevResource> getElevByBrukernavn(String orgId, String brukernavn) {
        return getOne(orgId, brukernavn.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getBrukernavn)
                .map(Identifikator::getIdentifikatorverdi)
                .map(brukernavn::equals)
                .orElse(false));
    }

    public Optional<ElevResource> getElevByElevnummer(String orgId, String elevnummer) {
        return getOne(orgId, elevnummer.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getElevnummer)
                .map(Identifikator::getIdentifikatorverdi)
                .map(elevnummer::equals)
                .orElse(false));
    }

    public Optional<ElevResource> getElevByFeidenavn(String orgId, String feidenavn) {
        return getOne(orgId, feidenavn.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getFeidenavn)
                .map(Identifikator::getIdentifikatorverdi)
                .map(feidenavn::equals)
                .orElse(false));
    }

    public Optional<ElevResource> getElevBySystemId(String orgId, String systemId) {
        return getOne(orgId, systemId.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(ElevResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(systemId::equals)
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
        data.forEach(resource -> {
            linker.mapLinks(resource);
            linker.resetSelfLinks(resource);
        });
        if (ElevActions.valueOf(event.getAction()) == ElevActions.UPDATE_ELEV) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                List<CacheObject<ElevResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
                addCache(event.getOrgId(), cacheObjects);
                log.info("Added {} cache objects to cache for {}", cacheObjects.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            List<CacheObject<ElevResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
            updateCache(event.getOrgId(), cacheObjects);
            log.info("Updated cache for {} with {} cache objects", event.getOrgId(), cacheObjects.size());
        }
    }
}
