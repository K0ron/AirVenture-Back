INSERT INTO role (type) VALUES ('admin');

INSERT INTO "user" (email, first_name, is_enabled, last_name, password, role_id)
VALUES ('john@doe.com', 'John', true, 'Doe', '$2a$12$S/VXri58yQkBCEIk9CnRtOSXZLFEa03dd5gJ5YwfqFH8wR6Lbfq8S', 1);

INSERT INTO activity (id, name,  description, duration, location, price)
VALUES (0, 'Parapente', 'Le parapente se pratiquqe avec une voile', 1, 'Pyrénées, France', 170);