
-----Registros cesar torres

	use BaseDeDatosQuickList;

---------------------------------------------------------------------- Seleccionar actividades pendientes

	select T_Actividades.ID_Actividad,T_Actividades.Titulo_Actividad 
	from T_Actividades
	join T_Calificaciones on T_Actividades.ID_Actividad= T_Calificaciones.ID_Actividad
	join T_Informacion_Aprendices on T_Informacion_Aprendices.Documento_De_Identidad=T_Calificaciones.ID_Aprendiz;
	


---------------------------------------------------------------------- Seleccionar si la actividad especifcada esta calificada (1 si 0 no)

	select count(*)
	from T_Actividades 
	join T_Calificaciones on T_Actividades.ID_Actividad='PE1CO1AA1RA1HO1AC1' and T_Calificaciones.ID_Actividad='PE1CO1AA1RA1HO1AC1'
	where T_Calificaciones.ID_Aprendiz = '1022419092';

---------------------------------------------------------------------- Seleccionar descripcion de una actividad

	select Titulo_Actividad,Descripcion_Actividad,Fecha_De_Entrega,Valor_Maximo_Actividad
	from T_Actividades 
	where T_Actividades.ID_Actividad='PE1CO1AA1RA1HO1AC1';

---------------------------------------------------------------------- Seleccionar descripcion de una actividad calificada

	select Titulo_Actividad,Descripcion_Actividad,Fecha_De_Entrega,Valor_Maximo_Actividad,Calificacion,Observaciones
	from T_Actividades 
	join T_Calificaciones on T_Actividades.ID_Actividad='PE1CO1AA1RA1HO1AC1' and T_Calificaciones.ID_Actividad='PE1CO1AA1RA1HO1AC1'
	where T_Calificaciones.ID_Aprendiz = '1022419092';
