package no.fint.consumer.models.elevforhold;

import no.fint.model.resource.utdanning.elev.ElevforholdResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class ElevforholdLinker extends FintLinker<ElevforholdResource> {

    public ElevforholdLinker() {
        super(ElevforholdResource.class);
    }


    @Override
    public String getSelfHref(ElevforholdResource elevforhold) {
        return createHrefWithId(elevforhold.getSystemId().getIdentifikatorverdi(), "systemid");
    }
    
    
    
}

