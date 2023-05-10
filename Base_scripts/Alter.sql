ALTER TABLE specialite ADD salaireHeure FLOAT NOT NULL DEFAULT 2000;

ALTER TABLE service ADD margebenef DOUBLE PRECISION NOT NULL DEFAULT 20;

ALTER TABLE produit ADD prixvente DOUBLE PRECISION ;

ALTER TABLE service ALTER COLUMN service TYPE VARCHAR(100) ;

UPDATE service SET margebenef = 30 WHERE id=1;

UPDATE service_specialite SET heureTravail = 4 WHERE id=3;

UPDATE produit SET nom = 'produit 6' WHERE id = 6;

UPDATE produit SET prixunitaire = 150000 WHERE id = 6;