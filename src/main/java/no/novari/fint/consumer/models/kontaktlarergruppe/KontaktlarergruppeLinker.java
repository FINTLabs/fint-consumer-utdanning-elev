package no.novari.fint.consumer.models.kontaktlarergruppe;

import no.novari.fint.model.resource.utdanning.elev.KontaktlarergruppeResource;
import no.novari.fint.model.resource.utdanning.elev.KontaktlarergruppeResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class KontaktlarergruppeLinker extends FintLinker<KontaktlarergruppeResource> {

    public KontaktlarergruppeLinker() {
        super(KontaktlarergruppeResource.class);
    }

    public void mapLinks(KontaktlarergruppeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public KontaktlarergruppeResources toResources(Collection<KontaktlarergruppeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public KontaktlarergruppeResources toResources(Stream<KontaktlarergruppeResource> stream, int offset, int size, int totalItems) {
        KontaktlarergruppeResources resources = new KontaktlarergruppeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(KontaktlarergruppeResource kontaktlarergruppe) {
        return getAllSelfHrefs(kontaktlarergruppe).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(KontaktlarergruppeResource kontaktlarergruppe) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(kontaktlarergruppe.getSystemId()) && !isEmpty(kontaktlarergruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(kontaktlarergruppe.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(KontaktlarergruppeResource kontaktlarergruppe) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(kontaktlarergruppe.getSystemId()) && !isEmpty(kontaktlarergruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(kontaktlarergruppe.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

