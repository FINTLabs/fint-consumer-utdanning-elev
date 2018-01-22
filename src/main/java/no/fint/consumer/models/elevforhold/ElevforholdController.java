package no.fint.consumer.models.elevforhold;

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

import no.fint.model.utdanning.elev.Elevforhold;
import no.fint.model.utdanning.elev.ElevActions;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = RestEndpoints.ELEVFORHOLD, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ElevforholdController {

    @Autowired
    private ElevforholdCacheService cacheService;

    @Autowired
    private FintAuditService fintAuditService;

    @Autowired
    private ElevforholdAssembler assembler;

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
    public ResponseEntity getElevforhold(@RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client,
            @RequestParam(required = false) Long sinceTimeStamp) {
        log.info("OrgId: {}, Client: {}", orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ALL_ELEVFORHOLD, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        List<FintResource<Elevforhold>> elevforhold;
        if (sinceTimeStamp == null) {
            elevforhold = cacheService.getAll(orgId);
        } else {
            elevforhold = cacheService.getAll(orgId, sinceTimeStamp);
        }

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        return assembler.resources(elevforhold);
    }


    @GetMapping("/systemid/{id}")
    public ResponseEntity getElevforholdBySystemId(@PathVariable String id,
            @RequestHeader(HeaderConstants.ORG_ID) String orgId,
            @RequestHeader(HeaderConstants.CLIENT) String client) {
        log.info("SystemId: {}, OrgId: {}, Client: {}", id, orgId, client);

        Event event = new Event(orgId, Constants.COMPONENT, ElevActions.GET_ELEVFORHOLD, client);
        fintAuditService.audit(event);

        fintAuditService.audit(event, Status.CACHE);

        Optional<FintResource<Elevforhold>> elevforhold = cacheService.getElevforholdBySystemId(orgId, id);

        fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT);

        if (elevforhold.isPresent()) {
            return assembler.resource(elevforhold.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}

