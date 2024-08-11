package com.ebilag.ebilag.System.service;

import com.ebilag.ebilag.System.model.brreg.Organisasjon;
import com.ebilag.ebilag.System.repository.OrganisasjonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisasjonService {

    private final OrganisasjonRepository organisasjonRepository;

    public OrganisasjonService(OrganisasjonRepository organisasjonRepository) {
        this.organisasjonRepository = organisasjonRepository;
    }

    // Create a new organisasjon
    public Organisasjon createOrganisasjon(Organisasjon organisasjon) {
        return organisasjonRepository.createOrganisasjon(organisasjon);
    }

    // Get an organisasjon by organisasjonsnummer
    public Organisasjon getOrganisasjonByNummer(String organisasjonsnummer) {
        // You can add business logic here if needed
        return organisasjonRepository.getOrganisasjonByNummer(organisasjonsnummer);
    }

    // Get all organisasjoner
    public List<Organisasjon> getAllOrganisasjoner() {
        return organisasjonRepository.getAllOrganisasjoner();
    }

    // Update an organisasjon
    public void updateOrganisasjon(Organisasjon organisasjon) {
        // Validate or transform the data if necessary
        Organisasjon existingOrganisasjon = organisasjonRepository.getOrganisasjonByNummer(organisasjon.organisasjonsnummer());
        if (existingOrganisasjon == null) {
            throw new RuntimeException("Organisasjon with nummer " + organisasjon.organisasjonsnummer() + " not found");
        }
        organisasjonRepository.updateOrganisasjon(organisasjon);
    }

    // Delete an organisasjon by organisasjonsnummer
    public void deleteOrganisasjon(String organisasjonsnummer) {
        // Any pre-delete checks or operations can be performed here
        Organisasjon existingOrganisasjon = organisasjonRepository.getOrganisasjonByNummer(organisasjonsnummer);
        if (existingOrganisasjon == null) {
            throw new RuntimeException("Organisasjon with nummer " + organisasjonsnummer + " not found");
        }
        organisasjonRepository.deleteOrganisasjon(organisasjonsnummer);
    }
}
