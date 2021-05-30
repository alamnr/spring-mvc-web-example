/* JDBC Authentication schema */

drop table if exists users cascade;
drop table if exists authorities cascade;    

create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users (username,password,enabled) values ('nuser','pass',1);
insert into authorities (username, authority) values ('nuser','ROLE_ADMIN');







/*     End    */



drop table if exists AppUser cascade;
drop table if exists AppUser_Role cascade;
drop table if exists Role cascade;



create table AppUser (id bigint not null, email varchar(255), firstName varchar(255), lastName varchar(255), password varchar(255)
, userName varchar(255), primary key (id));
create table AppUser_Role (AppUser_id bigint not null, roles_id bigint not null, primary key (AppUser_id, roles_id));
create table Role (id bigint not null, roleName varchar(255), primary key (id));

insert into AppUser (email, firstName, lastName, username,password, Id) values ('hakula@jakula.com', 'Hakula', 'Fakula', 'john','$2a$10$UB956BfymabRVmQnRy9nFO4Gaq.HWu7XpsaFMNmOxunxg51QbsenK', 100);


insert into Role (roleName, id) values ('ROLE_USER', 100);
insert into Role (roleName, id) values ('ROLE_ADMIN', 200);
insert into AppUser_Role (AppUser_id, roles_id) values (100, 100);
insert into AppUser_Role (AppUser_id, roles_id) values (100, 200);

insert into AppUser (email, firstName, lastName,  username,password, Id) values ('jane@jakula.com', 'Jane', 'Tarzan', 'jane','$2a$10$UB956BfymabRVmQnRy9nFO4Gaq.HWu7XpsaFMNmOxunxg51QbsenK', 200);
insert into AppUser_Role (AppUser_id, roles_id) values (200, 100);