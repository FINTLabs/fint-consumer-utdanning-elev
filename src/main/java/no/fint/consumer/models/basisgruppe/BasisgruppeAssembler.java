package no.fint.consumer.models.basisgruppe;

import no.fint.model.utdanning.elev.Basisgruppe;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class BasisgruppeAssembler extends FintResourceAssembler<Basisgruppe> {

    public BasisgruppeAssembler() {
        super(BasisgruppeController.class);
    }


    @Override
    public FintResourceSupport assemble(Basisgruppe basisgruppe , FintResource<Basisgruppe> fintResource) {
        return createResourceWithId(basisgruppe.getSystemId().getIdentifikatorverdi(), fintResource, "systemId");
    }
    
    
}

