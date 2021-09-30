package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.fint.model.utdanning.vurdering.Anmerkninger;
import no.fint.model.utdanning.elev.Basisgruppe;
import no.fint.model.utdanning.elev.Basisgruppemedlemskap;
import no.fint.model.utdanning.elev.Elev;
import no.fint.model.utdanning.elev.Elevforhold;
import no.fint.model.utdanning.elev.Elevtilrettelegging;
import no.fint.model.utdanning.elev.Kontaktlarergruppe;
import no.fint.model.utdanning.elev.Kontaktlarergruppemedlemskap;
import no.fint.model.felles.Kontaktperson;
import no.fint.model.utdanning.elev.Medlemskap;
import no.fint.model.felles.Person;
import no.fint.model.utdanning.elev.Persongruppe;
import no.fint.model.utdanning.elev.Persongruppemedlemskap;
import no.fint.model.utdanning.elev.Skoleressurs;
import no.fint.model.utdanning.elev.Undervisningsforhold;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Anmerkninger.class.getName(), contextPath + RestEndpoints.ANMERKNINGER)
            .put(Basisgruppe.class.getName(), contextPath + RestEndpoints.BASISGRUPPE)
            .put(Basisgruppemedlemskap.class.getName(), contextPath + RestEndpoints.BASISGRUPPEMEDLEMSKAP)
            .put(Elev.class.getName(), contextPath + RestEndpoints.ELEV)
            .put(Elevforhold.class.getName(), contextPath + RestEndpoints.ELEVFORHOLD)
            .put(Elevtilrettelegging.class.getName(), contextPath + RestEndpoints.ELEVTILRETTELEGGING)
            .put(Kontaktlarergruppe.class.getName(), contextPath + RestEndpoints.KONTAKTLARERGRUPPE)
            .put(Kontaktlarergruppemedlemskap.class.getName(), contextPath + RestEndpoints.KONTAKTLARERGRUPPEMEDLEMSKAP)
            .put(Kontaktperson.class.getName(), contextPath + RestEndpoints.KONTAKTPERSON)
            .put(Medlemskap.class.getName(), contextPath + RestEndpoints.MEDLEMSKAP)
            .put(Person.class.getName(), contextPath + RestEndpoints.PERSON)
            .put(Persongruppe.class.getName(), contextPath + RestEndpoints.PERSONGRUPPE)
            .put(Persongruppemedlemskap.class.getName(), contextPath + RestEndpoints.PERSONGRUPPEMEDLEMSKAP)
            .put(Skoleressurs.class.getName(), contextPath + RestEndpoints.SKOLERESSURS)
            .put(Undervisningsforhold.class.getName(), contextPath + RestEndpoints.UNDERVISNINGSFORHOLD)
            .put("no.fint.model.felles.kodeverk.iso.Landkode", "/felles/kodeverk/iso/landkode")
            .put("no.fint.model.utdanning.kodeverk.Skolear", "/utdanning/kodeverk/skolear")
            .put("no.fint.model.utdanning.kodeverk.Termin", "/utdanning/kodeverk/termin")
            .put("no.fint.model.utdanning.utdanningsprogram.Arstrinn", "/utdanning/utdanningsprogram/arstrinn")
            .put("no.fint.model.utdanning.utdanningsprogram.Skole", "/utdanning/utdanningsprogram/skole")
            .put("no.fint.model.utdanning.kodeverk.Fagmerknad", "/utdanning/kodeverk/fagmerknad")
            .put("no.fint.model.utdanning.kodeverk.Elevkategori", "/utdanning/kodeverk/elevkategori")
            .put("no.fint.model.utdanning.kodeverk.Avbruddsarsak", "/utdanning/kodeverk/avbruddsarsak")
            .put("no.fint.model.utdanning.timeplan.Undervisningsgruppemedlemskap", "/utdanning/timeplan/undervisningsgruppemedlemskap")
            .put("no.fint.model.utdanning.vurdering.Vurdering", "/utdanning/vurdering/vurdering")
            .put("no.fint.model.utdanning.vurdering.Sluttordensvurdering", "/utdanning/vurdering/sluttordensvurdering")
            .put("no.fint.model.utdanning.vurdering.Underveisfagvurdering", "/utdanning/vurdering/underveisfagvurdering")
            .put("no.fint.model.utdanning.vurdering.Halvarsfagvurdering", "/utdanning/vurdering/halvarsfagvurdering")
            .put("no.fint.model.utdanning.vurdering.Sluttfagvurdering", "/utdanning/vurdering/sluttfagvurdering")
            .put("no.fint.model.utdanning.vurdering.Eksamensgruppemedlemskap", "/utdanning/vurdering/eksamensgruppemedlemskap")
            .put("no.fint.model.utdanning.vurdering.Fravarsoversikt", "/utdanning/vurdering/fravarsoversikt")
            .put("no.fint.model.utdanning.vurdering.Halvarsordensvurdering", "/utdanning/vurdering/halvarsordensvurdering")
            .put("no.fint.model.utdanning.utdanningsprogram.Programomrade", "/utdanning/utdanningsprogram/programomrade")
            .put("no.fint.model.utdanning.vurdering.Fravar", "/utdanning/vurdering/fravar")
            .put("no.fint.model.utdanning.utdanningsprogram.Programomrademedlemskap", "/utdanning/utdanningsprogram/programomrademedlemskap")
            .put("no.fint.model.utdanning.vurdering.Underveisordensvurdering", "/utdanning/vurdering/underveisordensvurdering")
            .put("no.fint.model.utdanning.vurdering.Eksamensgruppe", "/utdanning/vurdering/eksamensgruppe")
            .put("no.fint.model.utdanning.timeplan.Undervisningsgruppe", "/utdanning/timeplan/undervisningsgruppe")
            .put("no.fint.model.utdanning.timeplan.Fag", "/utdanning/timeplan/fag")
            .put("no.fint.model.utdanning.kodeverk.Tilrettelegging", "/utdanning/kodeverk/tilrettelegging")
            .put("no.fint.model.felles.kodeverk.Kommune", "/felles/kodeverk/kommune")
            .put("no.fint.model.felles.kodeverk.iso.Kjonn", "/felles/kodeverk/iso/kjonn")
            .put("no.fint.model.felles.kodeverk.iso.Sprak", "/felles/kodeverk/iso/sprak")
            .put("no.fint.model.administrasjon.personal.Personalressurs", "/administrasjon/personal/personalressurs")
            .put("no.fint.model.administrasjon.personal.Arbeidsforhold", "/administrasjon/personal/arbeidsforhold")
            .put("no.fint.model.utdanning.timeplan.Time", "/utdanning/timeplan/time")
            /* .put(TODO,TODO) */
            .build();
    }

}
