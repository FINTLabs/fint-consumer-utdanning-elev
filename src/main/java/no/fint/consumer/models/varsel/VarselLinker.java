package no.fint.consumer.models.varsel;

import no.fint.model.resource.utdanning.elev.VarselResource;
import no.fint.model.resource.utdanning.elev.VarselResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class VarselLinker extends FintLinker<VarselResource> {

    public VarselLinker() {
        super(VarselResource.class);
    }

    public void mapLinks(VarselResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public VarselResources toResources(Collection<VarselResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public VarselResources toResources(Stream<VarselResource> stream, int offset, int size, int totalItems) {
        VarselResources resources = new VarselResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(VarselResource varsel) {
        return getAllSelfHrefs(varsel).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(VarselResource varsel) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(varsel.getSystemId()) && !isEmpty(varsel.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(varsel.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(VarselResource varsel) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(varsel.getSystemId()) && !isEmpty(varsel.getSystemId().getIdentifikatorverdi())) {
            builder.add(varsel.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

