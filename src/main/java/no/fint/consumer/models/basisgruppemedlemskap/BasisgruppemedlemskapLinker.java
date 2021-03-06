package no.fint.consumer.models.basisgruppemedlemskap;

import no.fint.model.resource.utdanning.elev.BasisgruppemedlemskapResource;
import no.fint.model.resource.utdanning.elev.BasisgruppemedlemskapResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class BasisgruppemedlemskapLinker extends FintLinker<BasisgruppemedlemskapResource> {

    public BasisgruppemedlemskapLinker() {
        super(BasisgruppemedlemskapResource.class);
    }

    public void mapLinks(BasisgruppemedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BasisgruppemedlemskapResources toResources(Collection<BasisgruppemedlemskapResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BasisgruppemedlemskapResources toResources(Stream<BasisgruppemedlemskapResource> stream, int offset, int size, int totalItems) {
        BasisgruppemedlemskapResources resources = new BasisgruppemedlemskapResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BasisgruppemedlemskapResource basisgruppemedlemskap) {
        return getAllSelfHrefs(basisgruppemedlemskap).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BasisgruppemedlemskapResource basisgruppemedlemskap) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(basisgruppemedlemskap.getSystemId()) && !isEmpty(basisgruppemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(basisgruppemedlemskap.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(BasisgruppemedlemskapResource basisgruppemedlemskap) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(basisgruppemedlemskap.getSystemId()) && !isEmpty(basisgruppemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(basisgruppemedlemskap.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

