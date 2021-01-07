package no.fint.consumer.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

@Slf4j
public class FintAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        log.info("Supports attribute: {}", attribute);
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("Supports class: {}", clazz);
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation invocation, Collection<ConfigAttribute> attributes) {
        log.debug("VOTING FOR:\nAuthorities: {}\nMethod: {}\nRequest: {}",
                authentication.getAuthorities(),
                invocation.getRequest().getMethod(),
                invocation.getRequest().getRequestURI());
        final String method = invocation.getRequest().getMethod();
        final String requestUri = invocation.getRequest().getRequestURI();

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority instanceof FintAccessAuthority) {
                FintAccessAuthority fintAccessAuthority = (FintAccessAuthority) authority;
                if (fintAccessAuthority.isAccessGranted(method, requestUri)) {
                    log.trace("Granted: {}", authority);
                    return ACCESS_GRANTED;
                }
            }
        }

        log.debug("No access for {}", requestUri);
        return ACCESS_DENIED;

    }
}
