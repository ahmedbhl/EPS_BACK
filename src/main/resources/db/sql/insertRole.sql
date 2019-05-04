--liquibase formatted sql
--changeset {zayneb}:{insertRole}
INSERT INTO Role(id,name) VALUES(1,'SUPER_ADMIN');
INSERT INTO Role(id,name) VALUES(2,'ADMINISTRATION');
INSERT INTO Role(id,name) VALUES(3,'STUDENT');
INSERT INTO Role(id,name) VALUES(4,'PROFESSOR');

--changeset {zayneb}:{insertUser}
INSERT INTO User(id, address,  email, enabled, last_name, first_name, password, phone_number, profile_picture, date_of_registration) VALUES ('1', 'cedro Technology', 'super-admin@cedro.com', 1, 'super Admin', 'super Admin', '$2a$10$RHVA.x6CUXfm5CqPlFb1..eEgxSnDnhQtZhD8nGOJt/9YjMTPTeUe', '00000000', 'super Admin picture', '2018-10-24');
INSERT INTO User(id, address,  email, enabled, last_name, first_name, password, phone_number, profile_picture, date_of_registration) VALUES ('2', 'cedro Technology', 'administration@cedro.com', 1, 'Administration', 'Administration', '$2a$10$RHVA.x6CUXfm5CqPlFb1..eEgxSnDnhQtZhD8nGOJt/9YjMTPTeUe', '00000000', 'Administration picture', '2018-10-24');
INSERT INTO User(id, address,  email, enabled, last_name, first_name, password, phone_number, profile_picture, date_of_registration) VALUES ('3', 'cedro Technology', 'student@cedro.com', 1, 'Student', 'Student', '$2a$10$RHVA.x6CUXfm5CqPlFb1..eEgxSnDnhQtZhD8nGOJt/9YjMTPTeUe', '00000000', 'Student picture', '2018-10-24');
INSERT INTO User(id, address,  email, enabled, last_name, first_name, password, phone_number, profile_picture, date_of_registration) VALUES ('4', 'cedro Technology', 'professor@cedro.com', 1, 'Professor', 'Professor', '$2a$10$RHVA.x6CUXfm5CqPlFb1..eEgxSnDnhQtZhD8nGOJt/9YjMTPTeUe', '00000000', 'Professor picture', '2018-10-24');

--changeset {zayneb}:{insertSuperAdmin}
INSERT INTO super_admin (id) VALUES(1);
INSERT INTO administration (id) VALUES(2);
INSERT INTO student (id) VALUES(3);
INSERT INTO professor (id) VALUES(4);


--changeset {zayneb}:{insertUserRole}
INSERT INTO users_role(user_id,role_id) VALUES(1,1);
INSERT INTO users_role(user_id,role_id) VALUES(2,2);
INSERT INTO users_role(user_id,role_id) VALUES(3,3);
INSERT INTO users_role(user_id,role_id) VALUES(4,4);