package com.ebilag.ebilag.System.controller;


import com.ebilag.ebilag.System.model.brreg.Enhet;
import com.ebilag.ebilag.System.service.BrregService;
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
    public Enhet getCompanyByOrgNumber(@PathVariable String orgnr) {
        return brregService.getCompanyByOrgNumber(orgnr);
    }

    @GetMapping
    public Enhet getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return brregService.getAllCompanies(page, size);
    }
}
