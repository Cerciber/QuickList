/*
 * Confirmacion.java
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

import java.awt.Component;
import java.sql.Statement;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.Arreglo;
import com.quicklist.funciones.AnimacionObjetos;
import com.quicklist.funciones.DatosUsuario;

/**
 * Esta clase permite confirmar si el usuario realmente desdea realizar 
 * una acción determinada de gran importancia
 */
public final class Confirmacion extends javax.swing.JPanel {

    int velocidad = 100;    //Corrimiento de la animación de los objetos
    String usuario;     //Documento del usuario que accede a la clase
    String retorno;     //Ruta de acceso a la ventana anterior
    String tipo;    //Rol del usuario que accede a la clase
    String nombrePantalla;      //Ruta de la ventana actual
    
    /** 
     * Arreglo que almacena los identificadores nesesarios para cargar los 
     * datos en cada una de las pantallas a las que se ha accedido desde el 
     * login para recuperar las pantallas anteriores en caso de retorno
     */
    String[] ID;    
    
    /**
     * Objeto empleado para realizar la consultas en la base de datos
     */
    Statement declaracion;
    
    /**
     * Arreglo que contiene todos los componentes de la pantalla 
     * a los cuales se les da movimineto inicial
     */
    Component[] objeto;
    
    /**
     * Arreglo que contiene las direcciones respectivas para la aprobacion 
     * y el rechazo de la condición
     */
    String[] vinculo;
    
    /**
     * Metodo constructor de la clase
     * @param tipo
     * @param retorno
     * @param nombrePantalla
     * @param usuario
     * @param ID
     * @param declaracion 
     * @param pregunta 
     * @param vinculo 
     */
    public Confirmacion(String tipo, String retorno, String nombrePantalla, 
                        String usuario, String[] ID, Statement declaracion, 
                        String pregunta, String[] vinculo) {
        
        /*
         * Se asignan los valores de los parametros de forma global
         */
        this.tipo = tipo;
        this.retorno = retorno;
        this.usuario = usuario;
        this.declaracion = declaracion;
        this.ID = ID;
        this.vinculo = vinculo;
        this.nombrePantalla = nombrePantalla;
        
        initComponents();   //Se crean los componentes graficos
        
        /* Se cargan y se ubican los datos del usuario */
        new DatosUsuario(usuario, tipo, declaracion, jLabel1, jLabel2, jLabel3);
        
        /* Se asigna la variable String con la pregunta en el label */
        jLabel15.setText(pregunta);
        
        /**
         * Permite que el usuario pueda mover el panel que contiene la tabla
         * dentro del frame con el mouse y con las flechas del teclado
         */
        new MoverObjeto(jPanel8);
        
        
    } 

    /**
     * Permite seleccionar los componentes en el panel a los cuales 
     * se les dara animación
     */
    public void movimiento(){
        
        /* Se crea el arreglo global con los componentes */
        objeto = new Component[3];
        objeto[0]=this.jLabel15;
        objeto[1]=this.jButton5;
        objeto[2]=this.jButton6;
        
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
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 204, 204));
        jButton1.setText("☼");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 204, 204));
        jButton2.setText("?");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jPanel4.add(jButton2);

        jButton3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 204, 204));
        jButton3.setText("Volver");
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 204, 204));
        jButton4.setText("Salir");
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setOpaque(false);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel15.setText("Pregunta");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 255, 255));
        jButton5.setText("Si");
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
        jButton6.setText("No");
        jButton6.setAutoscrolls(true);
        jButton6.setOpaque(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jButton9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 102, 102));
        jButton9.setText("Volver");
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9);

        jButton14.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 102, 102));
        jButton14.setText("Salir");
        jButton14.setBorder(null);
        jButton14.setContentAreaFilled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton14);

        jButton15.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 102, 102));
        jButton15.setText("☼");
        jButton15.setBorder(null);
        jButton15.setContentAreaFilled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton15);

        jButton16.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton16.setForeground(new java.awt.Color(0, 102, 102));
        jButton16.setText("?");
        jButton16.setBorder(null);
        jButton16.setContentAreaFilled(false);
        jPanel5.add(jButton16);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        if("PantallaInicio".equals(retorno)){
        
            jButton4ActionPerformed(evt);
            
        }else{
        
            new AnimacionObjetos().RIzquierda(objeto, velocidad,this,retorno,nombrePantalla,tipo,usuario,ID,declaracion);
        
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  
        Component[] componentes=new Component[objeto.length+2];
        componentes[0]=jPanel2;
        componentes[1]=jPanel3;
        
        for(int i=2;i<=componentes.length-1;i++){
        
            componentes[i]=objeto[i-2];
        }
        
        new AnimacionObjetos().RIzquierda(componentes, velocidad,this,"PantallaInicio",nombrePantalla,tipo,usuario,ID,declaracion);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        /* 
         * Se animan los objetos para que salgan del panel y se realiza 
         * el cambio de pantalla
         */
       new AnimacionObjetos().RIzquierda(objeto, velocidad,this,vinculo[0],nombrePantalla,tipo,usuario,ID,declaracion);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        /* 
         * Se animan los objetos para que salgan del panel y se realiza 
         * el cambio de pantalla
         */
        new AnimacionObjetos().RIzquierda(objeto, velocidad,this,vinculo[1],nombrePantalla,tipo,usuario,Arreglo.quitar(ID),declaracion);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * Boton "Volver"
     * Este evento permite retornar a la pantalla desde la que 
     * se accedió a la actual
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        /* Se verifica si el retorno corresponde a la pantalla inicio.*/
        if ("PantallaInicio".equals(retorno)) {

            /* Se emplea la funcionalidad del botón "Salir" */
            jButton4ActionPerformed(evt);

        } else {

            /* 
             * Se animan los objetos para que salgan del panel y se realiza 
             * el cambio de pantalla
             */
            new AnimacionObjetos().RIzquierda(objeto, velocidad, this, retorno, 
                                         nombrePantalla, tipo, usuario, 
                                         Arreglo.quitar(ID), declaracion);
            
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * Boton "Salir"
     * Este evento permite retornar a la pantalla de ingreso y cerrar la sesión
     */
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        /*
         * Se crea un arreglo de componentes para alamcenar todos los objetos 
         * que se van a animar al momento de la salida
         */
        Component[] componentes = new Component[objeto.length+2];
        
        componentes[0] = jPanel2;   //Se añade el panel superior
        componentes[1] = jPanel3;   //Se añade el panel inferior

        /* Se añaden los demas objetos a los que se les dió la animación */
        for (int i = 2; i <= componentes.length - 1; i++){
            componentes[i] = objeto[i - 2];       
        }

        /* 
         * Se animan los objetos para que salgan del panel y se realiza 
         * el cambio de pantalla
         */
        new AnimacionObjetos().RIzquierda(componentes, velocidad, this, 
                                     "PantallaInicio", nombrePantalla, tipo, 
                                     usuario, null, declaracion);
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
