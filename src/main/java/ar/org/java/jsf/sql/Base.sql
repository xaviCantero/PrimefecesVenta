-- drop database if exists todoVenta;
-- create database todoVenta;
use todoVenta;
create table persona(
	id int auto_increment primary key,
	nombre varchar(20),
	sexo char(1)
);
create table producto(
	codigo int auto_increment primary key,
	nombre varchar(150),
	precio double
);
create table venta(
	codigo int auto_increment primary key,
    fecha timestamp default current_timestamp,
    codPersona int,
    monto double
);

create table detalleVenta(
	codigo int auto_increment primary key,
    codVenta int,
    codProducto int,
    cantidad int
);

alter table venta
	add constraint FK_Venta_Persona
	foreign key(codPersona)
    references persona(id);
    
alter table detalleVenta
	add constraint FK_DetalleVenta_Producto
    foreign key(codProducto)
    references producto(codigo);

alter table detalleVenta
	add constraint FK_DetalleVenta_Venta
	foreign key(codVenta)
    references venta(codigo)

