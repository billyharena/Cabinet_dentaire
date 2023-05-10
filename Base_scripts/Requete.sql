
-- TSY RAISINA
SELECT idservice, SUM(prixUnitaire * quantiteProduit) AS revientMateriel, SUM(salaireSpecialite * heureTravail) AS revientsalarial FROM  v_produit_service_specialite GROUP BY idservice ORDER BY idservice ASC;

-- Calcul revient salarial
SELECT idservice, SUM(salaireHeure * heureTravail) AS revient FROM v_specialite_service GROUP BY idservice ORDER BY idservice ASC;

-- Calcul revient materiel
SELECT idservice, SUM(prixUnitaire * quantiteProduit) AS revientMateriel FROM v_produit_service GROUP BY idservice ORDER BY idservice ASC;

-- TSY RAISINA
-- Calcul de la recette par benefice par ordre croissant
SELECT idservice, prix FROM v_ssse GROUP BY idservice, prix ORDER BY idservice ;

SELECT ((SELECT prix FROM v_ssse)-(SELECT SUM(salaireHeure * heureTravail) FROM v_ssse)) FROM v_ssse GROUP BY idservice ORDER BY idservice ASC;
