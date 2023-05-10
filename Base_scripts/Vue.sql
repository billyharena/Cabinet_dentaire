CREATE OR REPLACE VIEW v_specialite_service AS 
SELECT ser.id AS idservice, ser.service, ser.duree, ser.prix, s.id AS idspecialite, s.specialite, s.salaireHeure, ss.heureTravail, ser.margebenef FROM 
specialite AS s JOIN service_specialite AS ss ON s.id = ss.idspecialite
JOIN service AS ser ON ss.idservice = ser.id;


CREATE OR REPLACE VIEW v_produit_service AS
SELECT 
p.id AS idproduit, p.nom AS nomproduit, p.prixUnitaire, sp.id AS idserviceProduit, sp.quantite AS quantiteProduit, se.id AS idservice, se.service AS nomservice
FROM
produit AS p JOIN service_produit AS sp ON p.id = sp.idproduit 
JOIN service AS se ON sp.idservice = se.id;


CREATE OR REPLACE VIEW v_produit AS 
SELECT PRODUIT.*,
    CASE 
        WHEN (prixunitaire <= 10000 and prixunitaire >=1  ) THEN 50 :: DOUBLE PRECISION
        ELSE 
            CASE 
                WHEN (prixunitaire > 10000 AND prixunitaire <= 50000 ) THEN 25:: DOUBLE PRECISION
                ELSE 
                    CASE 
                        WHEN (prixunitaire >50000 AND prixunitaire <= 100000 ) THEN 15 :: DOUBLE PRECISION
                        ELSE
                            CASE
                                WHEN (prixunitaire >100000  ) THEN 10 :: DOUBLE PRECISION
                                END 
                        END 
                END 
        END AS marge
FROM PRODUIT;
-- UTILISEE POUR LA DETERMINATION DU PRIX D'UN SERVICE
-- CREATE OR REPLACE VIEW v_produit_service_specialite AS
-- SELECT 
-- se.id AS idservice, se.service AS nomservice, se.prix AS prixService, se.margebenef AS margebeneficiaire , se.duree AS dureeservice, spe.id AS idspecialite, spe.specialite AS nomspecialite, spe.salaireHeure AS salaireSpecialite,
-- ss.heureTravail, p.id AS idproduit, p.nom AS nomproduit, p.prixUnitaire, sp.id AS idserviceProduit, sp.quantite AS quantiteProduit
-- FROM
-- produit AS p JOIN service_produit AS sp ON p.id = sp.idproduit
-- JOIN service AS se ON sp.idservice = se.id 
-- JOIN service_specialite AS ss ON ss.idservice = se.id
-- JOIN specialite AS spe ON spe.id = ss.idspecialite GROUP BY se.id;

-- SELECT * FROM v_produit_service_specialite GROUP BY idservice,  nomservice, prixService, margebeneficiaire ,  dureeservice, idspecialite, nomspecialite,  salaireSpecialite,
-- heureTravail,  idproduit, nomproduit, prixUnitaire, idserviceProduit,  quantiteProduit

-- SELECT se.id AS idservice, se.service AS nomservice, se.prix AS prixService, se.margebenef AS margebeneficiaire , se.duree AS dureeservice, MIN(spe.id) AS idspecialite, MIN(spe.specialite) AS nomspecialite, MIN(spe.salaireHeure) AS salaireSpecialite,
-- DISTINCT(ss.heureTravail) AS heureTravail, p.id AS idproduit, p.nom AS nomproduit, MIN(p.prixUnitaire), MIN(sp.id) AS idserviceProduit, MIN(sp.quantite) AS quantiteProduit
-- FROM produit AS p JOIN service_produit AS sp ON p.id = sp.idproduit
-- JOIN service AS se ON sp.idservice = se.id
-- JOIN service_specialite AS ss ON ss.idservice = se.id
-- JOIN specialite AS spe ON spe.id = ss.idspecialite GROUP BY se.id, p.id, ss.heureTravail;


-- SELECT DISTINCT v1.*, v2.*
-- FROM v_specialite_service v1
-- LEFT JOIN v_produit_service v2 ON v1.idservice = v2.idservice;
