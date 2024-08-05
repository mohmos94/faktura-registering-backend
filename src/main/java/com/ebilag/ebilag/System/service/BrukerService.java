package com.ebilag.ebilag.System.service;

import com.ebilag.ebilag.System.model.brukere.Bruker;
import com.ebilag.ebilag.System.model.brukere.Organisasjon;
import com.ebilag.ebilag.System.repository.BrukerRepository;
import com.ebilag.ebilag.System.repository.OrganisasjonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrukerService {

    private final BrukerRepository brukerRepository;
    private final OrganisasjonRepository organisasjonRepository;

    public BrukerService(BrukerRepository brukerRepository, OrganisasjonRepository organisasjonRepository) {
        this.brukerRepository = brukerRepository;
        this.organisasjonRepository = organisasjonRepository;
    }

    // Create a new bruker
    public void createBruker(Bruker bruker) {
        brukerRepository.createbruker(bruker);
    }

    // Get all brukers
    public List<Bruker> getAllBrukers() {
        return brukerRepository.getAllbrukers();
    }

    // Update a bruker
    public void updateBruker(Bruker bruker) {

        Organisasjon organisasjon = organisasjonRepository.getOrganisasjonByNummer(
                bruker.getOrganisasjonsnummer());

        bruker.setOrganisasjonsnavn(organisasjon.navn());

        brukerRepository.updatebruker(bruker);
    }

    public Optional<Bruker> getUserByEmail(String email) {
        return brukerRepository.getUserByEmail(email);
    }
}
