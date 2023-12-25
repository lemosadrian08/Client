
create database desafio2;

use desafio2;

create table clientes (
	cliente_id int primary key auto_increment,
    nombre varchar(50),
    apellido varchar(50),
    dni varchar(50),
	fecha_nacimiento date
);

CREATE TABLE productos (
	producto_id int primary key auto_increment,
    codigo int NOT NULL,
    descripcion varchar(255) NOT NULL,
    cantidad int,
    precio decimal(8,2)
);

CREATE TABLE facturas (
    factura_id int primary key NOT NULL auto_increment,
    fecha datetime,
    cantidad int,
    total decimal(8, 2),
    cliente_id int,
    CONSTRAINT FK_facturas_clientes FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);

CREATE TABLE lineas (
    linea_id int primary key not null auto_increment,
    descripcion varchar(255),
    cantidad int,
    precio decimal(8,2),
    factura_id int,
    producto_id int,
    CONSTRAINT FK_lineas_facturas FOREIGN KEY (factura_id) REFERENCES facturas(factura_id),
    CONSTRAINT FK_lineas_productos FOREIGN KEY (producto_id) REFERENCES productos(producto_id)
);



select*from clientes;
select*from productos;
select*from lineas;
select*from facturas;
