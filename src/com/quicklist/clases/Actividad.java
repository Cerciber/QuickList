/*
 * Actividad.java
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
import com.quicklist.funciones.Calendario;
import com.quicklist.funciones.ConvertirConsulta;

/**
 *
 * @author cesaraugusto
 */
public class Actividad {

    public static String[][] SeleccionarPorID(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Actividades where ID_Actividad = " + ID + ";");
            String[] campos = {"ID_Actividad", "ID_Horario", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 6);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorHorario(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Actividades where ID_Horario = " + ID + ";");
            String[] campos = {"ID_Actividad", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 5);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorInstrutorFicha(Statement declaracion, String usuario, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Actividades "
                    + "join T_Horario on T_Horario.ID_Horario=T_Actividades.ID_Horario "
                    + "where ID_Ficha = " + ID + " and ID_Funcionario= " + usuario + ";");
            String[] campos = {"ID_Actividad", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 5);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String SeleccionarUltimoRegistro(Statement declaracion) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select max(ID_Actividad) as Mayor from T_Actividades;");
            String[] campos = {"Mayor"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu[0][0];

    }

    public static void Insertar(Statement declaracion, String[] ID, String[] datos) {

        try {

            declaracion.executeQuery("insert into T_Actividades values("
                    + datos[0] + ",'"
                    + datos[1] + "','"
                    + datos[2] + "','"
                    + datos[3] + "','"
                    + datos[4] + "','"
                    + datos[5] + "');");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        EstadoActividad.InsertarEstadosDeActividad(declaracion, ID, datos);

    }

    public static String[][] BorrarPorInstructor(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorInstructor(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades from T_Horario "
                    + "               join T_Actividades on T_Horario.ID_Horario=T_Actividades.ID_Horario"
                    + "               where ID_Funcionario = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorFicha(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorFicha(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades from T_Actividades "
                    + "               join T_Horario on T_Horario.ID_Horario=T_Actividades.ID_Horario"
                    + "               where ID_Ficha = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorPlanDeEstudios(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorPlanDeEstudios(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades from T_Actividades "
                    + "               join T_Horario on T_Horario.ID_Horario=T_Actividades.ID_Horario "
                    + "               join T_Fichas on T_Horario.ID_Ficha=Numero_De_Ficha "
                    + "               where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorCompetencia(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorCompetencia(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades "
                    + "from T_Actividades "
                    + "join T_Horario "
                    + "on T_Actividades.ID_Horario=T_Horario.ID_Horario "
                    + "join T_Resultado_De_Aprendizaje "
                    + "on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                    + "join T_Actividad_De_Aprendizaje "
                    + "on T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
                    + "where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorActividadDeAprendizaje(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorActividadDeAprendizaje(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades "
                    + "from T_Actividades "
                    + "join T_Horario "
                    + "on T_Actividades.ID_Horario=T_Horario.ID_Horario "
                    + "join T_Resultado_De_Aprendizaje "
                    + "on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                    + "where ID_Actividad_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorResultadoDeAprendizaje(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorResultadoDeAprendizaje(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades "
                    + "from T_Actividades "
                    + "join T_Horario "
                    + "on T_Actividades.ID_Horario=T_Horario.ID_Horario "
                    + "where ID_Resultado_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorHorario(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorHorario(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades where ID_Horario = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static void ActualizarEnID(Statement declaracion, String[] datos, String ID) {

        try {

            declaracion.executeQuery("update T_Actividades set "
                    + "Nombre_Actividad = '" + datos[1] + "',"
                    + "Nombre_Evidencia = '" + datos[2] + "',"
                    + "Medio = '" + datos[3] + "',"
                    + "Tipo = '" + datos[4] + "',"
                    + "Fecha_RecoleccionEvidencia='" + datos[5] + "' "
                    + "where ID_Actividad= " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static String[][] BorrarPorID(Statement declaracion, String ID) {

        EstadoActividad.BorrarPorActividad(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Actividades where ID_Actividad = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }
}
