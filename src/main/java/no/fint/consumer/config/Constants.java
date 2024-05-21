package no.fint.consumer.config;

public enum Constants {
;

    public static final String COMPONENT = "utdanning-elev";
    public static final String COMPONENT_CONSUMER = COMPONENT + " consumer";
    public static final String CACHE_SERVICE = "CACHE_SERVICE";

    
    public static final String CACHE_INITIALDELAY_ANMERKNINGER = "${fint.consumer.cache.initialDelay.anmerkninger:900000}";
    public static final String CACHE_FIXEDRATE_ANMERKNINGER = "${fint.consumer.cache.fixedRate.anmerkninger:900000}";
    
    public static final String CACHE_INITIALDELAY_BASISGRUPPE = "${fint.consumer.cache.initialDelay.basisgruppe:1000000}";
    public static final String CACHE_FIXEDRATE_BASISGRUPPE = "${fint.consumer.cache.fixedRate.basisgruppe:900000}";
    
    public static final String CACHE_INITIALDELAY_BASISGRUPPEMEDLEMSKAP = "${fint.consumer.cache.initialDelay.basisgruppemedlemskap:1100000}";
    public static final String CACHE_FIXEDRATE_BASISGRUPPEMEDLEMSKAP = "${fint.consumer.cache.fixedRate.basisgruppemedlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_ELEV = "${fint.consumer.cache.initialDelay.elev:1200000}";
    public static final String CACHE_FIXEDRATE_ELEV = "${fint.consumer.cache.fixedRate.elev:900000}";
    
    public static final String CACHE_INITIALDELAY_ELEVFORHOLD = "${fint.consumer.cache.initialDelay.elevforhold:1300000}";
    public static final String CACHE_FIXEDRATE_ELEVFORHOLD = "${fint.consumer.cache.fixedRate.elevforhold:900000}";
    
    public static final String CACHE_INITIALDELAY_ELEVTILRETTELEGGING = "${fint.consumer.cache.initialDelay.elevtilrettelegging:1400000}";
    public static final String CACHE_FIXEDRATE_ELEVTILRETTELEGGING = "${fint.consumer.cache.fixedRate.elevtilrettelegging:900000}";
    
    public static final String CACHE_INITIALDELAY_KONTAKTLARERGRUPPE = "${fint.consumer.cache.initialDelay.kontaktlarergruppe:1500000}";
    public static final String CACHE_FIXEDRATE_KONTAKTLARERGRUPPE = "${fint.consumer.cache.fixedRate.kontaktlarergruppe:900000}";
    
    public static final String CACHE_INITIALDELAY_KONTAKTLARERGRUPPEMEDLEMSKAP = "${fint.consumer.cache.initialDelay.kontaktlarergruppemedlemskap:1600000}";
    public static final String CACHE_FIXEDRATE_KONTAKTLARERGRUPPEMEDLEMSKAP = "${fint.consumer.cache.fixedRate.kontaktlarergruppemedlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_KONTAKTPERSON = "${fint.consumer.cache.initialDelay.kontaktperson:1700000}";
    public static final String CACHE_FIXEDRATE_KONTAKTPERSON = "${fint.consumer.cache.fixedRate.kontaktperson:900000}";
    
    public static final String CACHE_INITIALDELAY_MEDLEMSKAP = "${fint.consumer.cache.initialDelay.medlemskap:1800000}";
    public static final String CACHE_FIXEDRATE_MEDLEMSKAP = "${fint.consumer.cache.fixedRate.medlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_PERSON = "${fint.consumer.cache.initialDelay.person:1900000}";
    public static final String CACHE_FIXEDRATE_PERSON = "${fint.consumer.cache.fixedRate.person:900000}";
    
    public static final String CACHE_INITIALDELAY_PERSONGRUPPE = "${fint.consumer.cache.initialDelay.persongruppe:2000000}";
    public static final String CACHE_FIXEDRATE_PERSONGRUPPE = "${fint.consumer.cache.fixedRate.persongruppe:900000}";
    
    public static final String CACHE_INITIALDELAY_PERSONGRUPPEMEDLEMSKAP = "${fint.consumer.cache.initialDelay.persongruppemedlemskap:2100000}";
    public static final String CACHE_FIXEDRATE_PERSONGRUPPEMEDLEMSKAP = "${fint.consumer.cache.fixedRate.persongruppemedlemskap:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLERESSURS = "${fint.consumer.cache.initialDelay.skoleressurs:2200000}";
    public static final String CACHE_FIXEDRATE_SKOLERESSURS = "${fint.consumer.cache.fixedRate.skoleressurs:900000}";
    
    public static final String CACHE_INITIALDELAY_UNDERVISNINGSFORHOLD = "${fint.consumer.cache.initialDelay.undervisningsforhold:2300000}";
    public static final String CACHE_FIXEDRATE_UNDERVISNINGSFORHOLD = "${fint.consumer.cache.fixedRate.undervisningsforhold:900000}";
    
    public static final String CACHE_INITIALDELAY_VARSEL = "${fint.consumer.cache.initialDelay.varsel:2400000}";
    public static final String CACHE_FIXEDRATE_VARSEL = "${fint.consumer.cache.fixedRate.varsel:900000}";
    

}
