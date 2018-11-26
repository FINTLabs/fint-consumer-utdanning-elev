package no.fint.consumer.models.elev;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.ElevResource;
import no.fint.model.resource.utdanning.elev.ElevResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class ElevLinker extends FintLinker<ElevResource> {

    public ElevLinker() {
        super(ElevResource.class);
    }

    public void mapLinks(ElevResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ElevResources toResources(Collection<ElevResource> collection) {
        ElevResources resources = new ElevResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(ElevResource elev) {
        if (!isNull(elev.getBrukernavn()) && !isEmpty(elev.getBrukernavn().getIdentifikatorverdi())) {
            return createHrefWithId(elev.getBrukernavn().getIdentifikatorverdi(), "brukernavn");
        }
        if (!isNull(elev.getElevnummer()) && !isEmpty(elev.getElevnummer().getIdentifikatorverdi())) {
            return createHrefWithId(elev.getElevnummer().getIdentifikatorverdi(), "elevnummer");
        }
        if (!isNull(elev.getFeidenavn()) && !isEmpty(elev.getFeidenavn().getIdentifikatorverdi())) {
            return createHrefWithId(elev.getFeidenavn().getIdentifikatorverdi(), "feidenavn");
        }
        if (!isNull(elev.getSystemId()) && !isEmpty(elev.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(elev.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }

    public int[] hashCodes(ElevResource elev) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(elev.getBrukernavn()) && !isEmpty(elev.getBrukernavn().getIdentifikatorverdi())) {
            builder.add(elev.getBrukernavn().getIdentifikatorverdi().hashCode());
        }
        if (!isNull(elev.getElevnummer()) && !isEmpty(elev.getElevnummer().getIdentifikatorverdi())) {
            builder.add(elev.getElevnummer().getIdentifikatorverdi().hashCode());
        }
        if (!isNull(elev.getFeidenavn()) && !isEmpty(elev.getFeidenavn().getIdentifikatorverdi())) {
            builder.add(elev.getFeidenavn().getIdentifikatorverdi().hashCode());
        }
        if (!isNull(elev.getSystemId()) && !isEmpty(elev.getSystemId().getIdentifikatorverdi())) {
            builder.add(elev.getSystemId().getIdentifikatorverdi().hashCode());
        }
        return builder.build().toArray();
    }
    
}

