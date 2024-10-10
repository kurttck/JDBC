 describe cliente;

DESCRIBE pedido

select * from cliente where id_cliente=5;
INSERT INTO cliente(id_cliente, codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, ciudad, region, pais, codigo_postal, id_empleado, limite_credito)
values ()

select count(*) as count from cliente

select max(id_pedido) as max from pedido

select * from pedido;
select concat(nombre, " ", apellido) as nombreEmpleado, codigo_cliente, nombre_cliente, concat(nombre_contacto, " ", apellido_contacto) as nombrecontacto, telefono  
from empleado 
inner join cliente
on empleado.id_empleado=cliente.id_empleado
where empleado.id_empleado=11


select * from cliente where id_empleado=11

select * from oficina

update cliente
set codigo_cliente=23
where id_cliente=23


/* ------------------------------ */

SELECT
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    TABLE_NAME = 'oficina'
    AND REFERENCED_TABLE_NAME IS NOT NULL;


/* ----------------------------- */    

use vivero;
alter table detalle_pedido drop foreign key fk_id_producto;


ALTER TABLE detalle_pedido

ADD CONSTRAINT fk_detalle_pedido_producto

FOREIGN KEY (id_producto)

REFERENCES producto (id_producto)

ON DELETE CASCADE;







SELECT concat(nombre_contacto, ' ', apellido_contacto) as contacto, id_cliente FROM cliente;