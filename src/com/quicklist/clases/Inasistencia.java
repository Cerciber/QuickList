/*
 * Inasistencia.java
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

import com.quicklist.funciones.Calendario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.quicklist.funciones.ConvertirConsulta;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author cesaraugusto
 */
public class Inasistencia {
    
    public static String[][] SeleccionarPorFormacion (Statement declaracion, String ID){
    
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("Select * from T_Informacion_Aprendices join T_Inasistencia on ID_Aprendiz = Documento_De_Identidad where ID_Formacion="+ID);    
            String[] campos={"ID_Inasistencia","Documento_De_Identidad","Nombre","Primer_Apellido","Segundo_Apellido","Estado_De_Inasistencia","Justificacion_De_Inasistencia"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorHorario (Statement declaracion, String usuario, String ID){
    
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("Select * from T_Inasistencia "
                                                            + "join T_Formacion on T_Inasistencia.ID_Formacion = T_Formacion.ID_Formacion "
                                                            + "where ID_Horario="+ID+" and ID_Aprendiz="+usuario);    
            String[] campos={"ID_Inasistencia","Fecha","Estado_De_Inasistencia","Justificacion_De_Inasistencia"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
            Calendario.reducirFecha(menu, 1);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorAprendizHorario (Statement declaracion, String aprendiz, String horario){
    
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select [Estado_De_Inasistencia] from [dbo].[T_Inasistencia]\n" +
                                                            "join [dbo].[T_Formacion] on [dbo].[T_Inasistencia].ID_Formacion= [dbo].[T_Formacion].ID_Formacion\n" +
                                                            "join [dbo].[T_Horario] on [dbo].[T_Horario].ID_Horario = [dbo].[T_Formacion].ID_Horario\n" +
                                                            " where [ID_Aprendiz]="+aprendiz+" and [dbo].[T_Horario].ID_Horario ="+horario);    
            String[] campos={"Estado_De_Inasistencia"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static Image SeleccionarExcusa(Statement declaracion,String ID){

        Image image = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select Foto from T_Inasistencia where ID_Inasistencia="+ID+";");    
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
    
    public static String[][] SeleccionarDescripcionExcusa (Statement declaracion, String ID){
    
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select Descripcion from T_Inasistencia where ID_Inasistencia="+ID);    
            String[] campos={"Descripcion"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorInstructor(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Inasistencia from T_Horario "
                    + "               join T_Formacion on T_Horario.ID_Horario = T_Formacion.ID_Horario"
                    + "               join T_Inasistencia on T_Inasistencia.ID_Formacion = T_Formacion.ID_Formacion"
                    + "               where ID_Funcionario = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    
    public static String[][] BorrarPorAprendiz(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Inasistencia "
                    + "               where ID_Aprendiz = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorFicha(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Inasistencia from T_Inasistencia"
                    + "               join T_Informacion_Aprendices on T_Inasistencia.ID_Aprendiz=T_Informacion_Aprendices.Documento_De_Identidad"
                    + "               where ID_Ficha = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorPlanDeEstudios(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery
            ("delete from T_Inasistencia "
            + "from T_Inasistencia "
            + "join T_Informacion_Aprendices "
            + "on Documento_De_Identidad=ID_Aprendiz "
            + "join T_Fichas "
            + "on Numero_De_Ficha=ID_Ficha "
            + "where ID_Plan_De_Estudios = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorCompetencia(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery
            ("delete from T_Inasistencia "
            + "from T_Inasistencia "
            + "join T_Informacion_Aprendices "
            + "on Documento_De_Identidad=ID_Aprendiz "
            + "join T_Fichas "
            + "on Numero_De_Ficha=ID_Ficha "
            + "join T_Plan_De_Estudios "
            + "on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
            + "join T_Competencias "
            + "on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
            + "where ID_Competencia = "+ID);

        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorActividadDeAprendizaje(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery
            ("delete from T_Inasistencia "
            + "from T_Inasistencia "
            + "join T_Informacion_Aprendices "
            + "on Documento_De_Identidad=ID_Aprendiz "
            + "join T_Fichas "
            + "on Numero_De_Ficha=ID_Ficha "
            + "join T_Plan_De_Estudios "
            + "on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
            + "join T_Competencias "
            + "on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
            + "join T_Actividad_De_Aprendizaje "
            + "on T_Competencias.ID_Competencia=T_Actividad_De_Aprendizaje.ID_Competencia "
            + "where ID_Actividad_De_Aprendizaje = "+ID);

        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorResultadoDeAprendizaje(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery
            ("delete from T_Inasistencia "
            + "from T_Inasistencia "
            + "join T_Informacion_Aprendices "
            + "on Documento_De_Identidad=ID_Aprendiz "
            + "join T_Fichas "
            + "on Numero_De_Ficha=ID_Ficha "
            + "join T_Plan_De_Estudios "
            + "on T_Plan_De_Estudios.ID_Plan_De_Estudios=T_Fichas.ID_Plan_De_Estudios "
            + "join T_Competencias "
            + "on T_Competencias.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios "
            + "join T_Actividad_De_Aprendizaje "
            + "on T_Competencias.ID_Competencia=T_Actividad_De_Aprendizaje.ID_Competencia "
            + "join T_Resultado_De_Aprendizaje "
            + "on T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje=T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
            + "where Resultado_De_Aprendizaje = "+ID);

        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorHorario(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Inasistencia from T_Inasistencia"
                    + "               join T_Formacion on T_Inasistencia.ID_Formacion=T_Formacion.ID_Formacion"
                    + "               where ID_Horario = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorFormacion(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Inasistencia from T_Inasistencia "
                    + "               where ID_Formacion = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] InsertarInasistencias(Statement declaracion,String[] ID){
    
        String[][] menu = null;
        
        String formacion = Formacion.SeleccionarUltimoRegistro(declaracion);

        menu=Aprendiz.SeleccionarPorFicha(declaracion,ID[ID.length-4]);

        for(int i=0;i<=menu.length-1;i++){
             try {

            declaracion.executeQuery("insert into T_Inasistencia values("+
                                formacion+","+
                                menu[i][0]+",'"+
                                "No','"+
                                "No',"+
                                "null,"+
                                "null);");    
             } catch (SQLException ex) {System.out.println(ex);}

        }

        return menu;
    }
    
    public static String[][] ActualizarInasistencias (Statement declaracion,String[][] datos){
    
        String[][] menu = null;

        for(int i=0;i<=datos.length-1;i++){
             try {

            declaracion.executeQuery("update T_Inasistencia set Estado_De_Inasistencia='"+datos[i][0]+"',Justificacion_De_Inasistencia='"+datos[i][1]+"' where ID_Inasistencia="+datos[i][2]);    
             } catch (SQLException ex) {System.out.println(ex);}

        }

        return menu;
    }
    
    public static void ActualizarExcusa(Statement declaracion,String ID,FileInputStream excusa){
        
        try {
            
            PreparedStatement preparedStatement = declaracion.getConnection().prepareStatement("update T_Inasistencia set Foto = ? where ID_Inasistencia="+ID+";");
            preparedStatement.setBinaryStream(1, excusa);
            preparedStatement.executeUpdate();
                        
        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
    public static void ActualizarDescripcionExcusa(Statement declaracion,String ID, String descripcion){
        
        try {
            
            PreparedStatement preparedStatement = declaracion.getConnection().prepareStatement("update T_Inasistencia set Descripcion = '"+descripcion+"' where ID_Inasistencia="+ID+";");
            preparedStatement.executeUpdate();
                        
        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
}
