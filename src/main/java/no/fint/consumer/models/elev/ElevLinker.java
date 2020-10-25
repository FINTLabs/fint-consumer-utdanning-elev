package no.fint.consumer.models.elev;

import no.fint.model.resource.utdanning.elev.ElevResource;
import no.fint.model.resource.utdanning.elev.ElevResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ElevResources toResources(Stream<ElevResource> stream, int offset, int size, int totalItems) {
        ElevResources resources = new ElevResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ElevResource elev) {
        return getAllSelfHrefs(elev).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ElevResource elev) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(elev.getBrukernavn()) && !isEmpty(elev.getBrukernavn().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elev.getBrukernavn().getIdentifikatorverdi(), "brukernavn"));
        }
        if (!isNull(elev.getElevnummer()) && !isEmpty(elev.getElevnummer().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elev.getElevnummer().getIdentifikatorverdi(), "elevnummer"));
        }
        if (!isNull(elev.getFeidenavn()) && !isEmpty(elev.getFeidenavn().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elev.getFeidenavn().getIdentifikatorverdi(), "feidenavn"));
        }
        if (!isNull(elev.getSystemId()) && !isEmpty(elev.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elev.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ElevResource elev) {
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

