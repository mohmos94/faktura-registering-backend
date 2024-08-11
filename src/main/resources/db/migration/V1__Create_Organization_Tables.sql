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


-- Opprettelse av fakturaer-tabellen
CREATE TABLE fakturaer (
    faktura_id SERIAL PRIMARY KEY,
    fakturanummer VARCHAR(50) NOT NULL,
    fakturadato DATE NOT NULL,
    forfallsdato DATE NOT NULL,
    sum_eks_mva DECIMAL(10, 2) NOT NULL,
    mva_belop DECIMAL(10, 2) NOT NULL,
    total_belop DECIMAL(10, 2) NOT NULL,
    avsender_organisasjonsnummer VARCHAR(20) NOT NULL,
    mottaker_organisasjonsnummer VARCHAR(20) NOT NULL,
    kontonummer VARCHAR(50),
    kid_nummer VARCHAR(50),
    kundenavn VARCHAR(255),
    postadresse VARCHAR(255),
    postnummer_sted VARCHAR(255),
    notat TEXT
);

-- Opprettelse av faktura_linjer-tabellen
CREATE TABLE faktura_linjer (
    linje_id SERIAL PRIMARY KEY,
    fakturanummer VARCHAR(50) NOT NULL,
    varebeskrivelse TEXT NOT NULL,
    antall INT NOT NULL,
    pris_per_enhet DECIMAL(10, 2) NOT NULL,
    mva_sats DECIMAL(5, 2) NOT NULL
);


