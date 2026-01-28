package no.fint.consumer.models.klassemedlemskap;

import no.novari.fint.model.resource.utdanning.elev.KlassemedlemskapResource;
import no.novari.fint.model.resource.utdanning.elev.KlassemedlemskapResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class KlassemedlemskapLinker extends FintLinker<KlassemedlemskapResource> {

    public KlassemedlemskapLinker() {
        super(KlassemedlemskapResource.class);
    }

    public void mapLinks(KlassemedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public KlassemedlemskapResources toResources(Collection<KlassemedlemskapResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public KlassemedlemskapResources toResources(Stream<KlassemedlemskapResource> stream, int offset, int size, int totalItems) {
        KlassemedlemskapResources resources = new KlassemedlemskapResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(KlassemedlemskapResource klassemedlemskap) {
        return getAllSelfHrefs(klassemedlemskap).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(KlassemedlemskapResource klassemedlemskap) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(klassemedlemskap.getSystemId()) && !isEmpty(klassemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(klassemedlemskap.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(KlassemedlemskapResource klassemedlemskap) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(klassemedlemskap.getSystemId()) && !isEmpty(klassemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(klassemedlemskap.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

