package com.ebilag.ebilag.System.repository;

import com.ebilag.ebilag.System.model.faktura.Faktura;
import com.ebilag.ebilag.System.model.faktura.FakturaLinje;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class FakturaRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final FakturaLinjeRepository fakturaLinjeRepository;
    private final Logger logger = LoggerFactory.getLogger(FakturaRepository.class);

    public FakturaRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, FakturaLinjeRepository fakturaLinjeRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.fakturaLinjeRepository = fakturaLinjeRepository;
    }

    public void lagreFaktura(Faktura faktura) {
        String sql = """
                INSERT INTO fakturaer (fakturanummer, fakturadato, forfallsdato, sum_eks_mva, mva_belop, total_belop, avsender_organisasjonsnummer, mottaker_organisasjonsnummer, kontonummer, kid_nummer, kundenavn, postadresse, postnummer_sted, notat)
                VALUES (:fakturanummer, :fakturadato, :forfallsdato, :sumEksMva, :mvaBelop, :totalBelop, :avsenderOrganisasjonsnummer, :mottakerOrganisasjonsnummer, :kontonummer, :kidNummer, :kundenavn, :postadresse, :postnummerSted, :notat)
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
                .addValue("postnummerSted", faktura.getPostnummerSted())
                .addValue("notat", faktura.getNotat());

        namedParameterJdbcTemplate.update(sql, params);

        for (FakturaLinje linje : faktura.getFakturaLinjer()) {
            fakturaLinjeRepository.lagreFakturaLinje(linje);
        }
    }

    public List<Faktura> hentFakturaerByOrgNummer(String orgNummer) {
        String sql = """
                SELECT * FROM fakturaer 
                WHERE avsender_organisasjonsnummer = :orgNummer OR mottaker_organisasjonsnummer = :orgNummer
                """;

        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("orgNummer", orgNummer), this::rowMapper);
    }

    public List<Faktura> henteAlleFakturaer() {
        String sql = """
                SELECT *
                FROM fakturaer
                """;

        return namedParameterJdbcTemplate.query(sql, this::rowMapper);
    }

    public boolean oppdaterFakturaNotat(String fakturanummer, String notat) {
        String sql = """
                UPDATE fakturaer
                SET notat = :notat
                WHERE fakturanummer = :fakturanummer
                """;

        logger.info("Oppdaterer faktura med nummer: {}, med notat: {}", fakturanummer, notat);

        Map<String, String> params = Map.of(
                "fakturanummer", fakturanummer,
                "notat", notat
        );

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);

        return rowsAffected > 0;
    }

    public Faktura henteFakturaByFakturanr(String fakturanr) {
        String sql = """
                SELECT *
                FROM fakturaer
                WHERE fakturanummer = :fakturanummer
                """;

        Map<String, String> params = Map.of("fakturanummer", fakturanr);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, this::rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Faktura rowMapper(ResultSet rs, int rowNum) throws SQLException {
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
        faktura.setNotat(rs.getString("notat"));

        faktura.setFakturaLinjer(fakturaLinjeRepository.hentFakturaLinjer(rs.getString("fakturanummer")));

        return faktura;
    }
}
