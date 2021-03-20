use ProiectAeroport;

drop procedure if exists insert_user;
delimiter //
create procedure insert_user(Nume varchar(45), Prenume varchar(45), varsta int,email varchar(45), telefon varchar(45)) 
begin
insert into user_aeroport(Nume,Prenume, varsta,email, telefon) values (Nume, Prenume, varsta,email, telefon);
end //
delimiter ;
 
drop procedure if exists update_masini;
delimiter //
create procedure update_masini(iddMasina int)
begin
select max(id_user) into @idP from user_aeroport;
select concat(nume,' ',prenume) into @numeP from user_aeroport where id_user=@idP;
update Masina 
set statuss='inchiriat',
id_user=@numeP where idMasina=iddMasina;
end //
 delimiter ;
 
 
drop procedure if exists update_user;
delimiter //
create procedure update_user(denumire varchar(45))
begin
select max(id_user) into @idU from user_aeroport;
update user_aeroport set masina_inchiriata=denumire where id_user=@idU;
end //
delimiter ;

drop procedure if exists deleteUser;
delimiter //
create procedure deleteUser()
begin
	Delete from user_aeroport where user_aeroport.destinatie_rezervata is null and user_aeroport.masina_inchiriata is null;
end //
delimiter ;

drop procedure if exists update_user_bilet;
delimiter //
create procedure update_user_bilet(denumire varchar(45))
begin
	select max(id_user) into @idU from user_aeroport;
	update user_aeroport set destinatie_rezervata=denumire where id_user=@idU;
end //
delimiter ;



drop procedure if exists insert_rezervari;
delimiter //
create procedure insert_rezervari(idPlecare varchar(20))
begin
	set @idUser=(select max(user_aeroport.id_User) from user_aeroport);
    insert into Rezervari(id_User,id_plecare) values (@idUser,idPlecare);

end //
delimiter ;

drop procedure if exists schimbare_parola;
delimiter //
create procedure schimbare_parola(parolaveche varchar(45), poarolaNoua varchar(45))
begin
	set @pV=(select admin_aeroport.parola from admin_aeroport);
    if @pV = parolaveche then
		update admin_aeroport set parola=poarolaNoua;
    end if;

end//
delimiter ;

drop procedure if exists editContact;
delimiter //
create procedure editContact (email varchar(45), telefon varchar(45))
begin
	if email is not null and email!="" then
		update contact set mail=email;
	end if;
    if telefon is not null and telefon!=""  then
		update contact set numar_telefon=telefon;
	end if;
end//
delimiter ;

drop procedure if exists insert_masina;
delimiter //
create procedure insert_masina(locuri varchar(45),MarcaM varchar(45), pret varchar(45),anFabricatie varchar(45))
begin
	insert into masina(id_aeroport, nr_locuri,Marca,pret_zi,an_fabricatie, statuss) values (1, locuri, MarcaM, pret, anFabricatie, "neinchiriat");
end //
delimiter ;

drop procedure if exists deleteMasina;
delimiter //
create procedure deleteMasina(id int)
begin
	delete from masina where idMasina=id;
end //
delimiter ;

drop procedure if exists updateMasina;
delimiter //
create procedure updateMasina(id varchar(45), pretNou varchar(45))
begin
	update masina set pret_zi=pretNou where id=idMasina;
end //
delimiter ;

drop procedure if exists insertAeronava;
delimiter //
create procedure insertAeronava(comp varchar(45), num varchar(45), cap varchar(45), an varchar(45))
begin
	insert into aeronava (companie, Nume, capacitate, an_fabricatie) values (comp,num,cap,an);
end //
delimiter ;

drop procedure if exists deleteAeronava;
delimiter //
create procedure deleteAeronava(id varchar(45))
begin
	update gestiune set idAeronava=1 where gestiune.idAeronava=id;
    update personal set id_aeronava=1 where personal.id_aeronava=id;
	 delete from aeronava where aeronava.id_aeronava=id;
end //
delimiter ;

drop procedure if exists stergerePlecare;
delimiter //
create procedure stergerePlecare(id varchar(45))
begin
	
		-- set @i=(select gestiune.id_gestiune from gestiune, plecari where plecari.id_plecare=id and gestiune.id_gestiune=plecari.id_gestiune);
-- 		delete from gestiune where gestiune.id_gestiune=@i;
   
	delete from bagaje  where bagaje.id_Bagaj=id;
	delete from rezervari where rezervari.id_plecare=id;
    delete from plecari where plecari.id_plecare=id;
   
end //
delimiter ;

drop procedure if exists InsertGestiune;
delimiter //
create procedure InsertGestiune( aeronava int)
begin
insert into gestiune(id_aeroport, idAeronava ) values(1, aeronava);
end //
 delimiter ;
 
 
 
drop procedure if exists InsertPlecari;
delimiter //
create procedure InsertPlecari(destinatie varchar(45), data_plecarii date, ora_plecarii time, zbor_direct varchar(45), escala varchar(45), pret_bilet int , tip_plecare varchar(45))
begin
	select max(id_gestiune) into @idG from gestiune;
	insert into Plecari(id_gestiune, destinatie, data_plecarii, ora_plecarii, zbor_direct, escala, pret_bilet, tip_plecare) 
	values (@idG, destinatie, data_plecarii,ora_plecarii, zbor_direct, escala, pret_bilet, tip_plecare);
    
     select max(id_plecare) into @id from plecari;
 
	select a.capacitate into @c
	from aeronava a
	join gestiune g on a.id_aeronava=g.idAeronava
	join plecari p on g.id_gestiune=p.id_gestiune where p.id_plecare=@id;

	update plecari set capacitate=@c where id_plecare=@id;
 
end //
 delimiter ;
 
drop procedure if exists UpdatePlecari;
delimiter //
create procedure UpdatePlecari(id int, pret int)
begin
update plecari set pret_bilet=pret where id_plecare=id;

end //
 delimiter ;
 
 drop procedure if exists InsertPersonal;
delimiter //
create procedure InsertPersonal(id_aeronava int,nume varchar(45),prenume varchar(45),varsta int,data_angajarii date,functie varchar(45))
begin
insert into Personal(id_aeronava ,nume,prenume,varsta ,data_angajarii ,functie) values (id_aeronava ,nume,prenume,varsta ,data_angajarii ,functie);
end //
delimiter ;
 
drop procedure if exists DeletePersonal;
delimiter //
create procedure DeletePersonal( id int)
begin
	delete from Personal where id_personal=id;
end //
delimiter ;

drop procedure if exists insertBagaj;
delimiter //
create procedure insertBagaj(greu varchar(45))
begin
	set @id=(select max(id_Rezervare) from rezervari);
    insert into bagaje (Greutate,id_rezervari) values (greu,@id);
end //
delimiter ;
 
drop procedure if exists UpdatePersonal;
delimiter //
create procedure UpdatePersonal(id int, aeronava int)
begin
update Personal set id_aeronava=aeronava where id_personal=id;
end //
delimiter ;
 
 
 drop trigger if exists ActualizarePlecari;
delimiter //
create trigger ActualizarePlecari after insert on rezervari
for each row
begin
 
update plecari set capacitate=capacitate-1 where new.id_plecare=plecari.id_plecare;
end //
delimiter ;

drop trigger if exists ActualizareNrMasini;
delimiter //
create trigger ActualizareNrMasini after update on Masina
for each row
begin
 
update Aeroport set nr_masini_disponibile=nr_masini_disponibile-1;
end //
delimiter ;



drop trigger if exists Nrmasini;
delimiter //
create trigger Nrmasini after insert on masina
for each row
begin
update aeroport set nr_masini_disponibile=nr_masini_disponibile+1;

end
//
delimiter ;
