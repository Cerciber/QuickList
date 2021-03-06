/*
 * PantallaInicio.java
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

import static com.quicklist.Foto.declaracion;
import static com.quicklist.clases.Configuracion.cargarConfiguracion;
import static com.quicklist.clases.Configuracion.cargarLogin;
import static com.quicklist.clases.Configuracion.guardarLogin;
import com.quicklist.clases.Historial;
import com.quicklist.funciones.AnimacionObjetos;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.ConvertirConsulta;
import com.quicklist.funciones.Validaciones;
import javax.swing.JOptionPane;

/**
 * Esta clase es la pantalla de inicio, la cual permite a los usuarios logearse
 * en el sistema
 */
public class PantallaInicio extends javax.swing.JPanel {

    int velocidad = 100;    //Corrimiento de la animación de los objetos
    String usuario;     //Documento del usuario que accede a la clase
    String tipo;    //Rol del usuario que accede a la clase

    /**
     * Objeto empleado para realizar la consultas en la base de datos
     */
    Statement declaracion;

    /**
     * Arreglo que contiene la configuración actual de la aplicación
     */
    int[] conf = cargarConfiguracion();

    /**
     * Metodo constructor de la clase
     */
    public PantallaInicio() {

        initComponents();   //Se crean los componentes graficos

        /**
         * Permite que el usuario pueda mover el panel que contiene la tabla
         * dentro del frame con el mouse y con las flechas del teclado
         */
        new MoverObjeto(jPanel1);

        /**
         * Se verifica si esta activada la opción de recordar contraseña
         */
        if (conf[12] == 1) {

            /**
             * Se cargan los datos de el ultimo ingreso a la aplicación
             */
            String[] login = cargarLogin();
            jTextField2.setText(login[0]);
            jPasswordField1.setText(login[1]);
            jComboBox1.setSelectedItem(login[2]);

        }

    }

    /**
     * Permite seleccionar los componentes en el panel a los cuales se les dara
     * animación
     */
    public void movimiento() {

        /* Se crea el arreglo con los componentes */
        Component[] objeto = {jPanel2, jPanel7, jPanel5, jPanel8, jPanel6};

        /* 
         * Permite dar un movimiento inicial a los objetos del arreglo en 
         * forma secuencial
         */
        new AnimacionObjetos().Izquierda(objeto, velocidad);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setOpaque(false);

        jPanel1.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 2, 120)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quick");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 320, 106));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 2, 120)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("List");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 200, 106));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Contraseña");

        jPasswordField1.setBackground(new java.awt.Color(0, 153, 153));
        jPasswordField1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addGap(94, 94, 94)
                .addComponent(jPasswordField1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ingresar");
        jButton1.setAutoscrolls(true);
        jButton1.setBorderPainted(false);
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Usuario");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addGap(182, 182, 182)
                .addComponent(jTextField2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Tipo");

        jComboBox1.setBackground(new java.awt.Color(0, 153, 153));
        jComboBox1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aprendiz", "Instructor", "Administrador" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(115, 115, 115)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(150, 150, 150))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(203, 203, 203))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(258, 258, 258))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        /**
         * Boton "Ingresar"
         */
        /**
         * Se crea el areglo que va a contener los identificadores de las
         * pantallas
         */
        String[] ID = {"☺"};

        /**
         * Se intenta establecer la conexión a la base de datos
         */
        try {

            /* Se crea la conexión en la ruta local especificada */
            /*Connection conexion = DriverManager
            .getConnection("jdbc:sqlserver://CERCIBER\\SQLEXPRESS:1433;"
            + "databaseName=BaseDeDatosQuickList",
            "cerciber", "123456789");*/

             //Se crea la conexión en el servidor web especificado
             Connection conexion = DriverManager
             .getConnection("jdbc:sqlserver://BaseDeDatosQuickList.mssql.somee.com;databaseName=BaseDeDatosQuickList",
             "quicklistcerciber","123456789"); 
            /* Se le da la propiedad de movimiento entre los registros 
             * y la propiedad de actualización 
             */
            declaracion = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            /* 
             * Variable que almacena la consulta realizada en la base de 
             * datos 
             */
            ResultSet resultado;

            /* Se obtiene el dato de el usuario ingresado */
            usuario = jTextField2.getText();

            /**
             * Se verifica si el capo del usuario esta vacio
             */
            if ("".equals(usuario)) {
                usuario = "0";
            }

            /**
             * Se verifica si el capo de la contraseña esta vacio
             */
            String contrasena = String.valueOf(jPasswordField1.getPassword());
            if ("".equals(contrasena)) {
                contrasena = "0";
            }

            /* Se crea el arreglo con los componentes a animar */
            Component[] objeto = {jPanel2, jPanel7, jPanel5, jPanel8, jPanel6};

            /* Se verifica si el rol seleccionado es un aprendiz */
            if (jComboBox1.getSelectedItem() == "Aprendiz") {

                /* 
                 * Se asigna el tipo de usuario que esta accediendo al 
                 * sistema 
                 */
                tipo = "Aprendiz";

                /* Se buscan los datos ingresados en la base de datos */
                resultado = declaracion.executeQuery("select "
                        + "Documento_De_Identidad,Contrasena from "
                        + "T_Informacion_Aprendices where "
                        + "Documento_De_Identidad=" + usuario + " and "
                        + "CONVERT(VARCHAR(300),DECRYPTBYPASSPHRASE('cerciber',contrasena))='" + contrasena + "';");

                /**
                 * Se verifica si existe el registro ingresado
                 */
                if (new ConvertirConsulta().NRegistros(resultado) == 1) {

                    /**
                     * Se guarda el login en caso de que se desee recordar la
                     * contraseña
                     */
                    guardarLogin(usuario, contrasena, tipo);

                    /* 
                     * Se animan los objetos para que salgan del panel y se 
                     * realiza el cambio de pantalla
                     */
                    new AnimacionObjetos().RIzquierda(objeto, velocidad,
                            (JPanel) this, "T_Aprendiz", "", tipo, usuario,
                            ID, declaracion);

                } else {

                    /**
                     * Se notifica que los datos son erroneos
                     */
                    jLabel7.setText("Aprendiz Incorrecto");

                }

            } /* Se verifica si el rol seleccionado es un Instructor */ else if (jComboBox1.getSelectedItem() == "Instructor") {

                /* 
                 * Se asigna el tipo de usuario que esta accediendo al 
                 * sistema 
                 */
                tipo = "Instructor";

                /* Se buscan los datos ingresados en la base de datos */
                resultado = declaracion.executeQuery("select "
                        + "Documento_De_Identidad,Contrasena from "
                        + "T_Informacion_Funcionarios where "
                        + "Documento_De_Identidad=" + usuario + " and "
                        + "CONVERT(VARCHAR(300),DECRYPTBYPASSPHRASE('cerciber',contrasena))='" + contrasena + "' and "
                        + "cargo='INSTRUCTOR';");

                /**
                 * Se verifica si existe el registro ingresado
                 */
                if (new ConvertirConsulta().NRegistros(resultado) == 1) {

                    /**
                     * Se guarda el login en caso de que se desee recordar la
                     * contraseña
                     */
                    guardarLogin(usuario, contrasena, tipo);
                    Historial.Insertar(declaracion, usuario, "Ingrezó al sistema");

                    /* 
                     * Se animan los objetos para que salgan del panel y se 
                     * realiza el cambio de pantalla
                     */
                    new AnimacionObjetos().RIzquierda(objeto, velocidad,
                            (JPanel) this, "T_Instructor", "", tipo, usuario,
                            ID, declaracion);

                } else {

                    /**
                     * Se notifica que los datos son erroneos
                     */
                    jLabel7.setText("Instructor Incorrecto");

                }

            } /* Se verifica si el rol seleccionado es un Instructor */ else if (jComboBox1.getSelectedItem() == "Administrador") {

                /* 
                 * Se asigna el tipo de usuario que esta accediendo al 
                 * sistema 
                 */
                tipo = "Administrador";

                /* Se buscan los datos ingresados en la base de datos */
                resultado = declaracion.executeQuery("select "
                        + "Documento_De_Identidad,Contrasena from "
                        + "T_Informacion_Funcionarios where "
                        + "Documento_De_Identidad=" + usuario + " and "
                        + "CONVERT(VARCHAR(300),DECRYPTBYPASSPHRASE('cerciber',contrasena))='" + contrasena + "' and "
                        + "cargo='ADMINISTRADOR';");

                /* Se verifica si existe el registro ingresado */
                if (new ConvertirConsulta().NRegistros(resultado) == 1) {

                    /**
                     * Se guarda el login en caso de que se desee recordar la
                     * contraseña
                     */
                    guardarLogin(usuario, contrasena, tipo);
                    Historial.Insertar(declaracion, usuario, "Ingrezó al sistema");

                    /* 
                     * Se animan los objetos para que salgan del panel y se 
                     * realiza el cambio de pantalla
                     */
                    new AnimacionObjetos().RIzquierda(objeto, velocidad,
                            (JPanel) this, "T_Administrador", "", tipo, usuario,
                            ID, declaracion);

                } else {

                    /**
                     * Se notifica que los datos son erroneos
                     */
                    jLabel7.setText("Administrador Incorrecto");

                }

            }

        } catch (SQLException ex) {

            /* Se muestra un mensaje de error */
            JOptionPane.showMessageDialog(null,
                    "No se pudo establecer la conexión", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

        /* Validaciones del campo de texto de usuario */
        /* Dar una longitud maxima de caracteres de 18 */
        Validaciones.longitud(evt, jTextField2.getText().length(), 18);

        /* restringir caracteres no numericos */
        Validaciones.restringirCaracterFueraDe(evt, evt.getKeyChar(), '0', '9');

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped

        /* Validaciones del campo de la contraseña de usuario */
        /* Dar una longitud maxima de caracteres de 30 */
        Validaciones.longitud(evt, String.valueOf(jPasswordField1
                .getPassword()).length(), 30);

        /* restringir caracteres no numericos */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);

    }//GEN-LAST:event_jPasswordField1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
