CREATE DATABASE ecommerce;

use ecommerce;

CREATE TABLE eproduct(ID bigint primary key auto_increment, name varchar(100),
price decimal(10,2),date_added timestamp default now());

INSERT INTO eproduct(name,price) values('DELL Laptop ABC model', 876033.23);
INSERT INTO eproduct(name,price) values('Acer Laptop XYZ model', 233033.23);
INSERT INTO eproduct(name,price) values('Lenovo Laptop XYZ model', 134233.23);

select * from eproduct;  -> result   -> 

UPDATE eproduct SET name='XYZ', price=17263 where id=2;