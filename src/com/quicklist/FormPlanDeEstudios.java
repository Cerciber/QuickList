/*
 * FormPlanDeEstudios.java
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

import static com.quicklist.clases.Configuracion.cargarConfiguracion;
import java.awt.Component;
import java.sql.Statement;
import com.quicklist.clases.PlanDeEstudios;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.Arreglo;
import com.quicklist.funciones.AnimacionObjetos;
import com.quicklist.funciones.DatosUsuario;
import com.quicklist.funciones.Validaciones;
import javax.swing.JOptionPane;

/**
 * Esta clase permite al administrador ingresar y editar la información de un
 * plan de estudios especifico
 */
public final class FormPlanDeEstudios extends javax.swing.JPanel {

    int velocidad = 100;    //Corrimiento de la animación de los objetos
    String usuario;     //Documento del usuario que accede a la clase
    String retorno;     //Ruta de acceso a la ventana anterior
    String tipo;    //Rol del usuario que accede a la clase
    String nombrePantalla;      //Ruta de la ventana actual

    /**
     * Arreglo que almacena los identificadores nesesarios para cargar los datos
     * en cada una de las pantallas a las que se ha accedido desde el login para
     * recuperar las pantallas anteriores en caso de retorno
     */
    String[] ID;

    /**
     * Objeto empleado para realizar la consultas en la base de datos
     */
    Statement declaracion;

    /**
     * Arreglo que contiene todos los componentes de la pantalla a los cuales se
     * les da movimineto inicial
     */
    Component[] objeto;

    /**
     * Arreglo que contiene la configuración actual de la aplicación
     */
    int[] conf = cargarConfiguracion();

    /**
     * Metodo constructor de la clase
     *
     * @param tipo
     * @param retorno
     * @param nombrePantalla
     * @param usuario
     * @param ID
     * @param declaracion
     */
    public FormPlanDeEstudios(String tipo, String retorno,
            String nombrePantalla, String usuario, String[] ID,
            Statement declaracion) {

        /*
         * Se asignan los valores de los parametros de forma global
         */
        this.tipo = tipo;
        this.retorno = retorno;
        this.usuario = usuario;
        this.declaracion = declaracion;
        this.ID = ID;
        this.nombrePantalla = nombrePantalla;

        initComponents();   //Se crean los componentes graficos

        /* Se cargan y se ubican los datos del usuario */
        new DatosUsuario(usuario, tipo, declaracion, jLabel1, jLabel2, jLabel3);

        datosActividad(ID);     //Se carga y se ubica la tabla de información

        /*Quitar el boton de edición de datos*/
        jButton8.setVisible(false);

        /*Dar fuente, tipo de letra y tamaño*/
        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel19.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));

        jTextField5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));
        jComboBox5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        /**
         * Permite que el usuario pueda mover el panel que contiene la tabla
         * dentro del frame con el mouse y con las flechas del teclado
         */
        new MoverObjeto(jPanel8);

    }

    /**
     * Este metodo carga los datos del plan de estudios que se quiere modificar
     * cuando sea necesario
     *
     * @param ID
     */
    public void datosActividad(String[] ID) {

        /*
         * El simbolo "☺" representa un dato vacio en el arreglo de 
         * identificadores lo que identifica que se esta haciendo una insersión
         * y no una actualización.
         */
        if (!"☺".equals(ID[ID.length - 1])) {

            /*
             * Se realiza la busqueda en la base de datos y se asigna en un 
             * arreglo bidimensional
             */
            String[][] lista = PlanDeEstudios.SeleccionarPorID(declaracion,
                    ID[ID.length - 1]);

            /**
             * Se asigna el dato del nombre del plan de estudios
             */
            jTextField5.setText(lista[0][1]);

            /**
             * Se asigna el dato del nombre del programa de formación
             */
            jTextField7.setText(lista[0][2]);

            /**
             * Se asigna el dato de la versión del plan de estudios
             */
            jTextField8.setText(lista[0][3]);

            /**
             * Se asigna el dato de los meses de etapa lectiva
             */
            jTextField9.setText(lista[0][4]);

            /**
             * Se asigna el nivel de formación del plan de estudios
             */
            jComboBox5.setSelectedItem(lista[0][5]);

        }

    }

    /**
     * Permite seleccionar los componentes en el panel a los cuales se les dara
     * animación
     */
    public void movimiento() {

        /* Se crea el arreglo con los componentes */
        Component[] objeto2 = {jPanel8};

        /*
         * Se asigna el arreglo de forma global para que este se pueda 
         * utiizar en los eventos
         */
        objeto = objeto2;

        /* 
         * Permite dar un movimiento inicial a los objetos del arreglo en 
         * forma secuencial
         */
        new AnimacionObjetos().Izquierda(objeto, velocidad);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setOpaque(false);
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Nombre Apellido");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("N° cedula");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 255, 255));
        jButton8.setText("Editar mis datos");
        jButton8.setAutoscrolls(true);
        jButton8.setOpaque(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 130, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(370, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setOpaque(false);

        jPanel8.setOpaque(false);

        jLabel15.setBackground(new java.awt.Color(204, 255, 255));
        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("*Nombre");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);

        jLabel16.setBackground(new java.awt.Color(204, 255, 255));
        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("*Programa");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setOpaque(true);

        jLabel17.setBackground(new java.awt.Color(204, 255, 255));
        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("*Versión");
        jLabel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel17.setOpaque(true);

        jLabel18.setBackground(new java.awt.Color(204, 255, 255));
        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("*Meses etapa lectiva");
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel18.setOpaque(true);

        jLabel19.setBackground(new java.awt.Color(204, 255, 255));
        jLabel19.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Nivel de formación");
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel19.setOpaque(true);

        jTextField5.setBackground(new java.awt.Color(0, 153, 153));
        jTextField5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jComboBox5.setBackground(new java.awt.Color(0, 153, 153));
        jComboBox5.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Técnico", "Tecnólogo", "Complementario" }));

        jTextField7.setBackground(new java.awt.Color(0, 153, 153));
        jTextField7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jTextField8.setBackground(new java.awt.Color(0, 153, 153));
        jTextField8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jTextField9.setBackground(new java.awt.Color(0, 153, 153));
        jTextField9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(255, 255, 255));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextField5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextField7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextField8))
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextField9))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox5)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(557, 70));

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jButton7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 102, 102));
        jButton7.setText("Volver");
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton7);

        jButton11.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 102, 102));
        jButton11.setText("Salir");
        jButton11.setBorder(null);
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton11);

        jButton12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 102, 102));
        jButton12.setText("☼");
        jButton12.setBorder(null);
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton12);

        jButton13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 102, 102));
        jButton13.setText("?");
        jButton13.setBorder(null);
        jButton13.setContentAreaFilled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton13);

        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 102, 102));
        jButton6.setText("Guardar");
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(157, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped

        /* Dar una longitud maxima de caracteres de 100 */
        Validaciones.longitud(evt, jTextField5.getText().length(), 100);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped

        /* Dar una longitud maxima de caracteres de 100 */
        Validaciones.longitud(evt, jTextField7.getText().length(), 100);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped

        /* Dar una longitud maxima de caracteres de 9 */
        Validaciones.longitud(evt, jTextField8.getText().length(), 9);

        /* Restringir caracteres no numericos */
        Validaciones.restringirCaracterFueraDe(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped

        /* Dar una longitud maxima de caracteres de 2 */
        Validaciones.longitud(evt, jTextField9.getText().length(), 2);

        /* Restringir caracteres no numericos */
        Validaciones.restringirCaracterFueraDe(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField9KeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        /* Se verifica si el retorno corresponde a la pantalla inicio.*/
        if ("PantallaInicio".equals(retorno)) {

            /* Se emplea la funcionalidad del botón "Salir" */
            jButton11ActionPerformed(evt);

        } else {

            /* 
             * Se animan los objetos para que salgan del panel y se realiza 
             * el cambio de pantalla
             */
            new AnimacionObjetos().RIzquierda(objeto, velocidad, this, retorno,
                    nombrePantalla, tipo, usuario,
                    Arreglo.quitar(ID), declaracion);

        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        /*
         * Se crea un arreglo de componentes para alamcenar todos los objetos 
         * que se van a animar al momento de la salida
         */
        Component[] componentes = new Component[objeto.length + 2];

        componentes[0] = jPanel2;   //Se añade el panel superior
        componentes[1] = jPanel3;   //Se añade el panel inferior

        /* Se añaden los demas objetos a los que se les dió la animación */
        for (int i = 2; i <= componentes.length - 1; i++) {
            componentes[i] = objeto[i - 2];
        }

        /* 
         * Se animan los objetos para que salgan del panel y se realiza 
         * el cambio de pantalla
         */
        new AnimacionObjetos().RIzquierda(componentes, velocidad, this,
                "PantallaInicio", nombrePantalla, tipo,
                usuario, null, declaracion);

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        /* Se abre la ventana de configuración de la aplicación */
        Configuracion c = new Configuracion();  //Instanciación
        c.setSize(800, 600);    //Tamaño de ventana
        c.setLocationRelativeTo(null);      //Ubicar al centro
        c.setVisible(true);     //Dar visivilidad

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        /*
         * Se crea un arrelo para contener los datos del plan de estudios con 
         * una longitud de 6 correspondientes a los inputs 
         */
        String[] datos = new String[5];

        /**
         * Se obtiene el nombre del plan de estudios
         */
        datos[0] = jTextField5.getText();

        /**
         * Se obtiene el programa de formación
         */
        datos[1] = jTextField7.getText();

        /**
         * Se obtiene la versión
         */
        datos[2] = jTextField8.getText();

        /**
         * Se obtienen el los meses de etapa lectiva
         */
        datos[3] = jTextField9.getText();

        /**
         * Se obtiene el nivel de formación
         */
        datos[4] = jComboBox5.getSelectedItem().toString();

        /*
         * El simbolo "☺" representa un dato vacio en el arreglo de 
         * identificadores lo que identifica que se esta haciendo una insersión
         * y no una actualización.
         */
        if ("☺".equals(ID[ID.length - 1])) {

            /* Se verifica si los datos del formulario estan vacios */
            if ("".equals(jTextField5.getText())
                    || "".equals(jTextField7.getText())
                    || "".equals(jTextField8.getText())
                    || "".equals(jTextField9.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "Debe diligenciar los campos obligatorios (*)", "Error",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                /*
                 * Se insertan los datos del plan de estudios en la 
                 * base de datos
                 */
                PlanDeEstudios.Insertar(declaracion, datos);

                /* 
                 * Se animan los objetos para que salgan del panel y se realiza 
                 * el cambio de pantalla
                 */
                new AnimacionObjetos().RIzquierda(objeto, velocidad, this,
                        retorno + ".Ver", nombrePantalla, tipo, usuario,
                        Arreglo.quitar(ID), declaracion);

            }

        } else {

            /* Se verifica si los datos del formulario estan vacios */
            if ("".equals(jTextField5.getText())
                    || "".equals(jTextField7.getText())
                    || "".equals(jTextField8.getText())
                    || "".equals(jTextField9.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "Debe diligenciar los campos obligatorios (*)", "Error",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                /* 
                 * Se actualizan los datos del plan de estudios en la base de 
                 * datos
                 */
                PlanDeEstudios.ActualizarEnID(declaracion, datos,
                        ID[ID.length - 1]);

                /* 
                 * Se animan los objetos para que salgan del panel y se realiza 
                 * el cambio de pantalla
                 */
                new AnimacionObjetos().RIzquierda(objeto, velocidad, this,
                        retorno, nombrePantalla, tipo, usuario,
                        Arreglo.quitar(ID), declaracion);

            }

        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed

        /**
         * Se abre el Frame corespondiente para gestionar la foto del usuario
         * actual
         */
        Foto foto = new Foto(jLabel3, declaracion, usuario, tipo);
        foto.setLocationRelativeTo(null);   //se ubica al centro
        foto.setVisible(true);      //se le da visivilidad

    }//GEN-LAST:event_jPanel6MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed

        /**
         * Se abre el Frame corespondiente para gestionar la foto del usuario
         * actual
         */
        Foto foto = new Foto(jLabel3, declaracion, usuario, tipo);
        foto.setLocationRelativeTo(null);   //se ubica al centro
        foto.setVisible(true);      //se le da visivilidad

    }//GEN-LAST:event_jLabel3MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
