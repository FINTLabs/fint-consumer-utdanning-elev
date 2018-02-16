package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.fint.model.felles.Person;
import no.fint.model.felles.kodeverk.iso.Kjonn;
import no.fint.model.felles.kodeverk.iso.Landkode;
import no.fint.model.felles.kodeverk.iso.Sprak;
import no.fint.model.utdanning.elev.*;
import no.fint.model.utdanning.kodeverk.Elevkategori;
import no.fint.model.utdanning.kodeverk.Fravarstype;
import no.fint.model.utdanning.timeplan.Fag;
import no.fint.model.utdanning.timeplan.Rom;
import no.fint.model.utdanning.timeplan.Time;
import no.fint.model.utdanning.timeplan.Undervisningsgruppe;

public class LinkMapper {

	public static Map<String, String> linkMapper(String contextPath) {
		return ImmutableMap.<String,String>builder()
			.put(Basisgruppe.class.getName(), contextPath + RestEndpoints.BASISGRUPPE)
			.put(Elev.class.getName(), contextPath + RestEndpoints.ELEV)
			.put(Elevforhold.class.getName(), contextPath + RestEndpoints.ELEVFORHOLD)
			.put(Kontaktlarergruppe.class.getName(), contextPath + RestEndpoints.KONTAKTLARERGRUPPE)
			.put(Medlemskap.class.getName(), contextPath + RestEndpoints.MEDLEMSKAP)
			.put(Person.class.getName(), contextPath + RestEndpoints.PERSON)
			.put(Undervisningsforhold.class.getName(), contextPath + RestEndpoints.UNDERVISNINGSFORHOLD)
			.put("no.fint.model.administrasjon.organisasjon.Organisasjonselement", "/administrasjon/organisasjon/organisasjonselement")
			.put("no.fint.model.administrasjon.personal.Arbeidsforhold", "/administrasjon/personal/arbeidsforhold")
            .put(Sprak.class.getName(), "/felles/kodeverk/sprak")
            .put(Landkode.class.getName(), "/felles/kodeverk/land")
            .put(Kjonn.class.getName(), "/felles/kodeverk/kjonn")
            .put(Fag.class.getName(), "/utdanning/timeplan/fag")
            .put(Rom.class.getName(), "/utdanning/timeplan/rom")
            .put(Time.class.getName(), "/utdanning/timeplan/time")
            .put(Undervisningsgruppe.class.getName(), "/utdanning/timeplan/undervisningsgruppe")
            .put(Elevkategori.class.getName(), "/utdanning/kodeverk/elevkategori")
            .put(Fravarstype.class.getName(), "/utdanning/kodeverk/fravarstype")
			.build();
	}

}
