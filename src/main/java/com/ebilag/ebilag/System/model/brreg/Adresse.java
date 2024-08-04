package com.ebilag.ebilag.System.model.brreg;

import java.util.List;

public record Adresse(
        String land,
        String landkode,
        String postnummer,
        String poststed,
        List<String> adresse,
        String kommune,
        String kommunenummer
) {
}
