package no.fint.consumer.models.skoleressurs;

import no.fint.model.resource.utdanning.elev.SkoleressursResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class SkoleressursLinker extends FintLinker<SkoleressursResource> {

    public SkoleressursLinker() {
        super(SkoleressursResource.class);
    }

    public void mapLinks(SkoleressursResource resource) {
        super.mapLinks(resource);
    }
    
    @Override
    public String getSelfHref(SkoleressursResource skoleressurs) {
        if (skoleressurs.getFeidenavn() != null && skoleressurs.getFeidenavn().getIdentifikatorverdi() != null) {
            return createHrefWithId(skoleressurs.getFeidenavn().getIdentifikatorverdi(), "feidenavn");
        }
        if (skoleressurs.getSystemId() != null && skoleressurs.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(skoleressurs.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

