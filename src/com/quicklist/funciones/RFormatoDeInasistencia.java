/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Horario;
import com.quicklist.clases.Inasistencia;
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
public class RFormatoDeInasistencia {
    
    JFileChooser abrirArchivo = new JFileChooser();
    
    public void porTrimestre(Statement declaracion, String horario){
    
        try {
            
            FileInputStream file = new FileInputStream(new File(
                    "src/com/quicklist/formatos/FormatoDeInasistencia.xls"));
            HSSFWorkbook excel = new HSSFWorkbook(file);
            
            HSSFSheet hoja = (HSSFSheet) excel.getSheetAt(0);
            
            String[][] celdaTexto = new String[2][31];
            HSSFCellStyle[][] celdaEstilo = new HSSFCellStyle[2][31];
            
            HSSFRow fila;
            
            for(int i=0;i<celdaTexto.length;i++){
                
                fila=hoja.getRow(i+19);
                
                for(int j=0;j<celdaTexto[0].length;j++){
                    
                    try{
                        
                        celdaTexto[i][j]=fila.getCell(j).getStringCellValue();
                        celdaEstilo[i][j]=fila.getCell(j).getCellStyle();
                                
                    }catch(NullPointerException ex){
                        
                        celdaTexto[i][j]="";
                    
                    }
                    
                }
                
            }
            
            String[][] menu =  Horario.SeleccionarDatosFormatoDeAsistencia(declaracion, horario);
            
            fila = hoja.getRow(8);
            HSSFCell celda = fila.getCell(0);
            celda.setCellValue("Programa de Formación: "+menu[0][0]);
            
            fila = hoja.getRow(8);
            celda = fila.getCell(20);
            celda.setCellValue(menu[0][1]);
            
            fila = hoja.getRow(9);
            celda = fila.getCell(0);
            celda.setCellValue("Competencia: "+menu[0][2]);
            
            fila = hoja.getRow(9);
            celda = fila.getCell(20);
            celda.setCellValue(menu[0][3]);
            
            fila = hoja.getRow(10);
            celda = fila.getCell(0);
            celda.setCellValue("Resultado de Aprendizaje: "+menu[0][4]);
            
            fila = hoja.getRow(11);
            celda = fila.getCell(6);
            celda.setCellValue(menu[0][5]);
            
            fila = hoja.getRow(11);
            celda = fila.getCell(22);
            celda.setCellValue(menu[0][6]);
            
            fila = hoja.getRow(12);
            celda = fila.getCell(1);
            celda.setCellValue(menu[0][7]);
            
            fila = hoja.getRow(12);
            celda = fila.getCell(19);
            celda.setCellValue(menu[0][8]);
            
            fila = hoja.getRow(13);
            celda = fila.getCell(1);
            celda.setCellValue(menu[0][9]);
            
            menu =  Horario.SeleccionarFechasFormatoDeAsistencia(declaracion, horario);
            
            for (int i=0;i<menu.length;i++){
                
                fila = hoja.getRow(14);
                celda = fila.getCell(i*2+3);
                celda.setCellValue(menu[i][0]);
                
            }
            
            menu =  Aprendiz.SeleccionarDatosFormatoDeAsistencia(declaracion, horario);
            String[][] menu2;
            HSSFCellStyle[] estiloRegistro = new HSSFCellStyle[31];
            
            for (int i=0;i<estiloRegistro.length;i++){
                
                fila = hoja.getRow(17);
                celda = fila.getCell(i);
                estiloRegistro[i] = celda.getCellStyle();
                
            }
            
            for (int i=0;i<menu.length;i++){
                
                fila = hoja.createRow(i+17);
                
                celda = fila.createCell(0);
                celda.setCellValue(menu[i][0]);
                celda.setCellStyle(estiloRegistro[0]);
                
                celda = fila.createCell(1);
                celda.setCellValue(menu[i][1]);
                celda.setCellStyle(estiloRegistro[1]);
                
                celda = fila.createCell(2);
                celda.setCellValue(menu[i][2]);
                celda.setCellStyle(estiloRegistro[2]);
                
                for(int j=3;j<31;j++){

                    celda = fila.createCell(j);
                    celda.setCellStyle(estiloRegistro[j]);

                }
                
                menu2=Inasistencia.SeleccionarPorAprendiz(declaracion, menu[i][0]);
                
                for(int j=0;j<menu2.length;j++){

                    if("CE".equals(menu2[j][0])){
                        
                        celda = fila.getCell(j*2+3);
                        celda.setCellValue("x");
                        
                    }else if("SE".equals(menu2[j][0])){
                        
                        celda = fila.getCell(j*2+4);
                        celda.setCellValue("x");
                        
                    }                      

                }
                
            }
            
            for(int i=0;i<celdaTexto.length;i++){
            
                fila = hoja.createRow(i+19+menu.length-1);

                for(int j=0;j<celdaTexto[0].length;j++){

                    celda = fila.createCell(j);
                    celda.setCellValue(celdaTexto[i][j]);
                    celda.setCellStyle(celdaEstilo[i][j]);

                }
            
            }
            
            hoja.addMergedRegion(new CellRangeAddress(19+menu.length-1,19+menu.length-1,0,1));
            hoja.addMergedRegion(new CellRangeAddress(19+menu.length-1,19+menu.length-1,2,9));
            hoja.addMergedRegion(new CellRangeAddress(19+menu.length-1,19+menu.length-1,10,19));
            hoja.addMergedRegion(new CellRangeAddress(19+menu.length-1,19+menu.length-1,20,30));
            hoja.addMergedRegion(new CellRangeAddress(20+menu.length-1,20+menu.length-1,2,11));
            
            int resp;
        
            resp=abrirArchivo.showSaveDialog(null);

            if (resp==JFileChooser.APPROVE_OPTION){

                File direccion = new File (abrirArchivo.getSelectedFile().toString()+".xls");

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
