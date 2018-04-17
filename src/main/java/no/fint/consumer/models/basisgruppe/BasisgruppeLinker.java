package no.fint.consumer.models.basisgruppe;

import no.fint.model.resource.utdanning.elev.BasisgruppeResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class BasisgruppeLinker extends FintLinker<BasisgruppeResource> {

    public BasisgruppeLinker() {
        super(BasisgruppeResource.class);
    }


    @Override
    public String getSelfHref(BasisgruppeResource basisgruppe) {
        return createHrefWithId(basisgruppe.getSystemId().getIdentifikatorverdi(), "systemid");
    }
    
    
    
}

