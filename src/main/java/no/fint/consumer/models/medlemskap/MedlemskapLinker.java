package no.fint.consumer.models.medlemskap;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.MedlemskapResource;
import no.fint.model.resource.utdanning.elev.MedlemskapResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class MedlemskapLinker extends FintLinker<MedlemskapResource> {

    public MedlemskapLinker() {
        super(MedlemskapResource.class);
    }

    public void mapLinks(MedlemskapResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public MedlemskapResources toResources(Collection<MedlemskapResource> collection) {
        MedlemskapResources resources = new MedlemskapResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(MedlemskapResource medlemskap) {
        if (!isNull(medlemskap.getSystemId()) && !isEmpty(medlemskap.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(medlemskap.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

