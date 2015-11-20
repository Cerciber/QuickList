/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author cesaraugusto
 */
public class DarIcono {

    public static void darIcono(JButton boton, String nombreIcono) {

        ImageIcon icono = null;

        switch (nombreIcono) {

            case "Editar":

                icono = new ImageIcon("src/com/quicklist/iconos/Editar.png");

                break;

            case "Editar Usuario":

                icono = new ImageIcon("src/com/quicklist/iconos/Editar 1.png");

                break;

            case "Borrar":

                icono = new ImageIcon("src/com/quicklist/iconos/eliminar.png");

                break;

            case "Borrar Usuario":

                icono = new ImageIcon("src/com/quicklist/iconos/Eliminar 2.png");

                break;

            case "Aprendices":

                icono = new ImageIcon("src/com/quicklist/iconos/Aprendices 2.png");

                break;

            case "Horarios":

                icono = new ImageIcon("src/com/quicklist/iconos/Horario 1.png");

                break;

            case "Formaciones":

                icono = new ImageIcon("src/com/quicklist/iconos/Formacion.png");

                break;

            case "Actividades":

                icono = new ImageIcon("src/com/quicklist/iconos/Actividades.png");

                break;

            case "Asistencia":

                icono = new ImageIcon("src/com/quicklist/iconos/asistencia.png");

                break;

            case "Aprobar":

                icono = new ImageIcon("src/com/quicklist/iconos/aprobar.png");

                break;

            case "Competencias":

                icono = new ImageIcon("src/com/quicklist/iconos/Competencias.png");

                break;

            case "Actividades De Aprendizaje":

                icono = new ImageIcon("src/com/quicklist/iconos/Competencias.png");

                break;

            case "Resultado De Aprendizaje":

                icono = new ImageIcon("src/com/quicklist/iconos/Competencias.png");

                break;

            case "Excusa":

                icono = new ImageIcon("src/com/quicklist/iconos/excusa.png");

                break;

            case "Formato":

                icono = new ImageIcon("src/com/quicklist/iconos/Formato.png");

                break;

            case "Ficha":

                icono = new ImageIcon("src/com/quicklist/iconos/Ficha.png");

                break;

            case "Buscar":

                icono = new ImageIcon("src/com/quicklist/iconos/Buscar 2.png");

                break;
                
            case "Ver":

                icono = new ImageIcon("src/com/quicklist/iconos/Ver.png");

                break;

        }

        ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance(30, 30, 30));

        boton.setIcon(imageScalada);

    }

}
