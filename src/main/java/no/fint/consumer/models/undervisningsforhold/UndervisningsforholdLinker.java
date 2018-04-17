package no.fint.consumer.models.undervisningsforhold;

import no.fint.model.resource.utdanning.elev.UndervisningsforholdResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class UndervisningsforholdLinker extends FintLinker<UndervisningsforholdResource> {

    public UndervisningsforholdLinker() {
        super(UndervisningsforholdResource.class);
    }


    @Override
    public String getSelfHref(UndervisningsforholdResource undervisningsforhold) {
        return createHrefWithId(undervisningsforhold.getSystemId().getIdentifikatorverdi(), "systemid");
    }
    
    
    
}

