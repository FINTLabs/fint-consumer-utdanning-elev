package no.fint.consumer.utils;


import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.HeaderConstants;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ResponseUtil {
    public static void logError(HttpServletRequest request) {
        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");
        long requestDuration = endTime - startTime;

        String orgId = request.getHeader(HeaderConstants.ORG_ID);
        String client = request.getHeader(HeaderConstants.CLIENT);

        log.error(String.format("404 Request took %dms. URL: %s. OrgId: %s, Client: %s", requestDuration, request.getRequestURL(), orgId, client));

    }
}
