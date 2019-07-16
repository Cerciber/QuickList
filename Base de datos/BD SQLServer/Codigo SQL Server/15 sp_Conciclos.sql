
	use BaseDeDatosQuickList;

	drop procedure ##sp_IniciarSesion;

---------------------------------------------INICIAR SESION

	go
	create proc ##sp_IniciarSesion (
	
		@Usuario bigint, 
		@Contrasena varchar(30), 
		@Tipo varchar(30)
	
	) as begin

		declare @num int;
		
		if @Tipo='Aprendiz'begin
			select @num = count(*) from T_Informacion_Aprendices where Documento_De_Identidad=@usuario and Contrasena=@Contrasena;
		end;

		if @Tipo='Instructor'begin
			select @num = count(*) from T_Informacion_Funcionarios where cargo='INSTRUCTOR' and Documento_De_Identidad=@usuario and Contrasena=@Contrasena;
		end;

		if @Tipo='Administrador'begin
			select @num = count(*) from T_Informacion_Funcionarios where cargo='ADMINISTRADOR' and  Documento_De_Identidad=@usuario and Contrasena=@Contrasena;
		end;

		if @num = 1 begin
			select 'Bienvenido' as mensaje;
		end; else 
			select  'Usuario Incorrecto' as mensaje;

	end;

	exec ##sp_IniciarSesion '10224190922','123456789','Instructor';


---------------------------------------------APROBAR APRENDICES

	go
	create proc ##sp_EstudiantesEnFormacion (
	
		@ficha int
	
	) as begin

		while (select T_Informacion_Aprendices.estado from T_Informacion_Aprendices) ='EN FORMACION'
		
		select T_Informacion_Aprendices.estado from T_Informacion_Aprendices;

	end;


