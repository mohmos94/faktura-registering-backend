package com.ebilag.ebilag.System.model.brukere;

import java.util.Objects;

public class Bruker {
    private String organisasjonsnummer;
    private String organisasjonsnavn;
    private String fornavn;
    private String etternavn;
    private String epost;
    private String telefonnummer;

    // Constructor
    public Bruker(String organisasjonsnummer, String organisasjonsnavn, String fornavn, String etternavn, String epost, String telefonnummer) {
        this.organisasjonsnummer = organisasjonsnummer;
        this.organisasjonsnavn = organisasjonsnavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
    }

    // Getters
    public String getOrganisasjonsnummer() {
        return organisasjonsnummer;
    }

    public String getOrganisasjonsnavn() {
        return organisasjonsnavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getEpost() {
        return epost;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    // Setters
    public void setOrganisasjonsnummer(String organisasjonsnummer) {
        this.organisasjonsnummer = organisasjonsnummer;
    }

    public void setOrganisasjonsnavn(String organisasjonsnavn) {
        this.organisasjonsnavn = organisasjonsnavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bruker bruker = (Bruker) o;
        return Objects.equals(organisasjonsnummer, bruker.organisasjonsnummer) &&
               Objects.equals(organisasjonsnavn, bruker.organisasjonsnavn) &&
               Objects.equals(fornavn, bruker.fornavn) &&
               Objects.equals(etternavn, bruker.etternavn) &&
               Objects.equals(epost, bruker.epost) &&
               Objects.equals(telefonnummer, bruker.telefonnummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisasjonsnummer, organisasjonsnavn, fornavn, etternavn, epost, telefonnummer);
    }

    // toString()
    @Override
    public String toString() {
        return "Bruker{" +
               "organisasjonsnummer='" + organisasjonsnummer + '\'' +
               ", organisasjonsnavn='" + organisasjonsnavn + '\'' +
               ", fornavn='" + fornavn + '\'' +
               ", etternavn='" + etternavn + '\'' +
               ", epost='" + epost + '\'' +
               ", telefonnummer='" + telefonnummer + '\'' +
               '}';
    }
}
