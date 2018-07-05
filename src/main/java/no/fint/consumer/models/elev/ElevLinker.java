package no.fint.consumer.models.elev;

import no.fint.model.resource.utdanning.elev.ElevResource;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

@Component
public class ElevLinker extends FintLinker<ElevResource> {

    public ElevLinker() {
        super(ElevResource.class);
    }

    public void mapLinks(ElevResource resource) {
        super.mapLinks(resource);
    }
    
    @Override
    public String getSelfHref(ElevResource elev) {
        if (elev.getBrukernavn() != null && elev.getBrukernavn().getIdentifikatorverdi() != null) {
            return createHrefWithId(elev.getBrukernavn().getIdentifikatorverdi(), "brukernavn");
        }
        if (elev.getElevnummer() != null && elev.getElevnummer().getIdentifikatorverdi() != null) {
            return createHrefWithId(elev.getElevnummer().getIdentifikatorverdi(), "elevnummer");
        }
        if (elev.getFeidenavn() != null && elev.getFeidenavn().getIdentifikatorverdi() != null) {
            return createHrefWithId(elev.getFeidenavn().getIdentifikatorverdi(), "feidenavn");
        }
        if (elev.getSystemId() != null && elev.getSystemId().getIdentifikatorverdi() != null) {
            return createHrefWithId(elev.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

