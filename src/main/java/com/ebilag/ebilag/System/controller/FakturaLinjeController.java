package com.ebilag.ebilag.System.controller;

import com.ebilag.ebilag.System.model.faktura.FakturaLinje;
import com.ebilag.ebilag.System.service.FakturaLinjeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fakturalinjer")
public class FakturaLinjeController {

    private final FakturaLinjeService fakturaLinjeService;

    public FakturaLinjeController(FakturaLinjeService fakturaLinjeService) {
        this.fakturaLinjeService = fakturaLinjeService;
    }

    @PostMapping
    public ResponseEntity<String> lagreFakturaLinje(@RequestBody FakturaLinje fakturaLinje) {
        fakturaLinjeService.lagreFakturaLinje(fakturaLinje);
        return new ResponseEntity<>("Faktura linje lagret", HttpStatus.CREATED);
    }

    @GetMapping("/faktura/{fakturanummer}")
    public ResponseEntity<List<FakturaLinje>> hentFakturaLinjer(@PathVariable String fakturanummer) {
        List<FakturaLinje> fakturaLinjer = fakturaLinjeService.hentFakturaLinjer(fakturanummer);
        return ResponseEntity.ok(fakturaLinjer);
    }
}
