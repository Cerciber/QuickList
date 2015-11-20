/*
 * PantallasAdministrador.java
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

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.AprobarActividades;
import com.quicklist.clases.Actividad;
import com.quicklist.clases.ActividadDeAprendizaje;
import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Competencia;
import com.quicklist.clases.Ficha;
import com.quicklist.clases.Formacion;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Horario;
import com.quicklist.clases.PlanDeEstudios;
import com.quicklist.clases.ResultadoDeAprendizaje;
import com.quicklist.Confirmacion;
import com.quicklist.FormActividad;
import com.quicklist.FormActividadDeAprendizaje;
import com.quicklist.FormAprendiz;
import com.quicklist.FormCompetencia;
import com.quicklist.FormFicha;
import com.quicklist.FormFormacion;
import com.quicklist.FormFuncionarios;
import com.quicklist.FormHorario;
import com.quicklist.FormPlanDeEstudios;
import com.quicklist.FormResultadoDeAprendizaje;
import com.quicklist.PantallaInicio;
import com.quicklist.PantallaUsuario;
import com.quicklist.TomarAsistencia;
import com.quicklist.clases.Consulta;
import com.quicklist.clases.Historial;

/**
 *
 * @author pabloycesar
 */
public class PantallasAdministrador {

    int usuario;
    ResultSet resultado;

    public PantallasAdministrador(String tipo, JPanel panelContenedor, String nombreClase, String pantallaActual, String usuario, String[] ID, Statement declaracion) {

        try {

            if ("EditarMisDatos".equals(nombreClase)) {

                FormFuncionarios p = new FormFuncionarios(tipo, pantallaActual, nombreClase, usuario, Arreglo.agregar(ID, usuario), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("T_Administrador".equals(nombreClase)) {

                String[] menu = {"Funcionarios", "Fichas", "Plan de estudios","Historial de movimiento"};
                String[] vinculo = {"Administrador.Funcionarios", "Administrador.Fichas", "Administrador.PlanDeEstudios","Administrador.Historial"};
                String retorno = "PantallaInicio";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, "Administrador", usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoInicial();

            }

            if ("Administrador".equals(nombreClase)) {

                String[] menu = {"Funcionarios", "Fichas", "Plan de estudios","Historial de movimiento"};
                String[] vinculo = {"Administrador.Funcionarios", "Administrador.Fichas", "Administrador.PlanDeEstudios","Administrador.Historial"};
                String retorno = "PantallaInicio";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Funcionarios".equals(nombreClase)) {

                String[] menu = {"Ver funcionarios", "Ingresar funcionario"};
                String[] vinculo = {"Administrador.Funcionarios.Ver", "Administrador.Funcionarios.Ingresar"};
                String retorno = "Administrador";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Funcionarios.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar"};
                String[] nombreIcono = {"Editar Usuario", "Borrar Usuario"};
                String[] columna = {"", "", "Documento", "Nombre", "Primer apellido", "Segundo apellido", "Cargo", "Correo electrónico", "Teléfono", "Celular"};
                String[] vinculo = {"Administrador.Funcionarios.Ver.Editar", "Administrador.Funcionarios.Ver.Borrar"};
                String retorno = "Administrador.Funcionarios";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Informacion_Funcionarios");
                String[] campos = {"Documento_De_Identidad", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "cargo", "Correo_Electronico", "Telefono_Fijo", "Telefono_Celular"};
                c.campos(campos);
                String[] alias = {"ID", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "cargo", "Correo_Electronico", "Telefono_Fijo", "Telefono_Celular"};
                c.alias(alias);
                c.columnaSeleccionada("Documento_De_Identidad");
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }

            if ("Administrador.Funcionarios.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Funcionarios.Ver";

                FormFuncionarios p = new FormFuncionarios(tipo, retorno, pantallaActual, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Funcionarios.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Funcionarios.Ver.Borrar"
                        + ".Confirmado", "Administrador.Funcionarios.Ver"};
                String retorno = "Administrador.Funcionarios.Ver";
                String pregunta = "¿Desea eliminar el registro " 
                        + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, 
                        nombreClase, usuario, ID, declaracion, pregunta,
                        vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Funcionarios.Ver.Borrar.Confirmado"
                    .equals(nombreClase)) {

                String[] vinculo = {"Administrador.Funcionarios.Ver.Borrar"
                        + ".Confirmado.Confirmado", "Administrador"
                        + ".Funcionarios.Ver"};
                String retorno = "Administrador.Funcionarios.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos "
                        + "ligados a éste usuario";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, 
                        usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Funcionarios.Ver.Borrar.Confirmado.Confirmado"
                    .equals(nombreClase)) {

                if (ID[ID.length - 1].equals(usuario)) {

                    String[] vinculo = {"Administrador.Funcionarios.Ver.Borrar"
                            + ".Confirmado.Confirmado.BorrarMiCuanta", 
                        "Administrador.Funcionarios.Ver"};
                    String retorno = "Administrador.Funcionarios.Ver";
                    String pregunta = "Está a punto de borrar su cuenta";

                    Confirmacion p = new Confirmacion(tipo, retorno, 
                            nombreClase, usuario, ID, declaracion, pregunta, 
                            vinculo);
                    panelContenedor.removeAll();
                    panelContenedor.add(p);
                    panelContenedor.validate();
                    p.movimiento();

                } else {

                    Funcionario.BorrarEnDocumento(declaracion, 
                            ID[ID.length - 1]);
                    Historial.Insertar(declaracion, usuario, "Eliminó un funcionario");

                    String[] nombreBotones = {"Editar", "Borrar"};
                    String[] nombreIcono = {"Editar Usuario", "Borrar Usuario"};
                    String[] columna = {"", "", "Documento", 
                        "Nombre", "Primer apellido", "segundo Apellido", 
                        "Cargo", "Correo electrónico", "Teléfono", "Celular"};
                    String[] vinculo = {"Administrador.Funcionarios.Ver.Editar",
                        "Administrador.Funcionarios.Ver.Borrar"};
                    String retorno = "Administrador.Funcionarios";

                    Consulta c = new Consulta(declaracion);
                    c.tabla("T_Informacion_Funcionarios");
                    String[] campos = {"Documento_De_Identidad", 
                        "Documento_De_Identidad", "Nombre",
                        "Primer_Apellido", "Segundo_Apellido", "cargo",
                        "Correo_Electronico", "Telefono_Fijo",
                        "Telefono_Celular"};
                    c.campos(campos);
                    String[] alias = {"ID", "Documento_De_Identidad", 
                        "Nombre", "Primer_Apellido", 
                        "Segundo_Apellido", "cargo", "Correo_Electronico", 
                        "Telefono_Fijo", "Telefono_Celular"};
                    c.alias(alias);
                    c.columnaSeleccionada("Documento_De_Identidad");
                    c.panelContenedor(panelContenedor);
                    String menu[][] = c.ejecutarConsulta();

                    PantallaUsuario p = new PantallaUsuario(tipo, menu, 
                            nombreBotones, nombreIcono, columna, vinculo, 
                            retorno, nombreClase, usuario, declaracion, 
                            Arreglo.quitar(ID), c);
                    panelContenedor.removeAll();
                    panelContenedor.add(p);
                    panelContenedor.validate();
                    p.movimientoSecuencial();

                }

            }

            if ("Administrador.Funcionarios.Ver.Borrar.Confirmado.Confirmado.BorrarMiCuanta".equals(nombreClase)) {

                Funcionario.BorrarEnDocumento(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó su propia cuenta");

                PantallaInicio p = new PantallaInicio();
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Funcionarios.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.Funcionarios";

                FormFuncionarios p = new FormFuncionarios(tipo, retorno, pantallaActual, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas".equals(nombreClase)) {

                String[] menu = {"Ver fichas", "Ingresar fichas"};
                String[] vinculo = {"Administrador.Fichas.Ver", "Administrador.Fichas.Ingresar"};
                String retorno = "Administrador";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar", "Aprendices", "Horarios"};
                String[] nombreIcono = {"Editar", "Borrar", "Aprendices", "Horarios"};
                String[] columna = {"", "", "", "", "Ficha", "Plan de estudios", "Fecha inicio", "Fecha Fin"};
                String[] vinculo = {"Administrador.Fichas.Ver.Editar", "Administrador.Fichas.Ver.Borrar", "Administrador.Fichas.Ver.Aprendices", "Administrador.Fichas.Ver.Horarios"};
                String retorno = "Administrador.Fichas";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Fichas join T_Plan_De_Estudios on T_Fichas.ID_Plan_De_Estudios=T_Plan_De_Estudios.ID_Plan_De_Estudios");
                String[] campos = {"Numero_De_Ficha", "Numero_De_Ficha", "Nombre_PlanDeEstudios", "Fecha_Inicio", "Fecha_Fin"};
                c.campos(campos);
                String[] alias = {"ID", "Numero_De_Ficha", "Nombre_PlanDeEstudios", "Fecha_Inicio", "Fecha_Fin"};
                c.alias(alias);
                c.columnaSeleccionada("Numero_De_Ficha");
                int[] nFechas = {3, 4};
                c.nFechas(nFechas);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }

            if ("Administrador.Fichas.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver";

                FormFicha p = new FormFicha(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Borrar.Confirmado", "Administrador.Fichas.Ver"};
                String retorno = "Administrador.Fichas.Ver";
                String pregunta = "¿Desea eliminar la ficha " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Borrar.Confirmado.Confirmado", "Administrador.Fichas.Ver"};
                String retorno = "Administrador.Fichas.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a esta ficha";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                Ficha.BorrarEnID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó una ficha");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.Fichas.Ver", pantallaActual, usuario, ID, declaracion);

            }

            if ("Administrador.Fichas.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas";

                FormFicha p = new FormFicha(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Aprendices".equals(nombreClase)) {

                String[] menu = {"Ver aprendices", "Ingresar aprendices"};
                String[] vinculo = {"Administrador.Fichas.Ver.Aprendices.Ver", "Administrador.Fichas.Ver.Aprendices.Ingresar"};
                String retorno = "Administrador.Fichas.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Aprendices.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar"};
                String[] nombreIcono = {"Editar Usuario", "Borrar Usuario"};
                String[] columna = {"", "", "Documento", "Nombre", "Primer apellido", "Segundo apellido", "Fecha de nacimiento", "Correo electrónico", "Género", "Ficha", "Teléfono", "Celular", "Estado", "Proyecto", "Estilos y ritmos de aprendizaje", "Estrategia metodológica de preferencia", "Caracteristicas culturales y sociales"};
                String[] vinculo = {"Administrador.Fichas.Ver.Aprendices.Ver.Editar", "Administrador.Fichas.Ver.Aprendices.Ver.Borrar"};
                String retorno = "Administrador.Fichas.Ver.Aprendices";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Informacion_Aprendices");
                String[] campos = {"Documento_De_Identidad", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "Fecha_De_Nacimiento", "Correo_Electronico", "Genero", "ID_Ficha", "Telefono_Fijo", "Telefono_Celular", "estado", "nombre_Proyecto", "Estilos_Y_Ritmos_De_Aprendizaje", "Estrategia_Metodológica_De_Preferencia", "Caracteristicas_Culturales_Y_Sociales"};
                c.campos(campos);
                String[] alias = {"ID", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "Fecha_De_Nacimiento", "Correo_Electronico", "Genero", "ID_Ficha", "Telefono_Fijo", "Telefono_Celular", "estado", "nombre_Proyecto", "Estilos_Y_Ritmos_De_Aprendizaje", "Estrategia_Metodológica_De_Preferencia", "Caracteristicas_Culturales_Y_Sociales"};
                c.alias(alias);
                c.columnaSeleccionada("Documento_De_Identidad");
                c.condicion("ID_Ficha = " + ID[ID.length - 1]);
                int[] nFechas = {5};
                c.nFechas(nFechas);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }

            if ("Administrador.Fichas.Ver.Aprendices.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Aprendices.Ver";

                FormAprendiz p = new FormAprendiz(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Aprendices.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado", "Administrador.Fichas.Ver.Aprendices.Ver"};
                String retorno = "Administrador.Fichas.Ver.Aprendices.Ver";
                String pregunta = "¿Desea eliminar el registro " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado.Confirmado", "Administrador.Fichas.Ver.Aprendices.Ver"};
                String retorno = "Administrador.Fichas.Ver.Aprendices.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a este usuario";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Aprendices.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                Aprendiz.BorrarEnDocumento(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó un aprendiz");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.Fichas.Ver.Aprendices.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.Fichas.Ver.Aprendices.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Aprendices";

                FormAprendiz p = new FormAprendiz(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios".equals(nombreClase)) {

                String[] menu = {"Ver horarios", "Ingresar horarios"};
                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver", "Administrador.Fichas.Ver.Horarios.Ingresar"};
                String retorno = "Administrador.Fichas.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar", "Actividades", "Asistencia"};
                String[] nombreIcono = {"Editar", "Borrar", "Actividades", "Asistencia"};
                String[] columna = {"", "", "", "", "instructor", "Día", "Hora inicio", "Hora fin", "Fecha inicio", "Fecha fin", "Lugar", "Resultado de aprendizaje"};
                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Editar", "Administrador.Fichas.Ver.Horarios.Ver.Borrar", "Administrador.Fichas.Ver.Horarios.Ver.Actividades", "Administrador.Fichas.Ver.Horarios.Ver.Asistencia"};
                String retorno = "Administrador.Fichas.Ver.Horarios";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Horario join T_Resultado_De_Aprendizaje on T_Horario.ID_Resultado_De_Aprendizaje=T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje join T_Informacion_Funcionarios on T_Horario.ID_Funcionario=T_Informacion_Funcionarios.Documento_De_Identidad");
                String[] campos = {"ID_Horario", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
                c.campos(campos);
                String[] alias = {"ID_Horario", "instructor", "Dia_Semana", "Hora_Inicio", "Hora_Fin", "Fecha_Inicio", "Fecha_Fin", "Lugar", "Resultado_De_Aprendizaje"};
                c.alias(alias);
                c.columnaSeleccionada("CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)");
                c.condicion("ID_Ficha = " + ID[ID.length - 1]);
                int[] nFechas = {5, 6};
                c.nFechas(nFechas);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver";

                FormHorario p = new FormHorario(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado", "Administrador.Fichas.Ver.Horarios.Ver"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver";
                String pregunta = "¿Desea eliminar el horario " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado.Confirmado", "Administrador.Fichas.Ver.Horarios.Ver"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a este horario";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                Horario.BorrarPorID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó un horario");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.Fichas.Ver.Horarios.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.Fichas.Ver.Horarios.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios";

                FormHorario p = new FormHorario(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades".equals(nombreClase)) {

                String[] menu = {"Ver actividades", "Ingresar actividad"};
                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver", "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ingresar"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar", "Aprobar"};
                String[] nombreIcono = {"Editar", "Borrar", "Aprobar"};
                String[] columna = {"", "", "", "Nombre actividad", "Nombre evidencia", "Medio", "Tipo", "Fecha recolección evidencia"};
                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Editar", "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar", "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Aprobar"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Actividades";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Actividades");
                String[] campos = {"ID_Actividad", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
                c.campos(campos);
                String[] alias = {"ID_Actividad", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_RecoleccionEvidencia"};
                c.alias(alias);
                c.columnaSeleccionada("Nombre_Actividad");
                c.condicion("ID_Horario = " + ID[ID.length - 1]);
                int[] nFechas = {5};
                c.nFechas(nFechas);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";

                FormActividad p = new FormActividad(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado", "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";
                String pregunta = "¿Desea eliminar la actividad " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado.Confirmado", "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a este usuario";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                Actividad.BorrarPorID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó una actividad");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Actividades";

                FormActividad p = new FormActividad(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver.Aprobar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Actividades.Ver";

                AprobarActividades p = new AprobarActividades(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia".equals(nombreClase)) {

                String[] menu = {"Ver asistencias", "Tomar asistencia"};
                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver", "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar"};
                String[] nombreIcono = {"Editar", "Borrar"};
                String[] columna = {"", "", "Fecha"};
                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar", "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Formacion");
                String[] campos = {"ID_Formacion", "Fecha"};
                c.campos(campos);
                String[] alias = {"ID_Formacion", "Fecha"};
                c.alias(alias);
                c.columnaSeleccionada("Fecha");
                c.condicion("ID_Horario = " + ID[ID.length - 1]);
                int[] nFechas = {1};
                c.nFechas(nFechas);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                String vinculo = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar.Siguiente";

                FormFormacion p = new FormFormacion(tipo, vinculo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Editar.Siguiente".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                String vinculo = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";

                TomarAsistencia p = new TomarAsistencia(tipo, vinculo, retorno, nombreClase, usuario, (ID), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar.Confirmado", "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver"};
                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";
                String pregunta = "¿Desea eliminar la formación " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver.Borrar.Confirmado".equals(nombreClase)) {

                Formacion.BorrarPorID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó una formación");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia";
                String vinculo = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar.Siguiente";

                FormFormacion p = new FormFormacion(tipo, vinculo, retorno, nombreClase, usuario, Arreglo.agregar(Arreglo.quitar(ID), "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar.Siguiente".equals(nombreClase)) {

                String retorno = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ingresar";
                String vinculo = "Administrador.Fichas.Ver.Horarios.Ver.Asistencia.Ver";

                TomarAsistencia p = new TomarAsistencia(tipo, vinculo, retorno, nombreClase, usuario, (ID), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios".equals(nombreClase)) {

                String[] menu = {"Ver plan de estudios", "Ingresar plan de estudios"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver", "Administrador.PlanDeEstudios.Ingresar"};
                String retorno = "Administrador";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar", "Competencias"};
                String[] nombreIcono = {"Editar", "Borrar", "Competencias"};
                String[] columna = {"", "", "", "Nombre", "Programa", "Versión", "Meses etapa lectiva", "Nivel de formación"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Editar", "Administrador.PlanDeEstudios.Ver.Borrar", "Administrador.PlanDeEstudios.Ver.Competencia"};
                String retorno = "Administrador.PlanDeEstudios";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Plan_De_Estudios");
                String[] campos = {"ID_Plan_De_Estudios", "Nombre_PlanDeEstudios", "Nombre_Programa", "Version_Plan_De_Estudios", "Meses_Etapa_Lectiva", "Nivel_De_Formacion"};
                c.campos(campos);
                String[] alias = {"ID_Plan_De_Estudios", "Nombre_PlanDeEstudios", "Nombre_Programa", "Version_Plan_De_Estudios", "Meses_Etapa_Lectiva", "Nivel_De_Formacion"};
                c.alias(alias);
                c.columnaSeleccionada("Nombre_PlanDeEstudios");
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);

                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver";

                FormPlanDeEstudios p = new FormPlanDeEstudios(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Borrar.Confirmado", "Administrador.PlanDeEstudios.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver";
                String pregunta = "¿Desea eliminar el plan de estudios " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Borrar.Confirmado.Confirmado", "Administrador.PlanDeEstudios.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a este plan de estudios";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                PlanDeEstudios.BorrarEnID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó un plan de estudios");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.PlanDeEstudios.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.PlanDeEstudios.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios";

                FormPlanDeEstudios p = new FormPlanDeEstudios(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia".equals(nombreClase)) {

                String[] menu = {"Ver competencias", "Ingresar competencia"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver", "Administrador.PlanDeEstudios.Ver.Competencia.Ingresar"};
                String retorno = "Administrador.PlanDeEstudios.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar", "Actividades de aprendizaje"};
                String[] nombreIcono = {"Editar", "Borrar", "Actividades De Aprendizaje"};
                String[] columna = {"", "", "", "Competencia a desarrollar"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.Editar", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Competencias");
                String[] campos = {"ID_Competencia", "Competencia_A_Desarrollar"};
                c.campos(campos);
                String[] alias = {"ID_Competencia", "Competencia_A_Desarrollar"};
                c.alias(alias);
                c.columnaSeleccionada("Competencia_A_Desarrollar");
                c.condicion("ID_Plan_De_Estudios = " + ID[ID.length - 1]);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver";

                FormCompetencia p = new FormCompetencia(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado", "Administrador.PlanDeEstudios.Ver.Competencia.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver";
                String pregunta = "¿Desea eliminar la competencia " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado.Confirmado", "Administrador.PlanDeEstudios.Ver.Competencia.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver";
                String pregunta = "Al eliminarlo se borraran todos los datos ligados a esta competencia";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                Competencia.BorrarEnID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó una competencia");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.PlanDeEstudios.Ver.Competencia.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia";

                FormCompetencia p = new FormCompetencia(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje".equals(nombreClase)) {

                String[] menu = {"Ver actividades de aprendizaje", "Ingresar actividad de aprendizaje"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ingresar"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar", "Resultado de aprendizaje"};
                String[] nombreIcono = {"Editar", "Borrar", "Resultado De Aprendizaje"};
                String[] columna = {"", "", "", "Fase del Proyecto", "Actividad de aprendizaje"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Editar", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Actividad_De_Aprendizaje");
                String[] campos = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
                c.campos(campos);
                String[] alias = {"ID_Actividad_De_Aprendizaje", "Fase_Del_Proyecto", "Actividad_De_Aprendizaje"};
                c.alias(alias);
                c.columnaSeleccionada("Fase_Del_Proyecto");
                c.condicion("ID_Competencia = " + ID[ID.length - 1]);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";

                FormActividadDeAprendizaje p = new FormActividadDeAprendizaje(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";
                String pregunta = "¿Desea eliminar la actividad de aprendizaje " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado.Confirmado", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a esta competencia";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                ActividadDeAprendizaje.BorrarEnID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó una actividad de aprendizaje");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje";

                FormActividadDeAprendizaje p = new FormActividadDeAprendizaje(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje".equals(nombreClase)) {

                String[] menu = {"Ver resultados de aprendizaje", "Ingresar resultado de aprendizaje"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ingresar"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver";

                PantallaUsuario p = new PantallaUsuario(tipo, menu, vinculo, retorno, nombreClase, usuario, declaracion, ID);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar"};
                String[] nombreIcono = {"Editar", "Borrar"};
                String[] columna = {"", "", "Resultado de aprendizaje"};
                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Editar", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Resultado_De_Aprendizaje");
                String[] campos = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
                c.campos(campos);
                String[] alias = {"ID_Resultado_De_Aprendizaje", "Resultado_De_Aprendizaje"};
                c.alias(alias);
                c.columnaSeleccionada("Resultado_De_Aprendizaje");
                c.condicion("ID_Actividad_De_Aprendizaje = " + ID[ID.length - 1]);
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver";

                FormResultadoDeAprendizaje p = new FormResultadoDeAprendizaje(tipo, retorno, nombreClase, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver";
                String pregunta = "¿Desea eliminar el resultado de aprendizaje " + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado".equals(nombreClase)) {

                String[] vinculo = {"Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado.Confirmado", "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver"};
                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos ligados a esta competencia";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver.Borrar.Confirmado.Confirmado".equals(nombreClase)) {

                ResultadoDeAprendizaje.BorrarEnID(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó un resultado de aprendizaje");
                new PantallasAdministrador(tipo, panelContenedor, "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ver", pantallaActual, usuario, Arreglo.quitar(ID), declaracion);

            }

            if ("Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje.Ingresar".equals(nombreClase)) {

                String retorno = "Administrador.PlanDeEstudios.Ver.Competencia.Ver.ActividadDeAprendizaje.Ver.ResultadoDeAprendizaje";

                FormResultadoDeAprendizaje p = new FormResultadoDeAprendizaje(tipo, retorno, nombreClase, usuario, Arreglo.agregar(ID, "☺"), declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Historial".equals(nombreClase)) {

                String[] nombreBotones = {"Ver"};
                String[] nombreIcono = {"Ver"};
                String[] columna = {"", "Fecha","Documento", "Funcionario", "Cargo", "Acción"};
                String[] vinculo = {"Administrador.Historial.Ver"};
                String retorno = "Administrador";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Informacion_Funcionarios join T_log on ID_Funcionario = Documento_De_Identidad");
                String[] campos = {"Documento_De_Identidad","Fecha", "Documento_De_Identidad", "CONCAT(T_Informacion_Funcionarios.Nombre,' ',T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido)", "cargo", "Accion"};
                c.campos(campos);
                String[] alias = {"Documento_De_Identidad","Fecha", "documento", "Instructor", "cargo", "Accion"};
                c.alias(alias);
                c.columnaSeleccionada("Fecha");
                c.panelContenedor(panelContenedor);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }
            
            if ("Administrador.Historial.Ver".equals(nombreClase)) {

                String[] nombreBotones = {"Editar", "Borrar"};
                String[] nombreIcono = {"Editar Usuario", "Borrar Usuario"};
                String[] columna = {"", "", "Documento", "Nombre", "Primer apellido", "Segundo apellido", "Cargo", "Correo electrónico", "Teléfono", "Celular"};
                String[] vinculo = {"Administrador.Historial.Ver.Editar", "Administrador.Historial.Ver.Borrar"};
                String retorno = "Administrador.Historial";

                Consulta c = new Consulta(declaracion);
                c.tabla("T_Informacion_Funcionarios");
                String[] campos = {"Documento_De_Identidad", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "cargo", "Correo_Electronico", "Telefono_Fijo", "Telefono_Celular"};
                c.campos(campos);
                String[] alias = {"ID", "Documento_De_Identidad", "Nombre", "Primer_Apellido", "Segundo_Apellido", "cargo", "Correo_Electronico", "Telefono_Fijo", "Telefono_Celular"};
                c.alias(alias);
                c.columnaSeleccionada("Documento_De_Identidad");
                c.panelContenedor(panelContenedor);
                c.condicion("Documento_De_Identidad = " + ID[ID.length - 1]);
                String menu[][] = c.ejecutarConsulta();

                PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBotones, nombreIcono, columna, vinculo, retorno, nombreClase, usuario, declaracion, ID, c);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimientoSecuencial();
            }
            
            if ("Administrador.Historial.Ver.Editar".equals(nombreClase)) {

                String retorno = "Administrador.Historial.Ver";

                FormFuncionarios p = new FormFuncionarios(tipo, retorno, pantallaActual, usuario, ID, declaracion);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Historial.Ver.Borrar".equals(nombreClase)) {

                String[] vinculo = {"Administrador.Historial.Ver.Borrar"
                        + ".Confirmado", "Administrador.Historial.Ver"};
                String retorno = "Administrador.Historial.Ver";
                String pregunta = "¿Desea eliminar el registro " 
                        + ID[ID.length - 1] + "?";

                Confirmacion p = new Confirmacion(tipo, retorno, 
                        nombreClase, usuario, ID, declaracion, pregunta,
                        vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Historial.Ver.Borrar.Confirmado"
                    .equals(nombreClase)) {

                String[] vinculo = {"Administrador.Historial.Ver.Borrar"
                        + ".Confirmado.Confirmado", "Administrador.Historial.Ver"};
                String retorno = "Administrador.Funcionarios.Ver";
                String pregunta = "Al eliminarlo se borrarán todos los datos "
                        + "ligados a éste usuario";

                Confirmacion p = new Confirmacion(tipo, retorno, nombreClase, 
                        usuario, ID, declaracion, pregunta, vinculo);
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }

            if ("Administrador.Historial.Ver.Borrar.Confirmado.Confirmado"
                    .equals(nombreClase)) {

                if (ID[ID.length - 1].equals(usuario)) {

                    String[] vinculo = {"Administrador.Historial.Ver.Borrar"
                            + ".Confirmado.Confirmado.BorrarMiCuanta", 
                        "Administrador.Historial.Ver"};
                    String retorno = "Administrador.Historial.Ver";
                    String pregunta = "Está a punto de borrar su cuenta";

                    Confirmacion p = new Confirmacion(tipo, retorno, 
                            nombreClase, usuario, ID, declaracion, pregunta, 
                            vinculo);
                    panelContenedor.removeAll();
                    panelContenedor.add(p);
                    panelContenedor.validate();
                    p.movimiento();

                } else {

                    Funcionario.BorrarEnDocumento(declaracion, 
                            ID[ID.length - 1]);
                    Historial.Insertar(declaracion, usuario, "Eliminó un funcionario");

                    String[] nombreBotones = {"Editar", "Borrar"};
                    String[] nombreIcono = {"Editar Usuario", "Borrar Usuario"};
                    String[] columna = {"", "", "Documento", 
                        "Nombre", "Primer apellido", "segundo Apellido", 
                        "Cargo", "Correo electrónico", "Teléfono", "Celular"};
                    String[] vinculo = {"Administrador.Historial.Ver.Editar",
                        "Administrador.Historial.Ver.Borrar"};
                    String retorno = "Administrador.Historial";

                    Consulta c = new Consulta(declaracion);
                    c.tabla("T_Informacion_Funcionarios");
                    String[] campos = {"Documento_De_Identidad", 
                        "Documento_De_Identidad", "Nombre",
                        "Primer_Apellido", "Segundo_Apellido", "cargo",
                        "Correo_Electronico", "Telefono_Fijo",
                        "Telefono_Celular"};
                    c.campos(campos);
                    String[] alias = {"ID", "Documento_De_Identidad", 
                        "Nombre", "Primer_Apellido", 
                        "Segundo_Apellido", "cargo", "Correo_Electronico", 
                        "Telefono_Fijo", "Telefono_Celular"};
                    c.alias(alias);
                    c.columnaSeleccionada("Documento_De_Identidad");
                    c.panelContenedor(panelContenedor);
                    String menu[][] = c.ejecutarConsulta();

                    PantallaUsuario p = new PantallaUsuario(tipo, menu, 
                            nombreBotones, nombreIcono, columna, vinculo, 
                            retorno, nombreClase, usuario, declaracion, 
                            Arreglo.quitar(ID), c);
                    panelContenedor.removeAll();
                    panelContenedor.add(p);
                    panelContenedor.validate();
                    p.movimientoSecuencial();

                }

            }

            if ("Administrador.Historial.Ver.Borrar.Confirmado.Confirmado.BorrarMiCuanta".equals(nombreClase)) {

                Funcionario.BorrarEnDocumento(declaracion, ID[ID.length - 1]);
                Historial.Insertar(declaracion, usuario, "Eliminó su propia cuenta");

                PantallaInicio p = new PantallaInicio();
                panelContenedor.removeAll();
                panelContenedor.add(p);
                panelContenedor.validate();
                p.movimiento();

            }
            
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }

    }

}
