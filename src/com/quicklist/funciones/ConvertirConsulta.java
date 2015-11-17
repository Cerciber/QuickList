/*
 * ConvertirConsulta.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pabloycesar
 */
public class ConvertirConsulta {

    public String[][] ArregloString(ResultSet resultado, String[] campo) throws SQLException {

        //Se define una variable para contar los registros
        int NRegistros = -1;

        //next() se utliza para avanzar un registro en la consulta
        while (resultado.next()) {
            ++NRegistros;
        }

        //beforeFirst apunta antes del primer registo 
        //para reinicar la consulta
        resultado.beforeFirst();

        //se crea el arreglo bidimencional con el numero de campos y registros
        String[][] menu = new String[NRegistros + 1][campo.length];

        //Se emplean dos ciclos reprtitivos para almacenar 
        //los resultados en forma de tabla
        for (int i = 0; i <= NRegistros; i++) {
            resultado.next();
            for (int j = 0; j <= campo.length - 1; j++) {

                menu[i][j] = resultado.getString(campo[j]);

            }
        }

        //Se retorna la variable con la consulta convertida
        return menu;

    }

    public int NRegistros(ResultSet resultado) throws SQLException {

        //Se define una variable para contar los registros
        int NRegistros = 0;

        //Se apunta justo antes del priner regisrto 
        //para empezar a contar
        resultado.beforeFirst();

        //se emplea next en un cliclo repetitivo 
        //para contar los registros
        while (resultado.next()) {
            NRegistros++;
        }

        //al termiar se apunta justo antes del primer registro
        //para dejar la consulta como estaba al comienzo
        resultado.beforeFirst();

        //se retorna el numero de registro contados.
        return NRegistros;

    }

}
