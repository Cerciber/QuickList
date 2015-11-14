/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author cesaraugusto
 */
public class Validaciones {
    
    public static void longitud(KeyEvent evt, int real, int esperado) {
        
        if(real>=esperado){
            
            evt.consume();
            
        }
        
    }
    
    public static void restringirCaracter(KeyEvent evt, char real, char esperado) {
        
        if(real==esperado){
            
            evt.consume();
            
        }
        
    }
    
    public static void restringirCaracterEntre(KeyEvent evt, char real, char inicio, char fin) {
        
        if(real>=inicio && real<=fin){
            
            evt.consume();
            
        }
        
    }
    
    public static void restringirCaracterFueraDe(KeyEvent evt, char real, char inicio, char fin) {
        
        if(real<inicio || real>fin){
            
            evt.consume();
            
        }
        
    }
    
    public static void numeroMinimoMaximo(JTextField campo, int minimo, int maximo) {
        
        /*if(Integer.parseInt(campo.getText())<minimo || real>fin){
        
        evt.consume();
        
        }*/
        
    }

    public static boolean formatoCorreo(String correo) {
        
        boolean arroba = false;
        boolean punto = false;
        boolean formato = false;
        
        for (int i = 0; i < correo.length(); i++) {
            
            if(correo.charAt(i)=='@' && !arroba){
                
                arroba = true;
                
            } else if(correo.charAt(i)=='.' && arroba){

                punto = true;

            }
            
        }
        
        if(arroba && punto){
            
            formato = true;
            
        }
        
        return formato;
        
    }
    
}
