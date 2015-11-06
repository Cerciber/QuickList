/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist;

import com.quicklist.clases.Aprendiz;
import com.quicklist.clases.Funcionario;
import com.quicklist.funciones.ConvertirFoto;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author cesaraugusto
 */
public class Foto extends javax.swing.JFrame {

    /**
     * Creates new form Foto
     */
    static String usuario;
    static String tipo;
    static JLabel label;
    static Statement declaracion;
    JFileChooser abrirArchivo = new JFileChooser();
    File direccion;
    FileInputStream archivoImagen = null;
    boolean quitarFoto=false;
    boolean modificado=false;
    
    public Foto(JLabel label, Statement declaracion, String usuario, String tipo) {
       
        Foto.label=label;
        Foto.usuario=usuario;
        Foto.declaracion=declaracion;
        Foto.tipo=tipo;
        initComponents();
        
        try{
            
            if("Administrador".equals(tipo) || "Instructor".equals(tipo)){

                ImageIcon icono = new ImageIcon(Funcionario.SeleccionarFoto(declaracion, usuario));
                ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( jLabel1.getWidth(),jLabel1.getHeight(), 300)); 
                this.jLabel1.setIcon(imageScalada);
                this.jLabel1.setText(null);

            }else if("Aprendiz".equals(tipo)){

                ImageIcon icono = new ImageIcon(Aprendiz.SeleccionarFoto(declaracion, usuario));
                ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( jLabel1.getWidth(),jLabel1.getHeight(), 300)); 
                this.jLabel1.setIcon(imageScalada);
                this.jLabel1.setText(null);

            }
            
        }catch(NullPointerException ex){
            
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

        int resp;
        
        resp=abrirArchivo.showOpenDialog(this);

        if (resp==JFileChooser.APPROVE_OPTION){
            
            direccion = new File (abrirArchivo.getSelectedFile().toString());
            
            ImageIcon icono = new ImageIcon(direccion.toString());
            
            try {
                
                archivoImagen = new FileInputStream(direccion);
                
            } catch (FileNotFoundException ex) {System.out.println(ex);}
            
            ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( jLabel1.getWidth(),jLabel1.getHeight(), 300)); 

            this.jLabel1.setText(null);
            this.jLabel1.setIcon(imageScalada);
            this.quitarFoto=false;
            modificado=true;

        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        this.jLabel1.setIcon(null);
        jLabel1.setText("Sin Foto");
        quitarFoto=true;
        modificado=true;
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        if(modificado){
            
            if(quitarFoto){

                if("Administrador".equals(tipo) || "Instructor".equals(tipo)){

                    Funcionario.ActualizarFoto(declaracion, usuario, archivoImagen);

                }else if("Aprendiz".equals(tipo)){

                    Aprendiz.ActualizarFoto(declaracion, usuario, archivoImagen);

                }

                this.label.setText(null);
                this.label.setIcon(null);

            }else{

                if("Administrador".equals(tipo) || "Instructor".equals(tipo)){

                    Funcionario.ActualizarFoto(declaracion, usuario, archivoImagen);

                }else if("Aprendiz".equals(tipo)){

                    Aprendiz.ActualizarFoto(declaracion, usuario, archivoImagen);

                }

                if("Administrador".equals(tipo) || "Instructor".equals(tipo)){

                    this.label.setText(null);
                    ImageIcon icono = new ImageIcon(Funcionario.SeleccionarFoto(declaracion, usuario));
                    ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( 66,76, 66)); 
                    this.label.setIcon(imageScalada);

                }else if("Aprendiz".equals(tipo)){

                    this.label.setText(null);
                    ImageIcon icono = new ImageIcon(Aprendiz.SeleccionarFoto(declaracion, usuario));
                    ImageIcon imageScalada = new ImageIcon(icono.getImage().getScaledInstance( 66,76, 66)); 
                    this.label.setIcon(imageScalada);

                }

            }

        }
            
        this.dispose();
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
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
                new Foto(label,declaracion,usuario,tipo).setVisible(true);
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
