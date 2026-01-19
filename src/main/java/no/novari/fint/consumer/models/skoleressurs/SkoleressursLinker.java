package no.novari.fint.consumer.models.skoleressurs;

import no.novari.fint.model.resource.utdanning.elev.SkoleressursResource;
import no.novari.fint.model.resource.utdanning.elev.SkoleressursResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class SkoleressursLinker extends FintLinker<SkoleressursResource> {

    public SkoleressursLinker() {
        super(SkoleressursResource.class);
    }

    public void mapLinks(SkoleressursResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkoleressursResources toResources(Collection<SkoleressursResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public SkoleressursResources toResources(Stream<SkoleressursResource> stream, int offset, int size, int totalItems) {
        SkoleressursResources resources = new SkoleressursResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(SkoleressursResource skoleressurs) {
        return getAllSelfHrefs(skoleressurs).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(SkoleressursResource skoleressurs) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(skoleressurs.getFeidenavn()) && !isEmpty(skoleressurs.getFeidenavn().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skoleressurs.getFeidenavn().getIdentifikatorverdi(), "feidenavn"));
        }
        if (!isNull(skoleressurs.getSystemId()) && !isEmpty(skoleressurs.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skoleressurs.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(SkoleressursResource skoleressurs) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(skoleressurs.getFeidenavn()) && !isEmpty(skoleressurs.getFeidenavn().getIdentifikatorverdi())) {
            builder.add(skoleressurs.getFeidenavn().getIdentifikatorverdi().hashCode());
        }
        if (!isNull(skoleressurs.getSystemId()) && !isEmpty(skoleressurs.getSystemId().getIdentifikatorverdi())) {
            builder.add(skoleressurs.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

