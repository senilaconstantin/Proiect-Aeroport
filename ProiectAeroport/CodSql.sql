Drop database if exists ProiectAeroport;
CREATE SCHEMA IF NOT EXISTS ProiectAeroport;
USE ProiectAeroport;

 create table if not exists Gestiune(
id_gestiune int auto_increment,
id_aeroport int,
idAeronava int,
constraint pk_Gestiune primary key nonclustered(id_gestiune,idAeronava)
);

Create table Aeroport(
id_aeroport int auto_increment,
Nume Varchar(45),
Locatie varchar(45),
tara varchar(45),
tip varchar(45),
nr_masini_disponibile int,
constraint PK_Aeroport primary key NONCLUSTERED(id_aeroport)
);
alter table Gestiune add
constraint fk_Gestiune_Aeroport foreign key(id_aeroport) references Aeroport(id_aeroport);

 
create table if not exists Admin_Aeroport(
idAdmin int auto_increment,
id_aeroport int,
Nume varchar(45),
prenume varchar(45),
Username varchar(45),
parola varchar(45),
constraint pk_Admin_Aeroport primary key nonclustered(idAdmin, id_aeroport)
);
 
 alter table Admin_Aeroport add
 constraint fk_admin_aeroport_aeroport1_idx foreign key(id_aeroport) references Aeroport(id_aeroport);
 
 
Create table Plecari(
id_plecare int auto_increment,
id_gestiune int,
destinatie varchar(45),
data_plecarii date,
ora_plecarii time,
zbor_direct varchar(45),
escala varchar(45),
pret_bilet int,
tip_plecare varchar(45),
capacitate int,
constraint pk_Plecari primary key NONCLUSTERED(id_plecare)
);
  create table Aeronava(
id_aeronava int primary key auto_increment,
companie varchar(45),
Nume varchar(45),
capacitate int,
an_fabricatie int
);
create table Personal(
id_personal int auto_increment,
id_aeronava int,
nume varchar(45),
prenume varchar(45),
varsta int,
data_angajarii date,
functie varchar(45),
constraint pf_Personal primary key nonclustered(id_personal)
);
 
alter table Personal add(
-- constraint fk_Aeroport_Personal foreign key(id_aeroport) references Aeroport(id_aeroport),
constraint fk_Personal_Aeronava foreign key(id_aeronava) references Aeronava(id_aeronava));
 
 
alter table Plecari add(
constraint FK_Gestiune_Plecari foreign key(id_gestiune) references Gestiune(id_gestiune));
 
 
 
alter table Gestiune add
constraint fk_Aeronava_Gestiune foreign key(idAeronava) references Aeronava(id_Aeronava);
 
create table if not exists User_Aeroport(
id_User int primary key auto_increment ,
Nume varchar(45),
Prenume varchar(45),
varsta int,
email varchar(45),
Telefon varchar(45),
masina_inchiriata varchar(45),
destinatie_rezervata varchar(45)
);
create table if not exists Rezervari(
id_Rezervare int auto_increment,
id_User int,
id_plecare int,
constraint PK_Rezervari primary key NONCLUSTERED(id_rezervare)
);
create table if not exists Bagaje(
id_Bagaj int auto_increment,
Greutate float,
id_rezervari int,
constraint pk_Bagaje primary key nonclustered(id_Bagaj)
);
 
alter table Bagaje add(
constraint fk_bagaje_rezervari foreign key(id_rezervari) references rezervari(id_rezervare));
 
alter table Rezervari add(
constraint FK_Rezervari_User_Aeroport foreign key(id_User) references User_aeroport(id_User),
constraint Fk_Plecari_Rezervari foreign key(id_plecare) references Plecari(id_plecare));
 
drop table if exists Sosiri;
create table Sosiri(
id_sosiri int primary key auto_increment,
id_gestiune int,
de_la varchar(45),
data_sosire date,
ora_sosire time);
 
 
alter table Sosiri add
constraint fk_Gestiune_Sosiri foreign key(id_gestiune) references Gestiune(id_gestiune);
 
create table if not exists Contact(
id_aeroport int,
mail varchar(45),
numar_telefon varchar(15),
mesaj text,
constraint pk_Contact primary key nonclustered(id_aeroport)
 
);
create table if not exists Masina(
idMasina int primary key auto_increment,
id_aeroport int,
nr_locuri int,
Marca varchar(45),
pret_zi int,
an_fabricatie int,
statuss varchar(45),
id_user varchar(45)
);
alter table Masina add(
constraint fk_Aeroport_Masina foreign key(id_aeroport) references Aeroport(id_aeroport) );
alter table Contact add 
constraint fk_Aeroport_Contact foreign key(id_aeroport) references Aeroport(id_aeroport);



-- Inserarile
insert into aeroport values
(1, 'Avram Iancu', 'Cluj-Napoca', 'Romania', 'international', 3);

insert into contact values 
(1, 'aeroportinternational@yahoo.com', '0364457',null);

insert into admin_aeroport values
 (1,1, 'Admin', 'Aeroport', 'admin', 'avramiancu');
 
 insert into masina values
 (1,1,5, 'Audi', 100, 2013, 'neinchiriat', null),
 (2,1,7, 'Ford', 60,2008,'neinchiriat', null),
 (3,1,5, 'BMW', 150, 2015,'neinchiriat', null);
 
 insert into aeronava values
 (1,'','',0,0),
(2, 'British Airlines','Boeing 707', 120, 2008),
(3, 'Tarom', 'Airbus 10', 80, 2005),
(4, 'Turkish Airlines','Antonotov', 300, 2020),
(5, 'Wizzair','Boeing 707', 240, 2018),
(6, 'Wizzair', 'Boeing 707', 140, 2019),
(7, 'Qatar', 'Airbus 100', 30, 2020),
(8,'ceva','altceva',50,2019);
insert into personal values
 (1, 2, 'Maria', 'Enescu', 20, '2020-06-06', 'stewardesa'),
(2, 3, 'Lucian', 'Echim', 34, '2016-10-06', 'pilot'),
(3, 7, 'Marian', 'Enescu', 25, '2018-06-29', 'steward'),
(4, 5, 'Andreea', 'Bucur', 45, '2008-03-18', 'pilot'),
(5, null, 'Antonia', 'Andreica', 55, '2008-03-28', 'casierita'),
(6, null, 'Constantin', 'Senila', 30, '2019-07-12', 'politist'),
(7, 2, 'Erik', 'Popescu', 24, '2020-07-10', 'pilot');

 insert into gestiune values
 (1,1,7),
 (2,1,2),
 (3,1,3),
 (4,1,4),
 (5,1,5),
 (6,1,6),
 (7,1,7),
 (8,1,2),
 (9,1,3),
 (10,1,4),
 (11,1,5),
 (12,1,6);
 

 
 insert into plecari values
(1, 2, 'Lisabona', '2020-12-30', '13:20','nu', 'Bucuresti',1000, 'intern',120),
(2, 3, 'Madrid', '2020-12-14', '13:45', 'da', null,2000, 'extern',80),
(3, 5, 'Roma', '2020-12-14', '15:40', 'da', null, 1400,'extern',300),
(4, 4, 'Tokyo', '2020-12-14', '18:00', 'nu', 'Paris',2890, 'extern',240),
(5, 1, 'Bucuresti', '2020-12-14', '20:30', 'da', null,400, 'intern',140),
(6, 6, 'Roma', '2020-12-15', '02:30', 'da', null, 2000, 'extern',140);

insert into sosiri values
(1, 7,'Frankfurt',  '2020-12-14', '04:30'),
(2, 8,'Dublin',  '2020-12-14', '07:45'),
(3,9,'Bucuresti',   '2020-12-14', '08:55'),
(4, 10,'Roma', '2020-12-14', '14:55'),
(5, 12,'Sofia', '2020-12-15', '00:30');

insert into user_aeroport values
(1, 'Popa', 'Mihaela',45,'popamihaela@gmail.com', '0745985830',null,'da'),
(2, 'Miron', 'Alina', 6,null,null,null,'da'),
 (3, 'Mateescu', 'Bogdan', 30,'mateescubogdan@gmail.com', '0745985876',null,'da');
 
  insert into rezervari values
 (1, 1, 1),
 (2, 2, 2);
 
 insert into bagaje values
 (1, 30, 1),
 (2,  40, 2);


 

 
 