package com.ebilag.ebilag.System.controller;

import com.ebilag.ebilag.System.model.brreg.Organisasjon;
import com.ebilag.ebilag.System.service.OrganisasjonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organisasjoner")
public class OrganisasjonController {

    private final OrganisasjonService organisasjonService;

    public OrganisasjonController(OrganisasjonService organisasjonService) {
        this.organisasjonService = organisasjonService;
    }

    // Create a new organisasjon
    @PostMapping
    public ResponseEntity<Organisasjon> createOrganisasjon(@RequestBody Organisasjon organisasjon) {
        organisasjonService.createOrganisasjon(organisasjon);
        return ResponseEntity.ok(organisasjon);
    }

    // Get an organisasjon by organisasjonsnummer
    @GetMapping("/{organisasjonsnummer}")
    public ResponseEntity<Organisasjon> getOrganisasjonByNummer(@PathVariable String organisasjonsnummer) {
        Organisasjon organisasjon = organisasjonService.getOrganisasjonByNummer(organisasjonsnummer);
        return ResponseEntity.ok(organisasjon);
    }

    // Get all organisasjoner
    @GetMapping
    public ResponseEntity<List<Organisasjon>> getAllOrganisasjoner() {
        List<Organisasjon> organisasjoner = organisasjonService.getAllOrganisasjoner();
        return ResponseEntity.ok(organisasjoner);
    }

    // Update an organisasjon
    @PutMapping("/{organisasjonsnummer}")
    public ResponseEntity<Organisasjon> updateOrganisasjon(@PathVariable String organisasjonsnummer, @RequestBody Organisasjon organisasjon) {
        organisasjonService.updateOrganisasjon(organisasjon);
        return ResponseEntity.ok(organisasjon);
    }

    // Delete an organisasjon by organisasjonsnummer
    @DeleteMapping("/{organisasjonsnummer}")
    public ResponseEntity<Void> deleteOrganisasjon(@PathVariable String organisasjonsnummer) {
        organisasjonService.deleteOrganisasjon(organisasjonsnummer);
        return ResponseEntity.noContent().build();
    }
}
