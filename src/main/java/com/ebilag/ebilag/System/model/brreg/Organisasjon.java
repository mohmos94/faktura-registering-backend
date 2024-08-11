package com.ebilag.ebilag.System.model.brreg;

public record Organisasjon(
        String organisasjonsnummer,
        String navn,
        String organisasjonsformKode,
        String registreringsdato,
        String stiftelsesdato,
        String institusjonellSektorkodeKode,
        String maalform
) {
}
