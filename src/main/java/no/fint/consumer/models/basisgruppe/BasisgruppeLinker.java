package no.fint.consumer.models.basisgruppe;

import no.fint.model.resource.utdanning.elev.BasisgruppeResource;
import no.fint.model.resource.utdanning.elev.BasisgruppeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class BasisgruppeLinker extends FintLinker<BasisgruppeResource> {

    public BasisgruppeLinker() {
        super(BasisgruppeResource.class);
    }

    public void mapLinks(BasisgruppeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BasisgruppeResources toResources(Collection<BasisgruppeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BasisgruppeResources toResources(Stream<BasisgruppeResource> stream, int offset, int size, int totalItems) {
        BasisgruppeResources resources = new BasisgruppeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BasisgruppeResource basisgruppe) {
        return getAllSelfHrefs(basisgruppe).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BasisgruppeResource basisgruppe) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(basisgruppe.getSystemId()) && !isEmpty(basisgruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(basisgruppe.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(BasisgruppeResource basisgruppe) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(basisgruppe.getSystemId()) && !isEmpty(basisgruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(basisgruppe.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

