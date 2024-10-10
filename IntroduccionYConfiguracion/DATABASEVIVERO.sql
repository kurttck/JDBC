create DATABASE vivero;
use vivero;

CREATE TABLE pago(
    id_pago int not null primary key,
    id_cliente int,
    forma_pago varchar(40),
    id_transaccion varchar(50),
    fecha_pago DATE,
    total DECIMAL(15,2)
)

CREATE TABLE oficina(
    id_oficina int not null primary key,
    codigo_oficina varchar(10),
    ciudad varchar(30),
    pais varchar(50),
    region varchar(50),
    codigo_postal varchar(10),
    telefono varchar(20)
)

CREATE TABLE empleado(
    id_empleado int not null primary key,
    codigo_empleado int,
    nombre varchar(50),
    apellido varchar(50),
    extesion varchar(10),
    email varchar(100),
    id_oficina int,
    id_jefe int,
    puesto varchar(50)
)

CREATE TABLE cliente(
    id_cliente int not null primary key,
    codigo_cliente int,
    nombre_cliente varchar(50),
    nombre_contacto varchar(30),
    apellido_contacto varchar(30),
    telefono varchar(20),
    fax varchar(20),
    ciudad varchar(50),
    region varchar(50),
    pais varchar(50),
    codigo_postal varchar(10),
    id_empleado int,
    limite_credito decimal(15,2)
)

select * from producto
select * from gama_producto

SELECT p.codigo_producto, p.nombre, g.id_gama, gama
from producto p
INNER JOIN gama_producto g
ON p.id_gama = g.id_gama
where g.gama="Frutales" 

DESCRIBE producto
select * from detalle_pedido

select id_pedido, codigo_pedido, estado 
from pedido 
where  estado= "?"

CREATE TABLE pedido(
    id_pedido INT not null primary key,
    codigo_pedido int,
    fecha_pedido DATE,
    fecha_esperada date,
    fecha_entrega date,
    estado varchar(15),
    comentarios TEXT,
    id_cliente int
)

CREATE TABLE detalle_pedido(
    id_detalle_pedido int not null primary key,
    id_pedido int,
    id_producto int,
    cantidad int,
    precio_unidad decimal(15,2),
    numero_linea SMALLINT
)

CREATE TABLE producto(
    id_producto int not null primary key,
    codigo_producto varchar(15),
    nombre varchar(70),
    id_gama int,
    dimensiones varchar(25),
    proveedor varchar(50),
    descripcion text,
    cantidad_en_stock smallint,
    precio_venta decimal(15,2),
    precio_proveedor decimal(15,2)
)

CREATE TABLE gama_producto(
    id_gama int not null primary key,
    gama varchar(50),
    descripcion_text text,
    descripcion_html text,
    imagen varchar(256)
)

ALTER TABLE pago
add constraint fk_id_cliente
foreign key(id_cliente) references cliente(id_cliente)

ALTER TABLE cliente
add constraint fk_id_empleado
foreign key(id_empleado) references empleado(id_empleado)

ALTER TABLE empleado
add constraint fk_id_oficina
foreign key(id_oficina) references oficina(id_oficina)

ALTER TABLE empleado
add constraint fk_id_jefe
foreign key(id_jefe) references empleado(id_empleado)

ALTER TABLE producto
add constraint fk_id_gama  
foreign key(id_gama) references gama_producto(id_gama)

ALTER TABLE detalle_pedido
add constraint fk_id_pedido
foreign key(id_pedido) references pedido(id_pedido)

ALTER TABLE detalle_pedido
add constraint fk_id_producto
foreign key(id_producto) references producto(id_producto)

ALTER TABLE pedido
add constraint fk_id_client
foreign key(id_cliente) references cliente(id_cliente)
