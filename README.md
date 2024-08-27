# Spring-Hibernate-Jwt

To add the Database Entries

INSERT INTO users (id, enabled, password, username) VALUES
(1, true, 'password123', 'john.doe'),
(2, true, 'password456', 'jane.smith');


INSERT INTO roles (id, role_name) VALUES
(1, 'USER'),
(2, 'ADMIN');


INSERT INTO users_roles (users_id, roles_id) VALUES
(1, 1), 
(2, 1), 
(2, 2);

testcommit