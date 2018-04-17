package no.fint.consumer.models.elev;

import no.fint.model.resource.utdanning.elev.ElevResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class ElevLinker extends FintLinker<ElevResource> {

    public ElevLinker() {
        super(ElevResource.class);
    }



    @Override
    public String getSelfHref(ElevResource elev) {
        return createHrefWithId(elev.getElevnummer().getIdentifikatorverdi(), "elevnummer");
    }
    
    
    
}

