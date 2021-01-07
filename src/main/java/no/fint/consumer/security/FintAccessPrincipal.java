package no.fint.consumer.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class FintAccessPrincipal {
    private final String name, orgId;
}
