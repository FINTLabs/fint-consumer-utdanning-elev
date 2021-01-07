package no.fint.consumer.security;

public enum FintAccessHeaders {
    COLLECTION("x-fint-access-collection"),
    READ("x-fint-access-read"),
    MODIFY("x-fint-access-modify");

    public final String header;

    FintAccessHeaders(String header) {

        this.header = header;
    }
}
