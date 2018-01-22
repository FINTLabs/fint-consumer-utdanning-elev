package no.fint.consumer.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConsumerProps {


	public static final String CACHE_INITIALDELAY_BASISGRUPPE = "${fint.consumer.cache.initialDelay.basisgruppe:60000}";
	public static final String CACHE_FIXEDRATE_BASISGRUPPE = "${fint.consumer.cache.fixedRate.basisgruppe:900000}";

	public static final String CACHE_INITIALDELAY_ELEV = "${fint.consumer.cache.initialDelay.elev:70000}";
	public static final String CACHE_FIXEDRATE_ELEV = "${fint.consumer.cache.fixedRate.elev:900000}";

	public static final String CACHE_INITIALDELAY_ELEVFORHOLD = "${fint.consumer.cache.initialDelay.elevforhold:80000}";
	public static final String CACHE_FIXEDRATE_ELEVFORHOLD = "${fint.consumer.cache.fixedRate.elevforhold:900000}";

	public static final String CACHE_INITIALDELAY_KONTAKTLARERGRUPPE = "${fint.consumer.cache.initialDelay.kontaktlarergruppe:90000}";
	public static final String CACHE_FIXEDRATE_KONTAKTLARERGRUPPE = "${fint.consumer.cache.fixedRate.kontaktlarergruppe:900000}";

	public static final String CACHE_INITIALDELAY_MEDLEMSKAP = "${fint.consumer.cache.initialDelay.medlemskap:100000}";
	public static final String CACHE_FIXEDRATE_MEDLEMSKAP = "${fint.consumer.cache.fixedRate.medlemskap:900000}";

	public static final String CACHE_INITIALDELAY_PERSON = "${fint.consumer.cache.initialDelay.person:110000}";
	public static final String CACHE_FIXEDRATE_PERSON = "${fint.consumer.cache.fixedRate.person:900000}";

	public static final String CACHE_INITIALDELAY_UNDERVISNINGSFORHOLD = "${fint.consumer.cache.initialDelay.undervisningsforhold:120000}";
	public static final String CACHE_FIXEDRATE_UNDERVISNINGSFORHOLD = "${fint.consumer.cache.fixedRate.undervisningsforhold:900000}";



        @Value("${fint.events.orgIds:fint.no}")
        private String[] orgs;

}
