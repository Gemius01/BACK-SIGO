INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');


INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);


*** NUEVO SQL ****

***AGREGAR SQL DIRECTO a la consola de PHP MYADMIN Y LUEGO MODIFICAR LAS FECHAS PQ TIRA ERROR 500(DESDE EL EDITOR DE REGISTROS EN PHPMYADMIN)

INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1, '');
INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 1, '');
INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 0, '');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');


INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);


*** Ultimo SQL **

INSERT INTO `e_cliente` (`rut`, `contacto`, `direccion`, `email`, `fono`, `nombre`, `id_ciudad`) VALUES ('11111111-1', '+56988888888', 'Argentina 23', 'sigo@asd.cl', '+56988888888', 'SIGO', '1');

INSERT INTO `funcionario` (`rut`, `apellido_m`, `apellido_p`, `direccion`, `email`, `fono`, `movil`, `nombre_1`, `nombre_2`, `id_ciudad`, `id_e_cliente`) VALUES ('11111111-1', 'apellidom', 'apellido_p', 'Argentina', 'sigo@asd.cl', '+56988888888', '+12345678910', 'nombre1', 'nombre2', '1', '11111111-1');

INSERT INTO `user` (`id`, `enabled`, `lastpasswordresetdate`, `password`, `username`, `id_funcionario`) VALUES ('1', b'1', '2018-06-14 00:00:00', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', '11111111-1');

INSERT INTO `modulo` (`id`, `nombre`) VALUES (NULL, 'Modulo1');

INSERT INTO `seccion` (`id`, `nombre`, `id_modulo`) VALUES (NULL, 'seccion1', '1');

INSERT INTO `authority` (`id`, `acceso`, `mantencion`, `name`, `tabla_main`, `id_seccion`) VALUES ('1', 'asd', b'0', 'ROLE_ADMIN', 'asddd', '1')

INSERT INTO `user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');
