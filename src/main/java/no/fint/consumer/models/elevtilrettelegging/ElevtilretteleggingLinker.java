package no.fint.consumer.models.elevtilrettelegging;

import no.fint.model.resource.utdanning.elev.ElevtilretteleggingResource;
import no.fint.model.resource.utdanning.elev.ElevtilretteleggingResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ElevtilretteleggingLinker extends FintLinker<ElevtilretteleggingResource> {

    public ElevtilretteleggingLinker() {
        super(ElevtilretteleggingResource.class);
    }

    public void mapLinks(ElevtilretteleggingResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ElevtilretteleggingResources toResources(Collection<ElevtilretteleggingResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ElevtilretteleggingResources toResources(Stream<ElevtilretteleggingResource> stream, int offset, int size, int totalItems) {
        ElevtilretteleggingResources resources = new ElevtilretteleggingResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ElevtilretteleggingResource elevtilrettelegging) {
        return getAllSelfHrefs(elevtilrettelegging).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ElevtilretteleggingResource elevtilrettelegging) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(elevtilrettelegging.getSystemId()) && !isEmpty(elevtilrettelegging.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elevtilrettelegging.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ElevtilretteleggingResource elevtilrettelegging) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(elevtilrettelegging.getSystemId()) && !isEmpty(elevtilrettelegging.getSystemId().getIdentifikatorverdi())) {
            builder.add(elevtilrettelegging.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

