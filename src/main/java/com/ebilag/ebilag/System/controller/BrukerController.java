package com.ebilag.ebilag.System.controller;

import com.ebilag.ebilag.System.model.brukere.Bruker;
import com.ebilag.ebilag.System.model.dto.BrukerDTO;
import com.ebilag.ebilag.System.model.dto.CreateUserDTO;
import com.ebilag.ebilag.System.service.BrukerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/brukere")
public class BrukerController {

    private final BrukerService brukerService;

    public BrukerController(BrukerService brukerService) {
        this.brukerService = brukerService;
    }

    // Create a new bruker
    @PostMapping
    public ResponseEntity<Bruker> createUser(@RequestBody CreateUserDTO createUserDTO) {
        brukerService.createBruker(createUserMappDTO(createUserDTO));
        return ResponseEntity.ok(createUserMappDTO(createUserDTO));
    }

    // Get all brukers
    @GetMapping
    public ResponseEntity<List<Bruker>> getAllUser() {
        List<Bruker> brukers = brukerService.getAllBrukers();
        return ResponseEntity.ok(brukers);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Optional<Bruker>> getUserByEmail(
            @PathVariable String email
    ) {
        Optional<Bruker> brukers = brukerService.getUserByEmail(email);
        return ResponseEntity.ok(brukers);
    }


    // Update a bruker
    @PutMapping("/updateUser")
    public ResponseEntity<Bruker> updateUser(@RequestBody BrukerDTO brukerDTO) {
        brukerService.updateBruker(updateUserMappDTO(brukerDTO));
        return ResponseEntity.ok(updateUserMappDTO(brukerDTO));
    }

    private Bruker updateUserMappDTO(BrukerDTO brukerDTO) {
        return new Bruker(brukerDTO.organisasjonsnummer(),
                null,
                brukerDTO.etternavn(),
                brukerDTO.etternavn(),
                brukerDTO.epost(),
                brukerDTO.telefonnummer());
    }

    private Bruker createUserMappDTO(CreateUserDTO createUserDTO) {
        return new Bruker(
                null,
                null,
                createUserDTO.etternavn(),
                createUserDTO.etternavn(),
                createUserDTO.epost(),
                createUserDTO.telefonnummer());
    }
}
