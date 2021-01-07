package no.fint.consumer.security;

import no.fint.event.model.HeaderConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class FintRequestHeaderPreauthProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return FintAccessPrincipal.builder()
                .name(request.getHeader(HeaderConstants.CLIENT))
                .orgId(request.getHeader(HeaderConstants.ORG_ID))
                .build();
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return FintAccessCredentials.builder()
                .read(split(request.getHeader(FintAccessHeaders.READ.header)))
                .modify(split(request.getHeader(FintAccessHeaders.MODIFY.header)))
                .collection(split(request.getHeader(FintAccessHeaders.COLLECTION.header)))
                .build();
    }

    private Set<String> split(String input) {
        if (input == null) {
            return Collections.emptySet();
        }
        return Arrays.stream(StringUtils.split(input, " ,;")).collect(Collectors.toSet());
    }
}
