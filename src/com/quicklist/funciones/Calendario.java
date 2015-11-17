/*
 * Calendario.java
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

import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author cesaraugusto
 */
public class Calendario {

    public static void darFecha(JDateChooser calendario, String fecha) {

        fecha = fecha.substring(8, 10) + "/" + fecha.substring(5, 7) + "/" + fecha.substring(0, 4);

        System.out.println(fecha);

        try {

            SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
            Date dateObj = curFormater.parse(fecha);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObj);
            calendario.setCalendar(calendar);

        } catch (ParseException ex) {
            System.out.println(ex);
        }

    }

    public static String obtenerFecha(JDateChooser calendario) {

        String fecha = null;

        try {

            int año = calendario.getCalendar().get(Calendar.YEAR);
            int mes = calendario.getCalendar().get(Calendar.MONTH) + 1;
            int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);

            fecha = dia + "/" + mes + "/" + año;

        } catch (NullPointerException ex) {
        }

        return fecha;

    }

    public static void reducirFecha(String[][] fecha, int campo) {

        for (int i = 0; i <= fecha.length - 1; i++) {

            fecha[i][campo] = fecha[i][campo].substring(0, 10);

        }
    }

}
