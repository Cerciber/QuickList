
use BaseDeDatosQuickList;
go
create trigger mensajeGuardar
on T_Informacion_Aprendices after
insert as begin 

	select 'Sus datos han sido guardados';

end;

go
create trigger mensajeBorrar
on T_Informacion_Aprendices after
delete as begin 

	select 'Sus datos han sido borrados';

end;


go
create trigger mensajeActualizar
on T_Informacion_Aprendices after
update as begin 

	select 'Sus datos han sido actualizados';

end;


go
create trigger noModificarNombre
on T_Informacion_Aprendices after
update as begin 

	if update(Nombre)
	begin
	
		print 'No se puede cambiar el nombre';
		ROLLBACK

	end;

end;

update T_Informacion_Aprendices set Nombre='Alfredo' where Documento_De_Identidad=7785885485;



go
create trigger noModificarApellido
on T_Informacion_Aprendices after
delete as begin 

	if update(Apellido)
	begin
	
		print 'No se puede cambiar el apellido';
		ROLLBACK

	end;

end;

update T_Informacion_Aprendices set Nombre='Alfredo' where Documento_De_Identidad=7785885485;


go
create trigger mensajeEnLugarDeGuardar
on T_Informacion_funcionarios instead of
insert as begin 

	select 'Este usuario no puede insetar registros';

end;

insert into T_Informacion_Funcionarios
values (8564566786,'7UH7YBHB','SARA','VARGAS','BOLIBAR','INSTRUCTOR','saraV_432@misena.edu.co',64523431,321786565654);

delete T_Informacion_Funcionarios where T_Informacion_Funcionarios.Documento_De_Identidad=8564566786;

go
create trigger mensajeEnLugarDeBorrar
on T_Informacion_funcionarios instead of
delete as begin 

	select 'Este usuario no puede borrar registros';

end;
