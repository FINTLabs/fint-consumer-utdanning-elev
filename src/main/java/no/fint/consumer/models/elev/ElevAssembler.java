package no.fint.consumer.models.elev;

import no.fint.model.utdanning.elev.Elev;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class ElevAssembler extends FintResourceAssembler<Elev> {

    public ElevAssembler() {
        super(ElevController.class);
    }



    @Override
    public FintResourceSupport assemble(Elev elev , FintResource<Elev> fintResource) {
        return createResourceWithId(elev.getElevnummer().getIdentifikatorverdi(), fintResource, "elevnummer");
    }
    
    
    
}

