package no.fint.consumer.models.kontaktlarergruppe;

import no.fint.model.utdanning.elev.Kontaktlarergruppe;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class KontaktlarergruppeAssembler extends FintResourceAssembler<Kontaktlarergruppe> {

    public KontaktlarergruppeAssembler() {
        super(KontaktlarergruppeController.class);
    }


    @Override
    public FintResourceSupport assemble(Kontaktlarergruppe kontaktlarergruppe , FintResource<Kontaktlarergruppe> fintResource) {
        return createResourceWithId(kontaktlarergruppe.getSystemId().getIdentifikatorverdi(), fintResource, "systemId");
    }
    
    
}

