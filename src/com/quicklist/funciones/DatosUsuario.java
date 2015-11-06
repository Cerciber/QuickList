/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author cesaraugusto
 */
public class DatosUsuario {
    
    public DatosUsuario(String usuario, String tipo, Statement declaracion, JLabel jLabel1, JLabel jLabel2, JLabel jLabel3) {
        
        try {
            
            ResultSet resultado=null;
            
            if("Aprendiz".equals(tipo)){resultado = declaracion.executeQuery("select Nombre,Primer_Apellido,Documento_De_Identidad  from T_Informacion_Aprendices where Documento_De_Identidad="+usuario+";");}
            if("Instructor".equals(tipo)){resultado = declaracion.executeQuery("select Nombre,Primer_Apellido,Documento_De_Identidad  from T_Informacion_Funcionarios where Documento_De_Identidad="+usuario+" and cargo='INSTRUCTOR';");}
            if("Administrador".equals(tipo)){resultado = declaracion.executeQuery("select Nombre,Primer_Apellido,Documento_De_Identidad  from T_Informacion_Funcionarios where Documento_De_Identidad="+usuario+" and cargo='ADMINISTRADOR';");}
            
            String[] campos={"Nombre","Primer_Apellido","Documento_De_Identidad"};
            String[][] menu=new ConvertirConsulta().ArregloString(resultado,campos);
            jLabel1.setText(menu[0][0]+" "+menu[0][1]);
            jLabel2.setText(menu[0][2]);
            
            try{
                
                if("Administrador".equals(tipo) || "Instructor".equals(tipo)){

                    jLabel3.setText(null);

                    ImageIcon icono = new ImageIcon(Funcionario.SeleccionarFoto(declaracion, usuario));
                    ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( 66,76, 66)); 
                    jLabel3.setIcon(imageScalada);

                }else if("Aprendiz".equals(tipo)){

                    ImageIcon icono = new ImageIcon(Aprendiz.SeleccionarFoto(declaracion, usuario));
                    ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( 66,76, 66)); 
                    jLabel3.setIcon(imageScalada);

                }

            }catch(NullPointerException ex){}
   
        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
}
