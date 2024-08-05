package com.ebilag.ebilag.System.repository;

import com.ebilag.ebilag.System.model.brukere.Bruker;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BrukerRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BrukerRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Create a new bruker
    public void createbruker(Bruker bruker) {
        String sql = """
                INSERT INTO brukere (fornavn, etternavn, epost, telefonnummer)
                VALUES (:fornavn, :etternavn, :epost, :telefonnummer)
                """;

        Map<String, Object> params = Map.of(
                "fornavn", bruker.getFornavn(),
                "etternavn", bruker.getEtternavn(),
                "epost", bruker.getEpost(),
                "telefonnummer", bruker.getTelefonnummer()
        );

        try {
            namedParameterJdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to create bruker with email " + bruker.getEpost(), ex);
        }
    }

    // Get all brukers
    public Optional<Bruker> getUserByEmail(String email) {
        String sql = """
                SELECT *
                FROM brukere
                WHERE epost = :epost
                """;

        Map<String, String> param = Map.of("epost", email);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    sql,
                    param,
                    brukerMapper));
        } catch (DataAccessException ex) {
            return Optional.empty(); // Return an empty optional if the user is not found
        }
    }


    // Get all brukers
    public List<Bruker> getAllbrukers() {
        String sql = """
                SELECT *
                FROM brukere
                """;

        try {
            return namedParameterJdbcTemplate.query(sql, brukerMapper);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to retrieve brukers", ex);
        }
    }

    // Update a bruker's details
    public void updatebruker(Bruker bruker) {
        String sql = """
                UPDATE brukere
                SET fornavn = :fornavn,
                    etternavn = :etternavn,
                    epost = :epost,
                    telefonnummer = :telefonnummer,
                    organisasjonsnummer = :organisasjonsnummer,
                    organisasjonsnavn = :organisasjonsnavn
                WHERE epost = :epost
                """;

        Map<String, Object> params = Map.of(
                "organisasjonsnummer", bruker.getOrganisasjonsnummer(),
                "organisasjonsnavn", bruker.getOrganisasjonsnummer(),
                "fornavn", bruker.getFornavn(),
                "etternavn", bruker.getEtternavn(),
                "epost", bruker.getEpost(),
                "telefonnummer", bruker.getTelefonnummer()
        );

        try {
            namedParameterJdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to update bruker with email " + bruker.getEpost(), ex);
        }
    }

//    public void deletebruker(int brukerId) {
//        String sql = """
//                DELETE FROM brukere
//                WHERE bruker_id = :brukerId
//                """;
//
//        Map<String, Integer> params = Map.of("brukerId", brukerId);
//
//        try {
//            namedParameterJdbcTemplate.update(sql, params);
//        } catch (DataAccessException ex) {
//            throw new RuntimeException("Failed to delete bruker with ID " + brukerId, ex);
//        }
//    }


    private final RowMapper<Bruker> brukerMapper = (rs, rowNum) -> {
        String organisasjonsnummer = rs.getString("organisasjonsnummer");
        String organisasjonsnavn = rs.getString("organisasjonsnavn");
        String fornavn = rs.getString("fornavn");
        String etternavn = rs.getString("etternavn");
        String epost = rs.getString("epost");
        String telefonnummer = rs.getString("telefonnummer");

        return new Bruker(organisasjonsnummer, organisasjonsnavn, fornavn, etternavn, epost, telefonnummer);
    };
}
