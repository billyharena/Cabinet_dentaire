CREATE TABLE genre(
    id SERIAL PRIMARY KEY,
    genre VARCHAR(10) NOT NULL
);

CREATE TABLE niveau_etude(
    id SERIAL PRIMARY KEY,
    niveau_etude VARCHAR(20) NOT NULL
);

CREATE TABLE specialite(
    id SERIAL PRIMARY KEY,
    specialite VARCHAR(50) NOT NULL
);

CREATE TABLE employe(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    date_naissance DATE NOT NULL,
    idgenre INT NOT NULL,
    idniveau_etude INT NOT NULL,
    FOREIGN KEY(idgenre) REFERENCES genre(id),
    FOREIGN KEY(idniveau_etude) REFERENCES niveau_etude(id)
);

CREATE TABLE employe_specialite(
    id SERIAL PRIMARY KEY,
    idemploye INT NOT NULL,
    idspecialite INT NOT NULL,
    FOREIGN KEY(idemploye) REFERENCES employe(id),
    FOREIGN KEY(idspecialite) REFERENCES specialite(id)
);

CREATE TABLE service(
    id SERIAL PRIMARY KEY,
    service VARCHAR(100) NOT NULL,
    duree DOUBLE PRECISION NOT NULL,
    prix DOUBLE PRECISION 
);

CREATE TABLE service_specialite(
    id SERIAL PRIMARY KEY,
    idservice INT NOT NULL,
    idspecialite INT NOT NULL,
    heureTravail DOUBLE PRECISION NOT NULL,
    FOREIGN KEY(idservice) REFERENCES service(id),
    FOREIGN KEY(idspecialite) REFERENCES specialite(id)
);

CREATE TABLE produit(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50), 
    prixUnitaire DOUBLE PRECISION
);

CREATE TABLE service_produit(
    id SERIAL PRIMARY KEY,
    idservice INT NOT NULL,
    idproduit INT NOT NULL,
    quantite INT NOT NULL,
    FOREIGN KEY(idservice) REFERENCES service(id),
    FOREIGN KEY(idproduit) REFERENCES produit(id)
);