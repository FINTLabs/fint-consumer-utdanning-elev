package no.fint.consumer.models.skoleressurs;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.SkoleressursResource;
import no.fint.model.resource.utdanning.elev.SkoleressursResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class SkoleressursLinker extends FintLinker<SkoleressursResource> {

    public SkoleressursLinker() {
        super(SkoleressursResource.class);
    }

    public void mapLinks(SkoleressursResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkoleressursResources toResources(Collection<SkoleressursResource> collection) {
        SkoleressursResources resources = new SkoleressursResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(SkoleressursResource skoleressurs) {
        if (!isNull(skoleressurs.getFeidenavn()) && !isEmpty(skoleressurs.getFeidenavn().getIdentifikatorverdi())) {
            return createHrefWithId(skoleressurs.getFeidenavn().getIdentifikatorverdi(), "feidenavn");
        }
        if (!isNull(skoleressurs.getSystemId()) && !isEmpty(skoleressurs.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(skoleressurs.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

