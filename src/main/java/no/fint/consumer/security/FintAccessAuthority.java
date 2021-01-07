package no.fint.consumer.security;

import org.springframework.security.core.GrantedAuthority;

public interface FintAccessAuthority extends GrantedAuthority {
    boolean isAccessGranted(String method, String path);
}
