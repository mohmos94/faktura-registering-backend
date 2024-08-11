package com.ebilag.ebilag.System.controller;


import com.ebilag.ebilag.System.model.brreg.Enhet;
import com.ebilag.ebilag.System.model.brreg.Organisasjon;
import com.ebilag.ebilag.System.service.BrregService;
import com.ebilag.ebilag.System.service.OrganisasjonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/brreg")
public class BrregController {
    private final BrregService brregService;
    private final OrganisasjonService organisasjonService;

    public BrregController(BrregService brregService, OrganisasjonService organisasjonService) {
        this.brregService = brregService;
        this.organisasjonService = organisasjonService;
    }


    @PostMapping("organisation/orgnr/{orgnr}")
    public ResponseEntity<Organisasjon> getCompanyByOrgNumber(
            @PathVariable String orgnr) {

        Enhet enhet = brregService
                .getCompanyByOrgNumber
                        (orgnr.replaceAll("\\s", ""));

        Organisasjon organisasjon = organisasjonService.createOrganisasjon(EnhetToOrganisationMapper(enhet));

        return ResponseEntity.ok(organisasjon);
    }

    @GetMapping("organisation/name/{orgnr}")
    public ResponseEntity<Organisasjon> getCompanyNameByOrgNumber(
            @PathVariable String orgnr) {
        Organisasjon organisasjon = organisasjonService.getOrganisasjonByNummer(orgnr);
        return ResponseEntity.ok(organisasjon);
    }


    @GetMapping("/{orgnr}")
    public ResponseEntity<Enhet> getOrganisationInformationByOrgNumber(
            @PathVariable String orgnr) {
        return ResponseEntity.ok(brregService
                .getCompanyByOrgNumber
                        (orgnr.replaceAll("\\s", "")));
    }

    private Organisasjon EnhetToOrganisationMapper(Enhet enhet) {
        return new Organisasjon(
                enhet.organisasjonsnummer(),
                enhet.navn(),
                enhet.organisasjonsform().kode(),
                enhet.registreringsdatoEnhetsregisteret(),
                enhet.stiftelsesdato(),
                enhet.institusjonellSektorkode().kode(),
                enhet.maalform()
        );
    }


}
