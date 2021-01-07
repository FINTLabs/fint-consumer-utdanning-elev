package no.fint.consumer.security;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public final class FintAccessPrincipal {
    private final @NonNull String name, orgId;
}
