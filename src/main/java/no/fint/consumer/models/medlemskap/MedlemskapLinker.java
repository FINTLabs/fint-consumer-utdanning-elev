package no.fint.consumer.models.medlemskap;

import no.fint.model.resource.utdanning.elev.MedlemskapResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class MedlemskapLinker extends FintLinker<MedlemskapResource> {

    public MedlemskapLinker() {
        super(MedlemskapResource.class);
    }

    public void mapLinks(MedlemskapResource resource) {
        super.mapLinks(resource);
    }
    
    @Override
    public String getSelfHref(MedlemskapResource medlemskap) {
        if (medlemskap.getSystemId() != null && medlemskap.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(medlemskap.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

