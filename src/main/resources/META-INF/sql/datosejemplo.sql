insert into Socio (dni,telefono,direccion,fechaNacimiento,email,nivel,nombre,apellidos,sexo,mediodePago,rol,password) values ('57463528H',645243946,'La calle de la patata','2002-05-05','hdgs@thd.com',3,'iouqyer','wertetr','Masculino','Visa','ADMINISTRADOR','1234');
insert into Socio (dni,telefono,direccion,fechaNacimiento,email,nivel,nombre,apellidos,sexo,mediodePago,rol,password) values ('75634245T',618263045,'La calle de la cebolla','1333-06-05','lkjha@thd.com',2,'adfasdf','iuywer','Femenino','Domiciliacion','SOCIO','1234');
insert into Socio (dni,telefono,direccion,fechaNacimiento,email,nivel,nombre,apellidos,sexo,mediodePago,rol,password) values ('74510463R',630125467,'La calle de la huevo','2002-03-03','kjhas@thd.com',1,'mnbxcx','kjhsdf','Masculino','PayPal','SOCIO','1234');
insert into Socio (dni,telefono,direccion,fechaNacimiento,email,nivel,nombre,apellidos,sexo,mediodePago,rol,password) values ('74635284F',674625162,'La calle olvidada','2001-02-04','sdfg@thd.com',4,'fhgjfghj','ryurtyu','Masculino','Visa','SOCIO','1234');

insert into Torneo (fecha,nombre,descripcion) values ('2023-03-03','Copa Piston','Torneo benéfico en honor a la UJA');
insert into Torneo (fecha,nombre,descripcion) values ('2021-04-22','Copa Spring','Torneo de gama baja');
insert into Torneo (fecha,nombre,descripcion) values ('2022-07-15','Copa Balsas','Torneo dedicado al profesor');
insert into Torneo (fecha,nombre,descripcion) values ('2023-02-10','Copa UJA','Torneo organizado por la UJA');

insert into Pista (tipo,fecha,hora) values ('Cesped',CURRENT_DATE,CURRENT_TIME);
insert into Pista (tipo,fecha,hora) values ('Tierra Batida',CURRENT_DATE,CURRENT_TIME);
insert into Pista (tipo,fecha,hora) values ('Cemento',CURRENT_DATE,CURRENT_TIME);
insert into Pista (tipo,fecha,hora) values ('Mixta',CURRENT_DATE,CURRENT_TIME);

insert into Inscribe(idSocio,idTorneo) values (2,1);
insert into Inscribe(idSocio,idTorneo) values (2,2);
insert into Inscribe(idSocio,idTorneo) values (2,3);
insert into Inscribe(idSocio,idTorneo) values (3,3);
insert into Inscribe(idSocio,idTorneo) values (3,4);
insert into Inscribe(idSocio,idTorneo) values (3,5);


insert into Reserva(horaIni,horaFin,fecha,idSocio,idPista) values ('08:00','10:00',CURRENT_DATE,2,1);
insert into Reserva(horaIni,horaFin,fecha,idSocio,idPista) values ('10:00','12:00',CURRENT_DATE,2,1);
insert into Reserva(horaIni,horaFin,fecha,idSocio,idPista) values ('12:00','14:00',CURRENT_DATE,3,1);
insert into Reserva(horaIni,horaFin,fecha,idSocio,idPista) values ('16:00','18:00',CURRENT_DATE,4,1);


