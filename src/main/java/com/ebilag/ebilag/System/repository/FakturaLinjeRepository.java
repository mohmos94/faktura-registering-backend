package com.ebilag.ebilag.System.repository;

import com.ebilag.ebilag.System.model.faktura.FakturaLinje;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FakturaLinjeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FakturaLinjeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void lagreFakturaLinje(FakturaLinje linje) {
        String sql = """
                INSERT INTO faktura_linjer (fakturanummer, varebeskrivelse, antall, pris_per_enhet, mva_sats)
                VALUES (:fakturanummer, :varebeskrivelse, :antall, :prisPerEnhet, :mvaSats)
                """;


        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("fakturanummer", linje.getFakturanummer())
                .addValue("varebeskrivelse", linje.getVarebeskrivelse())
                .addValue("antall", linje.getAntall())
                .addValue("prisPerEnhet", linje.getPrisPerEnhet())
                .addValue("mvaSats", linje.getMvaSats());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<FakturaLinje> hentFakturaLinjer(String fakturanummer) {
        String sql = """
                SELECT * FROM faktura_linjer WHERE fakturanummer = :fakturanummer
                """;

        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("fakturanummer", fakturanummer), (rs, rowNum) -> {
            FakturaLinje linje = new FakturaLinje();
            linje.setFakturanummer(rs.getString("fakturanummer"));
            linje.setVarebeskrivelse(rs.getString("varebeskrivelse"));
            linje.setAntall(rs.getInt("antall"));
            linje.setPrisPerEnhet(rs.getBigDecimal("pris_per_enhet"));
            linje.setMvaSats(rs.getBigDecimal("mva_sats"));
            return linje;
        });
    }
}
