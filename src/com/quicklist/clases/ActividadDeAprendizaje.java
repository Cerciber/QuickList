/*
 * ActividadDeAprendizaje.java
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
public class ActividadDeAprendizaje {

    public static String[][] SeleccionarPorID(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Actividad_De_Aprendizaje where ID_Actividad_De_Aprendizaje = " + ID + ";");
            String[] campos = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
        }

        return menu;

    }

    public static String[][] SeleccionarPorCompetencia(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Actividad_De_Aprendizaje where ID_Competencia = " + ID + ";");
            String[] campos = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
        }

        return menu;

    }

    public static void BorrarEnPlanDeEstudios(Statement declaracion, String ID) {

        ResultadoDeAprendizaje.BorrarEnPlanDeEstudios(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Actividad_De_Aprendizaje from T_Actividad_De_Aprendizaje "
                    + "join T_Competencias on  T_Competencias.ID_Competencia=T_Actividad_De_Aprendizaje.ID_Competencia "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnCompetencia(Statement declaracion, String ID) {

        ResultadoDeAprendizaje.BorrarEnCompetencia(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Actividad_De_Aprendizaje "
                    + "where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void Insertar(Statement declaracion, String[] datos) {

        try {

            declaracion.executeQuery("insert into T_Actividad_De_Aprendizaje values("
                    + datos[0] + ", '"
                    + datos[1] + "', '"
                    + datos[2] + "')");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void ActualizarEnID(Statement declaracion, String[] datos, String ID) {

        try {

            declaracion.executeQuery("update T_Actividad_De_Aprendizaje set "
                    + "Fase_Del_Proyecto = '" + datos[1] + "', "
                    + "Actividad_De_Aprendizaje = '" + datos[2] + "' "
                    + "where ID_Actividad_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnID(Statement declaracion, String ID) {

        ResultadoDeAprendizaje.BorrarEnActividadDeAprendizaje(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Actividad_De_Aprendizaje "
                    + "where ID_Actividad_De_Aprendizaje = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
