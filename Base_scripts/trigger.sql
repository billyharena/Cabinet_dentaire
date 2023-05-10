-- Liste de tous les triggers
SELECT * FROM pg_trigger;

-- Liste triggers associés à une table 
SELECT * FROM pg_trigger WHERE tgrelid = 'nom_de_ma_table'::regclass;



-- VUE v_produit
CREATE OR REPLACE FUNCTION update_v_produit() RETURNS TRIGGER AS $$
BEGIN
    EXECUTE 'CREATE OR REPLACE VIEW v_produit AS 
SELECT ser.id AS idservice, ser.service, ser.duree, ser.prix, s.id AS idspecialite, s.specialite, s.salaireHeure, ss.heureTravail, ser.margebenef FROM 
specialite AS s JOIN service_specialite AS ss ON s.id = ss.idspecialite
JOIN service AS ser ON ss.idservice = ser.id';
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER refresh_v_produit_1
AFTER INSERT OR UPDATE OR DELETE ON service
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit();

CREATE OR REPLACE TRIGGER refresh_v_produit_2
AFTER INSERT OR UPDATE OR DELETE ON service_specialite
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit();

CREATE OR REPLACE TRIGGER refresh_v_produit_3
AFTER INSERT OR UPDATE OR DELETE ON specialite
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit();

CREATE OR REPLACE FUNCTION refresh_v_produit() RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_v_produit_1();
    PERFORM refresh_v_produit_2();
    PERFORM refresh_v_produit_3();
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- VUE v_produit_service

CREATE OR REPLACE FUNCTION update_v_produit_service() RETURNS TRIGGER AS $$
BEGIN
    EXECUTE 'CREATE OR REPLACE VIEW v_produit_service AS
SELECT 
p.id AS idproduit, p.nom AS nomproduit, p.prixUnitaire, sp.id AS idserviceProduit, sp.quantite AS quantiteProduit, se.id AS idservice, se.service AS nomservice
FROM
produit AS p JOIN service_produit AS sp ON p.id = sp.idproduit 
JOIN service AS se ON sp.idservice = se.id';
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER refresh_v_produit_service_1
AFTER INSERT OR UPDATE OR DELETE ON produit
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit_service();

CREATE OR REPLACE TRIGGER refresh_v_produit_service_2
AFTER INSERT OR UPDATE OR DELETE ON service_produit
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit_service();

CREATE OR REPLACE TRIGGER refresh_v_produit_service_3
AFTER INSERT OR UPDATE OR DELETE ON service
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit_service();

CREATE OR REPLACE FUNCTION refresh_v_produit_service() RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_v_produit_service_1();
    PERFORM refresh_v_produit_service_2();
    PERFORM refresh_v_produit_service_3();
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


-- VUE PRODUIT

CREATE OR REPLACE FUNCTION update_v_produit() RETURNS TRIGGER AS $$
BEGIN
    EXECUTE 'CREATE OR REPLACE VIEW v_produit AS 
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
FROM PRODUIT';
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER refresh_v_produit_1
AFTER INSERT OR UPDATE OR DELETE ON produit
FOR EACH STATEMENT
EXECUTE FUNCTION update_v_produit();


CREATE OR REPLACE FUNCTION refresh_v_produit() RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_v_produit_1();
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;