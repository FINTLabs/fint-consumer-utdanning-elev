package no.fint.consumer.models.elevforhold;

import no.fint.model.resource.utdanning.elev.ElevforholdResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class ElevforholdLinker extends FintLinker<ElevforholdResource> {

    public ElevforholdLinker() {
        super(ElevforholdResource.class);
    }

    public void mapLinks(ElevforholdResource resource) {
        super.mapLinks(resource);
    }
    
    @Override
    public String getSelfHref(ElevforholdResource elevforhold) {
        if (elevforhold.getSystemId() != null && elevforhold.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(elevforhold.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

