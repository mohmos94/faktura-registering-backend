package com.ebilag.ebilag.System.controller;


import com.ebilag.ebilag.System.model.brreg.Enhet;
import com.ebilag.ebilag.System.service.BrregService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/brreg")
public class BrregController {
    private final BrregService brregService;

    public BrregController(BrregService brregService) {
        this.brregService = brregService;
    }

    @GetMapping("/{orgnr}")
    public ResponseEntity<Enhet> getCompanyByOrgNumber(
            @PathVariable String orgnr) {
        return ResponseEntity.ok(brregService
                .getCompanyByOrgNumber
                        (orgnr.replaceAll("\\s", "")));
    }


}
