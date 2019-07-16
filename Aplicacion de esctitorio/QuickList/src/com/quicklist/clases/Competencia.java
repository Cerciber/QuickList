/*
 * Competencia.java
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
public class Competencia {

    public static String[][] SeleccionarPorID(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Competencias where ID_Competencia=" + ID);
            String[] campos = {"ID_Competencia", "ID_Plan_De_Estudios", "Competencia_A_Desarrollar"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorPlanDeEstudios(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Competencias where ID_Plan_De_Estudios = " + ID + ";");
            String[] campos = {"ID_Competencia", "Competencia_A_Desarrollar"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorAprendiz(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Competencias "
                    + "join T_Plan_De_Estudios on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "join T_Fichas on T_Fichas.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "join T_Informacion_Aprendices on T_Fichas.Numero_De_Ficha=T_Informacion_Aprendices.ID_Ficha "
                    + "where Documento_De_Identidad = " + ID + ";");
            String[] campos = {"ID_Competencia", "Competencia_A_Desarrollar"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static void BorrarEnPlanDeEstudios(Statement declaracion, String ID) {

        ActividadDeAprendizaje.BorrarEnPlanDeEstudios(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Competencias "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void Insertar(Statement declaracion, String[] datos) {

        try {

            declaracion.executeQuery("insert into T_Competencias values("
                    + datos[0] + ", '"
                    + datos[1] + "')");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void ActualizarEnID(Statement declaracion, String[] datos, String ID) {

        try {

            declaracion.executeQuery("update T_Competencias set "
                    + "Competencia_A_Desarrollar = '" + datos[1] + "' "
                    + "where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnID(Statement declaracion, String ID) {

        ActividadDeAprendizaje.BorrarEnCompetencia(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Competencias "
                    + "where ID_Competencia = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
