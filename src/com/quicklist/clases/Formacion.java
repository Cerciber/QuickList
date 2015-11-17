/*
 * Formacion.java
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
public class Formacion {

    public static boolean VerificarFecha(Statement declaracion, String fecha, String horario) {

        boolean existente = false;

        try {

            ResultSet resultado = declaracion.executeQuery("select distinct Fecha from [dbo].[T_Horario] \n"
                    + "join [dbo].[T_Formacion] on [dbo].[T_Horario].ID_Horario = [dbo].[T_Formacion].ID_Horario\n"
                    + "where [dbo].[T_Formacion].Fecha = '" + fecha + "' and [dbo].[T_Horario].ID_Horario='" + horario + "';");

            if (new ConvertirConsulta().NRegistros(resultado) == 1) {

                existente = true;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return existente;
    }

    public static String[][] SeleccionarPorID(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Formacion where ID_Formacion = " + ID + ";");
            String[] campos = {"ID_Formacion", "ID_Horario", "Fecha"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 2);

        } catch (SQLException ex) {
        }

        return menu;

    }

    public static String[][] SeleccionarPorHorario(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Formacion where ID_Horario = " + ID + ";");
            String[] campos = {"ID_Formacion", "Fecha"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 1);

        } catch (SQLException ex) {
        }

        return menu;

    }

    public static String SeleccionarUltimoRegistro(Statement declaracion) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select max(ID_Formacion) as Mayor from T_Formacion;");
            String[] campos = {"Mayor"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
        }

        return menu[0][0];

    }

    public static String[][] BorrarPorInstructor(Statement declaracion, String ID) {

        Inasistencia.BorrarPorInstructor(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion from T_Horario "
                    + "               join T_Formacion on T_Horario.ID_Horario = T_Formacion.ID_Horario"
                    + "               where ID_Funcionario = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorFicha(Statement declaracion, String ID) {

        Inasistencia.BorrarPorFicha(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion from T_Horario "
                    + "               join T_Formacion on T_Horario.ID_Horario = T_Formacion.ID_Horario"
                    + "               where ID_Ficha = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorPlanDeEstudios(Statement declaracion, String ID) {

        Inasistencia.BorrarPorPlanDeEstudios(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion from T_Formacion "
                    + "               join T_Horario on T_Horario.ID_Horario=T_Formacion.ID_Horario "
                    + "               join T_Fichas on T_Horario.ID_Ficha=Numero_De_Ficha "
                    + "               where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorCompetencia(Statement declaracion, String ID) {

        Inasistencia.BorrarPorCompetencia(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion from T_Formacion "
                    + "               join T_Horario on T_Horario.ID_Horario=T_Formacion.ID_Horario "
                    + "               join T_Fichas on T_Horario.ID_Ficha=Numero_De_Ficha "
                    + "               join T_Plan_De_Estudios "
                    + "               on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
                    + "               join T_Competencias "
                    + "               on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "               where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorActividadDeAprendizaje(Statement declaracion, String ID) {

        Inasistencia.BorrarPorActividadDeAprendizaje(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion from T_Formacion "
                    + "               join T_Horario on T_Horario.ID_Horario=T_Formacion.ID_Horario "
                    + "               join T_Fichas on T_Horario.ID_Ficha=Numero_De_Ficha "
                    + "               join T_Plan_De_Estudios "
                    + "               on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
                    + "               join T_Competencias "
                    + "               on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "               join T_Actividad_De_Aprendizaje "
                    + "               on T_Competencias.ID_Competencia=T_Actividad_De_Aprendizaje.ID_Competencia "
                    + "               where ID_Actividad_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorResultadoDeAprendizaje(Statement declaracion, String ID) {

        Inasistencia.BorrarPorResultadoDeAprendizaje(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion from T_Formacion "
                    + "               join T_Horario on T_Horario.ID_Horario=T_Formacion.ID_Horario "
                    + "               join T_Fichas on T_Horario.ID_Ficha=Numero_De_Ficha "
                    + "               join T_Plan_De_Estudios "
                    + "               on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
                    + "               join T_Competencias "
                    + "               on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "               join T_Actividad_De_Aprendizaje "
                    + "               on T_Competencias.ID_Competencia=T_Actividad_De_Aprendizaje.ID_Competencia "
                    + "               join T_Resultado_De_Aprendizaje "
                    + "               on T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje=T_Actividad_De_Aprendizaje.ID_Competencia "
                    + "               where ID_Resultado_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] BorrarPorHorario(Statement declaracion, String ID) {

        Inasistencia.BorrarPorHorario(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion where ID_Horario = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static void ActualizarEnID(Statement declaracion, String[] datos, String ID) {

        try {

            declaracion.executeQuery("update T_Formacion set "
                    + "Fecha = '" + datos[1] + "' "
                    + "where ID_Formacion= " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void Insertar(Statement declaracion, String[] ID, String[] datos) {

        try {

            declaracion.executeQuery("insert into T_Formacion values("
                    + datos[0] + ",'"
                    + datos[1] + "');");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        Inasistencia.InsertarInasistencias(declaracion, ID);

    }

    public static String[][] BorrarPorID(Statement declaracion, String ID) {

        Inasistencia.BorrarPorFormacion(declaracion, ID);

        String[][] menu = null;

        try {

            declaracion.executeQuery("delete from T_Formacion where ID_Formacion = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }
}
