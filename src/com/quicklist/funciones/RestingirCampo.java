/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import java.awt.event.KeyEvent;

/**
 *
 * @author cesaraugusto
 */
public class RestingirCampo {
    
    public static void longitud(KeyEvent evt, int real, int esperado) {
        
        if(real>=esperado){
            
            evt.consume();
            
        }
        
    }
    
    public static void caracter(KeyEvent evt, char real, char esperado) {
        
        if(real==esperado){
            
            evt.consume();
            
        }
        
    }
    
    public static void caracterEntre(KeyEvent evt, char real, char inicio, char fin) {
        
        if(real>=inicio && real<=fin){
            
            evt.consume();
            
        }
        
    }
    
    public static void caracterFueraDe(KeyEvent evt, char real, char inicio, char fin) {
        
        if(real<inicio || real>fin){
            
            evt.consume();
            
        }
        
    }
    
}
