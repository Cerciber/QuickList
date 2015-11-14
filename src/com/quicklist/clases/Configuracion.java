/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author cesaraugusto
 */
public class Configuracion {
    
    public static int[] cargarConfiguracion(){
        
        int[] datos=new int[16];
        
        try{
        
            String cadena;
            FileReader f = new FileReader("src/com/quicklist/configuracion/configuracion.txt");
            BufferedReader b = new BufferedReader(f);

            for(int i=1;i<=39;i++){
                
                cadena = b.readLine();
                System.out.println("1    "+cadena);
                
                if(i==4){datos[0]=Integer.parseInt(cadena);}
                if(i==5){datos[1]=Integer.parseInt(cadena);}
                if(i==7){datos[2]=Integer.parseInt(cadena);}
                if(i==9){datos[3]=Integer.parseInt(cadena);}
                if(i==11){datos[4]=Integer.parseInt(cadena);}
                if(i==13){datos[5]=Integer.parseInt(cadena);}
                if(i==15){datos[6]=Integer.parseInt(cadena);}
                if(i==20){datos[7]=Integer.parseInt(cadena);}
                if(i==22){datos[8]=Integer.parseInt(cadena);}
                if(i==27){datos[9]=Integer.parseInt(cadena);}
                if(i==29){datos[10]=Integer.parseInt(cadena);}
                if(i==34){datos[11]=Integer.parseInt(cadena);}
                if(i==39){datos[12]=Integer.parseInt(cadena);}
                
            }
            
            b.close();
            
        }catch(FileNotFoundException ex){System.out.println(ex);}
         catch(IOException ex){System.out.println(ex);}
            
        return datos;
    }
    
    public static String[] cargarLogin(){
        
        String[] datos=new String[3];
        
        try{
        
            String cadena;
            FileReader f = new FileReader("src/com/quicklist/configuracion/configuracion.txt");
            BufferedReader b = new BufferedReader(f);

            for(int i=1;i<=45;i++){
                
                cadena = b.readLine();
                System.out.println("1    "+cadena);
                
                if(i==41){datos[0]=cadena;}
                if(i==43){datos[1]=cadena;}
                if(i==45){datos[2]=cadena;}
                
            }
            
            b.close();
            
        }catch(FileNotFoundException ex){System.out.println(ex);}
         catch(IOException ex){System.out.println(ex);}
            
        return datos;
    }
    
    public static void guardarConfiguracion(String[] datos){
        
        String[] texto=new String[45];
        
        try{
        
            FileReader f = new FileReader("src/com/quicklist/configuracion/configuracion.txt");
            BufferedReader b = new BufferedReader(f);

            for(int i=1;i<=45;i++){
                
                texto[i-1] = b.readLine();
                System.out.println("2    "+texto[i-1]);
                
                if(i==4){texto[i-1]=datos[0];}
                if(i==5){texto[i-1]=datos[1];}
                if(i==7){texto[i-1]=datos[2];}
                if(i==9){texto[i-1]=datos[3];}
                if(i==11){texto[i-1]=datos[4];}
                if(i==13){texto[i-1]=datos[5];}
                if(i==15){texto[i-1]=datos[6];}
                if(i==20){texto[i-1]=datos[7];}
                if(i==22){texto[i-1]=datos[8];}
                if(i==27){texto[i-1]=datos[9];}
                if(i==29){texto[i-1]=datos[10];}
                if(i==34){texto[i-1]=datos[11];}
                if(i==39){texto[i-1]=datos[12];}
                
            }
            
            b.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/quicklist/configuracion/configuracion.txt"));
            
            for(int i=1;i<=45;i++){
                
                bw.write(texto[i-1]+"\n");
                System.out.println("3    "+texto[i-1]);
                
            }
            
            bw.close();
            
        }catch(FileNotFoundException ex){System.out.println(ex);}
         catch(IOException ex){System.out.println(ex);}

    }
    
    public static void guardarLogin(String usuario, String contrasena, String tipo){
        
        String[] texto=new String[45];
        
        try{
        
            FileReader f = new FileReader("src/com/quicklist/configuracion/configuracion.txt");
            BufferedReader b = new BufferedReader(f);

            for(int i=1;i<=45;i++){
                
                texto[i-1] = b.readLine();
                System.out.println("2    "+texto[i-1]);
                
                if(i==41){texto[i-1]=usuario;}
                if(i==43){texto[i-1]=contrasena;}
                if(i==45){texto[i-1]=tipo;}
                
            }
            
            b.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/quicklist/configuracion/configuracion.txt"));
            
            for(int i=1;i<=45;i++){
                
                bw.write(texto[i-1]+"\n");
                System.out.println("3    "+texto[i-1]);
                
            }
            
            bw.close();
            
        }catch(FileNotFoundException ex){System.out.println(ex);}
         catch(IOException ex){System.out.println(ex);}

    }
    
}
