
---------------------------------------------------------------------- QUICKLIST

	Use BaseDeDatosQuickList;


---------------------------------------------------------------------- CREAR TABLAS

	--PE
	Create table T_Plan_De_Estudios( 

		ID_Plan_De_Estudios int primary key identity(1,10), 
		Nombre_PlanDeEstudios Varchar(100),
		Nombre_Programa Varchar(100), 
		Version_Plan_De_Estudios int, 
		Meses_Etapa_Lectiva int ,
		Nivel_De_Formacion Varchar(100)
	  
	) 

	--CO
	Create table T_Competencias( 

		ID_Competencia int primary key identity(2,10), 
		ID_Plan_De_Estudios int foreign key references T_Plan_De_Estudios (ID_Plan_De_Estudios),
		Competencia_A_Desarrollar varchar(500) not null 
	  
	)
	 
	--AA
	Create table T_Actividad_De_Aprendizaje( 

		ID_Actividad_De_Aprendizaje int primary key identity(3,10), 
		ID_Competencia int foreign key references T_Competencias (ID_Competencia),
		Fase_Del_Proyecto varchar(20) not null, 
		Actividad_De_Aprendizaje varchar(500) not null, 
	  
	)
	 
	--RA
	Create table T_Resultado_De_Aprendizaje( 

		ID_Resultado_De_Aprendizaje int primary key identity(4,10), 
		ID_Actividad_De_Aprendizaje int foreign key references T_Actividad_De_Aprendizaje (ID_Actividad_De_Aprendizaje),
		Resultado_De_Aprendizaje varchar(500) not null, 
	  
	)

	--FI
	Create table T_Fichas(
		
		Numero_De_Ficha int primary key, 
		ID_Plan_De_Estudios int foreign key references T_Plan_De_Estudios (ID_Plan_De_Estudios),
		Fecha_Inicio datetime not null, 
		Fecha_Fin datetime not null
		
	)

	--IF
	Create table T_Informacion_Funcionarios( 

		Documento_De_Identidad bigint primary key, 
		Contrasena Varchar(300), 
		Nombre Varchar(30) not null, 
		Primer_Apellido Varchar(30) not null, 
		Segundo_Apellido Varchar(30), 
		cargo Varchar(100),
		Correo_Electronico Varchar(100) not null, 
		Telefono_Fijo int, 
		Telefono_Celular bigint not null, 
		Foto image

	)

	--HO
	Create table T_Horario( 

		ID_Horario int primary key identity(5,10), 
		ID_Ficha int foreign key references T_Fichas (Numero_De_Ficha) on update cascade,
		ID_Resultado_De_Aprendizaje int foreign key references T_Resultado_De_Aprendizaje (ID_Resultado_De_Aprendizaje),
		ID_Funcionario bigint foreign key references T_Informacion_Funcionarios (Documento_De_Identidad) on update cascade,
		Dia_Semana Varchar(30), --Lunes, martes, mier......
		Hora_Inicio Varchar(30),
		Hora_Fin Varchar(30),
		Fecha_Inicio datetime not null,
		Fecha_Fin datetime not null,
		Lugar varchar(500)
	  
	)

	--IA
	Create table T_Informacion_Aprendices( 

		Documento_De_Identidad bigint primary key, 
		Contrasena Varchar(300), 
		Nombre Varchar(30) not null, 
		Primer_Apellido Varchar(30) not null, 
		Segundo_Apellido Varchar(30), 
		Fecha_De_Nacimiento datetime default 'Desconocido', 
		Correo_Electronico Varchar(100) not null, 
		Genero Varchar(10) default 'Desconocido', 
		ID_Ficha int foreign key references T_Fichas (Numero_De_Ficha) on update cascade,
		Telefono_Fijo int, 
		Telefono_Celular bigint not null, 
		estado Varchar(30), 
		nombre_Proyecto varchar(500), 
		Estilos_Y_Ritmos_De_Aprendizaje varchar(500), 
	    Estrategia_Metodológica_De_Preferencia varchar(500), 
	    Caracteristicas_Culturales_Y_Sociales varchar(500),
		Foto image
		

	)

	--AC 

	Create table T_Actividades( 

		ID_Actividad int primary key identity(6,10), 
		ID_Horario int foreign key references T_Horario (ID_Horario), 
		Nombre_Actividad Varchar(500) ,
		Nombre_Evidencia Varchar(500) ,
		Medio Varchar(10),
		Tipo Varchar(15),
		Fecha_RecoleccionEvidencia date
		
	) 

	--CA
	Create table T_EstadoActividad(
		
		ID_EstadoActividad int primary key identity(7,10), 
		ID_Actividad int foreign key references T_Actividades (ID_Actividad),
		ID_Aprendiz bigint foreign key references T_Informacion_Aprendices (Documento_De_Identidad) on update cascade,
		Autenticidad varchar(4),
		Calidad varchar(4),
		Pertinencia varchar(4),
		Vigencia varchar(4),
		LogroElAprendizaje varchar(4)
		
	)



	--CL
	Create table T_Formacion(
		
		ID_Formacion int primary key identity(8,10), 
		ID_Horario int foreign key references T_Horario (ID_Horario),
		Fecha datetime not null, 
		
	)

	--IN
	Create table T_Inasistencia( 

	    ID_Inasistencia int primary key identity(9,10),
		ID_Formacion int foreign key references T_Formacion (ID_Formacion),
		ID_Aprendiz bigint foreign key references T_Informacion_Aprendices (Documento_De_Identidad) on update cascade,
		Estado_De_Inasistencia Varchar(10) not null, --- CE (con escusa) y SE (sin escusa) 
		Justificacion_De_Inasistencia varchar(500) default 'No',
		Foto image,
		Descripcion varchar(500)

	)
	
	create table T_Log(
	
		ID_Log int primary key identity(10,10),
		ID_Funcionario bigint foreign key references T_Informacion_Funcionarios (Documento_De_Identidad) on update cascade,
		Fecha date,
		Accion varchar(300)
	
	)

---------------------------------------------------------------------- ELIMINAR TABLAS
/*
		if object_id('Informacion_Aprendices') is not null
		  drop table Informacion_Aprendices;

		if object_id('Informacion_Instructores') is not null
		  drop table Informacion_Instructores;

		if object_id('Informacion_Administradores') is not null
		  drop table Informacion_Administradores;

		if object_id('Informacion_Actividades') is not null
		  drop table Informacion_Actividades;

		if object_id('Informacion_Asistencia') is not null
		  drop table Informacion_Asistencia;

		if object_id('Plan_De_Estudios') is not null
		  drop table Plan_De_Estudios;
*/
