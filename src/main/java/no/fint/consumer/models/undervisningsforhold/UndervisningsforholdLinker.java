package no.fint.consumer.models.undervisningsforhold;

import no.fint.model.resource.utdanning.elev.UndervisningsforholdResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class UndervisningsforholdLinker extends FintLinker<UndervisningsforholdResource> {

    public UndervisningsforholdLinker() {
        super(UndervisningsforholdResource.class);
    }

    public void mapLinks(UndervisningsforholdResource resource) {
        super.mapLinks(resource);
    }
    
    @Override
    public String getSelfHref(UndervisningsforholdResource undervisningsforhold) {
        if (undervisningsforhold.getSystemId() != null && undervisningsforhold.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(undervisningsforhold.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

