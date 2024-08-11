package com.ebilag.ebilag.System.model.faktura;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Faktura {

    private String fakturanummer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

    private Date fakturadato;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date forfallsdato;
    private BigDecimal sumEksMva;
    private BigDecimal mvaBelop;
    private BigDecimal totalBelop;
    private String avsenderOrganisasjonsnummer;
    private String mottakerOrganisasjonsnummer;
    private String kontonummer;
    private String kidNummer;
    private String kundenavn;
    private String postadresse;
    private String postnummerSted;
    private String notat;
    private List<FakturaLinje> fakturaLinjer;


    public String getFakturanummer() {
        return fakturanummer;
    }

    public void setFakturanummer(String fakturanummer) {
        this.fakturanummer = fakturanummer;
    }

    public Date getFakturadato() {
        return fakturadato;
    }

    public void setFakturadato(Date fakturadato) {
        this.fakturadato = fakturadato;
    }

    public Date getForfallsdato() {
        return forfallsdato;
    }

    public void setForfallsdato(Date forfallsdato) {
        this.forfallsdato = forfallsdato;
    }

    public BigDecimal getSumEksMva() {
        return sumEksMva;
    }

    public void setSumEksMva(BigDecimal sumEksMva) {
        this.sumEksMva = sumEksMva;
    }

    public BigDecimal getMvaBelop() {
        return mvaBelop;
    }

    public void setMvaBelop(BigDecimal mvaBelop) {
        this.mvaBelop = mvaBelop;
    }

    public BigDecimal getTotalBelop() {
        return totalBelop;
    }

    public void setTotalBelop(BigDecimal totalBelop) {
        this.totalBelop = totalBelop;
    }

    public String getAvsenderOrganisasjonsnummer() {
        return avsenderOrganisasjonsnummer;
    }

    public void setAvsenderOrganisasjonsnummer(String avsenderOrganisasjonsnummer) {
        this.avsenderOrganisasjonsnummer = avsenderOrganisasjonsnummer;
    }

    public String getMottakerOrganisasjonsnummer() {
        return mottakerOrganisasjonsnummer;
    }

    public void setMottakerOrganisasjonsnummer(String mottakerOrganisasjonsnummer) {
        this.mottakerOrganisasjonsnummer = mottakerOrganisasjonsnummer;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getKidNummer() {
        return kidNummer;
    }

    public void setKidNummer(String kidNummer) {
        this.kidNummer = kidNummer;
    }

    public String getKundenavn() {
        return kundenavn;
    }

    public void setKundenavn(String kundenavn) {
        this.kundenavn = kundenavn;
    }

    public String getPostadresse() {
        return postadresse;
    }

    public void setPostadresse(String postadresse) {
        this.postadresse = postadresse;
    }

    public String getPostnummerSted() {
        return postnummerSted;
    }

    public void setPostnummerSted(String postnummerSted) {
        this.postnummerSted = postnummerSted;
    }

    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat = notat;
    }

    public List<FakturaLinje> getFakturaLinjer() {
        return fakturaLinjer;
    }

    public void setFakturaLinjer(List<FakturaLinje> fakturaLinjer) {
        this.fakturaLinjer = fakturaLinjer;
    }


}
