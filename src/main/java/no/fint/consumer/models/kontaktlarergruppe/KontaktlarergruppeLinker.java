package no.fint.consumer.models.kontaktlarergruppe;

import no.fint.model.resource.utdanning.elev.KontaktlarergruppeResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class KontaktlarergruppeLinker extends FintLinker<KontaktlarergruppeResource> {

    public KontaktlarergruppeLinker() {
        super(KontaktlarergruppeResource.class);
    }


    @Override
    public String getSelfHref(KontaktlarergruppeResource kontaktlarergruppe) {
        return createHrefWithId(kontaktlarergruppe.getSystemId().getIdentifikatorverdi(), "systemid");
    }
    
    
    
}

