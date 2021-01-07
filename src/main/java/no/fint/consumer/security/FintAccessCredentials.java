package no.fint.consumer.security;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public final class FintAccessCredentials {
    private final Set<String> read, modify, collection;
}
