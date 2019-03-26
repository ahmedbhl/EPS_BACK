--liquibase formatted sql
--changeset {zayneb}:{insertRole}
INSERT INTO Role(id,name) VALUES(1,'SUPER_ADMIN');
INSERT INTO Role(id,name) VALUES(2,'ADMINISTRATION');
INSERT INTO Role(id,name) VALUES(3,'STUDENT');
INSERT INTO Role(id,name) VALUES(4,'PROFESSOR');

--changeset {zayneb}:{insertUser}
INSERT INTO User(id, address,  email, enabled, last_name, first_name, password, phone_number, profil_picture, date_of_registration) VALUES ('1', 'cedro Technology', 'super-admin@cedro.com', 1, 'super Admin', 'super Admin', '$2a$10$RHVA.x6CUXfm5CqPlFb1..eEgxSnDnhQtZhD8nGOJt/9YjMTPTeUe', '00000000', 'super Admin picture', '2018-10-24');

--changeset {zayneb}:{insertUserRole}
INSERT INTO users_role(user_id,role_id) VALUES(1,1);