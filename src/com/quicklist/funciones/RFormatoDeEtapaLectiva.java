/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Horario;
import static java.awt.Desktop.getDesktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author cesaraugusto
 */
public class RFormatoDeEtapaLectiva {
    
    JFileChooser abrirArchivo = new JFileChooser();
    
    public void enBlanco() {
    
        try {
            
            FileInputStream file = new FileInputStream(new File(
                    "src/com/quicklist/formatos/FormatoEtapaLectiva.xls"));
            HSSFWorkbook excel = new HSSFWorkbook(file);
            
            int resp;
        
            resp=abrirArchivo.showSaveDialog(null);

            if (resp==JFileChooser.APPROVE_OPTION){

                String extencion = abrirArchivo.getSelectedFile().toString();
                
                if (!".xls".equals(extencion.substring(extencion.length()-4))) {
                    
                    extencion = extencion+".xls";
                    
                }
                
                File direccion = new File (extencion);

                FileOutputStream salida = new FileOutputStream(direccion);

                excel.write(salida);
                excel.close();
                
                getDesktop().edit(direccion);
                
            }
            
        } catch (FileNotFoundException  ex) { 
            
            System.out.println(ex);
            
        }
          catch (IOException  ex) { 
              
            System.out.println(ex);
          
        }
    }
    
    public void porInstructor(Statement declaracion, String usuario, String instrutor){
    
        try {
            
            FileInputStream file = new FileInputStream(new File(
                    "src/com/quicklist/formatos/FormatoEtapaLectiva.xls"));
            HSSFWorkbook excel = new HSSFWorkbook(file);
            
            HSSFSheet hoja = (HSSFSheet) excel.getSheetAt(0);
            
            String[][] menu =  Aprendiz.SeleccionarDatosFormatoEtapaLectiva(declaracion, usuario, instrutor);
            
            HSSFRow fila = hoja.getRow(5);
            HSSFCell celda = fila.getCell(3);
            celda.setCellValue(menu[0][0]);
            
            fila = hoja.getRow(5);
            celda = fila.getCell(14);
            celda.setCellValue(menu[0][1]);
            
            fila = hoja.getRow(7);
            celda = fila.getCell(3);
            celda.setCellValue(menu[0][2]);
            
            fila = hoja.getRow(7);
            celda = fila.getCell(7);
            celda.setCellValue(menu[0][3]);
            
            fila = hoja.getRow(7);
            celda = fila.getCell(16);
            celda.setCellValue(menu[0][4]);
            
            fila = hoja.getRow(9);
            celda = fila.getCell(3);
            celda.setCellValue(menu[0][5]);
            
            fila = hoja.getRow(10);
            celda = fila.getCell(3);
            celda.setCellValue(menu[0][6]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(1);
            celda.setCellValue("Estilos y Ritmos de aprendizaje: "+menu[0][7]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(7);
            celda.setCellValue("Estrategia metodológica de preferencia: "+menu[0][8]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(13);
            celda.setCellValue("Características culturales y sociales: "+menu[0][9]);
            
            String[][] celdaTexto = new String[3][28];
            HSSFCellStyle[][] celdaEstilo = new HSSFCellStyle[3][28];
            
            for(int i=0;i<celdaTexto.length;i++){
                
                fila=hoja.getRow(i+21);
                
                for(int j=0;j<celdaTexto[0].length;j++){
                    
                    try{
                        
                        celdaTexto[i][j]=fila.getCell(j).getStringCellValue();
                        celdaEstilo[i][j]=fila.getCell(j).getCellStyle();
                                
                    }catch(NullPointerException ex){
                        
                        celdaTexto[i][j]="";
                    
                    }
                    
                }
                
            }
            
            menu = Aprendiz.SeleccionarResultadosFormatoEtapaLectivaPorInstructor(declaracion, usuario, instrutor);
            HSSFCellStyle[] estiloRegistro = new HSSFCellStyle[28];
            
            fila = hoja.getRow(20);
            
            for(int i=0;i<estiloRegistro.length;i++){
                
                
                try{
                        
                    estiloRegistro[i]=fila.getCell(i).getCellStyle();
                    estiloRegistro[i].setBorderBottom(HSSFCellStyle.BORDER_THIN);
                                
                }catch(NullPointerException ex){}
                
            }
            
            
            
            for(int i=0;i<menu.length;i++){
            
                fila = hoja.createRow(i+20);
                fila.setHeight((short)-1);
                
                fila.createCell(0);
                fila.createCell(1).setCellStyle(estiloRegistro[1]);
                
                celda = fila.createCell(2);
                celda.setCellStyle(estiloRegistro[2]);
                celda.setCellValue(menu[i][0]);
                
                fila.createCell(3).setCellStyle(estiloRegistro[3]);
                fila.createCell(4).setCellStyle(estiloRegistro[4]);
                
                celda = fila.createCell(5);
                celda.setCellStyle(estiloRegistro[5]);
                celda.setCellValue(menu[i][1]);
                hoja.addMergedRegion(new CellRangeAddress(i+21,i+21,5,7));
                
                fila.createCell(6).setCellStyle(estiloRegistro[6]);
                fila.createCell(7).setCellStyle(estiloRegistro[7]);
                fila.createCell(8).setCellStyle(estiloRegistro[8]);
                hoja.getRow(i+20).getCell(8).setCellValue(i+1);
                
                celda = fila.createCell(9);
                celda.setCellStyle(estiloRegistro[9]);
                celda.setCellValue(menu[i][2]);
                
                fila.createCell(10).setCellStyle(estiloRegistro[10]);
                fila.createCell(11).setCellStyle(estiloRegistro[11]);
                
                switch (menu[i][3]) {
                    
                    case "D":
                        
                        celda = fila.createCell(10);
                        celda.setCellStyle(estiloRegistro[10]);
                        celda.setCellValue("x");
                        break;
                        
                    case "F":
                        
                        celda = fila.createCell(11);
                        celda.setCellStyle(estiloRegistro[11]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(12).setCellStyle(estiloRegistro[12]);
                fila.createCell(13).setCellStyle(estiloRegistro[13]);
                fila.createCell(14).setCellStyle(estiloRegistro[14]);
                
                switch (menu[i][4]) {
                    
                    case "C":
                        
                        celda = fila.createCell(12);
                        celda.setCellStyle(estiloRegistro[12]);
                        celda.setCellValue("x");
                        break;
                        
                    case "D":
                        
                        celda = fila.createCell(13);
                        celda.setCellStyle(estiloRegistro[13]);
                        celda.setCellValue("x");
                        break;
                        
                    case "P":
                        
                        celda = fila.createCell(14);
                        celda.setCellStyle(estiloRegistro[14]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                celda = fila.createCell(15);
                celda.setCellStyle(estiloRegistro[15]);
                celda.setCellValue(menu[i][5]);
                
                celda = fila.createCell(16);
                celda.setCellStyle(estiloRegistro[16]);
                celda.setCellValue(menu[i][6]);
                
                celda = fila.createCell(17);
                celda.setCellStyle(estiloRegistro[17]);
                celda.setCellValue(menu[i][7]);
            
                fila.createCell(18).setCellStyle(estiloRegistro[18]);
                fila.createCell(19).setCellStyle(estiloRegistro[19]);
                
                switch (menu[i][8]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(18);
                        celda.setCellStyle(estiloRegistro[18]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(19);
                        celda.setCellStyle(estiloRegistro[19]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(20).setCellStyle(estiloRegistro[20]);
                fila.createCell(21).setCellStyle(estiloRegistro[21]);
                
                switch (menu[i][9]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(20);
                        celda.setCellStyle(estiloRegistro[20]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(21);
                        celda.setCellStyle(estiloRegistro[21]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(22).setCellStyle(estiloRegistro[22]);
                fila.createCell(23).setCellStyle(estiloRegistro[23]);
                
                switch (menu[i][10]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(22);
                        celda.setCellStyle(estiloRegistro[22]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(23);
                        celda.setCellStyle(estiloRegistro[23]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(24).setCellStyle(estiloRegistro[24]);
                fila.createCell(25).setCellStyle(estiloRegistro[25]);
                
                switch (menu[i][11]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(24);
                        celda.setCellStyle(estiloRegistro[24]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(25);
                        celda.setCellStyle(estiloRegistro[25]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(26).setCellStyle(estiloRegistro[26]);
                fila.createCell(27).setCellStyle(estiloRegistro[27]);
                
            }
            
            int cont=1;
            
            for(int i=1;i<menu.length;i++){
                
                if(Integer.parseInt(menu[i][12])!=Integer.parseInt(menu[i-1][12])){
                    
                    cont++;
                    
                }
                
            }
            
            int[] registroInicial = new int[cont];
            int[] registroFinal = new int[cont];
            boolean[] logroElAprendizaje = new boolean[cont];
            cont=0;
            
            registroInicial[cont]=0;
            registroFinal[cont]=0;
            
            logroElAprendizaje[cont]=true;
            
            if("No".equals(menu[0][13])){
                
                logroElAprendizaje[cont]=false;
                
            }
            
            for(int i=1;i<menu.length;i++){
                
                if(Integer.parseInt(menu[i][12])==Integer.parseInt(menu[i-1][12])){
                    
                    registroFinal[cont]=i;
                    
                    if("No".equals(menu[i][13])){
                        
                        logroElAprendizaje[cont]=false;
                        
                    }
                    
                }else{
                    
                    cont++;
                    registroInicial[cont]=registroFinal[cont-1]+1;
                    registroFinal[cont]=registroFinal[cont-1]+1;
                    logroElAprendizaje[cont]=true;
                    
                    if("No".equals(menu[i][13])){
                        
                        logroElAprendizaje[cont]=false;

                    }
                    
                }
                
            }
            
            for(int i=0;i<=cont;i++){
                
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],1,1));
                hoja.getRow(20+registroInicial[i]).getCell(1).setCellValue(i+1);
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],2,4));
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],26,26));
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],27,27));
                
                if(logroElAprendizaje[i]){
                    
                    hoja.getRow(20+registroInicial[i]).getCell(26).setCellValue("x");
                    
                }else{
                    
                    hoja.getRow(20+registroInicial[i]).getCell(27).setCellValue("x");
                    
                }
                
                
                
                
                
            }
            
            for(int i=0;i<celdaTexto.length;i++){
            
                fila = hoja.createRow(i+20+menu.length);
                fila.setHeightInPoints((float) 40.5);

                for(int j=0;j<celdaTexto[0].length;j++){

                    celda = fila.createCell(j);
                    celda.setCellValue(celdaTexto[i][j]);
                    celda.setCellStyle(celdaEstilo[i][j]);
                    

                }
            
            }
            
            hoja.addMergedRegion(new CellRangeAddress(20+menu.length,20+menu.length,1,14));
            hoja.addMergedRegion(new CellRangeAddress(21+menu.length,21+menu.length,1,14));
            hoja.addMergedRegion(new CellRangeAddress(22+menu.length,22+menu.length,1,14));
            hoja.addMergedRegion(new CellRangeAddress(20+menu.length,20+menu.length,15,27));
            hoja.addMergedRegion(new CellRangeAddress(21+menu.length,21+menu.length,15,27));

            int resp;
        
            resp=abrirArchivo.showSaveDialog(null);

            if (resp==JFileChooser.APPROVE_OPTION){

                String extencion = abrirArchivo.getSelectedFile().toString();
                
                if (!".xls".equals(extencion.substring(extencion.length()-4))) {
                    
                    extencion = extencion+".xls";
                    
                }
                
                File direccion = new File (extencion);

                FileOutputStream salida = new FileOutputStream(direccion);

                excel.write(salida);
                excel.close();
                
                getDesktop().edit(direccion);
                
            }
            
            
            
            
        } catch (FileNotFoundException  ex) {
            
            System.out.println(ex);
        
        } catch (IOException  ex) {
              
            System.out.println(ex);
          
        }
    }   
    
    public void porHorario(Statement declaracion, String usuario, String horario){
    
        try {
            
            FileInputStream file = new FileInputStream(new File(
                    "src/com/quicklist/formatos/FormatoEtapaLectiva.xls"));
            HSSFWorkbook excel = new HSSFWorkbook(file);
            
            HSSFSheet hoja = (HSSFSheet) excel.getSheetAt(0);
            
            String[][] menu =  Horario.SeleccionarPorID(declaracion, horario);
            menu =  Aprendiz.SeleccionarDatosFormatoEtapaLectiva(declaracion, usuario, menu[0][4]);
            
            HSSFRow fila = hoja.getRow(5);
            HSSFCell celda = fila.getCell(3);
            celda.setCellValue(menu[0][0]);
            
            fila = hoja.getRow(5);
            celda = fila.getCell(14);
            celda.setCellValue(menu[0][1]);
            
            fila = hoja.getRow(7);
            celda = fila.getCell(3);
            celda.setCellValue(menu[0][2]);
            
            fila = hoja.getRow(7);
            celda = fila.getCell(7);
            celda.setCellValue(menu[0][3]);
            
            fila = hoja.getRow(7);
            celda = fila.getCell(16);
            celda.setCellValue(menu[0][4]);
            
            fila = hoja.getRow(9);
            celda = fila.getCell(3);
            celda.setCellValue(menu[0][5]);
            
            fila = hoja.getRow(10);
            celda = fila.getCell(3);
            celda.setCellValue(menu[0][6]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(1);
            celda.setCellValue("Estilos y Ritmos de aprendizaje: "+menu[0][7]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(7);
            celda.setCellValue("Estrategia metodológica de preferencia: "+menu[0][8]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(13);
            celda.setCellValue("Características culturales y sociales: "+menu[0][9]);
            
            String[][] celdaTexto = new String[3][28];
            HSSFCellStyle[][] celdaEstilo = new HSSFCellStyle[3][28];
            
            for(int i=0;i<celdaTexto.length;i++){
                
                fila=hoja.getRow(i+21);
                
                for(int j=0;j<celdaTexto[0].length;j++){
                    
                    try{
                        
                        celdaTexto[i][j]=fila.getCell(j).getStringCellValue();
                        celdaEstilo[i][j]=fila.getCell(j).getCellStyle();
                                
                    }catch(NullPointerException ex){
                        
                        celdaTexto[i][j]="";
                    
                    }
                    
                }
                
            }
            
            menu = Aprendiz.SeleccionarResultadosFormatoEtapaLectivaPorHorario(declaracion, usuario, horario);
            HSSFCellStyle[] estiloRegistro = new HSSFCellStyle[28];
            
            fila = hoja.getRow(20);
            
            for(int i=0;i<estiloRegistro.length;i++){
                
                
                try{
                    
                    estiloRegistro[i]=fila.getCell(i).getCellStyle();
                    estiloRegistro[i].setBorderBottom(HSSFCellStyle.BORDER_THIN);
                                
                }catch(NullPointerException ex){}
                
            }
            
            
            
            for(int i=0;i<menu.length;i++){
            
                fila = hoja.createRow(i+20);
                fila.setHeight((short)-1);
                
                fila.createCell(0);
                fila.createCell(1).setCellStyle(estiloRegistro[1]);
                
                celda = fila.createCell(2);
                celda.setCellStyle(estiloRegistro[2]);
                celda.setCellValue(menu[i][0]);
                
                fila.createCell(3).setCellStyle(estiloRegistro[3]);
                fila.createCell(4).setCellStyle(estiloRegistro[4]);
                
                celda = fila.createCell(5);
                celda.setCellStyle(estiloRegistro[5]);
                celda.setCellValue(menu[i][1]);
                hoja.addMergedRegion(new CellRangeAddress(i+21,i+21,5,7));
                
                fila.createCell(6).setCellStyle(estiloRegistro[6]);
                fila.createCell(7).setCellStyle(estiloRegistro[7]);
                fila.createCell(8).setCellStyle(estiloRegistro[8]);
                hoja.getRow(i+20).getCell(8).setCellValue(i+1);
                
                celda = fila.createCell(9);
                celda.setCellStyle(estiloRegistro[9]);
                celda.setCellValue(menu[i][2]);
                
                fila.createCell(10).setCellStyle(estiloRegistro[10]);
                fila.createCell(11).setCellStyle(estiloRegistro[11]);
                
                switch (menu[i][3]) {
                    
                    case "D":
                        
                        celda = fila.createCell(10);
                        celda.setCellStyle(estiloRegistro[10]);
                        celda.setCellValue("x");
                        break;
                        
                    case "F":
                        
                        celda = fila.createCell(11);
                        celda.setCellStyle(estiloRegistro[11]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(12).setCellStyle(estiloRegistro[12]);
                fila.createCell(13).setCellStyle(estiloRegistro[13]);
                fila.createCell(14).setCellStyle(estiloRegistro[14]);
                
                switch (menu[i][4]) {
                    
                    case "C":
                        
                        celda = fila.createCell(12);
                        celda.setCellStyle(estiloRegistro[12]);
                        celda.setCellValue("x");
                        break;
                        
                    case "D":
                        
                        celda = fila.createCell(13);
                        celda.setCellStyle(estiloRegistro[13]);
                        celda.setCellValue("x");
                        break;
                        
                    case "P":
                        
                        celda = fila.createCell(14);
                        celda.setCellStyle(estiloRegistro[14]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                celda = fila.createCell(15);
                celda.setCellStyle(estiloRegistro[15]);
                celda.setCellValue(menu[i][5]);
                
                celda = fila.createCell(16);
                celda.setCellStyle(estiloRegistro[16]);
                celda.setCellValue(menu[i][6]);
                
                celda = fila.createCell(17);
                celda.setCellStyle(estiloRegistro[17]);
                celda.setCellValue(menu[i][7]);
            
                fila.createCell(18).setCellStyle(estiloRegistro[18]);
                fila.createCell(19).setCellStyle(estiloRegistro[19]);
                
                switch (menu[i][8]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(18);
                        celda.setCellStyle(estiloRegistro[18]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(19);
                        celda.setCellStyle(estiloRegistro[19]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(20).setCellStyle(estiloRegistro[20]);
                fila.createCell(21).setCellStyle(estiloRegistro[21]);
                
                switch (menu[i][9]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(20);
                        celda.setCellStyle(estiloRegistro[20]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(21);
                        celda.setCellStyle(estiloRegistro[21]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(22).setCellStyle(estiloRegistro[22]);
                fila.createCell(23).setCellStyle(estiloRegistro[23]);
                
                switch (menu[i][10]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(22);
                        celda.setCellStyle(estiloRegistro[22]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(23);
                        celda.setCellStyle(estiloRegistro[23]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(24).setCellStyle(estiloRegistro[24]);
                fila.createCell(25).setCellStyle(estiloRegistro[25]);
                
                switch (menu[i][11]) {
                    
                    case "Si":
                        
                        celda = fila.createCell(24);
                        celda.setCellStyle(estiloRegistro[24]);
                        celda.setCellValue("x");
                        break;
                        
                    case "No":
                        
                        celda = fila.createCell(25);
                        celda.setCellStyle(estiloRegistro[25]);
                        celda.setCellValue("x");
                        break;
                        
                }
                
                fila.createCell(26).setCellStyle(estiloRegistro[26]);
                fila.createCell(27).setCellStyle(estiloRegistro[27]);
                
            }
            
            int cont=1;
            
            for(int i=1;i<menu.length;i++){
                
                if(Integer.parseInt(menu[i][12])!=Integer.parseInt(menu[i-1][12])){
                    
                    cont++;
                    
                }
                
            }
            
            int[] registroInicial = new int[cont];
            int[] registroFinal = new int[cont];
            boolean[] logroElAprendizaje = new boolean[cont];
            cont=0;
            
            registroInicial[cont]=0;
            registroFinal[cont]=0;
            
            logroElAprendizaje[cont]=true;
            
            if("No".equals(menu[0][13])){
                
                logroElAprendizaje[cont]=false;
                
            }
            
            for(int i=1;i<menu.length;i++){
                
                if(Integer.parseInt(menu[i][12])==Integer.parseInt(menu[i-1][12])){
                    
                    registroFinal[cont]=i;
                    
                    if("No".equals(menu[i][13])){
                        
                        logroElAprendizaje[cont]=false;
                        
                    }
                    
                }else{
                    
                    cont++;
                    registroInicial[cont]=registroFinal[cont-1]+1;
                    registroFinal[cont]=registroFinal[cont-1]+1;
                    logroElAprendizaje[cont]=true;
                    
                    if("No".equals(menu[i][13])){
                        
                        logroElAprendizaje[cont]=false;

                    }
                    
                }
                
            }
            
            for(int i=0;i<=cont;i++){
                
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],1,1));
                hoja.getRow(20+registroInicial[i]).getCell(1).setCellValue(i+1);
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],2,4));
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],26,26));
                hoja.addMergedRegion(new CellRangeAddress(20+registroInicial[i],20+registroFinal[i],27,27));
                
                if(logroElAprendizaje[i]){
                    
                    hoja.getRow(20+registroInicial[i]).getCell(26).setCellValue("x");
                    
                }else{
                    
                    hoja.getRow(20+registroInicial[i]).getCell(27).setCellValue("x");
                    
                }
                
            }
            
            for(int i=0;i<celdaTexto.length;i++){
            
                fila = hoja.createRow(i+20+menu.length);
                fila.setHeightInPoints((float) 40.5);

                for(int j=0;j<celdaTexto[0].length;j++){

                    celda = fila.createCell(j);
                    celda.setCellValue(celdaTexto[i][j]);
                    celda.setCellStyle(celdaEstilo[i][j]);
                    

                }
            
            }
            
            hoja.addMergedRegion(new CellRangeAddress(20+menu.length,20+menu.length,1,14));
            hoja.addMergedRegion(new CellRangeAddress(21+menu.length,21+menu.length,1,14));
            hoja.addMergedRegion(new CellRangeAddress(22+menu.length,22+menu.length,1,14));
            hoja.addMergedRegion(new CellRangeAddress(20+menu.length,20+menu.length,15,27));
            hoja.addMergedRegion(new CellRangeAddress(21+menu.length,21+menu.length,15,27));

            int resp;
        
            resp=abrirArchivo.showSaveDialog(null);

            if (resp==JFileChooser.APPROVE_OPTION){

                String extencion = abrirArchivo.getSelectedFile().toString();
                
                if (!".xls".equals(extencion.substring(extencion.length()-4))) {
                    
                    extencion = extencion+".xls";
                    
                }
                
                File direccion = new File (extencion);

                FileOutputStream salida = new FileOutputStream(direccion);

                excel.write(salida);
                excel.close();
                
                getDesktop().edit(direccion);
                
            }   
            
        } catch (FileNotFoundException  ex) {
            
            System.out.println(ex);
        
        } catch (IOException  ex) {
              
            System.out.println(ex);
          
        }
    
    }
    
}
