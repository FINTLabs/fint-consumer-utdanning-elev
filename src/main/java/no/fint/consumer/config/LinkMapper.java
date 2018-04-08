package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.fint.model.utdanning.elev.*;

public class LinkMapper {

	public static Map<String, String> linkMapper(String contextPath) {
		return ImmutableMap.<String,String>builder()
			.put(Basisgruppe.class.getName(), contextPath + RestEndpoints.BASISGRUPPE)
			.put(Elev.class.getName(), contextPath + RestEndpoints.ELEV)
			.put(Elevforhold.class.getName(), contextPath + RestEndpoints.ELEVFORHOLD)
			.put(Kontaktlarergruppe.class.getName(), contextPath + RestEndpoints.KONTAKTLARERGRUPPE)
			.put(Medlemskap.class.getName(), contextPath + RestEndpoints.MEDLEMSKAP)
			.put(Skoleressurs.class.getName(), contextPath + RestEndpoints.SKOLERESSURS)
			.put(Undervisningsforhold.class.getName(), contextPath + RestEndpoints.UNDERVISNINGSFORHOLD)
			/* .put(TODO,TODO) */
			.build();
	}

}
