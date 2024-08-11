package com.ebilag.ebilag.System.controller;

import com.ebilag.ebilag.System.model.faktura.Faktura;
import com.ebilag.ebilag.System.service.FakturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/fakturaer")
public class FakturaController {

    private final FakturaService fakturaService;

    public FakturaController(FakturaService fakturaService) {
        this.fakturaService = fakturaService;
    }

    @PostMapping
    public ResponseEntity<String> lagreFaktura(@RequestBody Faktura faktura) {
        fakturaService.lagreFaktura(faktura);
        return new ResponseEntity<>("Faktura lagret", HttpStatus.CREATED);
    }

    @GetMapping("/org/{orgNummer}")
    public ResponseEntity<List<Faktura>> hentFakturaerByOrgNummer(@PathVariable String orgNummer) {
        List<Faktura> fakturaer = fakturaService.hentFakturaerByOrgNummer(orgNummer);
        return ResponseEntity.ok(fakturaer);
    }


    @GetMapping("/org/fakturanr/{fakturanr}")
    public ResponseEntity<Faktura> hentFakturaerByFaktura(
            @PathVariable String fakturanr) {
        Faktura fakturaer = fakturaService.henteFakturaByFakturaNr(fakturanr);
        return ResponseEntity.ok(fakturaer);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Faktura>> henteALleFakturaer() {
        List<Faktura> fakturaList = fakturaService
                .henteAlleFakturaer();
        return ResponseEntity.ok(fakturaList);
    }

    @PutMapping("/oppdater/fakturanr/{fakturanr}/notat")
    public ResponseEntity<Boolean> oppdaterNotatFelt(
            @PathVariable String fakturanr,
            @RequestBody String notat
    ) {
        return ResponseEntity.ok(
                fakturaService
                        .oppdaterFakturaNotat(fakturanr, notat));
    }
}
