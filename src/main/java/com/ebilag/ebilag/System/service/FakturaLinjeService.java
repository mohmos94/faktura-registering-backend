package com.ebilag.ebilag.System.service;

import com.ebilag.ebilag.System.model.faktura.FakturaLinje;
import com.ebilag.ebilag.System.repository.FakturaLinjeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakturaLinjeService {

    private final FakturaLinjeRepository fakturaLinjeRepository;

    public FakturaLinjeService(FakturaLinjeRepository fakturaLinjeRepository) {
        this.fakturaLinjeRepository = fakturaLinjeRepository;
    }

    public void lagreFakturaLinje(FakturaLinje fakturaLinje) {
        fakturaLinjeRepository.lagreFakturaLinje(fakturaLinje);
    }

    public List<FakturaLinje> hentFakturaLinjer(String fakturanummer) {
        return fakturaLinjeRepository.hentFakturaLinjer(fakturanummer);
    }
}
