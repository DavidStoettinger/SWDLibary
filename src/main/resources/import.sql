Insert into medien (id, FSK, Name, Beschreibung) values (1, 01, 'testMedium1', 'Lorem ipsum'),(2, 16, 'testMedium2', 'Test Medium 2 Beschreibungstext'),(3, 18, 'testMedium3', 'Test Medium 3 FSK 18');
INSERT INTO kunde (id, name) VALUES (1, 'Kunde1'),(2, 'Kunde2'),(3, 'Kunde3'),(4, 'Kunde4');
INSERT INTO bibliothek (id, addresse) VALUES (001, 'Bibaddresse 1');
INSERT INTO exemplar (id, ausgeliehen, medien_id, bibliothek_id) VALUES (1, TRUE, (select(id) from medien where id = 1), 1),(2, FALSE , (select(id) from medien where id = 2), (select(id) from bibliothek where id = 1)),(3, TRUE, (select(id) from medien where id = 3), (select(id) from bibliothek where id = 1)),(4, TRUE, (select(id) from medien where id = 1), (select(id) from bibliothek where id = 1)),(5, FALSE , (select(id) from medien where id = 2), (select(id) from bibliothek where id = 1));
INSERT INTO reservierungen (id, Ausleihe, Zeitpunkt, Medien_id, kunde_id) VALUES (1, cast('2021-12-14' AS datetime), cast('2021-12-15' AS datetime), (select(id) from medien where id = 1),(select(id) from kunde where id = 1)),(2, cast('2021-12-14' AS datetime), cast('2021-12-13' AS datetime), (select(id) from medien where id = 3),(select(id) from kunde where id = 1)),(3, cast('2021-12-16' AS datetime), cast('2021-12-17'AS datetime), (select(id) from medien where id = 2), (select(id) from kunde where id = 2));
INSERT INTO ausleihe (id, Zeitpunkt, IstZeit, SollZeit, kunde_id, Exemplar_id, reservierungen_id) VALUES(1, cast('2021-12-13' AS datetime), null, cast('2022-12-16' as datetime), (select(id) from kunde where id = 1), (select(id) from exemplar where id = 1),(select(id) from reservierungen where id = 1)),(2, cast('2021-12-13' AS datetime), null, cast('2021-12-17' AS datetime), (select(id) from kunde where id = 2), (select(id) from exemplar where id = 3),(select(id) from reservierungen where id = 3)),(3, cast('2021-12-01' AS datetime), null, cast('2021-12-02' AS datetime), (select(id) from kunde where id = 2), (select(id) from exemplar where id = 4),(select(id) from reservierungen where id = 2));
