package no.fint.consumer.models.persongruppe;

import no.novari.fint.model.resource.utdanning.elev.PersongruppeResource;
import no.novari.fint.model.resource.utdanning.elev.PersongruppeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class PersongruppeLinker extends FintLinker<PersongruppeResource> {

    public PersongruppeLinker() {
        super(PersongruppeResource.class);
    }

    public void mapLinks(PersongruppeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public PersongruppeResources toResources(Collection<PersongruppeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public PersongruppeResources toResources(Stream<PersongruppeResource> stream, int offset, int size, int totalItems) {
        PersongruppeResources resources = new PersongruppeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(PersongruppeResource persongruppe) {
        return getAllSelfHrefs(persongruppe).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(PersongruppeResource persongruppe) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(persongruppe.getSystemId()) && !isEmpty(persongruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(persongruppe.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(PersongruppeResource persongruppe) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(persongruppe.getSystemId()) && !isEmpty(persongruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(persongruppe.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

