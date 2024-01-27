--liquibase formatted sql

--changeset valentinkd3:1
insert into orders(id, name, price)
values (1,'Микроволновка', 1250.1),
       (2,'Чайник', 2225.15),
       (3,'Духовка', 1111.2),
       (4,'Смартфон', 22234.34),
       (5,'Чехол', 141);

--changeset valentinkd3:2
insert into users(id,username,password,role)
values (1, 'Valentin', 1234, 'USER'),
       (2, 'Julia', 4321, 'ADMIN');

--changeset valentinkd3:3
insert into basket(usr_id, order_id)
values (1,1),
       (1,2),
       (1,3),
       (2,4),
       (2,5);