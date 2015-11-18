/*
 * PantallasInstructor.java
 *
 * version 1.0
 *
 * 05-Octubre-2015
 *
 * Copyright (c) 2014-2016 Cesar Torres, Andres Santana, Alejandra Sierra.
 * #750566 Analisis Y Desarrollo De Sistemas De Informacion (ADSI)
 * Servicio Nacional De Aprendizaje (SENA) Bogotá, Colombia
 * All rights reserved.
 *
 */
package com.quicklist.funciones;

import com.quicklist.AprobarActividades;
import com.quicklist.Confirmacion;
import com.quicklist.FormActividad;
import com.quicklist.FormAprendiz;
import com.quicklist.FormFormacion;
import com.quicklist.FormFuncionarios;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.PantallaUsuario;
import com.quicklist.TomarAsistencia;
import com.quicklist.clases.Actividad;
import com.quicklist.clases.Consulta;
import com.quicklist.clases.Ficha;
import com.quicklist.clases.Formacion;
import com.quicklist.clases.Horario;
import com.quicklist.clases.ResultadoDeAprendizaje;

/**
 *
 * @author pabloycesar
 */
public class PantallasInstructor {

    int usuario;
    ResultSet resultado;

    public PantallasInstructor(String tipo, JPanel panelContenedor, String nombreClase, String pantallaActual, String usuario, String[] ID, Statement declaracion) {

        if ("EditarMisDatos".equals(nombreClase)) {

            FormFuncionarios p = new FormFuncionarios(tipo, pantallaActual, nombreClase, usuario, Arreglo.agregar(ID, usuario), declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("T_Instructor".equals(nombreClase)) {

            String[] menu = {"Seleccionar ficha", "Ver horario"};
            String[] vinculo = {"Instructor.Fichas", "Instructor.Horario"};
            String retorno = "PantallaInicio";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, "Instructor", usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoInicial();

        }

        if ("Instructor".equals(nombreClase)) {

            String[] menu = {"Seleccionar ficha", "Ver horario"};
            String[] vinculo = {"Instructor.Fichas", "Instructor.Horario"};
            String retorno = "PantallaInicio";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas".equals(nombreClase)) {

            String[] nombreBotones = {"Ver ficha"};
            String[] nombreIcono = {"Ficha"};
            String[] columna = {"", "Ficha"};
            String[] vinculo = {"Instructor.Fichas.Menu"};
            String retorno = "Instructor";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Fichas join T_Horario on T_Fichas.Numero_De_Ficha=T_Horario.ID_Ficha");
            String[] campos = {"Numero_De_Ficha", "Numero_De_Ficha"};
            c.campos(campos);
            String[] alias = {"ID", "Numero_De_Ficha"};
            c.alias(alias);
            c.columnaSeleccionada("Numero_De_Ficha");
            c.condicion("ID_Funcionario = " + usuario);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Menu".equals(nombreClase)) {

            String[] menu = {"Horarios", "Reporte de aprendices"};
            String[] vinculo = {"Instructor.Fichas.Horario", "Instructor.Fichas.Reporte"};
            String retorno = "Instructor.Fichas";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Reporte".equals(nombreClase)) {

            new RAprendices().porFicha(declaracion, usuario, ID[ID.length - 1]);
            new PantallasInstructor(tipo, panelContenedor, "Instructor.Fichas.Menu", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }

        if ("Instructor.Fichas.Horario".equals(nombreClase)) {

            String[] nombreBotones = {"Ver horario"};
            String[] nombreIcono = {"Horarios"};
            String[] columna = {"", "Ficha", "Instructor", "Día", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu"};
            String retorno = "Instructor.Fichas.Menu";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad ");
            String[] campos = {"ID_Horario", "ID_Ficha", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Horario", "ID_Ficha", "instructor", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("ID_Ficha");
            c.condicion("ID_Ficha = " + ID[ID.length - 1] + " and ID_Funcionario = " + usuario + " and Fecha_Inicio<=getDate() and Fecha_Fin>=getDate()");
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();
            int[] nFechas = {6, 7};
            c.nFechas(nFechas);

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Horario.Menu".equals(nombreClase)) {

            String[] menu = {"Asistencia", "Actividades"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu.Asistencia", "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva"};
            String retorno = "Instructor.Fichas.Horario";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia".equals(nombreClase)) {

            String[] menu = {"Ver asistencias", "Tomar asistencia", "Reporte de asistencia"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu.Asistencia.Ver", "Instructor.Fichas.Horario.Menu.Asistencia.Ingresar", "Instructor.Fichas.Horario.Menu.Asistencia.Reporte"};
            String retorno = "Instructor.Fichas.Horario.Menu";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Reporte".equals(nombreClase)) {

            new RFormatoDeInasistencia().porTrimestre(declaracion, ID[ID.length - 1]);
            new PantallasInstructor(tipo, panelContenedor, "Instructor.Fichas.Horario.Menu.Asistencia", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ver".equals(nombreClase)) {

            String[] nombreBotones = {"Editar", "Borrar"};
            String[] nombreIcono = {"Editar", "Borrar"};
            String[] columna = {"", "", "Fecha"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar", "Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar"};
            String retorno = "Instructor.Fichas.Horario.Menu.Asistencia";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Formacion");
            String[] campos = {"ID_Formacion", "Fecha"};
            c.campos(campos);
            String[] alias = {"ID_Formacion", "Fecha"};
            c.alias(alias);
            c.columnaSeleccionada("Fecha");
            c.condicion("ID_Horario = " + ID[ID.length - 1]);
            c.panelContenedor(panelContenedor);
            int[] nFechas = {1};
            c.nFechas(nFechas);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.Asistencia.Ver";
            String vinculo = "Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar.siguiente";

            FormFormacion p = new FormFormacion(tipo, vinculo, retorno, nombreClase, usuario, ID, declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar.siguiente".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.Asistencia.Ver";
            String vinculo = "Instructor.Fichas.Horario.Menu.Asistencia.Ver";

            TomarAsistencia p = new TomarAsistencia(tipo, vinculo, retorno, nombreClase, usuario, ID, declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar".equals(nombreClase)) {

            String[] vinculo = {"Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar.Confirmado", "Instructor.Fichas.Horario.Menu.Asistencia.Ver"};
            String retorno = "Instructor.Fichas.Horario.Menu.Asistencia.Ver";
            String pregunta = "¿Desea eliminar la formación " + ID[ID.length - 1] + "?";

            Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar.Confirmado".equals(nombreClase)) {

            Formacion.BorrarPorID(declaracion, ID[ID.length - 1]);
            new PantallasInstructor(tipo, panelContenedor, "Instructor.Fichas.Horario.Menu.Asistencia.Ver", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ingresar".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.Asistencia";
            String vinculo = "Instructor.Fichas.Horario.Menu.Asistencia.Ingresar.Siguiente";

            FormFormacion p = new FormFormacion(tipo, vinculo, retorno, nombreClase, usuario, Arreglo.agregar(Arreglo.quitar(ID), "☺"), declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.Asistencia.Ingresar.Siguiente".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.Asistencia";
            String vinculo = "Instructor.Fichas.Horario.Menu.Asistencia.Ver";

            TomarAsistencia p = new TomarAsistencia(tipo, vinculo, retorno, nombreClase, usuario, (ID), declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva".equals(nombreClase)) {

            String[] menu = {"Ver actividades", "Ingresar actividad"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver", "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ingresar"};
            String retorno = "Instructor.Fichas.Horario.Menu";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver".equals(nombreClase)) {

            String[] nombreBotones = {"Editar", "Borrar", "Aprobar"};
            String[] nombreIcono = {"Editar", "Borrar", "Aprobar"};
            String[] columna = {"", "", "", "Nombre actividad", "Nombre evidencia", "Medio", "Tipo", "Fecha recolección de evidencia"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Editar", "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar", "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Aprobar"};
            String retorno = "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Actividades");
            String[] campos = {"ID_Actividad", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
            c.campos(campos);
            String[] alias = {"ID_Actividad", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
            c.alias(alias);
            c.columnaSeleccionada("Nombre_Actividad");
            c.condicion("ID_Horario = " + ID[ID.length - 1]);
            c.panelContenedor(panelContenedor);
            int[] nFechas = {5};
            c.nFechas(nFechas);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Editar".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";

            FormActividad p = new FormActividad(tipo, retorno, nombreClase, usuario, ID, declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar".equals(nombreClase)) {

            String[] vinculo = {"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado", "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver"};
            String retorno = "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";
            String pregunta = "¿Desea eliminar la actividad " + ID[ID.length - 1] + "?";

            Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado".equals(nombreClase)) {

            String[] vinculo = {"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado.Confirmado", "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver"};
            String retorno = "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";
            String pregunta = "Al eliminarla se borraran todos los datos ligados a esta actividad";

            Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

            Actividad.BorrarPorID(declaracion, ID[ID.length - 1]);
            new PantallasInstructor(tipo, panelContenedor, "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver", nombreClase, usuario, Arreglo.quitar(Arreglo.quitar(Arreglo.quitar(ID))), declaracion);

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ingresar".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva";

            FormActividad p = new FormActividad(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Aprobar".equals(nombreClase)) {

            String retorno = "Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";

            AprobarActividades p = new AprobarActividades(tipo, retorno, nombreClase, usuario, ID, declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("Instructor.Horario".equals(nombreClase)) {

            String[] nombreBotones = {"Ir al horario"};
            String[] nombreIcono = {"Horarios"};
            String[] columna = {"", "Ficha", "Día", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
            String[] vinculo = {"Instructor.Fichas.Horario.Menu"};
            String retorno = "Instructor";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad ");
            String[] campos = {"ID_Horario", "ID_Ficha", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Horario", "ID_Ficha", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("ID_Ficha");
            c.condicion("ID_Funcionario = " + usuario);
            c.panelContenedor(panelContenedor);
            int[] nFechas = {5, 6};
            c.nFechas(nFechas);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

    }

}
