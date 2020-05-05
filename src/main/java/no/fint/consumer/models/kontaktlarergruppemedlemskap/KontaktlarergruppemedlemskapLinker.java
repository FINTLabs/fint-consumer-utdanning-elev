package no.fint.consumer.models.kontaktlarergruppemedlemskap;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.KontaktlarergruppemedlemskapResource;
import no.fint.model.resource.utdanning.elev.KontaktlarergruppemedlemskapResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class KontaktlarergruppemedlemskapLinker extends FintLinker<KontaktlarergruppemedlemskapResource> {

    public KontaktlarergruppemedlemskapLinker() {
        super(KontaktlarergruppemedlemskapResource.class);
    }

    public void mapLinks(KontaktlarergruppemedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public KontaktlarergruppemedlemskapResources toResources(Collection<KontaktlarergruppemedlemskapResource> collection) {
        KontaktlarergruppemedlemskapResources resources = new KontaktlarergruppemedlemskapResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(KontaktlarergruppemedlemskapResource kontaktlarergruppemedlemskap) {
        return getAllSelfHrefs(kontaktlarergruppemedlemskap).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(KontaktlarergruppemedlemskapResource kontaktlarergruppemedlemskap) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(kontaktlarergruppemedlemskap.getSystemId()) && !isEmpty(kontaktlarergruppemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(kontaktlarergruppemedlemskap.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(KontaktlarergruppemedlemskapResource kontaktlarergruppemedlemskap) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(kontaktlarergruppemedlemskap.getSystemId()) && !isEmpty(kontaktlarergruppemedlemskap.getSystemId().getIdentifikatorverdi())) {
            builder.add(kontaktlarergruppemedlemskap.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

