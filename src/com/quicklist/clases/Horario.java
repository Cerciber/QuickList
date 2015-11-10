/*
 * Horario.java
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
public class Horario {
    
    public static String[][] SeleccionarPorFicha(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select *,CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido) as instructor from T_Horario "
                                                            + "join T_Resultado_De_Aprendizaje "
                                                            + "on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                                            + "join T_Informacion_Funcionarios "
                                                            + "on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad  "
                                                            + "where ID_Ficha = "+ID+";");    
            String[] campos={"ID_Horario","instructor","Dia_Semana","Hora_Inicio","Hora_Fin","Fecha_Inicio","Fecha_Fin","Lugar","Resultado_De_Aprendizaje"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
           
            Calendario.reducirFecha(menu,5);
            Calendario.reducirFecha(menu,6);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorInstructor(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select * from T_Horario "
                                                            + "join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                                            + "join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad "
                                                            + "where ID_Funcionario = "+ID+";");    
            String[] campos={"ID_Horario","ID_Ficha","Dia_Semana","Hora_Inicio","Hora_Fin","Fecha_Inicio","Fecha_Fin","Lugar","Resultado_De_Aprendizaje"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
           
            Calendario.reducirFecha(menu,5);
            Calendario.reducirFecha(menu,6);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarActualPorFichaInstructor(Statement declaracion,String usuario,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select *,CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido) as instructor from T_Horario "
                                                            + "join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                                            + "join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad "
                                                            + "where ID_Ficha = "+ID+" and ID_Funcionario = "+usuario+" and Fecha_Inicio<=getDate() and Fecha_Fin>=getDate();");    
            String[] campos={"ID_Horario","ID_Ficha","instructor","Dia_Semana","Hora_Inicio","Hora_Fin","Fecha_Inicio","Fecha_Fin","Lugar","Resultado_De_Aprendizaje"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
           
            Calendario.reducirFecha(menu,6);
            Calendario.reducirFecha(menu,7);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarActualPorAprendiz(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {

            ResultSet resultado = declaracion.executeQuery("select *,CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido) as instructor from T_Horario "
                                                            + "join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                                            + "join T_Informacion_Aprendices on T_Horario.ID_Ficha=T_Informacion_Aprendices.ID_Ficha "
                                                            + "join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad  "
                                                            + "where T_Informacion_Aprendices.Documento_De_Identidad = "+ID+" and Fecha_Inicio<=getDate() and Fecha_Fin>=getDate();");    
            String[] campos={"ID_Horario","instructor","Dia_Semana","Hora_Inicio","Hora_Fin","Fecha_Inicio","Fecha_Fin","Lugar","Resultado_De_Aprendizaje"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
           
            Calendario.reducirFecha(menu,5);
            Calendario.reducirFecha(menu,6);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorResultadoDeAprendizaje(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {

            ResultSet resultado = declaracion.executeQuery("select *,CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido) as instructor from T_Horario "
                                    + "join T_Resultado_De_Aprendizaje "
                                    + "on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                    + "join T_Informacion_Funcionarios "
                                    + "on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad "
                                    + "where T_Horario.ID_Resultado_De_Aprendizaje = "+ID);
            String[] campos={"ID_Horario","instructor","Dia_Semana","Hora_Inicio","Hora_Fin","Fecha_Inicio","Fecha_Fin","Lugar","Resultado_De_Aprendizaje"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
           
            Calendario.reducirFecha(menu,5);
            Calendario.reducirFecha(menu,6);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarPorID(Statement declaracion,String ID){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select ID_Horario,ID_Ficha,T_Horario.ID_Resultado_De_Aprendizaje,Resultado_De_Aprendizaje,concat(Nombre,' ', Primer_Apellido,' ',Segundo_Apellido,' - ',ID_Funcionario) as funcionario,Dia_Semana,Hora_Inicio,Hora_Fin,Fecha_Inicio,Fecha_Fin,Lugar \n" +
                                                            "from T_Horario \n" +
                                                            "join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje \n" +
                                                            "join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad \n" +
                                                            "where ID_Horario ="+ID+";");    
            String[] campos={"ID_Horario","ID_Ficha","ID_Resultado_De_Aprendizaje","Resultado_De_Aprendizaje","funcionario","Dia_Semana","Hora_Inicio","Hora_Fin","Fecha_Inicio","Fecha_Fin","Lugar"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
            Calendario.reducirFecha(menu,8);
            Calendario.reducirFecha(menu,9);
            
        } catch (SQLException ex) {}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarDatosFormatoDeAsistencia(Statement declaracion,String horario){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select [Nombre_Programa],[Version_Plan_De_Estudios],[Competencia_A_Desarrollar],[Numero_De_Ficha],[Resultado_De_Aprendizaje],[T_Horario].[Fecha_Inicio],[T_Horario].[Fecha_Fin],\n" +
                                                            "concat([Nombre],' ',[Primer_Apellido],' ',[Segundo_Apellido]) as instructor, [Documento_De_Identidad], CONCAT(DATENAME(MONTH,getdate()),' ',year(getdate())) as fecha\n" +
                                                            "from [T_Plan_De_Estudios]\n" +
                                                            "join [T_Competencias] on [T_Plan_De_Estudios].[ID_Plan_De_Estudios]=[T_Competencias].[ID_Plan_De_Estudios]\n" +
                                                            "join [T_Actividad_De_Aprendizaje] on [T_Actividad_De_Aprendizaje].[ID_Competencia]=[T_Competencias].[ID_Competencia]\n" +
                                                            "join [T_Resultado_De_Aprendizaje] on [T_Resultado_De_Aprendizaje].[ID_Actividad_De_Aprendizaje]=[T_Actividad_De_Aprendizaje].[ID_Actividad_De_Aprendizaje]\n" +
                                                            "join [T_Horario] on [T_Horario].[ID_Resultado_De_Aprendizaje]=[T_Resultado_De_Aprendizaje].[ID_Resultado_De_Aprendizaje]\n" +
                                                            "join [T_Fichas] on [T_Horario].[ID_Ficha] = [T_Fichas].[Numero_De_Ficha]\n" +
                                                            "join [T_Informacion_Funcionarios] on [T_Horario].[ID_Funcionario] = [T_Informacion_Funcionarios].[Documento_De_Identidad]\n" +
                                                            "where [T_Horario].[ID_Horario]="+horario);    
            String[] campos={"Nombre_Programa","Version_Plan_De_Estudios","Competencia_A_Desarrollar","Numero_De_Ficha","Resultado_De_Aprendizaje","Fecha_Inicio","Fecha_Fin","instructor","Documento_De_Identidad","fecha"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
            Calendario.reducirFecha(menu,5);
            Calendario.reducirFecha(menu,6);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] SeleccionarFechasFormatoDeAsistencia(Statement declaracion,String horario){
        
        String[][] menu = null;
        
        try {
            
            ResultSet resultado = declaracion.executeQuery("select [Fecha] from [T_Formacion] where [ID_Horario] ="+horario);    
            String[] campos={"Fecha"};
            menu=new ConvertirConsulta().ArregloString(resultado,campos);
            
            Calendario.reducirFecha(menu,0);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarPorInstructor(Statement declaracion,String ID){
        
        Formacion.BorrarPorInstructor(declaracion, ID);
        Actividad.BorrarPorInstructor(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario where ID_Funcionario = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarEnFicha(Statement declaracion,String ID){
        
        Actividad.BorrarPorFicha(declaracion, ID);
        Formacion.BorrarPorFicha(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario where ID_Ficha = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarEnPlanDeEstudios(Statement declaracion,String ID){
        
        Actividad.BorrarPorPlanDeEstudios(declaracion, ID);
        Formacion.BorrarPorPlanDeEstudios(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario "
                                    + "from T_Horario "
                                    + "join T_Fichas "
                                    + "on ID_Ficha=Numero_De_Ficha "
                                    + "where ID_Plan_De_Estudios = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarEnCompetencia(Statement declaracion,String ID){
        
        Actividad.BorrarPorCompetencia(declaracion, ID);
        Formacion.BorrarPorCompetencia(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario "
                                    + "from T_Horario "
                                    + "join T_Resultado_De_Aprendizaje "
                                    + "on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                    + "join T_Actividad_De_Aprendizaje "
                                    + "on T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
                                    + "where ID_Competencia = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarEnActividadDeAprendizaje(Statement declaracion,String ID){
        
        Actividad.BorrarPorActividadDeAprendizaje(declaracion, ID);
        Formacion.BorrarPorActividadDeAprendizaje(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario "
                                    + "from T_Horario "
                                    + "join T_Resultado_De_Aprendizaje "
                                    + "on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                                    + "where ID_Actividad_De_Aprendizaje = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] BorrarEnResultadoDeAprendizaje(Statement declaracion,String ID){
        
        Actividad.BorrarPorResultadoDeAprendizaje(declaracion, ID);
        Formacion.BorrarPorResultadoDeAprendizaje(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario "
                                    + "from T_Horario "
                                    + "where ID_Resultado_De_Aprendizaje = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static String[][] ActualizarEnInstructor(Statement declaracion,String dato,String ID){
        
        
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("update T_Horario set ID_Funcionario = "+dato+" where ID_Funcionario = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
    public static void Insertar(Statement declaracion,String[] datos){
        
        try {
            
            //Se ejecuta la consulta para inserta en la tabla 
            //T_Informacion_Funcionarios
            //con los 9 valores de la variable datos.
            
            declaracion.executeQuery("insert into T_Horario values("+
                                    datos[0]+","+
                                    datos[1]+","+
                                    datos[2]+",'"+
                                    datos[3]+"','"+
                                    datos[4]+"','"+
                                    datos[5]+"','"+
                                    datos[6]+"','"+
                                    datos[7]+"','"+
                                    datos[8]+"');");    

            
        } catch (SQLException ex) {}
        
    }
    
    public static void ActualizarEnID(Statement declaracion,String[] datos, String ID){
        
        try {
            
            declaracion.executeQuery("update T_Horario set "
                    + "ID_Ficha = "+datos[0]+","
                    + "ID_Resultado_De_Aprendizaje = "+datos[1]+","
                    + "ID_Funcionario = "+datos[2]+","
                    + "Dia_Semana = '"+datos[3]+"',"
                    + "Hora_Inicio = '"+datos[4]+"',"
                    + "Hora_Fin = '"+datos[5]+"',"
                    + "Fecha_Inicio = '"+datos[6]+"',"
                    + "Fecha_Fin = '"+datos[7]+"',"
                    + "Lugar = '"+datos[8]+"' "
                    + "where ID_Horario= "+ID);

        } catch (SQLException ex) {System.out.println(ex);}
        
    }
    
    
    public static String[][] BorrarPorID(Statement declaracion,String ID){
        
        Actividad.BorrarPorHorario(declaracion, ID);
        Formacion.BorrarPorHorario(declaracion, ID);
        
        String[][] menu = null;
        
        try {
            
            declaracion.executeQuery("delete from T_Horario where ID_Horario = "+ID);
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
}
