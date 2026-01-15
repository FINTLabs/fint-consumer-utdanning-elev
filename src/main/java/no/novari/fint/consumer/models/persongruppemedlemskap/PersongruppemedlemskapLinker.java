package no.novari.fint.consumer.models.persongruppemedlemskap;

import no.novari.fint.model.resource.utdanning.elev.PersongruppemedlemskapResource;
import no.novari.fint.model.resource.utdanning.elev.PersongruppemedlemskapResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class PersongruppemedlemskapLinker extends FintLinker<PersongruppemedlemskapResource> {

    public PersongruppemedlemskapLinker() {
        super(PersongruppemedlemskapResource.class);
    }

    public void mapLinks(PersongruppemedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public PersongruppemedlemskapResources toResources(Collection<PersongruppemedlemskapResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public PersongruppemedlemskapResources toResources(Stream<PersongruppemedlemskapResource> stream, int offset, int size, int totalItems) {
        PersongruppemedlemskapResources resources = new PersongruppemedlemskapResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(PersongruppemedlemskapResource persongruppemedlemskap) {
        return getAllSelfHrefs(persongruppemedlemskap).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(PersongruppemedlemskapResource persongruppemedlemskap) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(persongruppemedlemskap.getSystemId()) && !isEmpty(persongruppemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(persongruppemedlemskap.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(PersongruppemedlemskapResource persongruppemedlemskap) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(persongruppemedlemskap.getSystemId()) && !isEmpty(persongruppemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(persongruppemedlemskap.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

