/*
 * Foto.java
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
package com.quicklist;

import com.quicklist.clases.Aprendiz;
import static com.quicklist.clases.Configuracion.cargarConfiguracion;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Historial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * Esta clase permite a todos los usuarios cambiar y eliminar su propia foto de
 * perfil plan de estudios especifico
 */
public class Foto extends javax.swing.JFrame {

    static String usuario;  //Identificador del usuario actual
    static String tipo;     //Rol del usuario actual
    static JLabel label;    //Cuadro de la foto del frame principal

    /**
     * Objeto empleado para realizar la consultas en la base de datos
     */
    static Statement declaracion;

    /**
     * Objeto empleado para seleccionar la ubicación de la foto en el ordenador
     */
    JFileChooser abrirArchivo = new JFileChooser();

    /**
     * Objeto que almacena la ruta de la foto seleccionada
     */
    File direccion;

    /**
     * Objeto que contiene el codigo byte de la imagen que se va a almacenar
     */
    FileInputStream archivoImagen = null;

    boolean quitarFoto = false;   // Verifica si se ha quitado la foto actual
    boolean modificado = false;   // Verifica si se ha cambiado la foto actual

    /**
     * Arreglo que contiene la configuración actual de la aplicación
     */
    int[] conf = cargarConfiguracion();

    /**
     * Metodo constructor de la clase
     *
     * @param label
     * @param tipo
     * @param usuario
     * @param declaracion
     */
    public Foto(JLabel label, Statement declaracion, String usuario,
            String tipo) {

        /*
         * Se asignan los valores de los parametros de forma global
         */
        Foto.label = label;
        Foto.usuario = usuario;
        Foto.declaracion = declaracion;
        Foto.tipo = tipo;

        initComponents();   //Se crean los componentes graficos

        /*Dar fuente, tipo de letra y tamaño*/
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jButton5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));
        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));
        jButton7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));
        jButton8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));

        /* Se intenta cargar la foto almacenada en la base de datos */
        try {

            /* Se verifica si el usuario actual es un funcionario */
            if ("Administrador".equals(tipo) || "Instructor".equals(tipo)) {

                /* Se carga la imagen de la base de datos */
                ImageIcon icono = new ImageIcon(Funcionario
                        .SeleccionarFoto(declaracion, usuario));

                /* Se se redimenciona la imagen */
                ImageIcon imageScalada = new ImageIcon(icono.getImage()
                        .getScaledInstance(jLabel1.getWidth(), jLabel1.
                                getHeight(), 300));

                /* Se asigna la imagen redimencionada en el label */
                this.jLabel1.setIcon(imageScalada);

                /* Se eimina el teto de label */
                this.jLabel1.setText(null);

                /* Se verifica si el usuario actual es un aprendiz */
            } else if ("Aprendiz".equals(tipo)) {

                /* Se carga la imagen de la base de datos */
                ImageIcon icono = new ImageIcon(Aprendiz
                        .SeleccionarFoto(declaracion, usuario));

                /* Se se redimenciona la imagen */
                ImageIcon imageScalada = new ImageIcon(icono.getImage()
                        .getScaledInstance(jLabel1.getWidth(), jLabel1
                                .getHeight(), 300));

                /* Se asigna la imagen redimencionada en el label */
                this.jLabel1.setIcon(imageScalada);

                /* Se eimina el teto de label */
                this.jLabel1.setText(null);

            }

            /* 
             * Se detiene la carga de la foto en caso de que esta no exista o no 
             * se pueda cargar correctamente
             */
        } catch (NullPointerException ex) {

            /* Se imprime el error en la consola serial */
            System.out.println(ex);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(229, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sin Foto");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 255, 255));
        jButton5.setText("Cambiar");
        jButton5.setAutoscrolls(true);
        jButton5.setOpaque(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 255, 255));
        jButton6.setText("Quitar");
        jButton6.setAutoscrolls(true);
        jButton6.setOpaque(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 102, 102));
        jButton7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(204, 255, 255));
        jButton7.setText("Guardar");
        jButton7.setAutoscrolls(true);
        jButton7.setOpaque(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 255, 255));
        jButton8.setText("Cancelar");
        jButton8.setAutoscrolls(true);
        jButton8.setOpaque(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed


    }//GEN-LAST:event_jPanel6MousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int resp;   //Variable que contiene la respuesta de la carga de la foto

        /* Se abre la ventana para buscar la foto en el equipo */
        resp = abrirArchivo.showOpenDialog(this);

        /* Se verifica si se seleccionó una foto */
        if (resp == JFileChooser.APPROVE_OPTION) {

            /* se guarda la ruta de la foto seleccionada */
            direccion = new File(abrirArchivo.getSelectedFile().toString());

            /* se carga la imagen de la ruta seleccionada */
            ImageIcon icono = new ImageIcon(direccion.toString());

            /* Se intenta cargar el codigo byte de la imagen */
            try {

                /* 
                 * Se carga el codigo byte de la imagen de la ruta 
                 * especificada 
                 */
                archivoImagen = new FileInputStream(direccion);

                /* 
                 * Se detiene la carga de la foto en caso de que esta no exista o no 
                 * se pueda cargar correctamente
                 */
            } catch (FileNotFoundException ex) {

                /* Se imprime el error en la consola serial */
                System.out.println(ex);

            }

            /* Se redimenciona la imagen de acuerdo a el tamaño del label */
            ImageIcon imageScalada = new ImageIcon(icono.getImage()
                    .getScaledInstance(jLabel1.getWidth(), jLabel1
                            .getHeight(), 300));

            /* Se elimina el texto del label */
            this.jLabel1.setText(null);

            /* Se asigna la nueva imagen al label */
            this.jLabel1.setIcon(imageScalada);

            /* Se desactiva la verificación de foto nula */
            this.quitarFoto = false;

            /* Se activa el verificador de cambio de foto */
            modificado = true;

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        /* Se elimina la foto del label */
        this.jLabel1.setIcon(null);

        /* Se asigna el texto que evidencia la falta de foto */
        jLabel1.setText("Sin Foto");

        /* Se activa la verificación de foto nula */
        quitarFoto = true;

        /* Se activa el verificador de cambio de foto */
        modificado = true;

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        /* Se verifica si se ha modificado el estado de la foto */
        if (modificado) {

            /* Se verifica si se quito la foto actual */
            if (quitarFoto) {

                /* Se verifica si el usuario actual es un funcionario */
                if ("Administrador".equals(tipo) || "Instructor".equals(tipo)) {

                    /* Se elimina la foto de la base de datos */
                    Funcionario.ActualizarFoto(declaracion, usuario,
                            archivoImagen);

                    /* Se verifica si el usuario actual es un aprendiz */
                } else if ("Aprendiz".equals(tipo)) {

                    /* Se elimina la foto de la base de datos */
                    Aprendiz.ActualizarFoto(declaracion, usuario, archivoImagen);

                }

                /* Se elimina la foto de la vantana principal */
                this.label.setText(null);
                this.label.setIcon(null);

            } else {

                /* Se verifica si el usuario actual es un funcionario */
                if ("Administrador".equals(tipo) || "Instructor".equals(tipo)) {

                    /* Se elimina la foto de la base de datos */
                    Funcionario.ActualizarFoto(declaracion, usuario,
                            archivoImagen);

                    /* Se elimina el texto del label de la ventana principal */
                    this.label.setText(null);

                    /* Se carga la nueva foto de la base de datos */
                    ImageIcon icono = new ImageIcon(Funcionario
                            .SeleccionarFoto(declaracion, usuario));

                    /* Se redimenciona la imagen */
                    ImageIcon imageScalada = new ImageIcon(icono.getImage()
                            .getScaledInstance(66, 76, 66));

                    /* Se asigna la nueva foto en la ventana principal */
                    this.label.setIcon(imageScalada);

                    /* Se verifica si el usuario actual es un aprendiz */
                } else if ("Aprendiz".equals(tipo)) {

                    /* Se elimina la foto de la base de datos */
                    Aprendiz.ActualizarFoto(declaracion, usuario, archivoImagen);

                    /* Se elimina el texto del label de la ventana principal */
                    this.label.setText(null);

                    /* Se carga la nueva foto de la base de datos */
                    ImageIcon icono = new ImageIcon(Aprendiz.SeleccionarFoto(declaracion, usuario));

                    /* Se redimenciona la imagen */
                    ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance(66, 76, 66));

                    /* Se asigna la nueva foto en la ventana principal */
                    this.label.setIcon(imageScalada);

                }

            }

        }

        Historial.Insertar(declaracion, usuario, "Actualizó el estado de la foto de perfil");
        
        /* Cerrar la ventana actual */
        this.dispose();


    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        /* Cerrar la ventana actual */
        this.dispose();

    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Foto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Foto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Foto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Foto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Foto(label, declaracion, usuario, tipo).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
