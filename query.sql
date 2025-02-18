CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	nombre VARCHAR (50) NOT NULL,
	correo VARCHAR (50) NOT NULL,
	telefono VARCHAR (50) NOT NULL,
	direccion VARCHAR (50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE prestamo(
	id SERIAL PRIMARY KEY,
	monto DECIMAL (20,2) NOT NULL,
	interes DECIMAL (3,2) NOT NULL,
	duracion_meses INTEGER NOT NULL,
	estado VARCHAR (50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	id_cliente INTEGER REFERENCES cliente(id) NOT NULL
);

CREATE TABLE historial_prestamo(
	id SERIAL PRIMARY KEY,
	id_prestamo INTEGER REFERENCES prestamo(id) NOT NULL,
	monto DECIMAL (20,2) NOT NULL,
	estado VARCHAR (50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Batman', 'batman@banco.com', '5551234567', '1007 Mountain Drive, Gotham');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Joker', 'joker@banco.com', '5556667777', 'Arkham Asylum, Gotham');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Superman', 'superman@banco.com', '5559876543', '344 Clinton St, Metropolis');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Wonder Woman', 'wonder.woman@banco.com', '5557654321', 'Themyscira Island');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Spider-Man', 'spiderman@banco.com', '5558765432', '20 Ingram Street, Queens, NY');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Iron Man', 'ironman@banco.com', '5556543210', '10880 Malibu Point, Malibu, CA');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Captain America', 'captain.america@banco.com', '5551112222', '569 Leaman Place, Brooklyn, NY');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Thor', 'thor@banco.com', '5553334444', 'Asgard');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Hulk', 'hulk@banco.com', '5555556666', '890 Fifth Avenue, New York, NY');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Black Widow', 'black.widow@banco.com', '5557778888', '221B Baker Street, London');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Loki', 'loki@banco.com', '5559990000', 'Asgard');

INSERT INTO public.cliente(nombre, correo, telefono, direccion)
VALUES ('Thanos', 'thanos@banco.com', '5551239876', 'Titan');