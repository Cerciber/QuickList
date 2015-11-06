/*
 * PantallasAprendiz.java
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

package com.quicklist.funciones;

import com.quicklist.FormAprendiz;
import com.quicklist.FormFuncionarios;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.PantallaUsuario;
import com.quicklist.ReportarExcusa;
import com.quicklist.clases.ActividadDeAprendizaje;
import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Competencia;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Horario;
import com.quicklist.clases.Inasistencia;
import com.quicklist.clases.ResultadoDeAprendizaje;

/**
 *
 * @author pabloycesar
 */
public class PantallasAprendiz {
    
    int usuario;
    ResultSet resultado;
    
    public PantallasAprendiz(String tipo,JPanel panelContenedor, String nombreClase, String pantallaActual, String usuario,String[] ID,Statement declaracion){
    
            if("EditarMisDatos".equals(nombreClase)){

                FormAprendiz p = new FormAprendiz(tipo,pantallaActual,nombreClase,usuario,Arreglo.agregar(ID, usuario),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("T_Aprendiz".equals(nombreClase)){

                String[] menu={"Asistencia","Formato Etapa Lectiva"};
                String[] vinculo={"Aprendiz.Asistencia","Aprendiz.FormatoEtapaLectiva"};
                String retorno="PantallaInicio";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,"Aprendiz",usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoInicial();

            }
            
            if("Aprendiz".equals(nombreClase)){

                String[] menu={"Asistencia","Formato Etapa Lectiva"};
                String[] vinculo={"Aprendiz.Asistencia","Aprendiz.FormatoEtapaLectiva"};
                String retorno="PantallaInicio";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia".equals(nombreClase)){

                String[] menu={"Asistencia por horario","Buscar en Plan de Estudios"};
                String[] vinculo={"Aprendiz.Asistencia.Horario","Aprendiz.Asistencia.Competencia"};
                String retorno="Aprendiz";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia.Horario".equals(nombreClase)){

                String[][] menu=Horario.SeleccionarActualPorAprendiz(declaracion, usuario);
                String[] nombreBotones={"Formaciones"};
                String[] nombreIcono={"Formaciones"};
                String[] columna={"","instructor","Dia Semana","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[] vinculo={"Aprendiz.Asistencia.Horario.Descripcion"};
                String retorno="Aprendiz.Asistencia";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
//            
            if("Aprendiz.Asistencia.Horario.Descripcion".equals(nombreClase)){

                String[][] menu=Inasistencia.SeleccionarPorHorario(declaracion,usuario,ID[ID.length-1]);
                String[] nombreBotones={"Excusa"};
                String[] nombreIcono={"Excusa"};
                String[] columna={"","Fecha","Estado","Justificacion"};
                String[] vinculo={"Aprendiz.Asistencia.Horario.Descripcion.ReportarExcusa"};
                String retorno="Aprendiz.Asistencia.Horario";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            
            if("Aprendiz.Asistencia.Horario.Descripcion.ReportarExcusa".equals(nombreClase)){

                new ReportarExcusa(declaracion,ID[ID.length-1]).setVisible(true);
                new PantallasAprendiz(tipo,panelContenedor,"Aprendiz.Asistencia.Horario.Descripcion",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            
            if("Aprendiz.Asistencia.Competencia".equals(nombreClase)){

                String[][] menu=Competencia.SeleccionarPorAprendiz(declaracion,usuario);
                String[] nombreBotones={"Actividades De Aprendizaje"};
                String[] nombreIcono={"Actividades De Aprendizaje"};
                String[] columna={"","Competencia_A_Desarrollar"};
                String[] vinculo={"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje"};
                String retorno="Aprendiz.Asistencia";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje".equals(nombreClase)){

                String[][] menu=ActividadDeAprendizaje.SeleccionarPorCompetencia(declaracion, ID[ID.length-1]);
                String[] nombreBotones={"Resultado De Aprendizaje"};
                String[] nombreIcono={"Resultado De Aprendizaje"};
                String[] columna={"","Fase_Del_Proyecto","Actividad_De_Aprendizaje"};
                String[] vinculo={"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje"};
                String retorno="Aprendiz.Asistencia.Competencia";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje".equals(nombreClase)){

                String[][] menu=ResultadoDeAprendizaje.SeleccionarPorActividadDeAprendizaje(declaracion,ID[ID.length-1]);
                String[] nombreBotones={"Horario"};
                String[] nombreIcono={"Horarios"};
                String[] columna={"","Resultado_De_Aprendizaje"};
                String[] vinculo={"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario"};
                String retorno="Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario".equals(nombreClase)){

                String[][] menu=Horario.SeleccionarPorResultadoDeAprendizaje(declaracion, ID[ID.length-1]);
                String[] nombreBotones={"Formaciones"};
                String[] nombreIcono={"Formaciones"};
                String[] columna={"","Instructor","Dia Semana","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[] vinculo={"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia"};
                String retorno="Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia".equals(nombreClase)){

                String[][] menu=Inasistencia.SeleccionarPorHorario(declaracion, usuario, ID[ID.length-1]);
                String[] nombreBotones={"Excusa"};
                String[] nombreIcono={"Excusa"};
                String[] columna={"","Fecha","Estado","Justificacion"};
                String[] vinculo={"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia.ReportarExcusa"};
                String retorno="Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia.ReportarExcusa".equals(nombreClase)){

                new ReportarExcusa(declaracion,ID[ID.length-1]).setVisible(true);
                new PantallasAprendiz(tipo,panelContenedor,"Aprendiz.Asistencia.Competencia.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Asistencia",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Aprendiz.FormatoEtapaLectiva".equals(nombreClase)){

                String[] menu={"Obtener formato en blanco","Formato por Instructor","Formato por horario","Buscar en Plan de Estudios"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.FormatoEnBlanco","Aprendiz.FormatoEtapaLectiva.Instructor","Aprendiz.FormatoEtapaLectiva.Horario","Aprendiz.FormatoEtapaLectiva.Competencias"};
                String retorno="Aprendiz";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.FormatoEtapaLectiva.FormatoEnBlanco".equals(nombreClase)){

                new RFormatoDeEtapaLectiva().enBlanco();
                new PantallasAprendiz(tipo,panelContenedor,"Aprendiz.FormatoEtapaLectiva",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Aprendiz.FormatoEtapaLectiva.Instructor".equals(nombreClase)){

                String[][] menu=Funcionario.SeleccionarPorAprendiz(declaracion, usuario);
                String[] nombreBotones={"Formato De Etapa Lectiva"};
                String[] nombreIcono={"Formato"};
                String[] columna={"","Nombre","Primer Apellido","Segundo Apellido","cargo","Correo Electronico","Telefono","Celular"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.Instructor.Obtener"};
                String retorno="Aprendiz.FormatoEtapaLectiva";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                
            }
            
            if("Aprendiz.FormatoEtapaLectiva.Instructor.Obtener".equals(nombreClase)){

                new RFormatoDeEtapaLectiva().porInstructor(declaracion,usuario,ID[ID.length-1]);
                new PantallasAprendiz(tipo,panelContenedor,"Aprendiz.FormatoEtapaLectiva",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Aprendiz.FormatoEtapaLectiva.Horario".equals(nombreClase)){

                String[][] menu=Horario.SeleccionarActualPorAprendiz(declaracion, usuario);
                String[] nombreBotones={"Formato De Etapa Lectiva"};
                String[] nombreIcono={"Formato"};
                String[] columna={"","instructor","Dia","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.Horario.Obtener"};
                String retorno="Aprendiz.FormatoEtapaLectiva";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                
            }
            
            if("Aprendiz.FormatoEtapaLectiva.Horario.Obtener".equals(nombreClase)){

                new RFormatoDeEtapaLectiva().porHorario(declaracion,usuario,ID[ID.length-1]);
                new PantallasAprendiz(tipo,panelContenedor,"Aprendiz.FormatoEtapaLectiva",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Aprendiz.FormatoEtapaLectiva.Competencias".equals(nombreClase)){

                String[][] menu=Competencia.SeleccionarPorAprendiz(declaracion,usuario);
                String[] nombreBotones={"Actividades De Aprendizaje"};
                String[] nombreIcono={"Actividades De Aprendizaje"};
                String[] columna={"","Competencia A Desarrollar"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje"};
                String retorno="Aprendiz.FormatoEtapaLectiva";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje".equals(nombreClase)){

                String[][] menu=ActividadDeAprendizaje.SeleccionarPorCompetencia(declaracion, ID[ID.length-1]);
                String[] nombreBotones={"Resultado De Aprendizaje"};
                String[] nombreIcono={"Resultado De Aprendizaje"};
                String[] columna={"","Fase","Actividad De Aprendizaje"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje"};
                String retorno="Aprendiz.FormatoEtapaLectiva.Competencias";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje".equals(nombreClase)){

                String[][] menu=ResultadoDeAprendizaje.SeleccionarPorActividadDeAprendizaje(declaracion,ID[ID.length-1]);
                String[] nombreBotones={"Horario"};
                String[] nombreIcono={"Horarios"};
                String[] columna={"","Resultado De Aprendizaje"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario"};
                String retorno="Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario".equals(nombreClase)){

                String[][] menu=Horario.SeleccionarPorResultadoDeAprendizaje(declaracion,ID[ID.length-1]);
                String[] nombreBotones={"Formato De Etapa Lactiva"};
                String[] nombreIcono={"Formato"};
                String[] columna={"","instructor","Dia","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[] vinculo={"Aprendiz.FormatoEtapaLectiva.Horario.Obtener"};
                String retorno="Aprendiz.FormatoEtapaLectiva";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
    
            if("Aprendiz.FormatoEtapaLectiva.Competencias.ActividadDeAprendizaje.ResultadoDeAprendizaje.Horario.Obtener".equals(nombreClase)){

                new RFormatoDeEtapaLectiva().porHorario(declaracion,usuario,ID[ID.length-1]);
                new PantallasAprendiz(tipo,panelContenedor,"Aprendiz.FormatoEtapaLectiva",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
    }
    
}
