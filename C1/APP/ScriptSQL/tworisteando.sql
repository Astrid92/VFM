DROP TABLE IF EXISTS MedioTransporte;
CREATE TABLE MedioTransporte 
(nombre TEXT PRIMARY KEY  NOT NULL , 
precio REAL check(typeof(precio) = 'real') ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Linea;
CREATE TABLE Linea 
(numero INTEGER NOT NULL , 
color TEXT, 
nombreTransporte TEXT NOT NULL , PRIMARY KEY (numero, nombreTransporte),
FOREIGN KEY (nombreTransporte) REFERENCES MedioTransporte(nombre) ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Estaciones;
CREATE TABLE Estaciones 
(nombre TEXT PRIMARY KEY  NOT NULL , 
cordX REAL check(typeof(cordX) = 'real') , 
cordY REAL check(typeof(cordY) = 'real') , 
estacionSiguiente TEXT
ON UPDATE CASCADE ON DELETE RESTRICT
);


DROP TABLE IF EXISTS EstacionLinea;
CREATE TABLE EstacionLinea 
(estacionNombre TEXT NOT NULL , 
numeroLinea INTEGER NOT NULL  check(typeof(numeroLinea) = 'integer') , 
nombreTransporte TEXT NOT NULL , 
terminal INTEGER, 
transborde INTEGER, 
PRIMARY KEY (estacionNombre, numeroLinea, nombreTransporte),
FOREIGN KEY (numeroLinea,nombreTransporte) REFERENCES Linea(numero,nombreTransporte) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (estacionNombre) REFERENCES Estaciones(nombre) ON UPDATE CASCADE ON DELETE RESTRICT
);

