package no.fint.consumer.models.elevforhold;

import no.fint.model.utdanning.elev.Elevforhold;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class ElevforholdAssembler extends FintResourceAssembler<Elevforhold> {

    public ElevforholdAssembler() {
        super(ElevforholdController.class);
    }


    @Override
    public FintResourceSupport assemble(Elevforhold elevforhold , FintResource<Elevforhold> fintResource) {
        return createResourceWithId(elevforhold.getSystemId().getIdentifikatorverdi(), fintResource, "systemId");
    }
    
    
}

