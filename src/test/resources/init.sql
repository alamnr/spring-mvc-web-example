insert into AppUser (name, password, id) values ('john', 'pass', 1);
insert into Role (roleName, id) values ('ROLE_USER', 1);
insert into Role (roleName, id) values ('ROLE_ADMIN', 2);
insert into AppUser_Role (AppUser_id, roles_id) values (1, 1);
insert into AppUser_Role (AppUser_id, roles_id) values (1, 2);

insert into AppUser (name, password, id) values ('doe', 'pass', 2);
insert into AppUser_Role (AppUser_id, roles_id) values (2, 1);