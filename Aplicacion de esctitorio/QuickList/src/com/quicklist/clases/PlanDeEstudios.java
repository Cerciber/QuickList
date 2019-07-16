/*
 * PlanDeEstudios.java
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
public class PlanDeEstudios {

    public static String[][] SeleccionarNombres(Statement declaracion) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Plan_De_Estudios;");
            String[] campos = {"ID_Plan_De_Estudios", "Nombre_PlanDeEstudios"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarTodos(Statement declaracion) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Plan_De_Estudios;");
            String[] campos = {"ID_Plan_De_Estudios", "Nombre_PlanDeEstudios", "Nombre_Programa", "Version_Plan_De_Estudios", "Meses_Etapa_Lectiva", "Nivel_De_Formacion"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarPorID(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Plan_De_Estudios where ID_Plan_De_Estudios=" + ID);
            String[] campos = {"ID_Plan_De_Estudios", "Nombre_PlanDeEstudios", "Nombre_Programa", "Version_Plan_De_Estudios", "Meses_Etapa_Lectiva", "Nivel_De_Formacion"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static void Insertar(Statement declaracion, String[] datos) {

        try {

            declaracion.executeQuery("insert into T_Plan_De_Estudios values('"
                    + datos[0] + "', '"
                    + datos[1] + "', '"
                    + datos[2] + "', '"
                    + datos[3] + "', '"
                    + datos[4] + "')");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void ActualizarEnID(Statement declaracion, String[] datos, String ID) {

        try {

            declaracion.executeQuery("update T_Plan_De_Estudios set "
                    + "Nombre_PlanDeEstudios = '" + datos[0] + "',"
                    + "Nombre_Programa = '" + datos[1] + "',"
                    + "Version_Plan_De_Estudios = '" + datos[2] + "',"
                    + "Meses_Etapa_Lectiva = '" + datos[3] + "', "
                    + "Nivel_De_Formacion = '" + datos[4] + "' "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnID(Statement declaracion, String ID) {

        Competencia.BorrarEnPlanDeEstudios(declaracion, ID);
        Ficha.BorrarPorPlanDeEstudios(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Plan_De_Estudios "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
