package com.ebilag.ebilag.System.model.dto;

public record BrukerDTO(
        String organisasjonsnummer,
        String fornavn,
        String etternavn,
        String epost,
        String telefonnummer
) {
}