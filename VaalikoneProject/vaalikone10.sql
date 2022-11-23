-- save this file into your computer
-- open mysql monitor with root privileges so that you have mysql monitor prompt (mysql>)
-- give a command 'source path\nameofsavedfile'
--    where path is absolute path to the folder where the file exists, e.g. c:\temp
--    and nameofsavedfile is the name of the saved file, e.g. vaalikone.txt


-- create a user and user's password
-- credentials pena/kukkuu has all privileges to all all tables of database vaalikone (exept grant privilege)
grant all on vaalikone10.* to user@localhost identified by 'password';

-- if there exists database vaalikone, drop it
drop database if exists vaalikone10;

-- create database vaalikone (just dropped)
create database vaalikone10;

-- This line is to handle utf8 character needs (like ä and ö) if you have not set utf8 encoding already default for your mysql
ALTER DATABASE vaalikone10 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- use the database
use vaalikone10;

-- these drops are not needed now (we droppped the whole database), but as an example how to drop a table
DROP TABLE if exists EHDOKKAAT;
DROP TABLE if exists KYSYMYKSET;
DROP TABLE if exists VASTAUKSET;

-- create a table Puolue (mysql does not bother the case of letters - in this case)
CREATE TABLE Puolue (
   Puolue_ID INTEGER NOT NULL AUTO_INCREMENT, Nimi varchar(50), Selitys varchar(255), PRIMARY KEY (Puolue_ID)
);

-- create a table Adminit
CREATE TABLE Adminit (
   Admin_ID INTEGER NOT NULL AUTO_INCREMENT, Nimi VARCHAR(25), Tunnus varchar(25) UNIQUE, PRIMARY KEY (Admin_ID)
);

-- create a table Kysymykset
CREATE TABLE Kysymykset (
   Kysymys_ID INTEGER NOT NULL AUTO_INCREMENT, Kysymys varchar(255), PRIMARY KEY (Kysymys_ID)
);

-- create a table Vastaukset
CREATE TABLE Vastaukset ( Ehdokas_ID INTEGER NOT NULL, Kysymys_ID INTEGER NOT NULL, Vastaus ENUM('1','2','3','4','5'), Selvennys VARCHAR(255), PRIMARY KEY(Ehdokas_ID, Kysymys_ID));

-- create a table Ehdokkaat
CREATE TABLE Ehdokkaat ( Ehdokas_ID INTEGER NOT NULL AUTO_INCREMENT, Etunimi varchar(25), Sukunimi varchar(25), Puolue varchar(50), Paikkakunta varchar(25), Ika int, Ammatti varchar(50), Tunnus varchar(25), Primary Key(Ehdokas_ID)); 

-- create a table Tunnukset
CREATE TABLE Tunnukset ( Tunnus varchar(25) NOT NULL, Salasana varchar(64), Oikeus ENUM('1','2','3'), Primary Key(Tunnus)); -- 1 = Ehdokas, 2 = Puolue, 3 = Admin

-- create a table Linkit
CREATE TABLE Linkit ( Linkki_ID INTEGER NOT NULL AUTO_INCREMENT, Ehdokas_ID INTEGER NOT NULL, Linkki VARCHAR(255), Linkki_Desc VARCHAR(255), PRIMARY KEY(Linkki_ID));

-- create a table Kunnat
CREATE TABLE Kunnat ( Nimi VARCHAR(25) NOT NULL, PRIMARY KEY(Nimi));

-- insert values into the table Adminit
INSERT INTO Adminit VALUES (0,"Roope", "tunnus1");
INSERT INTO Adminit VALUES (0,"Admin", "admin");


-- insert values into the table Tunnukset
INSERT INTO Tunnukset VALUES ("tunnus1", "$2a$10$RSO5PUohfqobOpIjFOI75OhtKATVWmcJh4GC5TEkyyNWGvfZ72uU.", '3'); -- password = salasana
INSERT INTO Tunnukset VALUES ("admin", "$2a$10$fwKla6qg88nsnW6tkNNd1OoeZCRjUIVx3yAaIzWIG6YA5NvM.Ro/W", '3'); -- password = pass
INSERT INTO Tunnukset VALUES ("SpongeBob", "$2a$10$9mSicl/WOyfO21K1hfnD0.ntGZtW8Z8nAxcnb2zR9a4tDmKocqI3W", '1'); -- password = qwerty123
INSERT INTO Tunnukset VALUES ("MarinSa", "$2a$10$qBKJUXmEgY5ZZPTqW6eMLubt.LQQ5ucOK2e087gq6ydZvqgljTK.C", '1'); -- password = salasana
INSERT INTO Tunnukset VALUES ("LaineEs", "$2a$10$rxHPVwZgBeHpngnIwTQ0hu3s2HnWnBd8wD27FKCLilEVsNwM3IzC2", '1'); -- password = asdasd
INSERT INTO Tunnukset VALUES ("KorhoJa", "$2a$10$nc9z1CB3yDwnQNq7kvUOb.5Qyzvwcx7DPNG2Ta65gSW3Ij89IdSo6", '1'); -- password = qwerty
INSERT INTO Tunnukset VALUES ("VirtaPe", "$2a$10$OdlsCxA.kqHVxdtaGREii.ydha5.U9mJKlYatFk6DCvjHpyCXVlwC", '1'); -- password = Salasana
INSERT INTO Tunnukset VALUES ("JarviLe", "$2a$10$zaqPLtr8dM79KbmPJrzpA.4gfK/tuDrEEIlRFKfo7lYR8yB/6vVX6", '1'); -- password = lalala
INSERT INTO Tunnukset VALUES ("KoskiTe", "$2a$10$d3VwEnyLMJ3er3./vtk9YetDlXRiJnzk3j33GZ0tiIezNaftRCeGa", '1'); -- password = ytrewq
INSERT INTO Tunnukset VALUES ("MakinLi", "$2a$10$7jCV9zpxicDZCfaEC57/9.94DtG1EOIwpDegnCzK12mEJT5GM5wPK", '1'); -- password = jummijammi
INSERT INTO Tunnukset VALUES ("HeikkiSe", "$2a$10$dTT.cgl/HTnDEWbGacGrG.kZ7AHxfHKt3AU3vFRTWSIOhnl4Vif6e", '1'); -- password = aaaaaaaaaa
INSERT INTO Tunnukset VALUES ("MakelIl", "$2a$10$GHPm4DYcWCB2F7L1N18hjuOs/S0veFESpVBATzQZ9yVrEqtN0Y6jC", '1'); -- password = bababa
INSERT INTO Tunnukset VALUES ("NiemiIs", "$2a$10$a5g7sZhOgCn6ImzAEcl3LOHh7YwTFlmftScgXh0.aVm1RPmhwpMse", '1'); -- password = qazwsx
INSERT INTO Tunnukset VALUES ("PaanaJa", "$2a$10$deV8HFhphlx2MVQeaZeOq.of0hbQqjlZP1pfJy8tChqFlshzTLKma", '1'); -- password = poiuyt
INSERT INTO Tunnukset VALUES ("PaakkKa", "$2a$10$3.qsxXtFGq0HFpvClAsOkOtlkIFZtIv/3Oha15Ol.A5P/ZuL.6FQe", '1'); -- password = fdsazxc
INSERT INTO Tunnukset VALUES ("LaineOu", "$2a$10$8n39CLYd5kN2Ig2HQ7xYl.rCLRxENCQK5HesuXUY0nwb3xsFQ7zVq", '1'); -- password = kurwa
INSERT INTO Tunnukset VALUES ("KokkoMa", "$2a$10$a.7q8.FZC30AXpn.d6VGKu7w7U75GJKRfmHBxGkXmfWhiblYkmmTO", '1'); -- password = ylialioh
INSERT INTO Tunnukset VALUES ("LindsSt", "$2a$10$byFVqxSAcznKLx4/j4kIL.QWoFOQfHqwznZjRpimgSxoJYQo8NLn6", '1'); -- password = asopdijaopsfi
INSERT INTO Tunnukset VALUES ("LaiteRi", "$2a$10$1AReOxNmhU2GJTIj3d7mtOSCKwoKx4ToTSpp023OqTJnMXPz4Iuz.", '1'); -- password = quibiuas
INSERT INTO Tunnukset VALUES ("JormaMa", "$2a$10$YwT8h2XFPvI2sSJ8Ze/8ZuAjeR75H7Dpze2LiOL5P10v66aRoYziS", '1'); -- password = asiopgegeg
INSERT INTO Tunnukset VALUES ("AsdasMa", "$2a$10$46npQBxZ/Y4szjPsGEweA.flK1Qt8tDyWw6Ysi5psWeFEsPS9/X4G", '1'); -- password = wiugbhopegni
INSERT INTO Tunnukset VALUES ("9000Is", "$2a$10$Yuw2Txb7Hqw2mZK39sW0r.rW1MHI0zkPSDJWIT4bBUMDHN9p./zdC", '1'); -- password = uyieofgwuiefgb
INSERT INTO Tunnukset VALUES ("VirtalPe", "$2a$10$uhHAkoVx6ODjlisEwWxQQuI2xM16QkBA7.Tu57nqSnkadjO5cr1Jy", '1'); -- password = 786g8yasfbi8
INSERT INTO Tunnukset VALUES ("YrjolPe", "$2a$10$43JBNNotUh6NL.KXuoR5aeXR2dbUZlhkicYPdvqzp3/CRZdx29VlO", '1'); -- password = asdiofghhdo
INSERT INTO Tunnukset VALUES ("MatinEl", "$2a$10$.Wt/POpIALZJT6VmAdM3FOnCVbZ.CuDj4pGguz2q4VufkYFhCT9Qy", '1'); -- password = akuiosfb
INSERT INTO Tunnukset VALUES ("JummiEm", "$2a$10$uSSQfXvk1Lvxsdp92H2NM.2PO7uIuEmqEyzqVKsbXBlCMcOmie8Mu", '1'); -- password = asikosfgbao
INSERT INTO Tunnukset VALUES ("JammiEl", "$2a$10$ygWR/V8Qh/3mAN.QMhqztOGaW43IJmGnz4fEoU3rvTO7MXrCR.uqG", '1'); -- password = olduhiopsuasd


-- insert values into the table kysymykset
INSERT INTO Kysymykset VALUES (0, "Suomen pitää kiirehtiä rajoittamaan polttomoottoriautojen myyntiä ja käyttöä");
INSERT INTO Kysymykset VALUES (0, "Valtion pitää rajoittaa suomalaisten lihansyöntiä esimerkiksi vetotuksen avulla");
INSERT INTO Kysymykset VALUES (0, "Valtion menojen ja tulojen tasapainotus pitäisi tehdä karsimalla menoja, eikä kiristämällä verotusta");
INSERT INTO Kysymykset VALUES (0, "Suomi pärjäisi ilman euroa paremmin");
INSERT INTO Kysymykset VALUES (0, "Parantumattomasti sairaalla ja kärsivällä potilaalla on oltava oikeus eutanasiaan");
INSERT INTO Kysymykset VALUES (0, "Viinit ja vahvat oluet, sekä jotkin muut vahvemmat juomas pitää saada ruokakauppoihin");
INSERT INTO Kysymykset VALUES (0, "Maahanmuuttajien määrän kasvu on lisännyt turvattomuuden tunnessa Suomessa");
INSERT INTO Kysymykset VALUES (0, "Suomessa tarvitaan lisää työperäistä maahanmuuttoa");
INSERT INTO Kysymykset VALUES (0, "Nato-jäsenyys vahvistaisi Suomen turvallisuuspoliittista asemaa");
INSERT INTO Kysymykset VALUES (0, "Vihapuheen täytyy olla rangaistava teko rikoslaissa");
INSERT INTO Kysymykset VALUES (0, "On oikein, että yhteiskunnassa toiset ryhmät ovat paremmassa asemassa, kuin toiset");
INSERT INTO Kysymykset VALUES (0, "Poliitikon velvollisuus on ajaa omien äänestäjiensä etuja");

-- insert values into the puolue table
INSERT INTO Puolue VALUES (0, "Sosiaalidemokraatit", "Suomen Sosialidemokraattinen Puolue (lyhyemmin sosiaalidemokraatit)");
INSERT INTO Puolue VALUES (0, "Perussuomalaiset", "Suomalainen kansallismielinen ja EU-kriittinen puolue, joka tukeutuu kristillissosiaaliseen arvopohjaan");
INSERT INTO Puolue VALUES (0, "Kokoomus", "Keskusta-oikeistolainen yleispuolue");
INSERT INTO Puolue VALUES (0, "Keskusta", "Puolueen ideologiaa luonnehtivat kuuluminen poliittiseen keskustaan, alkiolaisuus ja sosiaaliliberalismi.");
INSERT INTO Puolue VALUES (0, "Vihreät", "Puolue näkee juuriensa olevan 1980-luvun alun vihreässä liikehdinnässä, jossa oli mukana ympäristöaktivisteja, feministejä, vammaisjärjestöjen edustajia sekä vaihtoehtokulttuurien ihmisiä");
INSERT INTO Puolue VALUES (0, "Vasemmistoliitto", "Tavoiteohjelmassaan vasemmistoliitto pyrkii parantamaan yhteiskunnan hyvinvointia, ympäristön ja talouden kestävyyttä, ihmisten välistä tasa-arvoa, vapautta ja mahdollisuutta työhön ja toimeentuloon");
INSERT INTO Puolue VALUES (0, "RKP", "Puolue, joka edustaa pääasiassa maan ruotsinkielistä väestönosaa eli suomenruotsalaisia.");
INSERT INTO Puolue VALUES (0, "Kristillisdemokraatit", "Suomalainen keskusta-oikeistolainen ja kristillisdemokraattinen puolue");

-- insert values into the ehdokkaat table
INSERT INTO Ehdokkaat VALUES (0, "Paavo", "Pesusieni", "Kristillisdemokraatit", "Merenalla", 34, "Pikaruoka työntekijä", "SpongeBob");
INSERT INTO Ehdokkaat VALUES (0, "Sanna", "Marin", "Sosiaalidemokraatit", "Tampere", 36, "Pääministeri", "MarinSa");
INSERT INTO Ehdokkaat VALUES (0, "Esa", "Laine", "Vihreät", "Akaa", 25, "Agrologi", "LaineEs");
INSERT INTO Ehdokkaat VALUES (0, "Jari", "Korhonen", "Vihreät", "Akaa", 30, "Astronautti", "KorhoJa");
INSERT INTO Ehdokkaat VALUES (0, "Pekka", "Virtanen", "Kokoomus", "Oulu", 22, "Työtön", "VirtaPe");
INSERT INTO Ehdokkaat VALUES (0, "Leila", "Järvinen", "RKP", "Oulu", 50, "Arkeologi", "JarviLe");
INSERT INTO Ehdokkaat VALUES (0, "Teppo", "Koskinen", "Kristillisdemokraatit", "Vaasa", 62, "Astronomi", "KoskiTe");
INSERT INTO Ehdokkaat VALUES (0, "Seppo", "Heikkinen", "Perussuomalaiset", "Helsinki", 64, "Laborantti", "HeikkiSe");
INSERT INTO Ehdokkaat VALUES (0, "Helena", "Koskinen", "Perussuomalaiset", "Vaasa", 53, "Leipuri", "KoskiHe");
INSERT INTO Ehdokkaat VALUES (0, "Liisa", "Mäkinen", "Kristillisdemokraatit", "Helsinki", 49, "Meedio", "MakinLi");
INSERT INTO Ehdokkaat VALUES (0, "Ilkka", "Mäkelä", "Sosiaalidemokraatit", "Turku", 44, "Metsuri", "MakelIl");
INSERT INTO Ehdokkaat VALUES (0, "Ismo", "Niemi", "Kokoomus", "Tampere", 71, "Postinjakaja", "NiemiIs");
INSERT INTO Ehdokkaat VALUES (0, "Jaana", "Paananen", "Vihreät", "Espoo", 66, "Pizzakuski", "PaanaJa");
INSERT INTO Ehdokkaat VALUES (0, "Kaisa", "Pääkkönen", "Vasemmistoliitto", "Turku", 29, "Patriarkka", "PaakkKa");
INSERT INTO Ehdokkaat VALUES (0, "Outi", "Laine", "Sosiaalidemokraatit", "Espoo", 38, "Räätäli", "LaineOu");
INSERT INTO Ehdokkaat VALUES (0, "Marja", "Kokko", "Sosiaalidemokraatit", "Helsinki", 48, "Apumekaanikko", "KokkoMa");
INSERT INTO Ehdokkaat VALUES (0, "Stefan", "Lindström", "RKP", "Maarianhamina", 44, "Rehtori", "LindsSt");
INSERT INTO Ehdokkaat VALUES (0, "Riikka", "Laitela", "RKP", "Rovaniemi", 28, "Tulkki", "LaiteRi");
INSERT INTO Ehdokkaat VALUES (0, "Matti", "Jormala", "Kristillisdemokraatit", "Rovaniemi", 67, "Toimitusjohtaja", "JormaMa");
INSERT INTO Ehdokkaat VALUES (0, "Matti", "Asdasd", "Sosiaalidemokraatit", "Tampere", 60, "Vanginvartija", "AsdasMa");
INSERT INTO Ehdokkaat VALUES (0, "Ismo", "9000", "Perussuomalaiset", "Helsinki", 59, "Vahtimestari", "9000Is");
INSERT INTO Ehdokkaat VALUES (0, "Pena", "Virtala", "Kokoomus", "Oulu", 53, "Äänittäjä", "VirtalPe");
INSERT INTO Ehdokkaat VALUES (0, "Pentti", "Yrjölä", "Kokoomus", "Vaasa", 53, "Yksityisetsivä", "YrjolPe");
INSERT INTO Ehdokkaat VALUES (0, "Elisa", "Matinpoika", "", "Akaa", 53, "Enologi", "MatinEl");
INSERT INTO Ehdokkaat VALUES (0, "Emilia", "Jummi", "Kristillisdemokraatit", "Maarianhamina", 53, "Floristi", "JummiEm");
INSERT INTO Ehdokkaat VALUES (0, "Ella", "Jammi", "Kokoomus", "Espoo", 53, "Geneetikko", "JammiEl");

-- insert values into the kunnat table
INSERT INTO Kunnat VALUES ("Tampere");
INSERT INTO Kunnat VALUES ("Akaa");
INSERT INTO Kunnat VALUES ("Oulu");
INSERT INTO Kunnat VALUES ("Merenalla");
INSERT INTO Kunnat VALUES ("Helsinki");
INSERT INTO Kunnat VALUES ("Vaasa");
INSERT INTO Kunnat VALUES ("Turku");
INSERT INTO Kunnat VALUES ("Espoo");
INSERT INTO Kunnat VALUES ("Maarianhamina");
INSERT INTO Kunnat VALUES ("Rovaniemi");


-- insert values into the Vastaukset table
INSERT INTO Vastaukset VALUES (2, 1,'1', "This is a test");
INSERT INTO Vastaukset VALUES (2, 2,'2', "This is a test2");
INSERT INTO Vastaukset VALUES (2, 3,'3', "This is a test3");
INSERT INTO Vastaukset VALUES (2, 4,'1', "This is a test4");
INSERT INTO Vastaukset VALUES (2, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (2, 6,'4', "This is a test6");
INSERT INTO Vastaukset VALUES (2, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (2, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (2, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (2, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (2, 11,'5', "This is a test11");
INSERT INTO Vastaukset VALUES (2, 12,'5', "This is a test12");
INSERT INTO Vastaukset VALUES (1, 1,'1', "asd");
INSERT INTO Vastaukset VALUES (1, 2,'1', "asdTasdast2");
INSERT INTO Vastaukset VALUES (1, 3,'1', "This is xcvxcvxcva test3");
INSERT INTO Vastaukset VALUES (1, 4,'1', "Thiasdasdasdasds is a test4");
INSERT INTO Vastaukset VALUES (1, 5,'1', "This is asdxcvxca test5");
INSERT INTO Vastaukset VALUES (1, 6,'1', "Thisasdasd is a test6");
INSERT INTO Vastaukset VALUES (1, 7,'1', "This iasdasasds a test7");
INSERT INTO Vastaukset VALUES (1, 8,'1', "This isasdas axcv test8");
INSERT INTO Vastaukset VALUES (1, 9,'1', "This is 5635dgsa test9");
INSERT INTO Vastaukset VALUES (1, 10,'4', "This i574564s a test10");
INSERT INTO Vastaukset VALUES (1, 11,'4', "This is a6ujhfg test11");
INSERT INTO Vastaukset VALUES (1, 12,'4', "This i45edfhs a test12");
INSERT INTO Vastaukset VALUES (3, 1,'5', "This is a test");
INSERT INTO Vastaukset VALUES (3, 2,'2', "This is a test2");
INSERT INTO Vastaukset VALUES (3, 3,'3', "This is a test3");
INSERT INTO Vastaukset VALUES (3, 4,'1', "This is a test4");
INSERT INTO Vastaukset VALUES (3, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (3, 6,'3', "This is a test6");
INSERT INTO Vastaukset VALUES (3, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (3, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (3, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (3, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (3, 11,'5', "This is a test11");
INSERT INTO Vastaukset VALUES (3, 12,'3', "This is a test12");
INSERT INTO Vastaukset VALUES (4, 1,'3', "This is a test");
INSERT INTO Vastaukset VALUES (4, 2,'3', "This is a test2");
INSERT INTO Vastaukset VALUES (4, 3,'3', "This is a test3");
INSERT INTO Vastaukset VALUES (4, 4,'1', "This is a test4");
INSERT INTO Vastaukset VALUES (4, 5,'3', "This is a test5");
INSERT INTO Vastaukset VALUES (4, 6,'3', "This is a test6");
INSERT INTO Vastaukset VALUES (4, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (4, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (4, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (4, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (4, 11,'5', "This is a test11");
INSERT INTO Vastaukset VALUES (4, 12,'1', "This is a test12");
INSERT INTO Vastaukset VALUES (5, 1,'1', "This is a test");
INSERT INTO Vastaukset VALUES (5, 2,'3', "This is a test2");
INSERT INTO Vastaukset VALUES (5, 3,'3', "This is a test3");
INSERT INTO Vastaukset VALUES (5, 4,'3', "This is a test4");
INSERT INTO Vastaukset VALUES (5, 5,'3', "This is a test5");
INSERT INTO Vastaukset VALUES (5, 6,'4', "This is a test6");
INSERT INTO Vastaukset VALUES (5, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (5, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (5, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (5, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (5, 11,'4', "This is a test11");
INSERT INTO Vastaukset VALUES (5, 12,'3', "This is a test12");
INSERT INTO Vastaukset VALUES (6, 1,'5', "This is a test");
INSERT INTO Vastaukset VALUES (6, 2,'5', "This is a test2");
INSERT INTO Vastaukset VALUES (6, 3,'5', "This is a test3");
INSERT INTO Vastaukset VALUES (6, 4,'5', "This is a test4");
INSERT INTO Vastaukset VALUES (6, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (6, 6,'5', "This is a test6");
INSERT INTO Vastaukset VALUES (6, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (6, 8,'1', "This is a test8");
INSERT INTO Vastaukset VALUES (6, 9,'1', "This is a test9");
INSERT INTO Vastaukset VALUES (6, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (6, 11,'1', "This is a test11");
INSERT INTO Vastaukset VALUES (6, 12,'1', "This is a test12");
INSERT INTO Vastaukset VALUES (7, 1,'1', "This is a test");
INSERT INTO Vastaukset VALUES (7, 2,'1', "This is a test2");
INSERT INTO Vastaukset VALUES (7, 3,'1', "This is a test3");
INSERT INTO Vastaukset VALUES (7, 4,'1', "This is a test4");
INSERT INTO Vastaukset VALUES (7, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (7, 6,'5', "This is a test6");
INSERT INTO Vastaukset VALUES (7, 7,'5', "This is a test7");
INSERT INTO Vastaukset VALUES (7, 8,'5', "This is a test8");
INSERT INTO Vastaukset VALUES (7, 9,'5', "This is a test9");
INSERT INTO Vastaukset VALUES (7, 10,'2', "This is a test10");
INSERT INTO Vastaukset VALUES (7, 11,'2', "This is a test11");
INSERT INTO Vastaukset VALUES (7, 12,'2', "This is a test12");
INSERT INTO Vastaukset VALUES (8, 1,'5', "This is a test");
INSERT INTO Vastaukset VALUES (8, 2,'5', "This is a test2");
INSERT INTO Vastaukset VALUES (8, 3,'5', "This is a test3");
INSERT INTO Vastaukset VALUES (8, 4,'5', "This is a test4");
INSERT INTO Vastaukset VALUES (8, 5,'1', "This is a test5");
INSERT INTO Vastaukset VALUES (8, 6,'1', "This is a test6");
INSERT INTO Vastaukset VALUES (8, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (8, 8,'1', "This is a test8");
INSERT INTO Vastaukset VALUES (8, 9,'1', "This is a test9");
INSERT INTO Vastaukset VALUES (8, 10,'3', "This is a test10");
INSERT INTO Vastaukset VALUES (8, 11,'3', "This is a test11");
INSERT INTO Vastaukset VALUES (8, 12,'3', "This is a test12");
INSERT INTO Vastaukset VALUES (9, 1,'2', "This is a test");
INSERT INTO Vastaukset VALUES (9, 2,'2', "This is a test2");
INSERT INTO Vastaukset VALUES (9, 3,'2', "This is a test3");
INSERT INTO Vastaukset VALUES (9, 4,'2', "This is a test4");
INSERT INTO Vastaukset VALUES (9, 5,'2', "This is a test5");
INSERT INTO Vastaukset VALUES (9, 6,'4', "This is a test6");
INSERT INTO Vastaukset VALUES (9, 7,'4', "This is a test7");
INSERT INTO Vastaukset VALUES (9, 8,'4', "This is a test8");
INSERT INTO Vastaukset VALUES (9, 9,'4', "This is a test9");
INSERT INTO Vastaukset VALUES (9, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (9, 11,'5', "This is a test11");
INSERT INTO Vastaukset VALUES (9, 12,'5', "This is a test12");
INSERT INTO Vastaukset VALUES (10, 1,'5', "This is a test");
INSERT INTO Vastaukset VALUES (10, 2,'5', "This is a test2");
INSERT INTO Vastaukset VALUES (10, 3,'5', "This is a test3");
INSERT INTO Vastaukset VALUES (10, 4,'5', "This is a test4");
INSERT INTO Vastaukset VALUES (10, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (10, 6,'5', "This is a test6");
INSERT INTO Vastaukset VALUES (10, 7,'5', "This is a test7");
INSERT INTO Vastaukset VALUES (10, 8,'5', "This is a test8");
INSERT INTO Vastaukset VALUES (10, 9,'5', "This is a test9");
INSERT INTO Vastaukset VALUES (10, 10,'5', "This is a test10");
INSERT INTO Vastaukset VALUES (10, 11,'5', "This is a test11");
INSERT INTO Vastaukset VALUES (10, 12,'5', "This is a test12");
INSERT INTO Vastaukset VALUES (11, 1,'4', "This is a test");
INSERT INTO Vastaukset VALUES (11, 2,'4', "This is a test2");
INSERT INTO Vastaukset VALUES (11, 3,'4', "This is a test3");
INSERT INTO Vastaukset VALUES (11, 4,'4', "This is a test4");
INSERT INTO Vastaukset VALUES (11, 5,'4', "This is a test5");
INSERT INTO Vastaukset VALUES (11, 6,'4', "This is a test6");
INSERT INTO Vastaukset VALUES (11, 7,'4', "This is a test7");
INSERT INTO Vastaukset VALUES (11, 8,'4', "This is a test8");
INSERT INTO Vastaukset VALUES (11, 9,'4', "This is a test9");
INSERT INTO Vastaukset VALUES (11, 10,'4', "This is a test10");
INSERT INTO Vastaukset VALUES (11, 11,'4', "This is a test11");
INSERT INTO Vastaukset VALUES (11, 12,'4', "This is a test12");
INSERT INTO Vastaukset VALUES (12, 1,'3', "This is a test");
INSERT INTO Vastaukset VALUES (12, 2,'3', "This is a test2");
INSERT INTO Vastaukset VALUES (12, 3,'3', "This is a test3");
INSERT INTO Vastaukset VALUES (12, 4,'3', "This is a test4");
INSERT INTO Vastaukset VALUES (12, 5,'3', "This is a test5");
INSERT INTO Vastaukset VALUES (12, 6,'3', "This is a test6");
INSERT INTO Vastaukset VALUES (12, 7,'3', "This is a test7");
INSERT INTO Vastaukset VALUES (12, 8,'3', "This is a test8");
INSERT INTO Vastaukset VALUES (12, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (12, 10,'3', "This is a test10");
INSERT INTO Vastaukset VALUES (12, 11,'3', "This is a test11");
INSERT INTO Vastaukset VALUES (12, 12,'3', "This is a test12");
INSERT INTO Vastaukset VALUES (13, 1,'2', "This is a test");
INSERT INTO Vastaukset VALUES (13, 2,'2', "This is a test2");
INSERT INTO Vastaukset VALUES (13, 3,'2', "This is a test3");
INSERT INTO Vastaukset VALUES (13, 4,'2', "This is a test4");
INSERT INTO Vastaukset VALUES (13, 5,'2', "This is a test5");
INSERT INTO Vastaukset VALUES (13, 6,'2', "This is a test6");
INSERT INTO Vastaukset VALUES (13, 7,'2', "This is a test7");
INSERT INTO Vastaukset VALUES (13, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (13, 9,'2', "This is a test9");
INSERT INTO Vastaukset VALUES (13, 10,'2', "This is a test10");
INSERT INTO Vastaukset VALUES (13, 11,'2', "This is a test11");
INSERT INTO Vastaukset VALUES (13, 12,'2', "This is a test12");
INSERT INTO Vastaukset VALUES (14, 1,'1', "This is a test");
INSERT INTO Vastaukset VALUES (14, 2,'1', "This is a test2");
INSERT INTO Vastaukset VALUES (14, 3,'1', "This is a test3");
INSERT INTO Vastaukset VALUES (14, 4,'1', "This is a test4");
INSERT INTO Vastaukset VALUES (14, 5,'1', "This is a test5");
INSERT INTO Vastaukset VALUES (14, 6,'1', "This is a test6");
INSERT INTO Vastaukset VALUES (14, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (14, 8,'1', "This is a test8");
INSERT INTO Vastaukset VALUES (14, 9,'1', "This is a test9");
INSERT INTO Vastaukset VALUES (14, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (14, 11,'1', "This is a test11");
INSERT INTO Vastaukset VALUES (14, 12,'1', "This is a test12");
INSERT INTO Vastaukset VALUES (15, 1,'4', "This is a test");
INSERT INTO Vastaukset VALUES (15, 2,'4', "This is a test2");
INSERT INTO Vastaukset VALUES (15, 3,'4', "This is a test3");
INSERT INTO Vastaukset VALUES (15, 4,'2', "This is a test4");
INSERT INTO Vastaukset VALUES (15, 5,'2', "This is a test5");
INSERT INTO Vastaukset VALUES (15, 6,'2', "This is a test6");
INSERT INTO Vastaukset VALUES (15, 7,'1', "This is a test7");
INSERT INTO Vastaukset VALUES (15, 8,'1', "This is a test8");
INSERT INTO Vastaukset VALUES (15, 9,'1', "This is a test9");
INSERT INTO Vastaukset VALUES (15, 10,'4', "This is a test10");
INSERT INTO Vastaukset VALUES (15, 11,'4', "This is a test11");
INSERT INTO Vastaukset VALUES (15, 12,'4', "This is a test12");
INSERT INTO Vastaukset VALUES (16, 1,'4', "This is a test");
INSERT INTO Vastaukset VALUES (16, 2,'4', "This is a test2");
INSERT INTO Vastaukset VALUES (16, 3,'4', "This is a test3");
INSERT INTO Vastaukset VALUES (16, 4,'3', "This is a test4");
INSERT INTO Vastaukset VALUES (16, 5,'3', "This is a test5");
INSERT INTO Vastaukset VALUES (16, 6,'3', "This is a test6");
INSERT INTO Vastaukset VALUES (16, 7,'2', "This is a test7");
INSERT INTO Vastaukset VALUES (16, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (16, 9,'2', "This is a test9");
INSERT INTO Vastaukset VALUES (16, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (16, 11,'1', "This is a test11");
INSERT INTO Vastaukset VALUES (16, 12,'1', "This is a test12");
INSERT INTO Vastaukset VALUES (17, 1,'1', "This is a test");
INSERT INTO Vastaukset VALUES (17, 2,'1', "This is a test2");
INSERT INTO Vastaukset VALUES (17, 3,'1', "This is a test3");
INSERT INTO Vastaukset VALUES (17, 4,'2', "This is a test4");
INSERT INTO Vastaukset VALUES (17, 5,'2', "This is a test5");
INSERT INTO Vastaukset VALUES (17, 6,'2', "This is a test6");
INSERT INTO Vastaukset VALUES (17, 7,'3', "This is a test7");
INSERT INTO Vastaukset VALUES (17, 8,'3', "This is a test8");
INSERT INTO Vastaukset VALUES (17, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (17, 10,'4', "This is a test10");
INSERT INTO Vastaukset VALUES (17, 11,'4', "This is a test11");
INSERT INTO Vastaukset VALUES (17, 12,'4', "This is a test12");
INSERT INTO Vastaukset VALUES (18, 1,'4', "This is a test");
INSERT INTO Vastaukset VALUES (18, 2,'4', "This is a test2");
INSERT INTO Vastaukset VALUES (18, 3,'4', "This is a test3");
INSERT INTO Vastaukset VALUES (18, 4,'4', "This is a test4");
INSERT INTO Vastaukset VALUES (18, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (18, 6,'4', "This is a test6");
INSERT INTO Vastaukset VALUES (18, 7,'2', "This is a test7");
INSERT INTO Vastaukset VALUES (18, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (18, 9,'2', "This is a test9");
INSERT INTO Vastaukset VALUES (18, 10,'3', "This is a test10");
INSERT INTO Vastaukset VALUES (18, 11,'3', "This is a test11");
INSERT INTO Vastaukset VALUES (18, 12,'3', "This is a test12");
INSERT INTO Vastaukset VALUES (19, 1,'1', "This is a test");
INSERT INTO Vastaukset VALUES (19, 2,'2', "This is a test2");
INSERT INTO Vastaukset VALUES (19, 3,'3', "This is a test3");
INSERT INTO Vastaukset VALUES (19, 4,'5', "This is a test4");
INSERT INTO Vastaukset VALUES (19, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (19, 6,'5', "This is a test6");
INSERT INTO Vastaukset VALUES (19, 7,'5', "This is a test7");
INSERT INTO Vastaukset VALUES (19, 8,'5', "This is a test8");
INSERT INTO Vastaukset VALUES (19, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (19, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (19, 11,'5', "This is a test11");
INSERT INTO Vastaukset VALUES (19, 12,'5', "This is a test12");
INSERT INTO Vastaukset VALUES (20, 1,'4', "This is a test");
INSERT INTO Vastaukset VALUES (20, 2,'4', "This is a test2");
INSERT INTO Vastaukset VALUES (20, 3,'4', "This is a test3");
INSERT INTO Vastaukset VALUES (20, 4,'1', "This is a test4");
INSERT INTO Vastaukset VALUES (20, 5,'5', "This is a test5");
INSERT INTO Vastaukset VALUES (20, 6,'4', "This is a test6");
INSERT INTO Vastaukset VALUES (20, 7,'4', "This is a test7");
INSERT INTO Vastaukset VALUES (20, 8,'2', "This is a test8");
INSERT INTO Vastaukset VALUES (20, 9,'3', "This is a test9");
INSERT INTO Vastaukset VALUES (20, 10,'1', "This is a test10");
INSERT INTO Vastaukset VALUES (20, 11,'2', "This is a test11");
INSERT INTO Vastaukset VALUES (20, 12,'5', "This is a test12");