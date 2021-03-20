drop view if exists DestinatiiCautate;
create view DestinatiiCautate as 
select destinatie,count(destinatie) as 'numar' from plecari group by destinatie order by count(destinatie) desc;
 select * from DestinatiiCautate;
 
 drop view if exists AfisareZile;
 create view AfisareZile as
 select distinct data_plecarii, count(data_plecarii) as Numar_Zboruri from plecari group by data_plecarii order by data_plecarii asc;
 
 select * from AfisareZile;
 
 
 drop view if exists Afisareore;
 create view Afisareore as
 select destinatie as Destinatia, ora_plecarii as Ora_Plecarii from plecari where ora_plecarii  between '00:00' and '06:00';
 
 select * from Afisareore;
 
  drop view if exists AfisareSosiriZi;
 create view AfisareSosiriZi as
 select de_la as De_la, ora_sosire as Ora_Sosire, data_sosire as Data_Sosirii from sosiri where ora_sosire between '07:00' and '23:00';
 
 select * from AfisareSosiriZi;
 
   drop view if exists AfisareSosiriNoapte;
 create view AfisareSosiriNoapte as
 select de_la as De_la, ora_sosire as Ora_Sosire, data_sosire as Data_Sosire from sosiri where ora_sosire between '00:00' and '07:00';
 
 select * from AfisareSosiriNoapte;