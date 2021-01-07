package no.fint.consumer.security;

import no.fint.event.model.HeaderConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FintRequestHeaderPreauthProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return FintAccessPrincipal.builder()
                .name(getHeader(request, HeaderConstants.CLIENT))
                .orgId(getHeader(request, HeaderConstants.ORG_ID))
                .build();
    }

    private String getHeader(HttpServletRequest request, String name) {
        final String header = request.getHeader(name);
        if (header != null)
            return header;

        throw new PreAuthenticatedCredentialsNotFoundException(name);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return FintAccessCredentials.builder()
                .read(split(getHeader(request, FintAccessHeaders.READ.header)))
                .modify(split(getHeader(request, FintAccessHeaders.MODIFY.header)))
                .collection(split(getHeader(request, FintAccessHeaders.COLLECTION.header)))
                .build();
    }

    private Set<String> split(String input) {
        return Arrays.stream(StringUtils.split(input, " ,;")).collect(Collectors.toSet());
    }
}
