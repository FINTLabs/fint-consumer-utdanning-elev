package no.fint.consumer.models.undervisningsforhold;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.UndervisningsforholdResource;
import no.fint.model.resource.utdanning.elev.UndervisningsforholdResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class UndervisningsforholdLinker extends FintLinker<UndervisningsforholdResource> {

    public UndervisningsforholdLinker() {
        super(UndervisningsforholdResource.class);
    }

    public void mapLinks(UndervisningsforholdResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public UndervisningsforholdResources toResources(Collection<UndervisningsforholdResource> collection) {
        UndervisningsforholdResources resources = new UndervisningsforholdResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(UndervisningsforholdResource undervisningsforhold) {
        return getAllSelfHrefs(undervisningsforhold).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(UndervisningsforholdResource undervisningsforhold) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(undervisningsforhold.getSystemId()) && !isEmpty(undervisningsforhold.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(undervisningsforhold.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(UndervisningsforholdResource undervisningsforhold) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(undervisningsforhold.getSystemId()) && !isEmpty(undervisningsforhold.getSystemId().getIdentifikatorverdi())) {
            builder.add(undervisningsforhold.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

