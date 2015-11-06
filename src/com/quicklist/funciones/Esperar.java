/*
 * Esperar.java
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

public class Esperar {
    
    public Esperar(Thread hilo,int tiempo){
    
        boolean esperaInterrumpida;

        do{

            esperaInterrumpida=false;

            try {

                hilo.join(tiempo);
                
            } catch (InterruptedException ex) {

                esperaInterrumpida=true;

            }

        }while(esperaInterrumpida);
    
    }
    
}
