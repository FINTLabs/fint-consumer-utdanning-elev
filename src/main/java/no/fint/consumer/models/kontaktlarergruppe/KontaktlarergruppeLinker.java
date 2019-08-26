package no.fint.consumer.models.kontaktlarergruppe;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.KontaktlarergruppeResource;
import no.fint.model.resource.utdanning.elev.KontaktlarergruppeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        KontaktlarergruppeResources resources = new KontaktlarergruppeResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(KontaktlarergruppeResource kontaktlarergruppe) {
        if (!isNull(kontaktlarergruppe.getSystemId()) && !isEmpty(kontaktlarergruppe.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(kontaktlarergruppe.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

