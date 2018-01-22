package no.fint.consumer.models.medlemskap;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.model.relation.FintResource;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import no.fint.model.utdanning.elev.Medlemskap;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@Service
public class MedlemskapCacheService extends CacheService<FintResource<Medlemskap>> {

    public static final String MODEL = Medlemskap.class.getSimpleName().toLowerCase();

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    public MedlemskapCacheService() {
        super(MODEL, ElevActions.GET_ALL_MEDLEMSKAP);
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


    public Optional<FintResource<Medlemskap>> getMedlemskapBySystemId(String orgId, String systemId) {
        Identifikator needle = new Identifikator();
        needle.setIdentifikatorverdi(systemId);
        return getOne(orgId, (fintResource) -> needle.equals(fintResource.getResource().getSystemId()));
    }


	@Override
    public void onAction(Event event) {
        update(event, new TypeReference<List<FintResource<Medlemskap>>>() {
        });
    }
}
