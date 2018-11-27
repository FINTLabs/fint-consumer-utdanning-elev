package no.fint.consumer.models.basisgruppe;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.elev.BasisgruppeResource;
import no.fint.model.resource.utdanning.elev.BasisgruppeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class BasisgruppeLinker extends FintLinker<BasisgruppeResource> {

    public BasisgruppeLinker() {
        super(BasisgruppeResource.class);
    }

    public void mapLinks(BasisgruppeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BasisgruppeResources toResources(Collection<BasisgruppeResource> collection) {
        BasisgruppeResources resources = new BasisgruppeResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(BasisgruppeResource basisgruppe) {
        if (!isNull(basisgruppe.getSystemId()) && !isEmpty(basisgruppe.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(basisgruppe.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }

    int[] hashCodes(BasisgruppeResource basisgruppe) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(basisgruppe.getSystemId()) && !isEmpty(basisgruppe.getSystemId().getIdentifikatorverdi())) {
            builder.add(basisgruppe.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

