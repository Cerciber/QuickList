/*
 * EstadoActividad.java
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
package com.quicklist.clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.quicklist.funciones.ConvertirConsulta;

/**
 *
 * @author cesaraugusto
 */
public class EstadoActividad {

    public static String[][] SeleccionarEstadosDeActividad(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("Select * from T_Informacion_Aprendices join T_EstadoActividad on ID_Aprendiz = Documento_De_Identidad where ID_Actividad=" + ID);
            String[] campos = {"ID_EstadoActividad", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "Autenticidad", "Calidad", "Pertinencia", "Vigencia", "LogroElAprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorInstructor(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad from T_Horario "
                    + "               join T_Actividades on T_Horario.ID_Horario=T_Actividades.ID_Horario"
                    + "               join T_EstadoActividad on T_EstadoActividad.ID_Actividad=T_Actividades.ID_Actividad"
                    + "               where ID_Funcionario = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorAprendiz(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad "
                    + "               where ID_Aprendiz = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorFicha(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad from T_EstadoActividad"
                    + "               join T_Informacion_Aprendices on T_EstadoActividad.ID_Aprendiz=T_Informacion_Aprendices.Documento_De_Identidad"
                    + "               where ID_Ficha = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorPlanDeEstudios(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad "
                    + "from T_EstadoActividad "
                    + "join T_Informacion_Aprendices "
                    + "on Documento_De_Identidad=ID_Aprendiz "
                    + "join T_Fichas "
                    + "on Numero_De_Ficha=ID_Ficha "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorCompetencia(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad "
                    + "from T_EstadoActividad "
                    + "join T_Informacion_Aprendices "
                    + "on Documento_De_Identidad=ID_Aprendiz "
                    + "join T_Fichas "
                    + "on Numero_De_Ficha=ID_Ficha "
                    + "join T_Plan_De_Estudios "
                    + "on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
                    + "join T_Competencias "
                    + "on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorActividadDeAprendizaje(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad "
                    + "from T_EstadoActividad "
                    + "join T_Informacion_Aprendices "
                    + "on Documento_De_Identidad=ID_Aprendiz "
                    + "join T_Fichas "
                    + "on Numero_De_Ficha=ID_Ficha "
                    + "join T_Plan_De_Estudios "
                    + "on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
                    + "join T_Competencias "
                    + "on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "join T_Actividad_De_Aprendizaje "
                    + "on T_Actividad_De_Aprendizaje.ID_Competencia=T_Competencias.ID_Competencia "
                    + "where ID_Actividad_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorResultadoDeAprendizaje(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad "
                    + "from T_EstadoActividad "
                    + "join T_Informacion_Aprendices "
                    + "on Documento_De_Identidad=ID_Aprendiz "
                    + "join T_Fichas "
                    + "on Numero_De_Ficha=ID_Ficha "
                    + "join T_Plan_De_Estudios "
                    + "on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
                    + "join T_Competencias "
                    + "on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "join T_Actividad_De_Aprendizaje "
                    + "on T_Actividad_De_Aprendizaje.ID_Competencia=T_Competencias.ID_Competencia "
                    + "join T_Resultado_De_Aprendizaje "
                    + "on T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
                    + "where ID_Resultado_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorHorario(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad from T_EstadoActividad "
                    + "               join T_Actividades on T_EstadoActividad.ID_Actividad=T_Actividades.ID_Actividad"
                    + "               where ID_Horario = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorActividad(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_EstadoActividad where ID_Actividad = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] InsertarEstadosDeActividad(Statement declaracion, String[] ID, String[] datos) {

        String[][] menu = null;

        String actividad = Actividad.SeleccionarUltimoRegistro(declaracion);

        menu = Aprendiz.SeleccionarPorFicha(declaracion, ID[ID.length - 5]);

        for (int i = 0; i <= menu.length - 1; i++) {
            try {

                declaracion.executeQuery("insert into T_EstadoActividad values("
                        + actividad + ","
                        + menu[i][0] + ",'"
                        + "No','"
                        + "No','"
                        + "No','"
                        + "No','"
                        + "No');");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }

        return menu;
    }

    public static String[][] ActualizarEstadosDeActividad(Statement declaracion, String[][] datos) {

        String[][] menu = null;

        for (int i = 0; i <= datos.length - 1; i++) {
            try {

                declaracion.executeQuery("update T_EstadoActividad set Autenticidad='" + datos[i][0] + "',Calidad='" + datos[i][1] + "',Pertinencia='" + datos[i][2] + "',Vigencia='" + datos[i][3] + "',LogroElAprendizaje='" + datos[i][4] + "' where ID_EstadoActividad=" + datos[i][5]);
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }

        return menu;
    }

}
