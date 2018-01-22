package no.fint.consumer.models.undervisningsforhold;

import no.fint.model.utdanning.elev.Undervisningsforhold;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class UndervisningsforholdAssembler extends FintResourceAssembler<Undervisningsforhold> {

    public UndervisningsforholdAssembler() {
        super(UndervisningsforholdController.class);
    }


    @Override
    public FintResourceSupport assemble(Undervisningsforhold undervisningsforhold , FintResource<Undervisningsforhold> fintResource) {
        return createResourceWithId(undervisningsforhold.getSystemId().getIdentifikatorverdi(), fintResource, "systemId");
    }
    
    
}

