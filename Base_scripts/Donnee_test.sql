INSERT INTO genre(genre) VALUES('Homme'),('Femme');

INSERT INTO niveau_etude(niveau_etude) VALUES
('BACC'),('LICENCE'),('MASTER'),('DOCTORAT');

INSERT INTO specialite(specialite) VALUES
('Orthodontie'), ('Parodontologie'), ('Chirurgie dentaire'),
('Esthétique dentaire'), ('Prothèse dentaire'),
('Pédiatrie'), ('Odontologie conservatrice'), ('Urgences dentaires');

INSERT INTO service(service, duree, prix) VALUES
('traitement des malocclusions', 2, 10000), ('traitement des maladies des gencives', 3, 15000),
('extractions dentaires', 3, 20000);

INSERT INTO service_specialite(idservice, idspecialite, heureTravail) VALUES
(1, 1, 2),(2, 2, 3),(3, 3, 3), (3,3,3),(1, 4, 1),(2,5,2),(3,6,2);


INSERT INTO produit(nom, prixUnitaire) VALUES
('produit 1', 3000),('produit 2', 4000),('produit 3', 4500),('produit 4', 35000);

INSERT INTO service_produit(idservice, idproduit, quantite) VALUES
(1, 1, 3),(1, 2, 4),(2,1, 1),(2,2, 3),(3, 3, 5),(3,4,2);


INSERT INTO employe(nom,prenom, date_naissance, idgenre, idniveau_etude) VALUES
('TEST', 'TEST1','2001-10-25', 1, 2);

INSERT INTO employe_specialite(idemploye, idspecialite)VALUES(1,1);