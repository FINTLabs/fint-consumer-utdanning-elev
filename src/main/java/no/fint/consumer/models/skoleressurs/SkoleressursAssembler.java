package no.fint.consumer.models.skoleressurs;

import no.fint.model.utdanning.elev.Skoleressurs;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class SkoleressursAssembler extends FintResourceAssembler<Skoleressurs> {

    public SkoleressursAssembler() {
        super(SkoleressursController.class);
    }



    @Override
    public FintResourceSupport assemble(Skoleressurs skoleressurs , FintResource<Skoleressurs> fintResource) {
        return createResourceWithId(skoleressurs.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}

