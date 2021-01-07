package no.fint.consumer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FintAccessUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        FintAccessCredentials credentials = (FintAccessCredentials) token.getCredentials();
        FintAccessPrincipal principal = (FintAccessPrincipal) token.getPrincipal();
        return User.withUsername(Objects.toString(principal.getName(), "anonymous"))
                .password(Objects.toString(principal.getOrgId(), ".none"))
                .authorities(getAuthorities(credentials))
                .build();
    }

    private Collection<GrantedAuthority> getAuthorities(FintAccessCredentials credentials) {
        Set<GrantedAuthority> result = new HashSet<>();
        if (credentials.getRead() != null) {
            credentials.getRead().stream().map(FintAccessRead::new).forEach(result::add);
        }
        if (credentials.getModify() != null) {
            credentials.getModify().stream().map(FintAccessModify::new).forEach(result::add);
        }
        if (credentials.getCollection() != null) {
            credentials.getCollection().stream().map(FintAccessCollection::new).forEach(result::add);
        }
        return result;
    }
}
