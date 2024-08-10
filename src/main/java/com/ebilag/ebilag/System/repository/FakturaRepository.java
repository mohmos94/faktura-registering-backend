package com.ebilag.ebilag.System.repository;


import com.ebilag.ebilag.System.model.faktura.Faktura;
import com.ebilag.ebilag.System.model.faktura.FakturaLinje;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FakturaRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final FakturaLinjeRepository fakturaLinjeRepository;

    public FakturaRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, FakturaLinjeRepository fakturaLinjeRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.fakturaLinjeRepository = fakturaLinjeRepository;
    }


    public void lagreFaktura(Faktura faktura) {
        String sql = """
                INSERT INTO fakturaer (fakturanummer, fakturadato, forfallsdato, sum_eks_mva, mva_belop, total_belop, avsender_organisasjonsnummer, mottaker_organisasjonsnummer, kontonummer, kid_nummer, kundenavn, postadresse, postnummer_sted)
                VALUES (:fakturanummer, :fakturadato, :forfallsdato, :sumEksMva, :mvaBelop, :totalBelop, :avsenderOrganisasjonsnummer, :mottakerOrganisasjonsnummer, :kontonummer, :kidNummer, :kundenavn, :postadresse, :postnummerSted)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("fakturanummer", faktura.getFakturanummer())
                .addValue("fakturadato", faktura.getFakturadato())
                .addValue("forfallsdato", faktura.getForfallsdato())
                .addValue("sumEksMva", faktura.getSumEksMva())
                .addValue("mvaBelop", faktura.getMvaBelop())
                .addValue("totalBelop", faktura.getTotalBelop())
                .addValue("avsenderOrganisasjonsnummer", faktura.getAvsenderOrganisasjonsnummer())
                .addValue("mottakerOrganisasjonsnummer", faktura.getMottakerOrganisasjonsnummer())
                .addValue("kontonummer", faktura.getKontonummer())
                .addValue("kidNummer", faktura.getKidNummer())
                .addValue("kundenavn", faktura.getKundenavn())
                .addValue("postadresse", faktura.getPostadresse())
                .addValue("postnummerSted", faktura.getPostnummerSted());

        namedParameterJdbcTemplate.update(sql, params);

        for (FakturaLinje linje : faktura.getFakturaLinjer()) {
            fakturaLinjeRepository.lagreFakturaLinje(linje);
        }
    }


    public List<Faktura> hentFakturaerByOrgNummer(String orgNummer) {
        String sql = """
                SELECT * FROM fakturaer WHERE avsender_organisasjonsnummer = :orgNummer OR mottaker_organisasjonsnummer = :orgNummer
                """;

        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("orgNummer", orgNummer), (rs, rowNum) -> {
            Faktura faktura = new Faktura();
            faktura.setFakturanummer(rs.getString("fakturanummer"));
            faktura.setFakturadato(rs.getDate("fakturadato"));
            faktura.setForfallsdato(rs.getDate("forfallsdato"));
            faktura.setSumEksMva(rs.getBigDecimal("sum_eks_mva"));
            faktura.setMvaBelop(rs.getBigDecimal("mva_belop"));
            faktura.setTotalBelop(rs.getBigDecimal("total_belop"));
            faktura.setAvsenderOrganisasjonsnummer(rs.getString("avsender_organisasjonsnummer"));
            faktura.setMottakerOrganisasjonsnummer(rs.getString("mottaker_organisasjonsnummer"));
            faktura.setKontonummer(rs.getString("kontonummer"));
            faktura.setKidNummer(rs.getString("kid_nummer"));
            faktura.setKundenavn(rs.getString("kundenavn"));
            faktura.setPostadresse(rs.getString("postadresse"));
            faktura.setPostnummerSted(rs.getString("postnummer_sted"));

            faktura.setFakturaLinjer(
                    fakturaLinjeRepository
                            .hentFakturaLinjer(rs.getString("fakturanummer")));

            return faktura;
        });
    }

    public List<Faktura> henteAlleFakturaer() {

        String sql = """
                SELECT *
                FROM fakturaer
                """;

        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
            Faktura faktura = new Faktura();
            faktura.setFakturanummer(rs.getString("fakturanummer"));
            faktura.setFakturadato(rs.getDate("fakturadato"));
            faktura.setForfallsdato(rs.getDate("forfallsdato"));
            faktura.setSumEksMva(rs.getBigDecimal("sum_eks_mva"));
            faktura.setMvaBelop(rs.getBigDecimal("mva_belop"));
            faktura.setTotalBelop(rs.getBigDecimal("total_belop"));
            faktura.setAvsenderOrganisasjonsnummer(rs.getString("avsender_organisasjonsnummer"));
            faktura.setMottakerOrganisasjonsnummer(rs.getString("mottaker_organisasjonsnummer"));
            faktura.setKontonummer(rs.getString("kontonummer"));
            faktura.setKidNummer(rs.getString("kid_nummer"));
            faktura.setKundenavn(rs.getString("kundenavn"));
            faktura.setPostadresse(rs.getString("postadresse"));
            faktura.setPostnummerSted(rs.getString("postnummer_sted"));

            faktura.setFakturaLinjer(
                    fakturaLinjeRepository
                            .hentFakturaLinjer(rs.getString("fakturanummer")));

            return faktura;
        });
    }
}
