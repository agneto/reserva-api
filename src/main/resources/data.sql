INSERT INTO filial (ID, nome) VALUES (1, 'Filial1');
INSERT INTO filial (ID, nome) VALUES (2, 'Filial2');
INSERT INTO filial (ID, nome) VALUES (3, 'Filial3');
INSERT INTO filial (ID, nome) VALUES (4, 'Filial4');

INSERT INTO sala (ID, nome) VALUES (1, 'Sala1');
INSERT INTO sala (ID, nome) VALUES (2, 'Sala2');
INSERT INTO sala (ID, nome) VALUES (3, 'Sala3');
INSERT INTO sala (ID, nome) VALUES (4, 'Sala4');


INSERT INTO reserva (inicio, fim, quantidade_pessoa, responsavel, codigo_filial, codigo_sala, cafe) values (CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(3), 3, 'Janaína Silva', 1, 1, true);
