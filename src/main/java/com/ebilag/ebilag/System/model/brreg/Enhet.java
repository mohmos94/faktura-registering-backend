package com.ebilag.ebilag.System.model.brreg;

import java.util.List;

public record Enhet(
        String organisasjonsnummer,
        String navn,
        Organisasjonsform organisasjonsform,
        String registreringsdatoEnhetsregisteret,
        boolean registrertIMvaregisteret,
        Næringskode naeringskode,
        boolean harRegistrertAntallAnsatte,
        Adresse forretningsadresse,
        Adresse postadresse,
        String stiftelsesdato,
        InstitusjonellSektorkode institusjonellSektorkode,
        boolean registrertIForetaksregisteret,
        boolean registrertIStiftelsesregisteret,
        boolean registrertIFrivillighetsregisteret,
        boolean konkurs,
        boolean underAvvikling,
        boolean underTvangsavviklingEllerTvangsopplosning,
        String maalform,
        List<String> aktivitet,
        TotalTreff totalTreff

        ) {

}
