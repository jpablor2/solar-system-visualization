--DROP DATABASE SSV;
--ALTER TABLE modulo DROP CONSTRAINT modulo_id_inversor_fkey;
--ALTER TABLE modulo DROP CONSTRAINT modulo_id_arreglo_fkey;

--DROP TABLE inversor;
--DROP TABLE arreglo;
--DROP TABLE modulo;
/*
CREATE TABLE inversor(
id_inversor VARCHAR(10) PRIMARY KEY ,
max_strings INTEGER,
modelo VARCHAR (25),
description VARCHAR (100),
micro BOOLEAN
);


CREATE TABLE arreglo(
id_arreglo VARCHAR(10) PRIMARY KEY ,
tipo_conexion CHAR,
num_paneles INTEGER,
angulo_incli DOUBLE PRECISION,
angulo_orient DOUBLE PRECISION
);


CREATE TABLE modulo(
id_modulo VARCHAR(10) PRIMARY KEY ,
id_inversor VARCHAR (10),
id_arreglo VARCHAR (10),
modelo VARCHAR (25),
descripcion VARCHAR (100),
p_stc INTEGER,
p_noct INTEGER,
eficiencia DOUBLE PRECISION,
fact_desemp DOUBLE PRECISION,

CONSTRAINT  modulo_id_inversor_fkey FOREIGN KEY (id_inversor) 
REFERENCES inversor (id_inversor) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION,

CONSTRAINT  modulo_id_arreglo_fkey FOREIGN KEY (id_arreglo) 
REFERENCES arreglo (id_arreglo) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION
);
*/
-- Función INSERT: Insertamos un registro a la tabla
CREATE OR REPLACE FUNCTION insertar_modulos(VARCHAR(10), VARCHAR(10), VARCHAR(10), 
VARCHAR(25),VARCHAR(100),INTEGER,INTEGER,DOUBLE PRECISION,DOUBLE PRECISION ) RETURNS VOID AS
$BODY$
BEGIN
    INSERT INTO modulo (id_modulo, id_inversor,id_arreglo,modelo,descripcion,p_stc,
    p_noct,eficiencia,fact_desemp)
    VALUES ($1, $2, $3, $4, $5,$6
    ,$7,$8,$9);
END;
$BODY$  LANGUAGE 'plpgsql' VOLATILE;