
---------------------------------------------------------------------- QUICKLIST

	Use BaseDeDatosQuickList;

---------------------------------------------------------------------- Insertar Plan de Estudios

	execute sp_Insertar_PlanDeEstudios 'PE1','ANALISIS Y DESARROLLO DE SISTEMAS DE INFORMACION','102','18','TECNOLOGO';

			execute sp_Insertar_Competencias '1','ANALIZAR LOS REQUISITOS DEL CLIENTE PARA CONSTRUIR EL SISTEMA DE INFORMACION.';

					execute sp_Insertar_ActividadDeAprendizaje '2','ANALISIS','ELABORAR DIAGRAMAS DE CLASES.';

							execute sp_Insertar_ResultadoDeAprendizaje '3','REPRESENTA EL BOSQUEJO DE LA SOLUCIÓN AL PROBLEMA PRESENTADO POR EL CLIENTE, MEDIANTE LA ELABORACIÓN DE DIAGRAMAS DE CASOS DE USO, APOYADO EN EL ANÁLISIS DEL INFORME DE REQUERIMIENTOS, AL CONFRONTAR LA SITUACIÓN PROBLEMICA CON EL USUARIO SEGÚN NORMAS Y PROTOCOLOS DE LA ORGANIZACIÓN.';
					
					execute sp_Insertar_ActividadDeAprendizaje '2','ANALISIS','ELABORAR DIAGRAMAS DE CASOS DE USO.';
							
							execute sp_Insertar_ResultadoDeAprendizaje '13','INTERPRETAR LOS DIAGRAMAS DE CASO DE USO, DE OBJETOS, DE ESTADO, DE SECUENCIA, DE PAQUETES O COMPONENTES, DE DESPLIEGUE, DE COLABORACIÓN SEGÚN EL DISEÑO ENTREGADO';
					
					execute sp_Insertar_ActividadDeAprendizaje '2','ANALISIS','REALIZAR EL INFORME DE ANÁLISIS DEL SISTEMA QUE CUMPLA CON LOS REQUERIMIENTOS DE LA EMPRESA.';
							
							execute sp_Insertar_ResultadoDeAprendizaje '23','ELABORAR EL INFORME DE LOS RESULTADOS DEL ANÁLISIS DEL SISTEMA DE INFORMACIÓN, DE ACUERDO CON LOS REQUERIMIENTOS DEL CLIENTE SEGÚN NORMAS Y PROTOCOLOS ESTABLECIDOS.';
					
					execute sp_Insertar_ActividadDeAprendizaje '2','DISEÑO','ELABORAR BASES DE DATOS.';
							
							execute sp_Insertar_ResultadoDeAprendizaje '33','VALORAR LA INCIDENCIA DE LOS DATOS EN LOS PROCESOS DEL MACRO SISTEMA, TOMANDO COMO REFERENTE EL DICCIONARIO DE DATOS Y LAS MINI ESPECIFICACIONES, PARA LA CONSOLIDACIÓN DE LOS DATOS QUE INTERVIENEN, DE ACUERDO CON PARÁMETROS ESTABLECIDOS.';
							execute sp_Insertar_ResultadoDeAprendizaje '33','CONSTRUIR EL MODELO CONCEPTUAL DEL MACROSISTEMA FRENTE A LOS REQUERIMIENTOS DEL CLIENTE, MEDIANTE EL USO E INTERPRETACIÓN DE LA INFORMACIÓN LEVANTADA, REPRESENTADO EN DIAGRAMAS DE CLASE, DE INTERACCIÓN, COLABORACIÓN Y CONTRATOS DE OPERACIÓN, DE ACUERDO CON LAS DIFERENTES SECUENCIAS, FASES Y PROCEDIMIENTOS DEL SISTEMA.';
					
					execute sp_Insertar_ActividadDeAprendizaje '2','ANALISIS','REALIZAR ALGORITMOS UTILIZANDO ATRIBUTOS, OBJETOS, MÉTODOS';	
							
							execute sp_Insertar_ResultadoDeAprendizaje '43','IDENTIFICAR CADA UNO DE LOS CONCEPTOS Y PRINCIPIOS QUE CONSTITUYE LA PROGRAMACIÓN ORIENTADA A OBJETOS PARA INTREPRETAR EL DISEÑO';
					
					execute sp_Insertar_ActividadDeAprendizaje '2','ANALISIS','ELABORAR DIAGRAMAS DE TRANSICIÓN DE ESTADO.';
							
							execute sp_Insertar_ResultadoDeAprendizaje '53','CONSTRUIR EL MODELO CONCEPTUAL DEL MACROSISTEMA FRENTE A LOS REQUERIMIENTOS DEL CLIENTE, MEDIANTE EL USO E INTERPRETACIÓN DE LA INFORMACIÓN LEVANTADA, REPRESENTADO EN DIAGRAMAS DE CLASE, DE INTERACCIÓN, COLABORACIÓN Y CONTRATOS DE OPERACIÓN, DE ACUERDO CON LAS DIFERENTES SECUENCIAS, FASES Y PROCEDIMIENTOS DEL SISTEMA.';
					
					execute sp_Insertar_ActividadDeAprendizaje '2','ANALISIS','REALIZAR ALGORITMOS UTILIZANDO VARIABLES, CONSTANTES, VARIABLES, BUCLES,';
							
							execute sp_Insertar_ResultadoDeAprendizaje '63','ELABORAR EL INFORME DE LOS RESULTADOS DEL ANÁLISIS DEL SISTEMA DE INFORMACIÓN, DE ACUERDO CON LOS REQUERIMIENTOS DEL CLIENTE SEGÚN NORMAS Y PROTOCOLOS ESTABLECIDOS.';
			
			execute sp_Insertar_Competencias '1','PROMOVER LA INTERACCION IDONEA CONSIGO MISMO, CON LOS DEMAS Y CON LA NATURALEZA EN LOS CONTEXTOS LABORAL Y SOCIAL.';
					
					execute sp_Insertar_ActividadDeAprendizaje '12','ANALISIS','ARGUMENTAR Y ACOGER LOS CRITERIOS QUE CONTRIBUYEN A LA RESOLUCIÓN DE PROBLEMAS';
							
							execute sp_Insertar_ResultadoDeAprendizaje '73','ASUMIR LOS DEBERES Y DERECHOS CON BASE EN LAS LEYES Y LA NORMATIVA INSTITUCIONAL EN EL MARCO DE SU PROYECTO DE VIDA.';
					
					execute sp_Insertar_ActividadDeAprendizaje '12','ANALISIS','ESTABLECER PROCESOS COMUNICATIVOS ASERTIVOS QUE POSIBILITEN LA CONVIVENCIA EN LOS CONTEXTOS SOCIAL Y PRODUCTIVO';
							
							execute sp_Insertar_ResultadoDeAprendizaje '83','DESARROLLAR PROCESOS COMUNICATIVOS EFICACES Y ASERTIVOS DENTRO DE CRITERIOS DE RACIONALIDAD QUE POSIBILITEN LA CONVIVENCIA, EL ESTABLECIMIENTO DE ACUERDOS, LA CONSTRUCCION COLECTIVA DEL CONOCIMIENTO Y LA RESOLUCION DE PROBLEMAS DE CARACTER PRODUCTIVO Y SOCIAL.';
					
					execute sp_Insertar_ActividadDeAprendizaje '12','PLANEACION','REALIZAR LOS PROCESOS DE FABRICACIÓN, CUMPLIENDO CON LAS NORMAS DE SEGURIDAD INDUSTRIAL Y AMBIENTAL. (SALUD OCUPACIONAL)';
					execute sp_Insertar_ActividadDeAprendizaje '12','DISEÑO','ASUMIR RESPONSABLEMENTE LOS CRITERIOS DE PRESERVACIÓN Y CONSERVACIÓN DEL MEDIO AMBIENTE Y DE DESARROLLO SOSTENIBLE, EN EL EJERCICIO DE SU DESEMPEÑO LABORAL Y SOCIAL.';
					execute sp_Insertar_ActividadDeAprendizaje '12','DISEÑO','ACTIVIDAD FÍSICA: DEFINICIÓN ,CARACTERÍSTICAS, COMPONENTES, VENTAJAS. (CULTURA FÍSICA)';
			
			execute sp_Insertar_Competencias '1','ESPECIFICAR LOS REQUISITOS NECESARIOS PARA DESARROLLAR EL SISTEMA DE INFORMACION DE ACUERDO CON LAS NECESIDADES DEL CLIENTE.';
					
					execute sp_Insertar_ActividadDeAprendizaje '22','PLANEACION','ELABORAR EL INFORME DE REQUERIMIENTOS.';
							
							execute sp_Insertar_ResultadoDeAprendizaje '93','ELABORAR MAPAS DE PROCESOS QUE PERMITAN IDENTIFICAR LAS ÁREAS INVOLUCRADAS EN UN SISTEMA DE INFORMACIÓN, UTILIZANDO HERRAMIENTAS INFORMÁTICAS Y LAS TICS, PARA GENERAR INFORMES SEGÚN LAS NECESIDADES DE LA EMPRESA';
					
					execute sp_Insertar_ActividadDeAprendizaje '22','PLANEACION','DISEÑAR Y APLICAR INSTRUMENTOS PARA RECOLECTAR INFORMACIÓN.';
							
							execute sp_Insertar_ResultadoDeAprendizaje '103','APLICAR LAS TÉCNICAS DE RECOLECCIÓN DE DATOS , DISEÑANDO lOS INSTRUMENTOS NECESARIOS PARA EL PROCESAMIENTO DE INFORMACIÓN, DEACUERDO CON LA SITUACIÓN PLANTEADA POR LA EMPRESA.';
					
					execute sp_Insertar_ActividadDeAprendizaje '22','PLANEACION','IDENTIFICAR EL SOFTWARE DE LA EMPRESA';
							
							execute sp_Insertar_ResultadoDeAprendizaje '113','PLANTEAR DIFERENTES ALTERNATIVAS, DE MODELOS TECNOLÓGICOS DE INFORMACIÓN EMPRESARIAL, TENIENDO EN CUENTA LA PLATAFORMA TECNOLÓGICA DE LA EMPRESA Y LAS TENDENCIAS DEL MERCADO, PARA DAR SOLUCIÓN A LAS SITUACIONES RELACIONADAS CON EL MANEJO DE LA INFORMACIÓN DE LA ORGANIZACIÓN.';
			
			execute sp_Insertar_Competencias '1','PARTICIPAR EN EL PROCESO DE NEGOCIACIÓN DE TECNOLOGÍA INFORMÁTICA PARA PERMITIR LA IMPLEMENTACIÓN DEL SISTEMA DE INFORMACIÓN.';
					
					execute sp_Insertar_ActividadDeAprendizaje '32','DEFINICION','DIFERENCIAR LOS ELEMENTOS, CÓDIGOS, CONVENCIONES, UNIDADES DE MEDIDA Y SÍMBOLOS EMPLEADOS EN PLANOS';
					execute sp_Insertar_ActividadDeAprendizaje '32','PLANEACION','VERIFICAR ESPECIFICACIONES DE LAS HERRAMIENTAS INFORMÁTICAS, ASÍ COMO DIFERENCIAS DE CARÁCTER TÉCNICO Y DE ESTÁNDARES DE CUMPLIMIENTO.';
					execute sp_Insertar_ActividadDeAprendizaje '32','PLANEACION','DETERMINAR ESPECIFICACIONES TÉCNICAS Y CONDICIONES PARA LA LICITACIÓN.';
			
			execute sp_Insertar_Competencias '1','DISEÑAR EL SISTEMA DE ACUERDO CON LOS REQUISITOS DEL CLIENTE.';

					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','ELABORAR BASES DE DATOS.';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','DISEÑAR EL COMPORTAMIENTO DE LAS CLASES Y OBJETOS.';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','ELABORAR DIAGRAMA DE DISTRIBUCIÓN.';
					execute sp_Insertar_ActividadDeAprendizaje '42','ANALISIS','ELABORAR BASES DE DATOS.';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','DISEÑAR EL DIAGRAMA DE DISTRIBUCIÓN.';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','DISEÑAR LAS CLASES, LOS OBJETOS Y MECANISMOS DE COLABORACIÓN.';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','DISEÑAR LA ARQUITECTURA DEL SISTEMA';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','ELABORAR EL INFORME DE SELECCIÓN DE LAS HERRAMIENTAS PARA EL MONTAJE DEL SISTEMA';
					execute sp_Insertar_ActividadDeAprendizaje '42','DISEÑO','DISEÑAR LA INTERFAZ DEL SISTEMA DE INFORMACIÓN';

			execute sp_Insertar_Competencias '1','CONSTRUIR EL SISTEMA QUE CUMPLA CON LOS REQUISITOS DE LA SOLUCIÓN INFORMÁTICA.';
				
					execute sp_Insertar_ActividadDeAprendizaje '52','DESARROLLO','IDENTIFICAR EL ENTORNO DE TRABAJO DE LAS HERRAMIENTAS DE BASE DE DATOS YA SEA SQL SERVER U ORACLE';
					execute sp_Insertar_ActividadDeAprendizaje '52','DESARROLLO','IDENTIFICAR LAS FUNCIONES DE CADA UNA DE LAS HERRAMIENTAS DE LENGUAJE DE PROGRAMACIÓN. .NET Y JAVA)';
					execute sp_Insertar_ActividadDeAprendizaje '52','DESARROLLO','HABILIDADES PROCEDIMENTALES A NIVEL TECNOLÓGICO EL HACER TECNOLÓGICO EN CONTEXTO';
					execute sp_Insertar_ActividadDeAprendizaje '52','DESARROLLO','HABILIDADES METODOLÓGICAS, DE LA ACTUACIÓN CONSCIENTE Y REFLEXIVA DE LOS EJERCICIOS DE MONITOREO DE LO QUE SE HACE APRENDER A APRENDER,';
					execute sp_Insertar_ActividadDeAprendizaje '52','DESARROLLO','DESARROLLAR SISTEMAS DE INFORMACIÓN ENTORNO WEB Y CLIENTE SERVIDOR';
			
			execute sp_Insertar_Competencias '1','IMPLANTAR LA SOLUCION QUE CUMPLA CON LOS REQUISISTOS PARA SU OPERACIÓN.';
			execute sp_Insertar_Competencias '1','APLICAR BUENAS PRÁCTICAS DE CALIDAD EN EL PROCESO DE DESARROLLO DE SOFTWARE,DE ACUERDO CON EL REFERENTE ADOPTADO EN LA EMPRESA.';
			execute sp_Insertar_Competencias '1','COMPRENDER TEXTOS EN INGLÉS EN FORMA ESCRITA Y AUDITIVA.';
			execute sp_Insertar_Competencias '1','PRODUCIR TEXTOS EN INGLÉS EN FORMA ESCRITA Y ORAL.';
			execute sp_Insertar_Competencias '1','COMUNICACIÓN PARA PROGRAMAS DE TECNÓLOGO.';
	

---------------------------------------------------------------------- Insertar Funcionarios

	execute sp_Insertar_InformacionFuncionarios '2','2','CESAR','TORRES','ARDILA','INSTRUCTOR','pedroivañes-343@misena.edu.co','945345454','853542345345';
	execute sp_Insertar_InformacionFuncionarios '3','3','CESAR','TORRES','ARDILA','ADMINISTRADOR','pedroivañes-343@misena.edu.co','945345454','853542345345';
	execute sp_Insertar_InformacionFuncionarios '10224190922','123456789','CESAR','TORRES','ARDILA','INSTRUCTOR','pedroivañes-343@misena.edu.co','945345454','853542345345';
	execute sp_Insertar_InformacionFuncionarios '1022453456','3EYRH48','ALFREDO','JIMENES','CAICEDO','ADMINISTRADOR','AlfredJ3453@misena.edu.co','8596421','312456789585';
	execute sp_Insertar_InformacionFuncionarios '7656756756','345T4G8','LUIS','RESTREPO','VENAVIDES','INSTRUCTOR','LRVENAVIDES12@misena.edu.co','7656754','312534234235';
	execute sp_Insertar_InformacionFuncionarios '7653567675','56Y6Y56','ENRIQUE','OLARTE','CASTRO','INSTRUCTOR','enriguartg453@hotmail.com','7634551','323656456353';
	execute sp_Insertar_InformacionFuncionarios '8564566786','7UH7YBHB','SARA','VARGAS','BOLIBAR','INSTRUCTOR','saraV_432@misena.edu.co','64523431','321786565654';
	execute sp_Insertar_InformacionFuncionarios '3576868647','56YERGF8','CAMILO','JIMENES','VEGA','INSTRUCTOR','CamiloJimenes-45@gmail.com','9246524','31588285655';
	execute sp_Insertar_InformacionFuncionarios '2686456557','CVFDGGFG','LORENA','MENDEZ','RODRIGUES','INSTRUCTOR','lorenita66_5@misena.edu.co','6535454','320545423563';
	execute sp_Insertar_InformacionFuncionarios '3467687864','45645346','CATALINA','VENAVIDEZ','TORRES','INSTRUCTOR','cataVenavidez@hotmail.com','4531356','76742156555';
	execute sp_Insertar_InformacionFuncionarios '4234565764','56YR5T66','ALGEMIRO','CASTRO','CASTAÑEDA','INSTRUCTOR','algemiroCastro__454@misena.edu.co','7673561','32476745676';
	execute sp_Insertar_InformacionFuncionarios '23455757434','5RGTRGF','PABLO','MEJIA','CASAS','INSTRUCTOR','AlfredJ3453@gmail.com','8655345','27342565566';
	execute sp_Insertar_InformacionFuncionarios '65345346577','12RRFED','PEDRO','IVAÑES','PINZON','INSTRUCTOR','pedroivañes-343@misena.edu.co','945345454','853542345345';

---------------------------------------------------------------------- Insertar Fichas


	execute sp_Insertar_Fichas '750566','1','07/07/2014','07/07/2016';
			
			execute sp_Insertar_Aprendiz '1','1', 'CESAR','TORRES','ARDILA','04/09/1996','cesartorres-13@hotmail.com','Hombre','750566','8103044','3102112394','EN FORMACION','QUICKLIST','','','';
			execute sp_Insertar_Aprendiz '1022419092','123456789', 'CESAR','TORRES','ARDILA','04/09/1996','cesartorres-13@hotmail.com','Hombre','750566','8103044','3102112394','EN FORMACION','QUICKLIST','','','';
			execute sp_Insertar_Aprendiz '7785885485','5G5G5UR', 'PEDRO','CASAS','CUEVAS','08/03/1995','Pedrocuevas4545@hotmail.com','Hombre','750566','6545342','2376534545','EN FORMACION','PROYECTO1','','','';
			execute sp_Insertar_Aprendiz '8456345345','R6TUR56Y', 'CATERINE','RIVERA','BAUTISTA','04/10/1994','caterinerivera@misena.edu.co','Mujer','750566','7345455','7634523434','EN FORMACION','PROYECTO2','','','';
			execute sp_Insertar_Aprendiz '5673563455','5FG55TG5G', 'LEO','JIMENES','MERCADO','12/12/1996','leji_4ee@gmail.com','Hombre','750566','5423434','134646235234','EN FORMACION','PROYECTO3','','','';
			execute sp_Insertar_Aprendiz '1235795434','55G5HJ6HH', 'CRISTIAN','GARZON','OTAM','15/07/1990','cristin5445@hotmail.com','Hombre','750566','56323434','7524546556','EN FORMACION','PROYECTO4','','','';
			execute sp_Insertar_Aprendiz '453413574','4G44G4G4G', 'MARIA','HERNANDEZ','RODRIGUEZ','26/10/1996','mariri343@misena.edu.co','Mujer','750566','21344454','5635423434','EN FORMACION','PROYECTO5','','','';
			execute sp_Insertar_Aprendiz '9842345766','5RY5RYGTY', 'ALFREDO','CUEVAS','VELASQUEZ','20/01/1989','alfrecuevas-34@gmail.com','Hombre','750566','8564356','7653454565','EN FORMACION','PROYECTO6','','','';
			execute sp_Insertar_Aprendiz '43843843','56yh56y56', 'Fredde','Cortez','CAstro','20/01/1989','feredde-3e4@gmail.com','Hombre','750566','8564356','7653454565','EN FORMACION','PROYECTO6','','','';
			execute sp_Insertar_Aprendiz '434545843843','56yh56y56', 'Fredde','Cortez','CAstro','20/01/1989','feredde-3e4@gmail.com','Hombre','750566','8564356','7653454565','EN FORMACION','PROYECTO6','','','';

			execute sp_Insertar_Horario '750566','4','1022453456','LUNES','12:00','15:00','07/07/2014','31/08/2014','Aula 402 CIDCA - Cr 38 Otro';

					execute sp_Insertar_Actividades '5','Realizar 3 ejemplos de diagramas de casos de uso','3 ejemplos de diagramas de casos de uso','D','D','04/06/2015';
					execute sp_Insertar_Actividades '5','Ralizar los diagramas de casos de uso del proyecto',' diagramas de casos de uso del proyecto','D','P','07/10/2015';
					execute sp_Insertar_Actividades '5','Invertigar acerca de los elemtos del diagrama de casos de uso','Informe Casos de uso','F','C','15/11/2015';

			execute sp_Insertar_Horario '750566','14','1022453456','LUNES','15:00','18:00','07/07/2015','31/08/2016','Aula 402 CIDCA - Cr 38 Otro';
			
					execute sp_Insertar_Actividades '15','Matirz crud en PHP MySQL','Crear un pasos a paso de como crear y aplicar la matriz crud en PHP con MySQL','D','D','04/06/2015';
					
					execute sp_Insertar_Actividades '15','Realizar el informe de calidad del proyecto',' Informe de calidad','D','P','07/10/2015';

					execute sp_Insertar_Actividades '15','Investigar sobre las convenciones','Convenciones aplicadas al proyecto','F','C','15/11/2015';
			
			execute sp_Insertar_Horario '750566','24','7653567675','MARTES','13:00','18:00','07/07/2014','31/08/2014','Aula 402 CIDCA - Cr 38 Otro';
			
					execute sp_Insertar_Actividades '25','ACTIVIDAD4','EVIDENCIA 4','D','P','08/04/2014';

					execute sp_Insertar_Actividades '25','ACTIVIDAD5','EVIDENCIA 5','D','P','10/04/2014';

					execute sp_Insertar_Actividades '25','ACTIVIDAD6','EVIDENCIA 6','D','P','11/04/2014';

			execute sp_Insertar_Horario '750566','34','8564566786','MIERCOLES','12:00','18:00','07/07/2014','31/08/2014','Aula 308 CIDCA - Cr 38 Otro';
			
					execute sp_Insertar_Actividades '35','ACTIVIDAD7','EVIDENCIA 7','D','P','12/04/2014';				
					execute sp_Insertar_Actividades '35','ACTIVIDAD8','EVIDENCIA 8','D','P','12/04/2014';				
					execute sp_Insertar_Actividades '35','ACTIVIDAD9','EVIDENCIA 9','D','P','12/04/2014';
					execute sp_Insertar_Actividades '35','ACTIVIDAD10','EVIDENCIA 10','D','P','12/04/2014';
							
			execute sp_Insertar_Horario '750566','44','3576868647','JUEVES','12:00','18:00','07/07/2014','31/08/2014','Aula 308 CIDCA - Cr 38 Otro';
			
					execute sp_Insertar_Actividades '45','ACTIVIDAD11','EVIDENCIA 11','D','P','14/04/2014';
					execute sp_Insertar_Actividades '45','ACTIVIDAD12','EVIDENCIA 12','D','P','14/04/2014';
					execute sp_Insertar_Actividades '45','ACTIVIDAD13','EVIDENCIA 13','D','P','14/04/2014';
		
			execute sp_Insertar_Horario '750566','54','2686456557','VIERNES','12:00','15:00','07/07/2014','31/08/2014','Aula 402 CIDCA - Cr 38 Otro';
			execute sp_Insertar_Horario '750566','64','3467687864','VIERNES','15:00','18:00','07/07/2014','31/08/2014','Aula 402 CIDCA - Cr 38 Otro';
	
					execute sp_Insertar_Actividades '65','ACTIVIDAD14','EVIDENCIA 14','D','P','16/04/2014';		
					execute sp_Insertar_Actividades '65','ACTIVIDAD15','EVIDENCIA 15','D','P','16/04/2014';
					execute sp_Insertar_Actividades '65','ACTIVIDAD16','EVIDENCIA 16','D','P','16/04/2014';


	execute sp_Insertar_Fichas '750567','1','01/10/2014','01/10/2016';
	
			execute sp_Insertar_Aprendiz '2356899745','E4TG4RTTT', 'DAVID','CASALLAS','VISCONTI','01/03/1995','davidcasallas_13@gmail.com','M','750567','5634545','677456356','EN FORMACION','PROYECTO7','','','';
			execute sp_Insertar_Aprendiz '9934545578','45TS4T5S5T', 'ANDREA','RIVERA','CALDAS','14/11/1997','andra2rivera@hotmail.com','F','750567','34223434','767673545','EN FORMACION','PROYECTO8','','','';
			execute sp_Insertar_Aprendiz '4352435865','Y5Y5YT5R', 'JOSE','GUAYAMBUCO','AREVALO','03/03/1996','guayambucojose@misena.edu.co','M','750567','52345434','65234234565','RETIRO VOLUNTARIO','PROYECTO9','','','';
			execute sp_Insertar_Aprendiz '9734568645','R5YR5Y6YR', 'DANIEL','PAREDES','SIERRA','20/09/1996','sierraDP@hotmail.com','M','750567','5445454','8756356565','EN FORMACION','PROYECTO10','','','';
			execute sp_Insertar_Aprendiz '4545579898','R6TYHT6HY', 'JESUS','CORREA','CIFUENTES','16/02/1993','jesuscorrea123123@misena.edu.co','M','750567','1233234','93560756677','EN FORMACION','PROYECTO11','','','';


	execute sp_Insertar_Fichas '750568','1','19/01/2015','19/01/2017';

			execute sp_Insertar_Aprendiz '1843684547','TH6YT6YT6', 'DANIELA','PARRA','LOPEZ','30/08/1995','danielaparra34@gmail.com','F','750568','1764566','95345466745','EN FORMACION','PROYECTO12','','','';
			execute sp_Insertar_Aprendiz '9423466724','5R5RY6R6Y', 'PEDRO','QUIROGA','MERCHAN','23/05/1996','padrqiro4345@gmail.com','M','750568','6745234','565456564','EN FORMACION','PROYECTO13','','','';
			execute sp_Insertar_Aprendiz '6834548534','FGBFTGFTG', 'OSWALDO','FARIAS','YEPEZ','02/08/1994','oswaldofarias-234@hotmail.com','M','750568','23455556','7634524345','EN FORMACION','PROYECTO14','','','';
			execute sp_Insertar_Aprendiz '1842346523','T4YT56566', 'WENDY','LOPEZ','TORRES','04/01/1998','wendyLT_15@misena.edu.co','F','750568','84243545','389565443443','RETIRO VOLUNTARIO','PROYECTO15','','','';
			execute sp_Insertar_Aprendiz '1237765234','165Y6Y6YY', 'PAUL','DIAS','DIAS','05/10/1995','pauldiasdias@gmail.com','M','750568','7356767','945624676766','EN FORMACION','PROYECTO16','','','';

	
	
	