/*
 * CambiarPantallas.java
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
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.PantallaInicio;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CambiarPantallas{

    int usuario;
    ResultSet resultado;
    
    public CambiarPantallas(String tipo,JPanel panelContenedor, String nombreClase, String pantallaActual, String usuario,String[] ID,Statement declaracion){
           
        try {

            for(int i=0;i<=ID.length-1;i++)
            {System.out.print(ID[i]+" ");}
            System.out.println();      

        } catch (NullPointerException ex) {}
            
        panelContenedor=(JPanel) panelContenedor.getParent();

        if("PantallaInicio".equals(nombreClase)){

            try {

                panelContenedor.removeAll();

            } catch (NullPointerException ex) {System.out.println(ex);}

            PantallaInicio p = new PantallaInicio();
            panelContenedor.add(p);
            panelContenedor.validate();
            p.movimiento();

        }else{

            if("Aprendiz".equals(tipo)){new PantallasAprendiz(tipo,panelContenedor,nombreClase,pantallaActual,usuario,ID,declaracion);}
            if("Instructor".equals(tipo)){new PantallasInstructor(tipo,panelContenedor,nombreClase,pantallaActual,usuario,ID,declaracion);}
            if("Administrador".equals(tipo)){new PantallasAdministrador(tipo,panelContenedor,nombreClase,pantallaActual,usuario,ID,declaracion);}

        }
            
    }
    
}
