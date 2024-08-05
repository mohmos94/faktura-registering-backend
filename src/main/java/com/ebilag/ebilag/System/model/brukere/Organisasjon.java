package com.ebilag.ebilag.System.model.brukere;

import java.time.LocalDate;

public record Organisasjon(
        String organisasjonsnummer,
        String navn,
        String organisasjonsformKode,
        LocalDate registreringsdato,
        LocalDate stiftelsesdato,
        String institusjonellSektorkodeKode,
        String maalform
) {
}
