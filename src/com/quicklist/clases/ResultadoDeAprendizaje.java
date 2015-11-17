/*
 * ResultadoDeAprendizaje.java
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
public class ResultadoDeAprendizaje {

    public static String[][] SeleccionarPorID(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Resultado_De_Aprendizaje where ID_Resultado_De_Aprendizaje = " + ID + " ");
            String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorActividadDeAprendizaje(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Resultado_De_Aprendizaje where ID_Actividad_De_Aprendizaje = " + ID + ";");
            String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorFichaInstructor(Statement declaracion, String ID, String usuario) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Resultado_De_Aprendizaje "
                    + "join T_Horario on  T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje=T_Horario.ID_Resultado_De_Aprendizaje "
                    + "where ID_Funcionario = " + usuario + " and ID_Ficha=" + ID + ";");
            String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarNombres(Statement declaracion) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select ID_Resultado_De_Aprendizaje,Resultado_De_Aprendizaje from T_Resultado_De_Aprendizaje order by Resultado_De_Aprendizaje;");
            String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static void BorrarEnPlanDeEstudios(Statement declaracion, String ID) {

        Horario.BorrarEnPlanDeEstudios(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Resultado_De_Aprendizaje from T_Resultado_De_Aprendizaje "
                    + "join T_Actividad_De_Aprendizaje on T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje =  T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
                    + "join T_Competencias on  T_Competencias.ID_Competencia=T_Actividad_De_Aprendizaje.ID_Competencia "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnCompetencia(Statement declaracion, String ID) {

        Horario.BorrarEnCompetencia(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Resultado_De_Aprendizaje from T_Resultado_De_Aprendizaje "
                    + "join T_Actividad_De_Aprendizaje on T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje =  T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
                    + "where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnActividadDeAprendizaje(Statement declaracion, String ID) {

        Horario.BorrarEnActividadDeAprendizaje(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Resultado_De_Aprendizaje from T_Resultado_De_Aprendizaje "
                    + "where ID_Actividad_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void Insertar(Statement declaracion, String[] datos) {

        try {

            declaracion.executeQuery("insert into T_Resultado_De_Aprendizaje values("
                    + datos[0] + ", '"
                    + datos[1] + "')");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void ActualizarEnID(Statement declaracion, String[] datos, String ID) {

        try {

            declaracion.executeQuery("update T_Resultado_De_Aprendizaje set "
                    + "Resultado_De_Aprendizaje = '" + datos[1] + "' "
                    + "where ID_Resultado_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnID(Statement declaracion, String ID) {

        Horario.BorrarEnResultadoDeAprendizaje(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Resultado_De_Aprendizaje "
                    + "where ID_Resultado_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
