/*
 * MoverObjeto.java
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

import java.awt.Component;
import java.sql.Statement;
import javax.swing.JPanel;

/**
 *
 * @author Administrador
 */
public class AnimacionObjetos {
    
    Thread moverObjetoIzquierda;
    Thread moverObjetoDerecha;
    Thread moverObjetoArriba;
    Thread moverObjetoAbajo;
    
    int posicionFinal[];
    
    
    public void InicializarObjetosIzquierda(Component[] objeto){
        
        for(int i=1;i<=objeto.length-1;i++){
         
             objeto[i].setLocation(-objeto[i].getWidth(),objeto[i].getLocation().y);
             
        }
        
    }
    
    public void Izquierda(final Component[] objeto, final int velocidad){
        
        
        posicionFinal = new int[objeto.length];

        for(int i=0;i<=objeto.length-1;i++){
         
            posicionFinal[i] = objeto[i].getLocation().x;
        }
        
        //Mover objetos
        
        moverObjetoIzquierda = new Thread("Mover panel"){ 

                
                @Override
                public void run() {
                    
                    
                    for(int i=0;i<=objeto.length-1;i++){
                    
                    for(int j=-objeto[i].getWidth();j<=posicionFinal[i]-velocidad;j+=velocidad){
                    
                    if(i==0){InicializarObjetosIzquierda(objeto);}
                    
                    objeto[i].setLocation(j, objeto[i].getLocation().y);
                    
                    new Esperar(moverObjetoIzquierda,1);
                    
                    }
                    
                    objeto[i].setLocation(posicionFinal[i], objeto[i].getLocation().y);
                    }
                }  
        };
        
        moverObjetoIzquierda.start();
        
    }
    
    public void RIzquierda(final Component[] objeto,final int velocidad, final JPanel panelContenedor, final String nombreClase,final String pantallaActual,String tipo, String usuario,String[] ID,Statement declaracion){
        
        moverObjetoIzquierda = new Thread("Mover panel"){ 
            
                @Override
                public void run() {
                    
                    for(int i=objeto.length-1;i>=0;i--){
                    
                        for(int j=objeto[i].getLocation().x;j>=-objeto[i].getWidth();j-=velocidad){

                            objeto[i].setLocation(j, objeto[i].getLocation().y);

                            new Esperar(moverObjetoIzquierda,1);

                        }
                    
                        objeto[i].setLocation(-objeto[i].getWidth(), objeto[i].getLocation().y);
                        
                    }
                    
                    new CambiarPantallas(tipo,panelContenedor,nombreClase,pantallaActual,usuario,ID,declaracion);
                    
                }
        };
        
        moverObjetoIzquierda.start();
    }

}
