package com.ebilag.ebilag.System.model.faktura;

import java.math.BigDecimal;

public class FakturaLinje {

    private String fakturanummer;
    private String varebeskrivelse;
    private int antall;
    private BigDecimal prisPerEnhet;
    private BigDecimal mvaSats;


    public String getFakturanummer() {
        return fakturanummer;
    }

    public void setFakturanummer(String fakturanummer) {
        this.fakturanummer = fakturanummer;
    }

    public String getVarebeskrivelse() {
        return varebeskrivelse;
    }

    public void setVarebeskrivelse(String varebeskrivelse) {
        this.varebeskrivelse = varebeskrivelse;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public BigDecimal getPrisPerEnhet() {
        return prisPerEnhet;
    }

    public void setPrisPerEnhet(BigDecimal prisPerEnhet) {
        this.prisPerEnhet = prisPerEnhet;
    }

    public BigDecimal getMvaSats() {
        return mvaSats;
    }

    public void setMvaSats(BigDecimal mvaSats) {
        this.mvaSats = mvaSats;
    }
}
