INSERT INTO filial (ID, nome) VALUES (1, 'Filial1');
INSERT INTO filial (ID, nome) VALUES (2, 'Filial2');

INSERT INTO sala (ID, nome) VALUES (1, 'Sala1');
INSERT INTO sala (ID, nome) VALUES (2, 'Sala2');


INSERT INTO reserva (inicio, fim, quantidade_pessoa, responsavel, codigo_filial, codigo_sala, cafe) values (CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(3), 3, 'Janaína Silva', 1, 1, true);