drop table if exists ingredients CASCADE 
drop table if exists receipe CASCADE 
drop table if exists receipe_ingredient CASCADE 
drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start with 1 increment by 1
create table ingredients (id bigint not null, ingredients varchar(255), primary key (id))
create table receipe (id bigint not null, instructions varchar(255), name varchar(255), no_of_servers integer, receipe_type integer, primary key (id))
create table receipe_ingredient (receipe_id bigint not null, ingredient_id bigint not null, primary key (receipe_id, ingredient_id))
alter table ingredients add constraint UK_fdqv709yky3kpkrmh09w3kxf unique (ingredients)
alter table receipe_ingredient add constraint FKl1xnpqik2jc1f7eymkvju6f4b foreign key (ingredient_id) references ingredients
alter table receipe_ingredient add constraint FK16kqirg8n6xgm5a2igxf4n67d foreign key (receipe_id) references receipe