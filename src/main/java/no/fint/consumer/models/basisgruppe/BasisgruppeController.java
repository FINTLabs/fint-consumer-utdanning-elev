package no.fint.consumer.models.basisgruppe;

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

import no.fint.model.utdanning.elev.Basisgruppe;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = RestEndpoints.BASISGRUPPE, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class BasisgruppeController {

    @Autowired
    private BasisgruppeCacheService cacheService;

    @Autowired
    private FintAuditService fintAuditService;

    @Autowired
    private BasisgruppeAssembler assembler;

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
    public ResponseEntity getBasisgruppe(@RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client,
            @RequestParam(required = false) Long sinceTimeStamp) {
        log.info("OrgId: {}, Client: {}", orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_BASISGRUPPE, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        List<FintResource<Basisgruppe>> basisgruppe;
        if (sinceTimeStamp == null) {
            basisgruppe = cacheService.getAll(orgId);
        } else {
            basisgruppe = cacheService.getAll(orgId, sinceTimeStamp);
        }

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        return assembler.resources(basisgruppe);
    }


    @GetMapping("/systemid/{id}")
    public ResponseEntity getBasisgruppeBySystemId(@PathVariable String id,
            @RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client) {
        log.info("SystemId: {}, OrgId: {}, Client: {}", id, orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_BASISGRUPPE, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        Optional<FintResource<Basisgruppe>> basisgruppe = cacheService.getBasisgruppeBySystemId(orgId, id);

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        if (basisgruppe.isPresent()) {
            return assembler.resource(basisgruppe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}

