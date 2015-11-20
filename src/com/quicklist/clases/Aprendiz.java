/*
 * Aprendiz.java
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
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author cesaraugusto
 */
public class Aprendiz {

    public static String[][] SeleccionarDatosUsuario(Statement declaracion, String usuario) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select Nombre,Primer_Apellido,Documento_De_Identidad  from T_Informacion_Aprendiz where Documento_De_Identidad=" + usuario + ";");
            String[] campos = {"Nombre", "Primer_Apellido", "Documento_De_Identidad"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
        }

        return menu;

    }

    public static String[][] SeleccionarPorDocumento(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select *,CONVERT(VARCHAR(300),DECRYPTBYPASSPHRASE('cerciber',contrasena)) as contrasenadesc from T_Informacion_Aprendices where Documento_De_Identidad = " + ID + ";");
            String[] campos = {"Documento_De_Identidad", "Documento_De_Identidad", "contrasenadesc", "Nombre", "Primer_Apellido", "Segundo_Apellido", "Fecha_De_Nacimiento", "Correo_Electronico", "Genero", "ID_Ficha", "Telefono_Fijo", "Telefono_Celular", "estado", "nombre_Proyecto", "Estilos_Y_Ritmos_De_Aprendizaje", "Estrategia_Metodológica_De_Preferencia", "Caracteristicas_Culturales_Y_Sociales"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static Image SeleccionarFoto(Statement declaracion, String usuario) {

        Image image = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select Foto from T_Informacion_Aprendices where Documento_De_Identidad=" + usuario + ";");
            resultado.next();
            byte[] img = resultado.getBytes("Foto");

            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) readers.next();
            Object source = bis; // File or InputStream
            ImageInputStream iis = null;

            try {

                iis = ImageIO.createImageInputStream(source);
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                image = reader.read(0, param);

            } catch (IOException ex) {
                System.out.println(ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return image;

    }

    public static String[][] SeleccionarPorFicha(Statement declaracion, String ID) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Aprendices where ID_Ficha = " + ID + ";");
            String[] campos = {"Documento_De_Identidad", "Documento_De_Identidad", "Contrasena", "Nombre", "Primer_Apellido", "Segundo_Apellido", "Fecha_De_Nacimiento", "Correo_Electronico", "Genero", "ID_Ficha", "Telefono_Fijo", "Telefono_Celular", "estado", "nombre_Proyecto", "Estilos_Y_Ritmos_De_Aprendizaje", "Estrategia_Metodológica_De_Preferencia", "Caracteristicas_Culturales_Y_Sociales"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 6);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarDatosFormatoEtapaLectiva(Statement declaracion, String aprendiz, String instructor) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select Nombre_Programa,CONCAT(T_Informacion_Funcionarios.Nombre,' ', "
                    + "T_Informacion_Funcionarios.Primer_Apellido,' ',T_Informacion_Funcionarios.Segundo_Apellido) "
                    + "as instructor,Numero_De_Ficha,nombre_Proyecto,Fase_Del_Proyecto, "
                    + "CONCAT(T_Informacion_Aprendices.Nombre,' ',T_Informacion_Aprendices.Primer_Apellido, "
                    + "' ',T_Informacion_Aprendices.Segundo_Apellido)as aprendiz ,"
                    + "T_Informacion_Aprendices.Documento_De_Identidad,Estilos_Y_Ritmos_De_Aprendizaje, "
                    + "Estrategia_Metodológica_De_Preferencia,Caracteristicas_Culturales_Y_Sociales "
                    + "from T_Informacion_Aprendices "
                    + "join T_Fichas "
                    + "on T_Informacion_Aprendices.ID_Ficha = T_Fichas.Numero_De_Ficha "
                    + "join T_Plan_De_Estudios "
                    + "on T_Fichas.ID_Plan_De_Estudios = T_Plan_De_Estudios.ID_Plan_De_Estudios "
                    + "join T_Horario "
                    + "on T_Horario.ID_Ficha = T_Fichas.Numero_De_Ficha "
                    + "join T_Informacion_Funcionarios "
                    + "on T_Horario.ID_Funcionario = T_Informacion_Funcionarios.Documento_De_Identidad "
                    + "join T_Resultado_De_Aprendizaje "
                    + "on T_Horario.ID_Resultado_De_Aprendizaje = T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje "
                    + "join T_Actividad_De_Aprendizaje "
                    + "on T_Resultado_De_Aprendizaje.ID_Actividad_De_Aprendizaje = T_Actividad_De_Aprendizaje.ID_Actividad_De_Aprendizaje "
                    + "where T_Informacion_Aprendices.Documento_De_Identidad=" + aprendiz + " and T_Informacion_Funcionarios.Documento_De_Identidad=" + instructor + ";");

            String[] campos = {"Nombre_Programa", "instructor", "Numero_De_Ficha", "nombre_Proyecto", "Fase_Del_Proyecto", "aprendiz", "Documento_De_Identidad", "Estilos_Y_Ritmos_De_Aprendizaje", "Estrategia_Metodológica_De_Preferencia", "Caracteristicas_Culturales_Y_Sociales"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarResultadosFormatoEtapaLectivaPorInstructor(Statement declaracion, String aprendiz, String instructor) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select Resultado_De_Aprendizaje,\n"
                    + "Nombre_Actividad,Nombre_Evidencia,\n"
                    + "T_Actividades.Medio,T_Actividades.Tipo,\n"
                    + "T_Fichas.Fecha_Inicio,T_Fichas.Fecha_Fin,\n"
                    + "T_Actividades.Fecha_RecoleccionEvidencia,\n"
                    + "T_EstadoActividad.Autenticidad,T_EstadoActividad.Calidad,\n"
                    + "T_EstadoActividad.Pertinencia,T_EstadoActividad.Vigencia,\n"
                    + "T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje, "
                    + "T_EstadoActividad.LogroElAprendizaje "
                    + "from T_EstadoActividad\n"
                    + "join T_Actividades\n"
                    + "on T_EstadoActividad.ID_Actividad = T_Actividades.ID_Actividad\n"
                    + "join T_Horario\n"
                    + "on T_Horario.ID_Horario = T_Actividades.ID_Horario\n"
                    + "join T_Resultado_De_Aprendizaje\n"
                    + "on T_Horario.ID_Resultado_De_Aprendizaje = T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje\n"
                    + "join T_Fichas\n"
                    + "on T_Horario.ID_Ficha = T_Fichas.Numero_De_Ficha\n"
                    + "where T_EstadoActividad.ID_Aprendiz=" + aprendiz + " and T_Horario.ID_Funcionario=" + instructor + ";\n");

            String[] campos = {"Resultado_De_Aprendizaje", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_Inicio", "Fecha_Fin", "Fecha_RecoleccionEvidencia", "Autenticidad", "Calidad", "Pertinencia", "Vigencia", "ID_Resultado_De_Aprendizaje", "LogroElAprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 5);
            Calendario.reducirFecha(menu, 6);
            Calendario.reducirFecha(menu, 7);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarResultadosFormatoEtapaLectivaPorHorario(Statement declaracion, String aprendiz, String horario) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select Resultado_De_Aprendizaje,\n"
                    + "Nombre_Actividad,Nombre_Evidencia,\n"
                    + "T_Actividades.Medio,T_Actividades.Tipo,\n"
                    + "T_Fichas.Fecha_Inicio,T_Fichas.Fecha_Fin,\n"
                    + "T_Actividades.Fecha_RecoleccionEvidencia,\n"
                    + "T_EstadoActividad.Autenticidad,T_EstadoActividad.Calidad,\n"
                    + "T_EstadoActividad.Pertinencia,T_EstadoActividad.Vigencia,\n"
                    + "T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje, "
                    + "T_EstadoActividad.LogroElAprendizaje "
                    + "from T_EstadoActividad\n"
                    + "join T_Actividades\n"
                    + "on T_EstadoActividad.ID_Actividad = T_Actividades.ID_Actividad\n"
                    + "join T_Horario\n"
                    + "on T_Horario.ID_Horario = T_Actividades.ID_Horario\n"
                    + "join T_Resultado_De_Aprendizaje\n"
                    + "on T_Horario.ID_Resultado_De_Aprendizaje = T_Resultado_De_Aprendizaje.ID_Resultado_De_Aprendizaje\n"
                    + "join T_Fichas\n"
                    + "on T_Horario.ID_Ficha = T_Fichas.Numero_De_Ficha\n"
                    + "where T_EstadoActividad.ID_Aprendiz=" + aprendiz + " and T_Horario.ID_Horario=" + horario + ";\n");

            String[] campos = {"Resultado_De_Aprendizaje", "Nombre_Actividad", "Nombre_Evidencia", "Medio", "Tipo", "Fecha_Inicio", "Fecha_Fin", "Fecha_RecoleccionEvidencia", "Autenticidad", "Calidad", "Pertinencia", "Vigencia", "ID_Resultado_De_Aprendizaje", "LogroElAprendizaje"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

            Calendario.reducirFecha(menu, 5);
            Calendario.reducirFecha(menu, 6);
            Calendario.reducirFecha(menu, 7);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public static String[][] SeleccionarDatosFormatoDeAsistencia(Statement declaracion, String horario) {

        String[][] menu = null;

        try {

            ResultSet resultado = declaracion.executeQuery("select [Documento_De_Identidad],CONCAT([Primer_Apellido],' ',[Segundo_Apellido],' ',[Nombre]) as aprendiz,\n"
                    + "[estado]\n"
                    + "from [T_Informacion_Aprendices] \n"
                    + "join [T_Horario] on [T_Horario].[ID_Ficha] = [T_Informacion_Aprendices].[ID_Ficha]\n"
                    + "where [T_Horario].[ID_Horario] =" + horario);
            String[] campos = {"Documento_De_Identidad", "aprendiz", "estado"};
            menu = new ConvertirConsulta().ArregloString(resultado, campos);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return menu;

    }

    public Aprendiz() {
    }

    @Override
    public String toString() {
        return "Aprendiz{" + '}';
    }

    public static void Insertar(Statement declaracion, String[] datos) {

        try {
            
            if("".equals(datos[9])){
                
                datos[9]="null";
                         
            }

            declaracion.executeQuery("insert into T_Informacion_Aprendices values("
                    + datos[0] + ", ENCRYPTBYPASSPHRASE('cerciber', '"
                    + datos[1] + "'), '"
                    + datos[2] + "', '"
                    + datos[3] + "', '"
                    + datos[4] + "', '"
                    + datos[5] + "','"
                    + datos[6] + "','"
                    + datos[7] + "', "
                    + datos[8] + ", "
                    + datos[9] + ", "
                    + datos[10] + ",' "
                    + datos[11] + "',' "
                    + datos[12] + "',' "
                    + datos[13] + "',' "
                    + datos[14] + "',' "
                    + datos[15] + "',"
                    + "null);");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnDocumento(Statement declaracion, String ID) {

        Inasistencia.BorrarPorAprendiz(declaracion, ID);
        EstadoActividad.BorrarPorAprendiz(declaracion, ID);

        try {

            //Se ejeculta la consulta para borrar 
            //empeando la variable ID en la condicion
            declaracion.executeQuery("delete from T_Informacion_Aprendices "
                    + "where Documento_De_Identidad = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnFicha(Statement declaracion, String ID) {

        Inasistencia.BorrarPorFicha(declaracion, ID);
        EstadoActividad.BorrarPorFicha(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Informacion_Aprendices "
                    + "where ID_Ficha = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarEnPlanDeEstudios(Statement declaracion, String ID) {

        Inasistencia.BorrarPorPlanDeEstudios(declaracion, ID);
        EstadoActividad.BorrarPorPlanDeEstudios(declaracion, ID);

        try {

            declaracion.executeQuery("delete from T_Informacion_Aprendices "
                    + "from T_Informacion_Aprendices "
                    + "join T_Fichas "
                    + "on Numero_De_Ficha=ID_Ficha "
                    + "where ID_Plan_De_Estudios = " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void ActualizarEnDocumento(Statement declaracion, String[] datos, String ID) {

        try {

            //Se ejeculta la consulta para actualizar
            //asignando cada una de las varibles de arrgelo datos
            //empeando la variable ID en la condicion
            
            if("".equals(datos[9])){
                
                datos[9]="null";
                         
            }
            
            declaracion.executeQuery("update T_Informacion_Aprendices set "
                    + "Documento_De_Identidad = " + datos[0] + ","
                    + "Contrasena = ENCRYPTBYPASSPHRASE('cerciber', '" + datos[1] + "'),"
                    + "Nombre = '" + datos[2] + "',"
                    + "Primer_Apellido = '" + datos[3] + "',"
                    + "Segundo_Apellido = '" + datos[4] + "',"
                    + "Fecha_De_Nacimiento = '" + datos[5] + "',"
                    + "Correo_Electronico = '" + datos[6] + "',"
                    + "Genero = '" + datos[7] + "',"
                    + "ID_Ficha = " + datos[8] + ", "
                    + "Telefono_Fijo = " + datos[9] + ", "
                    + "Telefono_Celular = " + datos[10] + ", "
                    + "estado = '" + datos[11] + "', "
                    + "nombre_Proyecto = '" + datos[12] + "', "
                    + "Estilos_Y_Ritmos_De_Aprendizaje = '" + datos[13] + "', "
                    + "Estrategia_Metodológica_De_Preferencia = '" + datos[14] + "', "
                    + "Caracteristicas_Culturales_Y_Sociales = '" + datos[15] + "' "
                    + "where Documento_De_Identidad= " + ID);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void ActualizarFoto(Statement declaracion, String usuario, FileInputStream foto) {

        try {

            PreparedStatement preparedStatement = declaracion.getConnection().prepareStatement("update T_Informacion_aprendices set Foto = ? where Documento_De_Identidad=" + usuario + ";");
            preparedStatement.setBinaryStream(1, foto);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void BorrarFoto(Statement declaracion, String usuario) {

        try {

            PreparedStatement preparedStatement = declaracion.getConnection().prepareStatement("update T_Informacion_aprendices set Foto = null where Documento_De_Identidad=" + usuario + ";");
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static boolean VerificarDocumento(Statement declaracion, String documento) {

        boolean existente = false;

        try {

            ResultSet resultado = declaracion.executeQuery("select * from T_Informacion_Aprendices where Documento_De_Identidad = " + documento + ";");

            if (new ConvertirConsulta().NRegistros(resultado) == 1) {

                existente = true;

            }

        } catch (SQLException ex) {
        }

        return existente;
    }

}
