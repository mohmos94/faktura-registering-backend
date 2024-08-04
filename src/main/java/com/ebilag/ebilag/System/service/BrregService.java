package com.ebilag.ebilag.System.service;

import com.ebilag.ebilag.System.model.brreg.Enhet;
import com.ebilag.ebilag.System.restemplate.BrregRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class BrregService {

    private final BrregRestTemplate brregRestTemplate;

    public BrregService(BrregRestTemplate brregRestTemplate) {
        this.brregRestTemplate = brregRestTemplate;
    }

    public Enhet getCompanyByOrgNumber(String orgnr) {
        return brregRestTemplate.getCompanyByOrgNumber(orgnr);
    }

    public Enhet getAllCompanies(int page, int size) {
        return brregRestTemplate.getAllCompanies(page, size);
    }
}
