package no.fint.consumer.models.elevforhold;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.ElevforholdResource;
import no.fint.model.resource.utdanning.elev.ElevforholdResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        if (!isNull(elevforhold.getSystemId()) && !isEmpty(elevforhold.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(elevforhold.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

