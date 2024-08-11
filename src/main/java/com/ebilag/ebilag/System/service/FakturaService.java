package com.ebilag.ebilag.System.service;

import com.ebilag.ebilag.System.model.faktura.Faktura;
import com.ebilag.ebilag.System.repository.FakturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakturaService {

    private final FakturaRepository fakturaRepository;

    public FakturaService(FakturaRepository fakturaRepository) {
        this.fakturaRepository = fakturaRepository;
    }

    public void lagreFaktura(Faktura faktura) {
        fakturaRepository.lagreFaktura(faktura);
    }

    public List<Faktura> hentFakturaerByOrgNummer(String orgNummer) {
        return fakturaRepository.hentFakturaerByOrgNummer(orgNummer);
    }

    public List<Faktura> henteAlleFakturaer() {

        return fakturaRepository.henteAlleFakturaer();
    }

    public boolean oppdaterFakturaNotat(String fakturanummer, String notat) {
        Faktura faktura = fakturaRepository.henteFakturaByFakturanr(fakturanummer);
        if (faktura != null) {
            return fakturaRepository.oppdaterFakturaNotat(faktura.getFakturanummer(), notat);

        } else {
            throw new RuntimeException("feil ved henting av fakturaen");
        }
    }

    public Faktura henteFakturaByFakturaNr(String fakturanr) {
        return fakturaRepository.henteFakturaByFakturanr(fakturanr);
    }
}
