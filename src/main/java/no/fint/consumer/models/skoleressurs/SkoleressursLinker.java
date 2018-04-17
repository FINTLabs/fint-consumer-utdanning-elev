package no.fint.consumer.models.skoleressurs;

import no.fint.model.resource.utdanning.elev.SkoleressursResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class SkoleressursLinker extends FintLinker<SkoleressursResource> {

    public SkoleressursLinker() {
        super(SkoleressursResource.class);
    }



    @Override
    public String getSelfHref(SkoleressursResource skoleressurs) {
        return createHrefWithId(skoleressurs.getSystemId().getIdentifikatorverdi(), "systemid");
    }
    
    
    
}

