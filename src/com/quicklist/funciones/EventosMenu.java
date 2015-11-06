/*
 * EventosMenu.java
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

import com.quicklist.clases.Ficha;
import java.awt.Component;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author pabloycesar
 */
public class EventosMenu {
    
    public EventosMenu(final Component[] objeto, final int velocidad, JButton[] boton,  final JPanel panelContenedor, final String[] menu,final String pantallaActual,String tipo,String usuario,Statement declaracion,String[] ID){
    
        for(int i=0;i<=boton.length-1;i++){
        
            final int i2=i;  
            
            boton[i].addActionListener(new java.awt.event.ActionListener() {
  
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    new AnimacionObjetos().RIzquierda(objeto, velocidad, panelContenedor,menu[i2],pantallaActual,tipo,usuario,Arreglo.agregar(ID),declaracion);

                }
                
            });
        
        }
        
    }
    
    
    public EventosMenu(final Component[] objeto, final int velocidad,final JButton[][] boton,String[] ID,String[] ID2,final JPanel panelContenedor, final String[] nombreBotones,final String pantallaActual,String tipo,String usuario,Statement declaracion){
    
        for(int i=0;i<=boton.length-1;i++){
           for(int j=0;j<=boton[i].length-1;j++){

               final int j2=j;  
               final int i2=i; 
               
               if(!("".equals(nombreBotones[j2]))){
               
                   boton[i][j].addActionListener(new java.awt.event.ActionListener() {

                     @Override
                     public void actionPerformed(java.awt.event.ActionEvent evt) {

                         if(pantallaActual.equals("Instructor.Horario")){
                         
                            String[][] menu = Ficha.SeleccionarPorHorario(declaracion, ID2[i2]);
                            String[] IDT=Arreglo.agregar(Arreglo.agregar(ID,menu[0][0]));
                            new AnimacionObjetos().RIzquierda(objeto, velocidad, panelContenedor,nombreBotones[j2],pantallaActual,tipo,usuario,Arreglo.agregar(IDT,ID2[i2]),declaracion);
                         
                         }else{
                         
                            new AnimacionObjetos().RIzquierda(objeto, velocidad, panelContenedor,nombreBotones[j2],pantallaActual,tipo,usuario,Arreglo.agregar(ID,ID2[i2]),declaracion);
                         
                         }
                     }

                    });
                   
               } 
               
            }
           
        }
        
    }
    
}
