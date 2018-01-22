package no.fint.consumer.models.person;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import no.fint.audit.FintAuditService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.utils.RestEndpoints;
import no.fint.event.model.Event;
import no.fint.event.model.HeaderConstants;
import no.fint.event.model.Status;

import no.fint.model.relation.FintResource;
import no.fint.relations.FintRelationsMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import no.fint.model.felles.Person;
import no.fint.model.felles.FellesActions;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = RestEndpoints.PERSON, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class PersonController {

    @Autowired
    private PersonCacheService cacheService;

    @Autowired
    private FintAuditService fintAuditService;

    @Autowired
    private PersonAssembler assembler;

    @GetMapping("/last-updated")
    public Map<String, String> getLastUpdated(@RequestHeader(HeaderConstants.ORG_ID) String orgId) {
        String lastUpdated = Long.toString(cacheService.getLastUpdated(orgId));
        return ImmutableMap.of("lastUpdated", lastUpdated);
    }

    @GetMapping("/cache/size")
     public ImmutableMap<String, Integer> getCacheSize(@RequestHeader(HeaderConstants.ORG_ID) String orgId) {
        return ImmutableMap.of("size", cacheService.getAll(orgId).size());
    }

    @PostMapping("/cache/rebuild")
    public void rebuildCache(@RequestHeader(HeaderConstants.ORG_ID) String orgId) {
        cacheService.rebuildCache(orgId);
    }

    @GetMapping
    public ResponseEntity getPerson(@RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client,
            @RequestParam(required = false) Long sinceTimeStamp) {
        log.info("OrgId: {}, Client: {}", orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, FellesActions.GET_ALL_PERSON, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        List<FintResource<Person>> person;
        if (sinceTimeStamp == null) {
            person = cacheService.getAll(orgId);
        } else {
            person = cacheService.getAll(orgId, sinceTimeStamp);
        }

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        return assembler.resources(person);
    }


    @GetMapping("/fodselsnummer/{id}")
    public ResponseEntity getPersonByFodselsnummer(@PathVariable String id,
            @RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client) {
        log.info("Fodselsnummer: {}, OrgId: {}, Client: {}", id, orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, FellesActions.GET_PERSON, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        Optional<FintResource<Person>> person = cacheService.getPersonByFodselsnummer(orgId, id);

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        if (person.isPresent()) {
            return assembler.resource(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}

