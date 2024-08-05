-- Create table for storing organizations
CREATE TABLE enheter (
    organisasjonsnummer VARCHAR(20) PRIMARY KEY,
    navn VARCHAR(255),
    organisasjonsform_kode VARCHAR(10),
    registreringsdato DATE,
    registrert_i_mvaregisteret BOOLEAN,
    naeringskode_kode VARCHAR(10),
    har_registrert_antall_ansatte BOOLEAN,
    forretningsadresse_id INT,
    postadresse_id INT,
    stiftelsesdato DATE,
    institusjonell_sektorkode_kode VARCHAR(10),
    registrert_i_foretaksregisteret BOOLEAN,
    registrert_i_stiftelsesregisteret BOOLEAN,
    registrert_i_frivillighetsregisteret BOOLEAN,
    konkurs BOOLEAN,
    under_avvikling BOOLEAN,
    under_tvangsavvikling BOOLEAN,
    maalform VARCHAR(10)
);

-- Create table for storing addresses
CREATE TABLE adresser (
    id SERIAL PRIMARY KEY,
    land VARCHAR(100),
    landkode VARCHAR(10),
    postnummer VARCHAR(10),
    poststed VARCHAR(100),
    adresse TEXT,
    kommune VARCHAR(100),
    kommunenummer VARCHAR(10)
);

-- Create table for storing organization forms
CREATE TABLE organisasjonsformer (
    kode VARCHAR(10) PRIMARY KEY,
    beskrivelse VARCHAR(255)
);

-- Create table for storing industry codes
CREATE TABLE naeringskoder (
    kode VARCHAR(10) PRIMARY KEY,
    beskrivelse VARCHAR(255)
);

-- Create table for storing institutional sector codes
CREATE TABLE institusjonell_sektorkoder (
    kode VARCHAR(10) PRIMARY KEY,
    beskrivelse VARCHAR(255)
);

-- Create table for storing links
CREATE TABLE links (
    organisasjonsnummer VARCHAR(20),
    href VARCHAR(255),
    FOREIGN KEY (organisasjonsnummer) REFERENCES enheter(organisasjonsnummer)
);

-- Create table for storing activities
CREATE TABLE aktiviteter (
    organisasjonsnummer VARCHAR(20),
    aktivitet TEXT,
    FOREIGN KEY (organisasjonsnummer) REFERENCES enheter(organisasjonsnummer)
);


CREATE TABLE organisasjoner (
    organisasjonsnummer VARCHAR(20) PRIMARY KEY,
    navn VARCHAR(255),
    organisasjonsform_kode VARCHAR(10),
    registreringsdato DATE,
    stiftelsesdato DATE,
    institusjonell_sektorkode_kode VARCHAR(10),
    maalform VARCHAR(10)
);


-- Create table for storing users with reference to organizations
CREATE TABLE brukere (
    bruker_id SERIAL PRIMARY KEY,
    organisasjonsnummer VARCHAR(20),
    organisasjonsnavn VARCHAR(200),
    fornavn VARCHAR(100),
    etternavn VARCHAR(100),
    epost VARCHAR(255),
    telefonnummer VARCHAR(20)
);

