/*
 * PantallasAprendiz.java
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

import com.quicklist.FormAprendiz;
import com.quicklist.FormFuncionarios;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.PantallaUsuario;
import com.quicklist.ReportarExcusa;
import com.quicklist.clases.ActividadDeAprendizaje;
import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Competencia;
import com.quicklist.clases.Consulta;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Horario;
import com.quicklist.clases.Inasistencia;
import com.quicklist.clases.ResultadoDeAprendizaje;

/**
 *
 * @author pabloycesar
 */
public class PantallasAprendiz {

    int usuario;
    ResultSet resultado;

    public PantallasAprendiz(String tipo, JPanel panelContenedor, String nombreClase, String pantallaActual, String usuario, String[] ID, Statement declaracion) {

        if ("EditarMisDatos".equals(nombreClase)) {

            FormAprendiz p = new FormAprendiz(tipo, pantallaActual, nombreClase, usuario, Arreglo.agregar(ID, usuario), declaracion);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }

        if ("T_Aprendiz".equals(nombreClase)) {

            String[] menu = {"Asistencia", "Formato etapa lectiva"};
            String[] vinculo = {"Aprendiz.Asistencia", "Aprendiz.FormatoEtapaLectiva"};
            String retorno = "PantallaInicio";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, "Aprendiz", usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoInicial();

        }

        if ("Aprendiz".equals(nombreClase)) {

            String[] menu = {"Asistencia", "Formato etapa lectiva"};
            String[] vinculo = {"Aprendiz.Asistencia", "Aprendiz.FormatoEtapaLectiva"};
            String retorno = "PantallaInicio";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.Asistencia".equals(nombreClase)) {

            String[] menu = {"Asistencia por horario", "Buscar en plan de estudios"};
            String[] vinculo = {"Aprendiz.Asistencia.Horario", "Aprendiz.Asistencia.Competencia"};
            String retorno = "Aprendiz";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.Asistencia.Horario".equals(nombreClase)) {

            String[] nombreBotones = {"Formaciones"};
            String[] nombreIcono = {"Formaciones"};
            String[] columna = {"", "Instructor", "Dia semana", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
            String[] vinculo = {"Aprendiz.Asistencia.Horario.Descripcion"};
            String retorno = "Aprendiz.Asistencia";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Aprendices on T_Horario.ID_Ficha=T_Informacion_Aprendices.ID_Ficha join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad  ");
            String[] campos = {"ID_Horario", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Horario", "instructor", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)");
            c.condicion("T_Informacion_Aprendices.Documento_De_Identidad = " + usuario + " and Fecha_Inicio <= getDate() and Fecha_Fin >= getDate()");
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

        if ("Aprendiz.Asistencia.Horario.Descripcion".equals(nombreClase)) {

            String[] nombreBotones = {"Excusa"};
            String[] nombreIcono = {"Excusa"};
            String[] columna = {"", "Fecha", "Estado"};
            String[] vinculo = {"Aprendiz.Asistencia.Horario.Descripcion.ReportarExcusa"};
            String retorno = "Aprendiz.Asistencia.Horario";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Inasistencia join T_Formacion on T_Inasistencia.ID_Formacion = T_Formacion.ID_Formacion ");
            String[] campos = {"ID_Inasistencia", "Fecha", "CAST(CASE WHEN Estado_De_Inasistencia = 'CE' THEN 'Falla con excusa' WHEN Estado_De_Inasistencia = 'SE' THEN 'Falla sin excusa' WHEN Estado_De_Inasistencia = 'NO' THEN 'Asistió' END AS varchar )"};
            c.campos(campos);
            String[] alias = {"ID_Inasistencia", "Fecha", "Estado_De_Inasistencia"};
            c.alias(alias);
            c.columnaSeleccionada("Fecha");
            c.condicion("ID_Horario=" + ID[ID.length - 1] + " and ID_Aprendiz=" + usuario);
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

        if ("Aprendiz.Asistencia.Horario.Descripcion.ReportarExcusa".equals(nombreClase)) {

            new ReportarExcusa(declaracion, ID[ID.length - 1]).setVisible(true);
            new PantallasAprendiz(tipo, panelContenedor, "Aprendiz.Asistencia.Horario.Descripcion", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }

        if ("Aprendiz.Asistencia.Competencia".equals(nombreClase)) {

            String[] nombreBotones = {"Actividades de aprendizaje"};
            String[] nombreIcono = {"Actividades De Aprendizaje"};
            String[] columna = {"", "Competencia a desarrollar"};
            String[] vinculo = {"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje"};
            String retorno = "Aprendiz.Asistencia";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Competencias join T_Plan_De_Estudios on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios join T_Fichas on T_Fichas.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios join T_Informacion_Aprendices on T_Fichas.Numero_De_Ficha=T_Informacion_Aprendices.ID_Ficha ");
            String[] campos = {"ID_Competencia", "Competencia_A_Desarrollar"};
            c.campos(campos);
            String[] alias = {"ID_Competencia", "Competencia_A_Desarrollar"};
            c.alias(alias);
            c.columnaSeleccionada("Competencia_A_Desarrollar");
            c.condicion("Documento_De_Identidad = " + usuario);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje".equals(nombreClase)) {

            String[] nombreBotones = {"Resultado de aprendizaje"};
            String[] nombreIcono = {"Resultado De Aprendizaje"};
            String[] columna = {"", "Fase del proyecto", "Actividad de aprendizaje"};
            String[] vinculo = {"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje"};
            String retorno = "Aprendiz.Asistencia.Competencia";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Actividad_De_Aprendizaje");
            String[] campos = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("Fase_Del_Proyecto");
            c.condicion("ID_Competencia = " + ID[ID.length - 1]);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje".equals(nombreClase)) {

            String[] nombreBotones = {"Horario"};
            String[] nombreIcono = {"Horarios"};
            String[] columna = {"", "Resultado de aprendizaje"};
            String[] vinculo = {"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario"};
            String retorno = "Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Resultado_De_Aprendizaje");
            String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("Resultado_De_Aprendizaje");
            c.condicion("ID_Actividad_De_Aprendizaje = " + ID[ID.length - 1]);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario".equals(nombreClase)) {

            String[] nombreBotones = {"Formaciones"};
            String[] nombreIcono = {"Formaciones"};
            String[] columna = {"", "Instructor", "Dia semana", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
            String[] vinculo = {"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia"};
            String retorno = "Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad ");
            String[] campos = {"ID_Horario", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Horario", "instructor", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)");
            c.condicion("T_Horario.ID_Resultado_De_Aprendizaje = " + ID[ID.length - 1]);
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

        if ("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia".equals(nombreClase)) {

            String[] nombreBotones = {"Excusa"};
            String[] nombreIcono = {"Excusa"};
            String[] columna = {"", "Fecha", "Estado", "Justificación"};
            String[] vinculo = {"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia.ReportarExcusa"};
            String retorno = "Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Inasistencia join T_Formacion on T_Inasistencia.ID_Formacion = T_Formacion.ID_Formacion ");
            String[] campos = {"ID_Inasistencia", "Fecha", "CAST(CASE WHEN Estado_De_Inasistencia = 'CE' THEN 'Falla con excusa' WHEN Estado_De_Inasistencia = 'SE' THEN 'Falla sin excusa' WHEN Estado_De_Inasistencia = 'NO' THEN 'Asistió' END AS varchar )"};
            c.campos(campos);
            String[] alias = {"ID_Inasistencia", "Fecha", "Estado_De_Inasistencia"};
            c.alias(alias);
            c.columnaSeleccionada("Fecha");
            c.condicion("ID_Horario=" + ID[ID.length - 1] + " and ID_Aprendiz=" + usuario);
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

        if ("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia.ReportarExcusa".equals(nombreClase)) {

            new ReportarExcusa(declaracion, ID[ID.length - 1]).setVisible(true);
            new PantallasAprendiz(tipo, panelContenedor, "Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }

        if ("Aprendiz.FormatoEtapaLectiva".equals(nombreClase)) {

            String[] menu = {"Obtener formato en blanco", "Formato por instructor", "Formato por horario", "Buscar en plan de estudios"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.FormatoEnBlanco", "Aprendiz.FormatoEtapaLectiva.Instructor", "Aprendiz.FormatoEtapaLectiva.Horario", "Aprendiz.FormatoEtapaLectiva.Competencias"};
            String retorno = "Aprendiz";

            PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.FormatoEtapaLectiva.FormatoEnBlanco"
                .equals(nombreClase)) {

            new RFormatoDeEtapaLectiva().enBlanco();
            new PantallasAprendiz(tipo, panelContenedor, 
                    "Aprendiz.FormatoEtapaLectiva", nombreClase, usuario, 
                    Arreglo.quitar(ID), declaracion);

        }

        if ("Aprendiz.FormatoEtapaLectiva.Instructor".equals(nombreClase)) {

            String[] nombreBotones = {"Formato de etapa lectiva"};
            String[] nombreIcono = {"Formato"};
            String[] columna = {"", "Nombre", "Primer Apellido", "Segundo Apellido", "Cargo", "Correo electrónico", "Teléfono", "Celular"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.Instructor.Obtener"};
            String retorno = "Aprendiz.FormatoEtapaLectiva";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Informacion_Funcionarios join T_Horario on  T_Informacion_Funcionarios.Documento_De_Identidad=T_Horario.ID_Funcionario join T_Informacion_Aprendices on  T_Informacion_Aprendices.ID_Ficha=T_Horario.ID_Ficha ");
            String[] campos = {"T_Informacion_Funcionarios.Documento_De_Identidad", "T_Informacion_Funcionarios.Nombre", "T_Informacion_Funcionarios.Primer_Apellido", "T_Informacion_Funcionarios.Segundo_Apellido", "cargo", "T_Informacion_Funcionarios.Correo_Electronico", "T_Informacion_Funcionarios.Telefono_Fijo", "T_Informacion_Funcionarios.Telefono_Celular"};
            c.campos(campos);
            String[] alias = {"Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "cargo", "Correo_Electronico", "Telefono_Fijo", "Telefono_Celular"};
            c.alias(alias);
            c.columnaSeleccionada("T_Informacion_Funcionarios.Nombre");
            c.condicion("T_Informacion_Aprendices.Documento_De_Identidad = " + usuario);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.FormatoEtapaLectiva.Instructor.Obtener".equals(nombreClase)) {

            new RFormatoDeEtapaLectiva().porInstructor(declaracion, usuario, ID[ID.length - 1]);
            new PantallasAprendiz(tipo, panelContenedor, "Aprendiz.FormatoEtapaLectiva", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }

        if ("Aprendiz.FormatoEtapaLectiva.Horario".equals(nombreClase)) {

            String[] nombreBotones = {"Formato de etapa lectiva"};
            String[] nombreIcono = {"Formato"};
            String[] columna = {"", "Instructor", "Día", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.Horario.Obtener"};
            String retorno = "Aprendiz.FormatoEtapaLectiva";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Aprendices on T_Horario.ID_Ficha=T_Informacion_Aprendices.ID_Ficha join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad");
            String[] campos = {"ID_Horario", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Horario", "instructor", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)");
            c.condicion("T_Informacion_Aprendices.Documento_De_Identidad = " + usuario + " and Fecha_Inicio<=getDate() and Fecha_Fin>=getDate()");
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

        if ("Aprendiz.FormatoEtapaLectiva.Horario.Obtener"
                .equals(nombreClase)) {

            new RFormatoDeEtapaLectiva().porHorario(declaracion, usuario, 
                    ID[ID.length - 1]);
            new PantallasAprendiz(tipo, panelContenedor, 
                    "Aprendiz.FormatoEtapaLectiva", nombreClase, usuario, 
                    Arreglo.quitar(ID), declaracion);

        }

        if ("Aprendiz.FormatoEtapaLectiva.Competencias".equals(nombreClase)) {

            String[] nombreBotones = {"Actividades De aprendizaje"};
            String[] nombreIcono = {"Actividades De Aprendizaje"};
            String[] columna = {"", "Competencia a desarrollar"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje"};
            String retorno = "Aprendiz.FormatoEtapaLectiva";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Competencias join T_Plan_De_Estudios on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios join T_Fichas on T_Fichas.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios join T_Informacion_Aprendices on T_Fichas.Numero_De_Ficha=T_Informacion_Aprendices.ID_Ficha");
            String[] campos = {"ID_Competencia", "Competencia_A_Desarrollar"};
            c.campos(campos);
            String[] alias = {"ID_Competencia", "Competencia_A_Desarrollar"};
            c.alias(alias);
            c.columnaSeleccionada("Competencia_A_Desarrollar");
            c.condicion("Documento_De_Identidad = " + usuario);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje".equals(nombreClase)) {

            String[] nombreBotones = {"Resultado de aprendizaje"};
            String[] nombreIcono = {"Resultado De Aprendizaje"};
            String[] columna = {"", "Fase", "Actividad de aprendizaje"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje"};
            String retorno = "Aprendiz.FormatoEtapaLectiva.Competencias";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Actividad_De_Aprendizaje");
            String[] campos = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("Fase_Del_Proyecto");
            c.condicion("ID_Competencia = " + ID[ID.length - 1]);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje".equals(nombreClase)) {

            String[] nombreBotones = {"Horario"};
            String[] nombreIcono = {"Horarios"};
            String[] columna = {"", "Resultado de aprendizaje"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario"};
            String retorno = "Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Resultado_De_Aprendizaje");
            String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("Resultado_De_Aprendizaje");
            c.condicion("ID_Actividad_De_Aprendizaje = " + ID[ID.length - 1]);
            c.panelContenedor(panelContenedor);
            String menu[][] = c.ejecutarConsulta();

            PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
            panelContenedor.removeAll();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimientoSecuencial();

        }

        if ("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario".equals(nombreClase)) {

            String[] nombreBotones = {"Formato de etapa lectiva"};
            String[] nombreIcono = {"Formato"};
            String[] columna = {"", "Instructor", "Día", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
            String[] vinculo = {"Aprendiz.FormatoEtapaLectiva.Horario.Obtener"};
            String retorno = "Aprendiz.FormatoEtapaLectiva";

            Consulta c = new Consulta(declaracion);
            c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad");
            String[] campos = {"ID_Horario", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.campos(campos);
            String[] alias = {"ID_Horario", "instructor", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
            c.alias(alias);
            c.columnaSeleccionada("CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)");
            c.condicion("T_Horario.ID_Resultado_De_Aprendizaje = " + ID[ID.length - 1]);
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

        if ("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Obtener".equals(nombreClase)) {

            new RFormatoDeEtapaLectiva().porHorario(declaracion, usuario, ID[ID.length - 1]);
            new PantallasAprendiz(tipo, panelContenedor, "Aprendiz.FormatoEtapaLectiva", nombreClase, usuario, Arreglo.quitar(ID), declaracion);

        }
    }

}
