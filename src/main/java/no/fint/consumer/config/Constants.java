
package no.fint.consumer.config;

public enum Constants {
;

    public static final String COMPONENT = "utdanning-elev";
    public static final String COMPONENT_CONSUMER = COMPONENT + " consumer";
    public static final String CACHE_SERVICE = "CACHE_SERVICE";

    
    public static final String CACHE_INITIALDELAY_BASISGRUPPE = "${fint.consumer.cache.initialDelay.basisgruppe:900000}";
    public static final String CACHE_FIXEDRATE_BASISGRUPPE = "${fint.consumer.cache.fixedRate.basisgruppe:900000}";
    
    public static final String CACHE_INITIALDELAY_BASISGRUPPEMEDLEMSKAP = "${fint.consumer.cache.initialDelay.basisgruppemedlemskap:960000}";
    public static final String CACHE_FIXEDRATE_BASISGRUPPEMEDLEMSKAP = "${fint.consumer.cache.fixedRate.basisgruppemedlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_ELEV = "${fint.consumer.cache.initialDelay.elev:1020000}";
    public static final String CACHE_FIXEDRATE_ELEV = "${fint.consumer.cache.fixedRate.elev:900000}";
    
    public static final String CACHE_INITIALDELAY_ELEVFORHOLD = "${fint.consumer.cache.initialDelay.elevforhold:1080000}";
    public static final String CACHE_FIXEDRATE_ELEVFORHOLD = "${fint.consumer.cache.fixedRate.elevforhold:900000}";
    
    public static final String CACHE_INITIALDELAY_KONTAKTLARERGRUPPE = "${fint.consumer.cache.initialDelay.kontaktlarergruppe:1140000}";
    public static final String CACHE_FIXEDRATE_KONTAKTLARERGRUPPE = "${fint.consumer.cache.fixedRate.kontaktlarergruppe:900000}";
    
    public static final String CACHE_INITIALDELAY_KONTAKTLARERGRUPPEMEDLEMSKAP = "${fint.consumer.cache.initialDelay.kontaktlarergruppemedlemskap:1200000}";
    public static final String CACHE_FIXEDRATE_KONTAKTLARERGRUPPEMEDLEMSKAP = "${fint.consumer.cache.fixedRate.kontaktlarergruppemedlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_KONTAKTPERSON = "${fint.consumer.cache.initialDelay.kontaktperson:1260000}";
    public static final String CACHE_FIXEDRATE_KONTAKTPERSON = "${fint.consumer.cache.fixedRate.kontaktperson:900000}";
    
    public static final String CACHE_INITIALDELAY_MEDLEMSKAP = "${fint.consumer.cache.initialDelay.medlemskap:1320000}";
    public static final String CACHE_FIXEDRATE_MEDLEMSKAP = "${fint.consumer.cache.fixedRate.medlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_PERSON = "${fint.consumer.cache.initialDelay.person:1380000}";
    public static final String CACHE_FIXEDRATE_PERSON = "${fint.consumer.cache.fixedRate.person:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLERESSURS = "${fint.consumer.cache.initialDelay.skoleressurs:1440000}";
    public static final String CACHE_FIXEDRATE_SKOLERESSURS = "${fint.consumer.cache.fixedRate.skoleressurs:900000}";
    
    public static final String CACHE_INITIALDELAY_UNDERVISNINGSFORHOLD = "${fint.consumer.cache.initialDelay.undervisningsforhold:1500000}";
    public static final String CACHE_FIXEDRATE_UNDERVISNINGSFORHOLD = "${fint.consumer.cache.fixedRate.undervisningsforhold:900000}";
    

}
