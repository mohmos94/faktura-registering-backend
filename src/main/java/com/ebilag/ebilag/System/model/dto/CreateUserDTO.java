package com.ebilag.ebilag.System.model.dto;

public record CreateUserDTO(
        String fornavn,
        String etternavn,
        String epost,
        String telefonnummer
) {
}
