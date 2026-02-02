package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.novari.fint.model.utdanning.vurdering.Anmerkninger;
import no.novari.fint.model.utdanning.elev.Elev;
import no.novari.fint.model.utdanning.elev.Elevforhold;
import no.novari.fint.model.utdanning.elev.Elevtilrettelegging;
import no.novari.fint.model.utdanning.elev.Klasse;
import no.novari.fint.model.utdanning.elev.Klassemedlemskap;
import no.novari.fint.model.utdanning.elev.Kontaktlarergruppe;
import no.novari.fint.model.utdanning.elev.Kontaktlarergruppemedlemskap;
import no.novari.fint.model.felles.Kontaktperson;
import no.novari.fint.model.felles.Person;
import no.novari.fint.model.utdanning.elev.Persongruppe;
import no.novari.fint.model.utdanning.elev.Persongruppemedlemskap;
import no.novari.fint.model.utdanning.elev.Skoleressurs;
import no.novari.fint.model.utdanning.elev.Undervisningsforhold;
import no.novari.fint.model.utdanning.elev.Varsel;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Anmerkninger.class.getName(), contextPath + RestEndpoints.ANMERKNINGER)
            .put(Elev.class.getName(), contextPath + RestEndpoints.ELEV)
            .put(Elevforhold.class.getName(), contextPath + RestEndpoints.ELEVFORHOLD)
            .put(Elevtilrettelegging.class.getName(), contextPath + RestEndpoints.ELEVTILRETTELEGGING)
            .put(Klasse.class.getName(), contextPath + RestEndpoints.KLASSE)
            .put(Klassemedlemskap.class.getName(), contextPath + RestEndpoints.KLASSEMEDLEMSKAP)
            .put(Kontaktlarergruppe.class.getName(), contextPath + RestEndpoints.KONTAKTLARERGRUPPE)
            .put(Kontaktlarergruppemedlemskap.class.getName(), contextPath + RestEndpoints.KONTAKTLARERGRUPPEMEDLEMSKAP)
            .put(Kontaktperson.class.getName(), contextPath + RestEndpoints.KONTAKTPERSON)
            .put(Person.class.getName(), contextPath + RestEndpoints.PERSON)
            .put(Persongruppe.class.getName(), contextPath + RestEndpoints.PERSONGRUPPE)
            .put(Persongruppemedlemskap.class.getName(), contextPath + RestEndpoints.PERSONGRUPPEMEDLEMSKAP)
            .put(Skoleressurs.class.getName(), contextPath + RestEndpoints.SKOLERESSURS)
            .put(Undervisningsforhold.class.getName(), contextPath + RestEndpoints.UNDERVISNINGSFORHOLD)
            .put(Varsel.class.getName(), contextPath + RestEndpoints.VARSEL)
            .put("no.novari.fint.model.felles.kodeverk.iso.Landkode", "/felles/kodeverk/iso/landkode")
            .put("no.novari.fint.model.utdanning.kodeverk.Skolear", "/utdanning/kodeverk/skolear")
            .put("no.novari.fint.model.utdanning.kodeverk.Elevkategori", "/utdanning/kodeverk/elevkategori")
            .put("no.novari.fint.model.utdanning.utdanningsprogram.Skole", "/utdanning/utdanningsprogram/skole")
            .put("no.novari.fint.model.utdanning.kodeverk.Avbruddsarsak", "/utdanning/kodeverk/avbruddsarsak")
            .put("no.novari.fint.model.utdanning.vurdering.Elevfravar", "/utdanning/vurdering/elevfravar")
            .put("no.novari.fint.model.utdanning.timeplan.Faggruppemedlemskap", "/utdanning/timeplan/faggruppemedlemskap")
            .put("no.novari.fint.model.utdanning.timeplan.Undervisningsgruppemedlemskap", "/utdanning/timeplan/undervisningsgruppemedlemskap")
            .put("no.novari.fint.model.utdanning.vurdering.Eksamensgruppemedlemskap", "/utdanning/vurdering/eksamensgruppemedlemskap")
            .put("no.novari.fint.model.utdanning.vurdering.Fravarsoversikt", "/utdanning/vurdering/fravarsoversikt")
            .put("no.novari.fint.model.utdanning.vurdering.Elevvurdering", "/utdanning/vurdering/elevvurdering")
            .put("no.novari.fint.model.utdanning.utdanningsprogram.Programomrademedlemskap", "/utdanning/utdanningsprogram/programomrademedlemskap")
            .put("no.novari.fint.model.utdanning.timeplan.Fag", "/utdanning/timeplan/fag")
            .put("no.novari.fint.model.utdanning.kodeverk.Tilrettelegging", "/utdanning/kodeverk/tilrettelegging")
            .put("no.novari.fint.model.utdanning.kodeverk.Eksamensform", "/utdanning/kodeverk/eksamensform")
            .put("no.novari.fint.model.utdanning.kodeverk.Termin", "/utdanning/kodeverk/termin")
            .put("no.novari.fint.model.utdanning.utdanningsprogram.Arstrinn", "/utdanning/utdanningsprogram/arstrinn")
            .put("no.novari.fint.model.felles.kodeverk.Kommune", "/felles/kodeverk/kommune")
            .put("no.novari.fint.model.felles.kodeverk.iso.Kjonn", "/felles/kodeverk/iso/kjonn")
            .put("no.novari.fint.model.felles.kodeverk.iso.Sprak", "/felles/kodeverk/iso/sprak")
            .put("no.novari.fint.model.administrasjon.personal.Personalressurs", "/administrasjon/personal/personalressurs")
            .put("no.novari.fint.model.utdanning.larling.Larling", "/utdanning/larling/larling")
            .put("no.novari.fint.model.utdanning.ot.OtUngdom", "/utdanning/ot/otungdom")
            .put("no.novari.fint.model.utdanning.vurdering.Sensor", "/utdanning/vurdering/sensor")
            .put("no.novari.fint.model.administrasjon.personal.Arbeidsforhold", "/administrasjon/personal/arbeidsforhold")
            .put("no.novari.fint.model.utdanning.timeplan.Time", "/utdanning/timeplan/time")
            .put("no.novari.fint.model.utdanning.timeplan.Undervisningsgruppe", "/utdanning/timeplan/undervisningsgruppe")
            .put("no.novari.fint.model.utdanning.vurdering.Eksamensgruppe", "/utdanning/vurdering/eksamensgruppe")
            .put("no.novari.fint.model.utdanning.kodeverk.Varseltype", "/utdanning/kodeverk/varseltype")
            /* .put(TODO,TODO) */
            .build();
    }

}
