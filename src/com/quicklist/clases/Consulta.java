/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.clases;

import com.quicklist.funciones.Calendario;
import com.quicklist.funciones.ConvertirConsulta;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;

/**
 *
 * @author cesaraugusto
 */
public class Consulta {
    
    public Statement declaracion;
    public String tabla; 
    public String[] campos; 
    public String[] alias; 
    public String columnaSeleccionada;
    public int nColumnaSeleccionada=0;
    public String columnaSeleccionadaAnterior;
    public String orientacion="ASC"; 
    public String nRegistros="40"; 
    public String busqueda="";
    public String condicion="1=1";
    public int[] nFechas;
    public JPanel panelContenedor;
    public Point posicion = new Point(0,0);
    
    public Consulta(Statement declaracion){
        
        this.declaracion=declaracion;
        
    }
    
    public void tabla(String tabla){
        
        this.tabla=tabla;
        
    }
    
    public void campos(String[] campos){
        
        this.campos=campos;
        
    }
    
    public void alias(String[] alias){
        
        this.alias=alias;
        
    }
    
    public void columnaSeleccionada(String columnaSeleccionada){
        
        this.columnaSeleccionada=columnaSeleccionada;
        
    }
    
    public void orientacion(String orientacion){
        
        this.orientacion=orientacion;
        
    }
    
    public void nRegistros(String nRegistros){
        
        this.nRegistros=nRegistros;
        
    }
    
    public void busqueda(String busqueda){
        
        this.busqueda=busqueda;
        
    }
    
    public void condicion(String condicion){
        
        this.condicion=condicion;
        
    }   
    
    public void panelContenedor(JPanel panelContenedor){
        
        this.panelContenedor=panelContenedor;
        
    }  
    
    public void nFechas(int[] nFechas){
        
        this.nFechas=nFechas;
        
    }  
    
    public String[][] ejecutarConsulta(){
        
        String[][] menu = null;
        
        try {
            
            String consulta = "select top("+nRegistros+")";
            
            for (int i=0;i<campos.length;i++){
                
                consulta = consulta.concat(campos[i]+" ");
                consulta = consulta.concat("as "+alias[i]+" ");
                if(i!=campos.length-1){
                    consulta = consulta.concat(", ");
                }
                
            }
            
            consulta = consulta.concat("from "+tabla+" where "+columnaSeleccionada+" like ('%"+busqueda+"%') "
                    +" and "+condicion+" order by "+columnaSeleccionada
                    +" "+orientacion);
            
            System.out.println("#######################################");
            System.out.println(consulta);
            System.out.println("#######################################");
            ResultSet resultado = declaracion.executeQuery(consulta);
            menu=new ConvertirConsulta().ArregloString(resultado,alias);
            
            try{
                
                for(int i=0;i<nFechas.length;i++){

                    Calendario.reducirFecha(menu,nFechas[i]);

                }
                
            }catch(NullPointerException ex){}
                
            
        } catch (SQLException ex) {System.out.println(ex);}
        
        return menu;
        
    }
    
}
