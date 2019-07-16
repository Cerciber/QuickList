/*
 * Arreglo.java
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

public class Arreglo {

    public static String[] agregar(String[] arreglo, String elemento) {

        String[] ID3 = new String[arreglo.length + 1];

        for (int k = 0; k <= arreglo.length - 1; k++) {

            ID3[k] = arreglo[k];

        }

        ID3[ID3.length - 1] = elemento;

        arreglo = ID3;

        return arreglo;

    }

    public static String[] agregar(String[] arreglo) {

        String[] ID3;

        try {

            ID3 = new String[arreglo.length + 1];

        } catch (NullPointerException ex) {

            String[] ID4 = {""};
            ID3 = ID4;

        }

        for (int k = 0; k <= arreglo.length - 1; k++) {

            ID3[k] = arreglo[k];

        }

        ID3[ID3.length - 1] = arreglo[arreglo.length - 1];

        arreglo = ID3;

        return arreglo;

    }

    public static String[] quitar(String[] arreglo) {

        String[] ID3 = new String[arreglo.length - 1];

        for (int k = 0; k <= ID3.length - 1; k++) {

            ID3[k] = arreglo[k];

        }

        arreglo = ID3;

        return arreglo;
    }

}
