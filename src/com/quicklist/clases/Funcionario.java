/*
 * Funcionario.java
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

package com.quicklist.clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.quicklist.funciones.ConvertirConsulta;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author cesaraugusto
 */
public class Funcionario {
    
    public static String[][] SeleccionarTodos(Statement declaracion){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Funcionarios;");
            String[] campos={"Documento_De_Identidad","Documento_De_Identidad","Contrasena","Nombre","Primer_Apellido","Segundo_Apellido","cargo","Correo_Electronico","Telefono_Fijo","Telefono_Celular"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarDatosUsuario(Statement declaracion,String usuario){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select Nombre,Primer_Apellido,Documento_De_Identidad  from T_Informacion_funcionarios where Documento_De_Identidad="+usuario+";");    
            String[] campos={"Nombre","Primer_Apellido","Documento_De_Identidad"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static Image SeleccionarFoto(Statement declaracion,String usuario){

        Image image = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select Foto from T_Informacion_funcionarios where Documento_De_Identidad="+usuario+";");    
            resultado.next();
            byte[] img = resultado.getBytes("Foto");
            
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            Object source = bis; // File or InputStream
            ImageInputStream iis = null;
            
            try {
                
                iis = ImageIO.createImageInputStream(source);
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                image = reader.read(0, param);
                
            } catch (IOException ex) {System.out.println(ex);}
            
        } catch (SQLException ex) {System.out.println(ex);}
          catch (NullPointerException ex) {System.out.println(ex);}
        
        return image;
        
    }
    
    public static String[][] SeleccionarPorDocumento(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Funcionarios where Documento_De_Identidad = "+ID);    
            String[] campos={"Documento_De_Identidad","Documento_De_Identidad","Contrasena","Nombre","Primer_Apellido","Segundo_Apellido","cargo","Correo_Electronico","Telefono_Fijo","Telefono_Celular"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorAprendiz(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Funcionarios "
                                                            + "join T_Horario on  T_Informacion_Funcionarios.Documento_De_Identidad=T_Horario.ID_Funcionario "
                                                            + "join T_Informacion_Aprendices on  T_Informacion_Aprendices.ID_Ficha=T_Horario.ID_Ficha "
                                                            + "where T_Informacion_Aprendices.Documento_De_Identidad = "+ID);    
            String[] campos={"Documento_De_Identidad","Nombre","Primer_Apellido","Segundo_Apellido","cargo","Correo_Electronico","Telefono_Fijo","Telefono_Celular"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarNombres(Statement declaracion){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Funcionarios;");
            String[] campos={"Documento_De_Identidad"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static void Insertar(Statement declaracion,String[] datos){
        
        try {
            
            //Se ejecuta la consulta para inserta en la tabla 
            //T_Informacion_Funcionarios
            //con los 9 valores de la variable datos.
            
            declaracion.executeQuery("insert into T_Informacion_Funcionarios values("+
                                    datos[0]+", '"+
                                    datos[1]+"', '"+
                                    datos[2]+"', '"+
                                    datos[3]+"', '"+
                                    datos[4]+"', '"+
                                    datos[5]+"','"+
                                    datos[6]+"',"+
                                    datos[7]+", "+
                                    datos[8]+", null);");    

            
        } catch (SQLException ex) {System.out.println(ex);}
        
        
    }
  
    public static void BorrarEnDocumento(Statement declaracion,String ID){
        
        Horario.BorrarPorInstructor(declaracion, ID);
        
        try {
            
            //Se ejeculta la consulta para borrar 
            //empeando la variable ID en la condicion
            declaracion.executeQuery
            ("delete from T_Informacion_Funcionarios "
            + "where Documento_De_Identidad = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
       
        
    }
    
    public static void ActualizarEnDocumento(Statement declaracion,String[] datos, String ID){
        
        try {
            
            //Se ejeculta la consulta para actualizar
            //asignando cada una de las varibles de arrgelo datos
            //empeando la variable ID en la condicion
            declaracion.executeQuery("update T_Informacion_Funcionarios set "
                    + "Documento_De_Identidad = "+datos[0]+","
                    + "Contrasena = '"+datos[1]+"',"
                    + "Nombre = '"+datos[2]+"',"
                    + "Primer_Apellido = '"+datos[3]+"',"
                    + "Segundo_Apellido = '"+datos[4]+"',"
                    + "cargo = '"+datos[5]+"',"
                    + "Correo_Electronico = '"+datos[6]+"',"
                    + "Telefono_Fijo = "+datos[7]+","
                    + "Telefono_Celular = "+datos[8]+" "
                    + "where Documento_De_Identidad= "+ID);

        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
    public static void ActualizarFoto(Statement declaracion,String usuario,FileInputStream foto){
        
        try {
            
            PreparedStatement preparedStatement = declaracion.getConnection().prepareStatement("update T_Informacion_funcionarios set Foto = ? where Documento_De_Identidad="+usuario+";");
            preparedStatement.setBinaryStream(1, foto);
            preparedStatement.executeUpdate();
                        
        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
    public static void BorrarFoto(Statement declaracion,String usuario){
        
        try {
            
            PreparedStatement preparedStatement = declaracion.getConnection().prepareStatement("update T_Informacion_funcionarios set Foto = null where Documento_De_Identidad="+usuario+";");
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
    public static boolean VerificarDocumento(Statement declaracion,String documento){
        
        boolean existente=false;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Funcionarios where Documento_De_Identidad = "+documento+";");

            if(new ConvertirConsulta().NRegistros(resultado)==1){
                
                existente=true;
                    
            }
            
        } catch (SQLException ex) {}
        
        return existente;
    }
    
    
    
}
