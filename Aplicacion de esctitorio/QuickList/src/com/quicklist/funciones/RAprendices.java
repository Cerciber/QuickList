/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Ficha;
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
import javax.swing.JOptionPane;
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
public class RAprendices {

    JFileChooser abrirArchivo = new JFileChooser();

    public void porFicha(Statement declaracion, String usuario, String ficha) {

        try {

            FileInputStream file = new FileInputStream(new File(
                    "src/com/quicklist/formatos/ReporteDeAprendices.xls"));
            HSSFWorkbook excel = new HSSFWorkbook(file);

            HSSFSheet hoja = (HSSFSheet) excel.getSheetAt(0);

            String[][] menu = Ficha.SeleccionarDatosReporteAprendices(declaracion, ficha);

            HSSFRow fila = hoja.getRow(1);
            HSSFCell celda = fila.getCell(2);
            celda.setCellValue(menu[0][0]);

            fila = hoja.getRow(2);
            celda = fila.getCell(2);
            celda.setCellValue(menu[0][1]);

            fila = hoja.getRow(3);
            celda = fila.getCell(2);
            celda.setCellValue(menu[0][2]);

            menu = Ficha.SeleccionarResgistrosReporteAprendices(declaracion, ficha);
            HSSFCellStyle estiloRegistro = hoja.getRow(5).getCell(1).getCellStyle();

            for (int i = 0; i < menu.length; i++) {

                try {

                    fila = hoja.getRow(i + 5);

                    celda = fila.createCell(0);
                    celda.setCellValue(menu[i][0]);
                    celda.setCellStyle(estiloRegistro);

                    celda = fila.createCell(1);
                    celda.setCellValue(menu[i][1]);
                    celda.setCellStyle(estiloRegistro);

                    celda = fila.createCell(2);
                    celda.setCellValue(menu[i][2]);
                    celda.setCellStyle(estiloRegistro);

                    celda = fila.createCell(3);
                    celda.setCellValue(menu[i][3]);
                    celda.setCellStyle(estiloRegistro);

                    celda = fila.createCell(4);
                    celda.setCellValue(menu[i][4]);
                    celda.setCellStyle(estiloRegistro);

                    celda = fila.createCell(5);
                    celda.setCellValue(menu[i][5]);
                    celda.setCellStyle(estiloRegistro);

                    celda = fila.createCell(6);
                    celda.setCellValue(menu[i][6]);
                    celda.setCellStyle(estiloRegistro);

                } catch (NullPointerException ex) {
                }

            }

            int resp;

            resp = abrirArchivo.showSaveDialog(null);

            if (resp == JFileChooser.APPROVE_OPTION) {

                String extencion = abrirArchivo.getSelectedFile().toString();

                if (!".xls".equals(extencion.substring(extencion.length() - 4))) {

                    extencion = extencion + ".xls";

                }

                File direccion = new File(extencion);

                FileOutputStream salida = new FileOutputStream(direccion);

                excel.write(salida);
                excel.close();

                getDesktop().edit(direccion);

            }

        } catch (FileNotFoundException ex) {

            System.out.println(ex);

        } catch (IOException ex) {

            System.out.println(ex);

        }catch (ArrayIndexOutOfBoundsException ex) {

            /* Se muestra un mensaje de error */
            JOptionPane.showMessageDialog(null,
            "No existen registros para generar el formato(*)", "Error",
            JOptionPane.ERROR_MESSAGE);

        }
        
    }
}
