package no.fint.consumer.models.medlemskap;

import no.fint.model.utdanning.elev.Medlemskap;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class MedlemskapAssembler extends FintResourceAssembler<Medlemskap> {

    public MedlemskapAssembler() {
        super(MedlemskapController.class);
    }


    @Override
    public FintResourceSupport assemble(Medlemskap medlemskap , FintResource<Medlemskap> fintResource) {
        return createResourceWithId(medlemskap.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}

