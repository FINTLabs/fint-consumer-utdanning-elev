package no.fint.consumer.models.elevforhold;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.ElevforholdResource;
import no.fint.model.resource.utdanning.elev.ElevforholdResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ElevforholdLinker extends FintLinker<ElevforholdResource> {

    public ElevforholdLinker() {
        super(ElevforholdResource.class);
    }

    public void mapLinks(ElevforholdResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ElevforholdResources toResources(Collection<ElevforholdResource> collection) {
        ElevforholdResources resources = new ElevforholdResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(ElevforholdResource elevforhold) {
        return getAllSelfHrefs(elevforhold).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ElevforholdResource elevforhold) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(elevforhold.getSystemId()) && !isEmpty(elevforhold.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elevforhold.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ElevforholdResource elevforhold) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(elevforhold.getSystemId()) && !isEmpty(elevforhold.getSystemId().getIdentifikatorverdi())) {
            builder.add(elevforhold.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

