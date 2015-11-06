/*
 * Ficha.java
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
import com.quicklist.funciones.Calendario;
import com.quicklist.funciones.ConvertirConsulta;

/**
 *
 * @author cesaraugusto
 */
public class Ficha {
    
    public static String[][] SeleccionarTodos(Statement declaracion){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Fichas join T_Plan_De_Estudios on T_Fichas.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios;");
            String[] campos={"Numero_De_Ficha","Numero_De_Ficha","Nombre_PlanDeEstudios","Fecha_Inicio","Fecha_Fin"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
            Calendario.reducirFecha(menu,3);
            Calendario.reducirFecha(menu,4);
            
        } catch (SQLException ex) {}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarNombres(Statement declaracion){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Fichas;");
            String[] campos={"Numero_De_Ficha"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorID(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Fichas where Numero_De_Ficha = "+ID+";");    
            String[] campos={"Numero_De_Ficha","Numero_De_Ficha","ID_Plan_De_Estudios","Fecha_Inicio","Fecha_Fin"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorInstructor(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select distinct Numero_De_Ficha,ID_Plan_De_Estudios,T_Fichas.Fecha_Inicio,T_Fichas.Fecha_Fin from T_Fichas "
                                                            + "join T_Horario on T_Fichas.Numero_De_Ficha=T_Horario.ID_Ficha "
                                                            + "where ID_Funcionario = "+ID+";");    
            String[] campos={"Numero_De_Ficha","Numero_De_Ficha"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorHorario(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select distinct Numero_De_Ficha from T_Fichas "
                                                            + "join T_Horario on T_Fichas.Numero_De_Ficha=T_Horario.ID_Ficha "
                                                            + "where ID_Horario = "+ID+";");    
            String[] campos={"Numero_De_Ficha"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarDatosReporteAprendices(Statement declaracion, String ficha){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select concat([Numero_De_Ficha],' ',T_Plan_De_Estudios.Nombre_Programa) as ficha,\n" +
                                                            "CAST(\n" +
                                                            "    CASE \n" +
                                                            "            WHEN [Fecha_Inicio] >= GETDATE() THEN 'NO INICIADA'\n" +
                                                            "			WHEN [Fecha_Inicio] <= GETDATE() and [Fecha_Fin] >= GETDATE() THEN 'EN EJECUCIÓN' \n" +
                                                            "			WHEN [Fecha_Fin] <= GETDATE() THEN 'FINALIZADA' \n" +
                                                            "	END AS varchar\n" +
                                                            "	) as estado, GETDATE() as fecha\n" +
                                                            "from [dbo].[T_Fichas]\n" +
                                                            "join T_Plan_De_Estudios on T_Fichas.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios\n" +
                                                            "where [Numero_De_Ficha]="+ficha);
            
            String[] campos={"ficha","estado","fecha"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
            Calendario.reducirFecha(menu,2);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarResgistrosReporteAprendices(Statement declaracion, String ficha){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select 'CC' as tipo, Documento_De_Identidad, Nombre, \n" +
                                                            "CONCAT(Primer_Apellido,' ',Segundo_Apellido) as apellidos,\n" +
                                                            "Telefono_Celular,Correo_Electronico,estado\n" +
                                                            "from T_Informacion_Aprendices\n" +
                                                            "where T_Informacion_Aprendices.ID_Ficha="+ficha);
            
            String[] campos={"tipo","Documento_De_Identidad","Nombre","apellidos","Telefono_Celular","Correo_Electronico","estado"};
            
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static void Insertar(Statement declaracion,String[] datos){
        
        try {
            
            declaracion.executeQuery("insert into T_Fichas values("+
                                    datos[0]+", '"+
                                    datos[1]+"', '"+
                                    datos[2]+"', '"+
                                    datos[3]+"')");    

            
        } catch (SQLException ex) {System.out.println(ex);}
        
        
    }
  
    public static void BorrarEnID(Statement declaracion,String ID){
        
        Aprendiz.BorrarEnFicha(declaracion, ID);
        Horario.BorrarEnFicha(declaracion, ID);
        
        try {
            
            //Se ejeculta la consulta para borrar 
            //empeando la variable ID en la condicion
            declaracion.executeQuery
            ("delete from T_Fichas "
            + "where Numero_De_Ficha = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
       
        
    }
    
    public static void BorrarPorPlanDeEstudios(Statement declaracion,String ID){
        
        Aprendiz.BorrarEnPlanDeEstudios(declaracion, ID);
        Horario.BorrarEnPlanDeEstudios(declaracion, ID);
        
        try {
            
            declaracion.executeQuery
            ("delete from T_Fichas "
            + "where ID_Plan_De_Estudios = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
       
        
    }
    
    public static void ActualizarEnID(Statement declaracion,String[] datos, String ID){

        try {
            
            declaracion.executeQuery("update T_Fichas set "
                    + "Numero_De_Ficha = "+datos[0]+","
                    + "ID_Plan_De_Estudios = '"+datos[1]+"',"
                    + "Fecha_Inicio = '"+datos[2]+"',"
                    + "Fecha_Fin = '"+datos[3]+"' "
                    + "where Numero_De_Ficha = "+ID);

        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
    public static boolean VerificarID(Statement declaracion,String ID){
        
        boolean existente=false;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Fichas where Numero_De_Ficha = "+ID+";");

            if(new ConvertirConsulta().NRegistros(resultado)==1){
                
                existente=true;
                    
            }
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return existente;
    }
    
}
