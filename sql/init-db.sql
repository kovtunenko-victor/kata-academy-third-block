CREATE TABLE `roles` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `login` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `title` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `users_roles` (
                               `user_id` bigint NOT NULL,
                               `roles_id` bigint NOT NULL,
                               PRIMARY KEY (`user_id`,`roles_id`),
                               KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
                               CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                               CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (login, name, password, title) VALUES('admin', 'Kovtunenko Viktor', '$2a$12$wS1VBcb/eGzeomweWrDAf.4gFcQ0UpAsgg0n3ooFUMNO8LGXEFiFu', 'engineer'); #123
INSERT INTO users (login, name, password, title) VALUES('user', 'Velichko Varvara', '$2a$12$DNrUZaJY5ys52ib7/g014.ZJxR5qd.yKZvCC.Okipq4ZWE617U2v6', 'housewife'); #456


INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_USER');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);CREATE TABLE `roles` (
                                                                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                                                                  `name` varchar(255) DEFAULT NULL,
                                                                                  PRIMARY KEY (`id`)
                                                         ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `login` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `title` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `users_roles` (
                               `user_id` bigint NOT NULL,
                               `roles_id` bigint NOT NULL,
                               PRIMARY KEY (`user_id`,`roles_id`),
                               KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
                               CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                               CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (login, name, password, title) VALUES('admin', 'Kovtunenko Viktor', '$2a$12$wS1VBcb/eGzeomweWrDAf.4gFcQ0UpAsgg0n3ooFUMNO8LGXEFiFu', 'engineer'); #123
INSERT INTO users (login, name, password, title) VALUES('user', 'Velichko Varvara', '$2a$12$DNrUZaJY5ys52ib7/g014.ZJxR5qd.yKZvCC.Okipq4ZWE617U2v6', 'housewife'); #456


INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_USER');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);