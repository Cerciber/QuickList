/*
 * FormFuncionarios.java
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
import javax.swing.JOptionPane;
import com.quicklist.clases.Funcionario;
import com.quicklist.clases.Historial;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.Arreglo;
import com.quicklist.funciones.AnimacionObjetos;
import com.quicklist.funciones.DatosUsuario;
import com.quicklist.funciones.Validaciones;

/**
 * Esta clase permite a los usuarios administrador e instructor ingresar y
 * editar los datos de un funcionario especifico
 */
public final class FormFuncionarios extends javax.swing.JPanel {

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
    public FormFuncionarios(String tipo, String retorno, String nombrePantalla,
            String usuario, String[] ID, Statement declaracion) {

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
        jLabel20.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel21.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel22.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));
        jLabel23.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[3]));

        jTextField2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jPasswordField1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jComboBox1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        jTextField9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1,
                conf[3]));

        /**
         * Permite que el usuario pueda mover el panel que contiene la tabla
         * dentro del frame con el mouse y con las flechas del teclado
         */
        new MoverObjeto(jPanel8);

    }

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
            String[][] lista = Funcionario
                    .SeleccionarPorDocumento(declaracion, ID[ID.length - 1]);

            /*Se asigna el documento del instructor*/
            jTextField2.setText(lista[0][1]);

            /*Se asigna la contrseña del instructor*/
            jPasswordField1.setText(lista[0][2]);

            /*Se asigna el nombre del instructor*/
            jTextField3.setText(lista[0][3]);

            /*Se asigna el primer apellido del instructor*/
            jTextField4.setText(lista[0][4]);

            /*Se asigna el segundo apellido del instructor*/
            jTextField5.setText(lista[0][5]);

            /*Se asigna el cargo del instructor*/
            jComboBox1.setSelectedItem(lista[0][6]);

            /*Se asigna el correo del instructor*/
            jTextField7.setText(lista[0][7]);

            /*Se asigna el telefono fijo del instructor*/
            jTextField8.setText(lista[0][8]);

            /*Se asigna el telefono celular del instructor*/
            jTextField9.setText(lista[0][9]);

            /*
             * Se verifica si el tipo de usuario que accedió al sistema es un 
             * instructor
             */
            if ("Instructor".equals(tipo)) {

                jTextField2.setFocusable(false);    //No seleccionable
                jTextField3.setFocusable(false);    //No seleccionable
                jTextField4.setFocusable(false);    //No seleccionable
                jTextField5.setFocusable(false);    //No seleccionable

                /*Se asigna un unico elemento en la lista desplegable*/
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(
                        new String[]{lista[0][6]}));

            }

        }

    }

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
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
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
                .addContainerGap(341, Short.MAX_VALUE))
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
        jLabel15.setText("*Documento");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);

        jTextField2.setBackground(new java.awt.Color(0, 153, 153));
        jTextField2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(204, 255, 255));
        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("*Contraseña");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setOpaque(true);

        jPasswordField1.setBackground(new java.awt.Color(0, 153, 153));
        jPasswordField1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(204, 255, 255));
        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("*Nombre");
        jLabel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel17.setOpaque(true);

        jTextField3.setBackground(new java.awt.Color(0, 153, 153));
        jTextField3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(204, 255, 255));
        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("*Primer apellido");
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel18.setOpaque(true);

        jTextField4.setBackground(new java.awt.Color(0, 153, 153));
        jTextField4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(204, 255, 255));
        jLabel19.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Segundo apellido");
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

        jLabel20.setBackground(new java.awt.Color(204, 255, 255));
        jLabel20.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Cargo");
        jLabel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel20.setOpaque(true);

        jLabel21.setBackground(new java.awt.Color(204, 255, 255));
        jLabel21.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("*Correo");
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel21.setOpaque(true);

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

        jLabel22.setBackground(new java.awt.Color(204, 255, 255));
        jLabel22.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 102));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Teléfono fijo");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel22.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(204, 255, 255));
        jLabel23.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("*Teléfono celular");
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel23.setOpaque(true);

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

        jComboBox1.setBackground(new java.awt.Color(0, 153, 153));
        jComboBox1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INSTRUCTOR", "ADMINISTRADOR" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
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
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(128, Short.MAX_VALUE)))
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

    }//GEN-LAST:event_jButton8ActionPerformed

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

        /* Boton "Guardar" */
        
        /*
         * Se crea un arrelo para recojer los datos de los inputs 
         */
        String[] datos = {jTextField2.getText(),
            String.valueOf(jPasswordField1.getPassword()),
            jTextField3.getText(),
            jTextField4.getText(),
            jTextField5.getText(),
            jComboBox1.getSelectedItem().toString(),
            jTextField7.getText(),
            jTextField8.getText(),
            jTextField9.getText()};

        /*
         * El simbolo "☺" representa un dato vacio en el arreglo de 
         * identificadores lo que identifica que se esta haciendo una insersión
         * y no una actualización.
         */
        if ("☺".equals(ID[ID.length - 1])) {

            /* Se verifica si los datos del formulario estan vacios */
            if ("".equals(jTextField2.getText())
                    || "".equals(String.valueOf(jPasswordField1.getPassword()))
                    || "".equals(jTextField3.getText())
                    || "".equals(jTextField4.getText())
                    || "".equals(jTextField7.getText())
                    || "".equals(jTextField9.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "Debe diligenciar los campos obligatorios (*)", "Error",
                        JOptionPane.ERROR_MESSAGE);

                /* Se verifica si el formato del correo es correcto */
            } else if (!Validaciones.formatoCorreo(jTextField7.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "Formato de correo incorrecto", "Error",
                        JOptionPane.ERROR_MESSAGE);

                /* Se verifica si el docuemento ingresado ya existe */
            } else if (Funcionario.VerificarDocumento(declaracion,
                    jTextField2.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "El documento seleccionado ya existe", "Error",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                /*
                 * Se insertan los datos del instructor en 
                 * la base de datos
                 */
                Funcionario.Insertar(declaracion, datos);
                Historial.Insertar(declaracion, usuario, "ingresó un funcionario");

                /* 
                 * Se animan los objetos para que salgan del panel y se realiza 
                 * el cambio de pantalla
                 */
                new AnimacionObjetos().RIzquierda(objeto, velocidad, this,
                        retorno + ".Ver", nombrePantalla, tipo, usuario,
                        Arreglo.agregar(Arreglo.quitar(ID)), declaracion);

            }

        } else {

            /* Se verifica si los datos del formulario estan vacios */
            if ("".equals(jTextField2.getText())
                    || "".equals(String.valueOf(jPasswordField1.getPassword()))
                    || "".equals(jTextField3.getText())
                    || "".equals(jTextField4.getText())
                    || "".equals(jTextField7.getText())
                    || "".equals(jTextField9.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "Debe diligenciar los campos obligatorios (*)", "Error",
                        JOptionPane.ERROR_MESSAGE);

                /* Se verifica si el formato del correo es correcto */
            } else if (!Validaciones.formatoCorreo(jTextField7.getText())) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "Formato de correo incorrecto", "Error",
                        JOptionPane.ERROR_MESSAGE);

                /* 
                 * Se verifica si el docuemento ingresado ya existe y es 
                 * diferente al actual
                 */
            } else if (Funcionario.VerificarDocumento(declaracion,
                    jTextField2.getText()) && !jTextField2.getText()
                    .equals(ID[ID.length - 1])) {

                /* Se muestra un mensaje de error */
                JOptionPane.showMessageDialog(null,
                        "El documento seleccionado ya existe", "Error",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                /*
                 * Se actualizan los datos del instructor en 
                 * la base de datos
                 */
                Funcionario.ActualizarEnDocumento(declaracion, datos,
                        ID[ID.length - 1]);
                

                /* 
                 * Se verifica si el documento del usuario se ha actualizado 
                 * para cambiarlo en pantalla
                 */
                if (usuario.equals(ID[ID.length - 1])) {

                    usuario = jTextField2.getText();
                    Historial.Insertar(declaracion, usuario, "Actualizó sus datos personales");
                    
                }else{
                    Historial.Insertar(declaracion, usuario, "Actualizó un funcionario");
                }

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

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

        /* Dar una longitud maxima de caracteres de 18 */
        Validaciones.longitud(evt, jTextField2.getText().length(), 18);

        /* Restringir los caracteres no numericos */
        Validaciones.restringirCaracterFueraDe(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped

        /* Dar una longitud maxima de caracteres de 30 */
        Validaciones.longitud(evt, String.valueOf(jPasswordField1.getPassword()).length(), 30);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped

        /* Dar una longitud maxima de caracteres de 30 */
        Validaciones.longitud(evt, jTextField3.getText().length(), 30);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

        /* Restringir el caracter 32 (espacio) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 32);

        /* restringir numeros */
        Validaciones.restringirCaracterEntre(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped

        /* Dar una longitud maxima de caracteres de 30 */
        Validaciones.longitud(evt, jTextField4.getText().length(), 30);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

        /* Restringir el caracter 32 (espacio) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 32);

        /* restringir numeros */
        Validaciones.restringirCaracterEntre(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped

        /* Dar una longitud maxima de caracteres de 30 */
        Validaciones.longitud(evt, jTextField5.getText().length(), 30);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

        /* Restringir el caracter 32 (espacio) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 32);

        /* restringir numeros */
        Validaciones.restringirCaracterEntre(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped

        /* Dar una longitud maxima de caracteres de 30 */
        Validaciones.longitud(evt, jTextField7.getText().length(), 100);

        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

        /* Restringir el caracter 32 (espacio) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 32);

    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped

        /* Dar una longitud maxima de caracteres de 18 */
        Validaciones.longitud(evt, jTextField8.getText().length(), 18);

        /* Restringir los caracteres no numericos*/
        Validaciones.restringirCaracterFueraDe(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped

        /* Dar una longitud maxima de caracteres de 18 */
        Validaciones.longitud(evt, jTextField9.getText().length(), 18);

        /* Restringir los caracteres no numericos*/
        Validaciones.restringirCaracterFueraDe(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField9KeyTyped

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed

        /**
         * Se abre el Frame corespondiente para gestionar la foto del usuario
         * actual
         */
        Foto foto = new Foto(jLabel3, declaracion, usuario, tipo);
        foto.setLocationRelativeTo(null);   //se ubica al centro
        foto.setVisible(true);      //se le da visivilidad

    }//GEN-LAST:event_jLabel3MousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
        /**
         * Se abre el Frame corespondiente para la ayuda del sistema
         */
        Ayuda ayuda = new Ayuda();
        ayuda.setLocationRelativeTo(null);   //se ubica al centro
        ayuda.setVisible(true);      //se le da visivilidad
        
    }//GEN-LAST:event_jButton13ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
