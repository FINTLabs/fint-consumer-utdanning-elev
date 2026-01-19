package no.novari.fint.consumer.config;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.novari.fint.consumer.utils.RestEndpoints;
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
            .put("no.novari.fint.model.felles.kodeverk.iso.Landkode", "/model/felles/kodeverk/iso/landkode")
            .put("no.novari.fint.model.utdanning.kodeverk.Skolear", "/model/utdanning/kodeverk/skolear")
            .put("no.novari.fint.model.utdanning.kodeverk.Elevkategori", "/model/utdanning/kodeverk/elevkategori")
            .put("no.novari.fint.model.utdanning.utdanningsprogram.Skole", "/model/utdanning/utdanningsprogram/skole")
            .put("no.novari.fint.model.utdanning.kodeverk.Avbruddsarsak", "/model/utdanning/kodeverk/avbruddsarsak")
            .put("no.novari.fint.model.utdanning.vurdering.Elevfravar", "/model/utdanning/vurdering/elevfravar")
            .put("no.novari.fint.model.utdanning.timeplan.Faggruppemedlemskap", "/model/utdanning/timeplan/faggruppemedlemskap")
            .put("no.novari.fint.model.utdanning.timeplan.Undervisningsgruppemedlemskap", "/model/utdanning/timeplan/undervisningsgruppemedlemskap")
            .put("no.novari.fint.model.utdanning.vurdering.Eksamensgruppemedlemskap", "/model/utdanning/vurdering/eksamensgruppemedlemskap")
            .put("no.novari.fint.model.utdanning.vurdering.Fravarsoversikt", "/model/utdanning/vurdering/fravarsoversikt")
            .put("no.novari.fint.model.utdanning.vurdering.Elevvurdering", "/model/utdanning/vurdering/elevvurdering")
            .put("no.novari.fint.model.utdanning.utdanningsprogram.Programomrademedlemskap", "/model/utdanning/utdanningsprogram/programomrademedlemskap")
            .put("no.novari.fint.model.utdanning.timeplan.Fag", "/model/utdanning/timeplan/fag")
            .put("no.novari.fint.model.utdanning.kodeverk.Tilrettelegging", "/model/utdanning/kodeverk/tilrettelegging")
            .put("no.novari.fint.model.utdanning.kodeverk.Eksamensform", "/model/utdanning/kodeverk/eksamensform")
            .put("no.novari.fint.model.utdanning.kodeverk.Termin", "/model/utdanning/kodeverk/termin")
            .put("no.novari.fint.model.utdanning.utdanningsprogram.Arstrinn", "/model/utdanning/utdanningsprogram/arstrinn")
            .put("no.novari.fint.model.felles.kodeverk.Kommune", "/model/felles/kodeverk/kommune")
            .put("no.novari.fint.model.felles.kodeverk.iso.Kjonn", "/model/felles/kodeverk/iso/kjonn")
            .put("no.novari.fint.model.felles.kodeverk.iso.Sprak", "/model/felles/kodeverk/iso/sprak")
            .put("no.novari.fint.model.administrasjon.personal.Personalressurs", "/model/administrasjon/personal/personalressurs")
            .put("no.novari.fint.model.utdanning.larling.Larling", "/model/utdanning/larling/larling")
            .put("no.novari.fint.model.utdanning.ot.OtUngdom", "/model/utdanning/ot/otungdom")
            .put("no.novari.fint.model.utdanning.vurdering.Sensor", "/model/utdanning/vurdering/sensor")
            .put("no.novari.fint.model.administrasjon.personal.Arbeidsforhold", "/model/administrasjon/personal/arbeidsforhold")
            .put("no.novari.fint.model.utdanning.timeplan.Undervisningsgruppe", "/model/utdanning/timeplan/undervisningsgruppe")
            .put("no.novari.fint.model.utdanning.vurdering.Eksamensgruppe", "/model/utdanning/vurdering/eksamensgruppe")
            .put("no.novari.fint.model.utdanning.timeplan.Time", "/model/utdanning/timeplan/time")
            .put("no.novari.fint.model.utdanning.kodeverk.Varseltype", "/model/utdanning/kodeverk/varseltype")
            /* .put(TODO,TODO) */
            .build();
    }

}
