package no.fint.consumer.models.basisgruppe;

import no.fint.model.resource.utdanning.elev.BasisgruppeResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class BasisgruppeLinker extends FintLinker<BasisgruppeResource> {

    public BasisgruppeLinker() {
        super(BasisgruppeResource.class);
    }

    public void mapLinks(BasisgruppeResource resource) {
        super.mapLinks(resource);
    }
    
    @Override
    public String getSelfHref(BasisgruppeResource basisgruppe) {
        if (basisgruppe.getSystemId() != null && basisgruppe.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(basisgruppe.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

