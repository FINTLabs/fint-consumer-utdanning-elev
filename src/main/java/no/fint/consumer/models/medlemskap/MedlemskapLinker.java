package no.fint.consumer.models.medlemskap;

import no.fint.model.resource.utdanning.elev.MedlemskapResource;
import no.fint.model.resource.utdanning.elev.MedlemskapResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class MedlemskapLinker extends FintLinker<MedlemskapResource> {

    public MedlemskapLinker() {
        super(MedlemskapResource.class);
    }

    public void mapLinks(MedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public MedlemskapResources toResources(Collection<MedlemskapResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public MedlemskapResources toResources(Stream<MedlemskapResource> stream, int offset, int size, int totalItems) {
        MedlemskapResources resources = new MedlemskapResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(MedlemskapResource medlemskap) {
        return getAllSelfHrefs(medlemskap).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(MedlemskapResource medlemskap) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(medlemskap.getSystemId()) && !isEmpty(medlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(medlemskap.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(MedlemskapResource medlemskap) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(medlemskap.getSystemId()) && !isEmpty(medlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(medlemskap.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

