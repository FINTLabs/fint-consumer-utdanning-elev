package no.fint.consumer.models.kontaktlarergruppe;

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

import no.fint.model.utdanning.elev.Kontaktlarergruppe;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = RestEndpoints.KONTAKTLARERGRUPPE, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class KontaktlarergruppeController {

    @Autowired
    private KontaktlarergruppeCacheService cacheService;

    @Autowired
    private FintAuditService fintAuditService;

    @Autowired
    private KontaktlarergruppeAssembler assembler;

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
    public ResponseEntity getKontaktlarergruppe(@RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client,
            @RequestParam(required = false) Long sinceTimeStamp) {
        log.info("OrgId: {}, Client: {}", orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_KONTAKTLARERGRUPPE, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        List<FintResource<Kontaktlarergruppe>> kontaktlarergruppe;
        if (sinceTimeStamp == null) {
            kontaktlarergruppe = cacheService.getAll(orgId);
        } else {
            kontaktlarergruppe = cacheService.getAll(orgId, sinceTimeStamp);
        }

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        return assembler.resources(kontaktlarergruppe);
    }


    @GetMapping("/systemid/{id}")
    public ResponseEntity getKontaktlarergruppeBySystemId(@PathVariable String id,
            @RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client) {
        log.info("SystemId: {}, OrgId: {}, Client: {}", id, orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_KONTAKTLARERGRUPPE, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        Optional<FintResource<Kontaktlarergruppe>> kontaktlarergruppe = cacheService.getKontaktlarergruppeBySystemId(orgId, id);

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        if (kontaktlarergruppe.isPresent()) {
            return assembler.resource(kontaktlarergruppe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}

