/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author cesaraugusto
 */
public class DarIcono {
    
    public static void darIcono(JButton boton, String nombreIcono){
        
        ImageIcon icono = null;
        
        switch (nombreIcono){
            
            case "Editar":
                
                icono = new ImageIcon("src/com/quicklist/iconos/Editar.jpg");
                
            break;   
            
            case "Editar Usuario":
                
                icono = new ImageIcon("src/com/quicklist/iconos/Editar.jpg");
                
            break;   
              
            case "Borrar":
                
                icono = new ImageIcon("src/com/quicklist/iconos/Eliminar.png");
                
            break;       
                
            case "Borrar Usuario":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Aprendices":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Horarios":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
            
            case "Formaciones":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;  
                
            case "Actividades":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
            
            case "Asistencia":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Aprobar":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Competencias":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Actividades De Aprendizaje":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Resultado De Aprendizaje":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;   
                
            case "Excusa":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;  
                
            case "Formato":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;  
                
            case "Ficha":
                
                icono = new ImageIcon("src/com/quicklist/iconos/EliminarUsuario.png");
                
            break;  
            
        }
        
        boton.setIcon(icono);
        
    }
    
}
