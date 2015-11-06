/*
 * PantallasInstructor.java
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

import com.quicklist.AprobarActividades;
import com.quicklist.Confirmacion;
import com.quicklist.FormActividad;
import com.quicklist.FormAprendiz;
import com.quicklist.FormFormacion;
import com.quicklist.FormFuncionarios;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.PantallaUsuario;
import com.quicklist.TomarAsistencia;
import com.quicklist.clases.Actividad;
import com.quicklist.clases.Ficha;
import com.quicklist.clases.Formacion;
import com.quicklist.clases.Horario;
import com.quicklist.clases.ResultadoDeAprendizaje;

/**
 *
 * @author pabloycesar
 */
public class PantallasInstructor {
    
    int usuario;
    ResultSet resultado;
    
    public PantallasInstructor(String tipo,JPanel panelContenedor, String nombreClase,String pantallaActual, String usuario,String[] ID,Statement declaracion){

            if("EditarMisDatos".equals(nombreClase)){
                
                FormFuncionarios p = new FormFuncionarios(tipo,pantallaActual,nombreClase,usuario,Arreglo.agregar(ID, usuario),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("T_Instructor".equals(nombreClase)){

                
                String[] menu={"Seleccionar Ficha","Ver Horario"};
                String[] vinculo={"Instructor.Fichas","Instructor.Horario"};
                String retorno="PantallaInicio";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,"Instructor",usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoInicial();

            }
            
            if("Instructor".equals(nombreClase)){

                String[] menu={"Seleccionar Ficha","Ver Horario"};
                String[] vinculo={"Instructor.Fichas","Instructor.Horario"};
                String retorno="PantallaInicio";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas".equals(nombreClase)){
                
                String[][] menu=Ficha.SeleccionarPorInstructor(declaracion, usuario);
                String[] nombreBotones={"Ver Ficha"};
                String[] nombreIcono={"Ficha"};
                String[] columna={"","Ficha"};
                String[] vinculo={"Instructor.Fichas.Menu"};
                String retorno="Instructor";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas.Menu".equals(nombreClase)){

                String[] menu={"Horarios","Reporte de aprendices"};
                String[] vinculo={"Instructor.Fichas.Horario","Instructor.Fichas.Reporte"};
                String retorno="Instructor.Fichas";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas.Reporte".equals(nombreClase)){

                new RAprendices().porFicha(declaracion, usuario, ID[ID.length-1]);
                new PantallasInstructor(tipo,panelContenedor,"Instructor.Fichas.Menu",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Instructor.Fichas.Horario".equals(nombreClase)){

                String[] nombreBotones={"Ver Horario"};
                String[] nombreIcono={"Horarios"};
                String[] columna={"","Ficha","Instructor","Dia","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[][] menu=Horario.SeleccionarActualPorFichaInstructor(declaracion, usuario, ID[ID.length-1]);
                String[] vinculo={"Instructor.Fichas.Horario.Menu"};
                String retorno="Instructor.Fichas.Menu";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas.Horario.Menu".equals(nombreClase)){

                String[] menu={"Asistencia","Formato Etapa Lectiva"};
                String[] vinculo={"Instructor.Fichas.Horario.Menu.Asistencia","Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva"};
                String retorno="Instructor.Fichas.Horario";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia".equals(nombreClase)){

                String[] menu={"Ver Asistencias","Tomar Asistencia","Reporte de Asistencia"};
                String[] vinculo={"Instructor.Fichas.Horario.Menu.Asistencia.Ver","Instructor.Fichas.Horario.Menu.Asistencia.Ingresar","Instructor.Fichas.Horario.Menu.Asistencia.Reporte"};
                String retorno="Instructor.Fichas.Horario.Menu";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Reporte".equals(nombreClase)){

                new RFormatoDeInasistencia().porTrimestre(declaracion, ID[ID.length-1]);
                new PantallasInstructor(tipo,panelContenedor,"Instructor.Fichas.Horario.Menu.Asistencia",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar"};
                String[] nombreIcono={"Editar","Borrar"};
                String[] columna={"","","Fecha"};
                String[][] menu=Formacion.SeleccionarPorHorario(declaracion, ID[ID.length-1]);
                String[] vinculo={"Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar","Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar"};
                String retorno="Instructor.Fichas.Horario.Menu.Asistencia";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar".equals(nombreClase)){

                String retorno="Instructor.Fichas.Horario.Menu.Asistencia.Ver";
                String vinculo="Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar.siguiente";
                
                FormFormacion p = new FormFormacion(tipo,vinculo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar.siguiente".equals(nombreClase)){
                
                String retorno="Instructor.Fichas.Horario.Menu.Asistencia.Ver.Editar";
                String vinculo="Instructor.Fichas.Horario.Menu.Asistencia.Ver";
                
                TomarAsistencia p = new TomarAsistencia(tipo,vinculo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar.Confirmado","Instructor.Fichas.Horario.Menu.Asistencia.Ver"};
                String retorno="Instructor.Fichas.Horario.Menu.Asistencia.Ver";
                String pregunta="¿Desea eliminar la Formación "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ver.Borrar.Confirmado".equals(nombreClase)){

                Formacion.BorrarPorID(declaracion, ID[ID.length-1]);
                new PantallasInstructor(tipo,panelContenedor,"Instructor.Fichas.Horario.Menu.Asistencia.Ver",nombreClase,usuario,Arreglo.quitar(ID),declaracion);

            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ingresar".equals(nombreClase)){
                
                String retorno="Instructor.Fichas.Horario.Menu.Asistencia";
                String vinculo="Instructor.Fichas.Horario.Menu.Asistencia.Ingresar.Siguiente";
                
                FormFormacion p = new FormFormacion(tipo,vinculo,retorno,nombreClase,usuario,Arreglo.agregar(Arreglo.quitar(ID),"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Instructor.Fichas.Horario.Menu.Asistencia.Ingresar.Siguiente".equals(nombreClase)){
                
                String retorno="Instructor.Fichas.Horario.Menu.Asistencia.Ingresar";
                String vinculo="Instructor.Fichas.Horario.Menu.Asistencia.Ver";
                
                TomarAsistencia p = new TomarAsistencia(tipo,vinculo,retorno,nombreClase,usuario,(ID),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();
                
            }
            
            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva".equals(nombreClase)){

                String[] menu={"Ver Actividades","Ingresar actividad"};
                String[] vinculo={"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver","Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ingresar"};
                String retorno="Instructor.Fichas.Horario.Menu";
                
                PantallaUsuario p = new PantallaUsuario(tipo,menu,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
            
            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver".equals(nombreClase)){

                String[] nombreBotones={"Editar","Borrar","Aprobar"};
                String[] nombreIcono={"Editar","Borrar","Aprobar"};
                String[] columna={"","","","Nombre Actividad","Nombre Evidencia","Medio","Tipo","Fecha Recoleccion De Evidencia"};
                String[][] menu=Actividad.SeleccionarPorHorario(declaracion,ID[ID.length-1]);
                String[] vinculo={"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Editar","Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar","Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Aprobar"};
                String retorno="Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
                

            }
            
            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Editar".equals(nombreClase)){

                String retorno="Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";
                
                FormActividad p = new FormActividad(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar".equals(nombreClase)){

                String[] vinculo={"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado","Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver"};
                String retorno="Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";
                String pregunta="¿Desea eliminar la Actividad "+ID[ID.length-1]+"?";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado".equals(nombreClase)){

                String[] vinculo={"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado.Confirmado","Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver"};
                String retorno="Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";
                String pregunta="Al eliminarlo se borraran todos los datos ligados a este usuario";
               
                Confirmacion p = new Confirmacion(tipo,retorno,nombreClase,usuario,ID,declaracion,pregunta,vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)){
                
                Actividad.BorrarPorID(declaracion, ID[ID.length-1]);
                new PantallasInstructor(tipo,panelContenedor,"Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver",nombreClase,usuario,Arreglo.quitar(Arreglo.quitar(Arreglo.quitar(ID))),declaracion);
            
            }

            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ingresar".equals(nombreClase)){

                String retorno="Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva";
                
                FormActividad p = new FormActividad(tipo,retorno,nombreClase,usuario,Arreglo.agregar(ID,"☺"),declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if("Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver.Aprobar".equals(nombreClase)){

                String retorno="Instructor.Fichas.Horario.Menu.FormatoEtapaLectiva.Ver";
                
                AprobarActividades p = new AprobarActividades(tipo,retorno,nombreClase,usuario,ID,declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
           
            if("Instructor.Horario".equals(nombreClase)){

                String[] nombreBotones={"Ir al Horario"};
                String[] nombreIcono={"Horarios"};
                String[] columna={"","Ficha","Dia","Hora Inicio","Hora Fin","Fecha Inicio","Fecha Fin","Lugar","Resultado De Aprendizaje"};
                String[][] menu=Horario.SeleccionarPorInstructor(declaracion, usuario);
                String[] vinculo={"Instructor.Fichas.Horario.Menu"};
                String retorno="Instructor";

                PantallaUsuario p = new PantallaUsuario(tipo,menu,nombreBotones,nombreIcono,columna,vinculo,retorno,nombreClase,usuario,declaracion,ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }
    
    }
    
}
