package com.ebilag.ebilag.System.repository;

import com.ebilag.ebilag.System.model.brukere.Organisasjon;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class OrganisasjonRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrganisasjonRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Create a new organisasjon
    public void createOrganisasjon(Organisasjon organisasjon) {
        String sql = """
                INSERT INTO organisasjoner (organisasjonsnummer, navn, organisasjonsform_kode, registreringsdato, stiftelsesdato, institusjonell_sektorkode_kode, maalform)
                VALUES (:organisasjonsnummer, :navn, :organisasjonsform_kode, :registreringsdato, :stiftelsesdato, :institusjonell_sektorkode_kode, :maalform)
                """;

        Map<String, Object> params = Map.of(
                "organisasjonsnummer", organisasjon.organisasjonsnummer(),
                "navn", organisasjon.navn(),
                "organisasjonsform_kode", organisasjon.organisasjonsformKode(),
                "registreringsdato", organisasjon.registreringsdato(),
                "stiftelsesdato", organisasjon.stiftelsesdato(),
                "institusjonell_sektorkode_kode", organisasjon.institusjonellSektorkodeKode(),
                "maalform", organisasjon.maalform()
        );

        try {
            namedParameterJdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to create organisasjon with nummer " + organisasjon.organisasjonsnummer(), ex);
        }
    }

    // Get an organisasjon by organisasjonsnummer
    public Organisasjon getOrganisasjonByNummer(String organisasjonsnummer) {
        String sql = """
                SELECT *
                FROM organisasjoner
                WHERE organisasjonsnummer = :organisasjonsnummer
                """;

        Map<String, Object> params = Map.of("organisasjonsnummer", organisasjonsnummer);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, organisasjonMapper);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Organisasjon with nummer " + organisasjonsnummer + " not found", ex);
        }
    }

    // Get all organisasjoner
    public List<Organisasjon> getAllOrganisasjoner() {
        String sql = """
                SELECT *
                FROM organisasjoner
                """;

        try {
            return namedParameterJdbcTemplate.query(sql, organisasjonMapper);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to retrieve organisasjoner", ex);
        }
    }

    // Update an organisasjon
    public void updateOrganisasjon(Organisasjon organisasjon) {
        String sql = """
                UPDATE organisasjoner
                SET navn = :navn,
                    organisasjonsform_kode = :organisasjonsform_kode,
                    registreringsdato = :registreringsdato,
                    stiftelsesdato = :stiftelsesdato,
                    institusjonell_sektorkode_kode = :institusjonell_sektorkode_kode,
                    maalform = :maalform
                WHERE organisasjonsnummer = :organisasjonsnummer
                """;

        Map<String, Object> params = Map.of(
                "organisasjonsnummer", organisasjon.organisasjonsnummer(),
                "navn", organisasjon.navn(),
                "organisasjonsform_kode", organisasjon.organisasjonsformKode(),
                "registreringsdato", organisasjon.registreringsdato(),
                "stiftelsesdato", organisasjon.stiftelsesdato(),
                "institusjonell_sektorkode_kode", organisasjon.institusjonellSektorkodeKode(),
                "maalform", organisasjon.maalform()
        );

        try {
            namedParameterJdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to update organisasjon with nummer " + organisasjon.organisasjonsnummer(), ex);
        }
    }

    // Delete an organisasjon by organisasjonsnummer
    public void deleteOrganisasjon(String organisasjonsnummer) {
        String sql = """
                DELETE FROM organisasjoner
                WHERE organisasjonsnummer = :organisasjonsnummer
                """;

        Map<String, Object> params = Map.of("organisasjonsnummer", organisasjonsnummer);

        try {
            namedParameterJdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to delete organisasjon with nummer " + organisasjonsnummer, ex);
        }
    }

    // RowMapper for mapping SQL result set to Organisasjon object
    private final RowMapper<Organisasjon> organisasjonMapper = (rs, rowNum) -> {
        String organisasjonsnummer = rs.getString("organisasjonsnummer");
        String navn = rs.getString("navn");
        String organisasjonsformKode = rs.getString("organisasjonsform_kode");
        LocalDate registreringsdato = rs.getDate("registreringsdato") != null ? rs.getDate("registreringsdato").toLocalDate() : null;
        LocalDate stiftelsesdato = rs.getDate("stiftelsesdato") != null ? rs.getDate("stiftelsesdato").toLocalDate() : null;
        String institusjonellSektorkodeKode = rs.getString("institusjonell_sektorkode_kode");
        String maalform = rs.getString("maalform");

        return new Organisasjon(organisasjonsnummer, navn, organisasjonsformKode, registreringsdato, stiftelsesdato, institusjonellSektorkodeKode, maalform);
    };
}
