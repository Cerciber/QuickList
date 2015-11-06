/*
 * UbicarLista.java
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

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author pabloycesar
 */
public final class UbicarLista {
    
    public UbicarLista(JPanel contenedor,Component[] objeto){

        contenedor.removeAll();
        distribucion(contenedor,objeto);

    }
    
    public void distribucion(JPanel panel, Component[] objeto){
    
        javax.swing.GroupLayout jPanelDeslizableLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanelDeslizableLayout);
        jPanelDeslizableLayout.setVerticalGroup(alineamientoVertical(jPanelDeslizableLayout,objeto));
        jPanelDeslizableLayout.setHorizontalGroup(alineamientoHorizontal(jPanelDeslizableLayout,objeto));
        

    }
    
    public GroupLayout.ParallelGroup alineamientoHorizontal(GroupLayout layout, Component[] objeto){
        
        GroupLayout.ParallelGroup parallelGroup= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING); 
        GroupLayout.ParallelGroup parallelGroup2= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING); 
     
        for(int i=0;i<=objeto.length-1;i++){
        
            parallelGroup2.addComponent(objeto[i], javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE);
            parallelGroup2.addGap(5);
        }
        
        GroupLayout.SequentialGroup sequentialGroup= layout.createSequentialGroup(); 
        sequentialGroup.addGap(30);
        sequentialGroup.addGroup(parallelGroup2);
        sequentialGroup.addGap(30);
        parallelGroup.addGroup(sequentialGroup); 
        

        return parallelGroup; 

        
    }
    
     public GroupLayout.ParallelGroup alineamientoVertical(GroupLayout layout,Component[] objeto){
        
        GroupLayout.ParallelGroup parallelGroup= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING); 
        GroupLayout.SequentialGroup sequentialGroup= layout.createSequentialGroup();
        
        sequentialGroup.addGap(30);
        for(int i=0;i<=objeto.length-1;i++){
        
            sequentialGroup.addComponent(objeto[i], javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE); 
            sequentialGroup.addGap(5);
            
        }
        sequentialGroup.addGap(30);

        parallelGroup.addGroup(sequentialGroup); 
        layout.setHorizontalGroup(parallelGroup); 

        return parallelGroup; 
        
    }
     
    public UbicarLista(JPanel contenedor,Component[][] objeto, Component[][] boton){

        contenedor.removeAll();
        distribucion(contenedor,objeto,boton);

    }
    
    public void distribucion(JPanel panel, Component[][] objeto, Component[][] boton){
    
        javax.swing.GroupLayout jPanelDeslizableLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanelDeslizableLayout);
        jPanelDeslizableLayout.setVerticalGroup(alineamientoVertical(jPanelDeslizableLayout,objeto,boton));
        jPanelDeslizableLayout.setHorizontalGroup(alineamientoHorizontal(jPanelDeslizableLayout,objeto,boton));
        

    }
    
    public GroupLayout.ParallelGroup alineamientoHorizontal(GroupLayout layout, Component[][] objeto, Component[][] boton){
        
        GroupLayout.ParallelGroup parallelGroup= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING); 
        GroupLayout.SequentialGroup sequentialGroup= layout.createSequentialGroup(); 
        GroupLayout.ParallelGroup parallelGroupBoton[] = new GroupLayout.ParallelGroup[boton[0].length];
        GroupLayout.ParallelGroup parallelGroupObjeto[] = new GroupLayout.ParallelGroup[objeto[0].length]; 
     
        for(int i=0;i<=boton[0].length-1;i++){
            
            parallelGroupBoton[i]= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);       
            
        }
        
        for(int i=0;i<=objeto[0].length-1;i++){
            
            parallelGroupObjeto[i]= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);       
            
        }

        for(int i=0;i<=boton.length-1;i++){
            
            for(int j=0;j<=boton[i].length-1;j++){
        
                parallelGroupBoton[j].addComponent(boton[i][j], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE);
        
            }
           
        }
        
        for(int i=0;i<=objeto.length-1;i++){
            
            for(int j=0;j<=objeto[i].length-1;j++){
        
                parallelGroupObjeto[j].addComponent(objeto[i][j], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
            }
           
        }
        
        sequentialGroup.addGap(30);
        
        for(int i=0;i<=boton[0].length-1;i++){
            
            sequentialGroup.addGroup(parallelGroupBoton[i]);     
            sequentialGroup.addGap(5);
            
        }
        
        for(int i=0;i<=objeto[0].length-1;i++){
            
            sequentialGroup.addGroup(parallelGroupObjeto[i]);
            sequentialGroup.addGap(5);
            
        }
        
        sequentialGroup.addGap(30);
        parallelGroup.addGroup(sequentialGroup); 
        

        return parallelGroup; 

        
    }
    
    public GroupLayout.ParallelGroup alineamientoVertical(GroupLayout layout,Component[][] objeto, Component[][] boton){
        
        GroupLayout.ParallelGroup parallelGroup= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING); 
        GroupLayout.SequentialGroup sequentialGroup= layout.createSequentialGroup();
        GroupLayout.ParallelGroup parallelGroupComponentes[] = new GroupLayout.ParallelGroup[boton.length+objeto.length];
        
        sequentialGroup.addGap(30);
        
        for(int i=0;i<=boton.length+objeto.length-1;i++){
        
            parallelGroupComponentes[i]=layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING); 
            
        }
        
        for(int i=0;i<=objeto.length-1;i++){
            
            for(int j=0;j<=objeto[i].length-1;j++){
        
                parallelGroupComponentes[i].addComponent(objeto[i][j], javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE); 
                
            }

        }
        
        for(int i=0;i<=boton.length-1;i++){
            
            for(int j=0;j<=boton[i].length-1;j++){
        
                parallelGroupComponentes[i].addComponent(boton[i][j], javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE);
                
            }
            
        }
        
        for(int i=0;i<=boton.length-1;i++){

            sequentialGroup.addGroup(parallelGroupComponentes[i]);
            sequentialGroup.addGap(5);
            
        }
        
        sequentialGroup.addGap(30);

        parallelGroup.addGroup(sequentialGroup); 
        layout.setHorizontalGroup(parallelGroup); 

        return parallelGroup; 
        
    }
     
    public UbicarLista(JPanel contenedor,Component[][] objeto, Component[][] boton, JButton[] campo){

        contenedor.removeAll();
        distribucion(contenedor,objeto,boton,campo);

    }
    
    public void distribucion(JPanel panel, Component[][] objeto, Component[][] boton, JButton[] campo){
    
        javax.swing.GroupLayout jPanelDeslizableLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanelDeslizableLayout);
        jPanelDeslizableLayout.setVerticalGroup(alineamientoVertical(jPanelDeslizableLayout,objeto,boton,campo));
        jPanelDeslizableLayout.setHorizontalGroup(alineamientoHorizontal(jPanelDeslizableLayout,objeto,boton,campo));
        

    }
    
    public GroupLayout.ParallelGroup alineamientoHorizontal(GroupLayout layout, Component[][] objeto, Component[][] boton, JButton[] campo){
        
        GroupLayout.ParallelGroup parallelGroup= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING); 
        GroupLayout.SequentialGroup sequentialGroup= layout.createSequentialGroup(); 
        GroupLayout.ParallelGroup parallelGroupBoton[] = new GroupLayout.ParallelGroup[boton[0].length];
        GroupLayout.ParallelGroup parallelGroupObjeto[] = new GroupLayout.ParallelGroup[objeto[0].length]; 
     
        for(int i=0;i<=boton[0].length-1;i++){
            
            parallelGroupBoton[i]= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);       
            
        }
        
        for(int i=0;i<=objeto[0].length-1;i++){
            
            parallelGroupObjeto[i]= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);       
            
        }
        
        for(int j=0;j<=parallelGroupBoton.length-1;j++){

            if(campo[j].getText().isEmpty()){
                
                parallelGroupBoton[j].addComponent(campo[j], 50,  50,  50);
                
            }else{
                
                parallelGroupBoton[j].addComponent(campo[j], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
                
            }
            
        }
        
        for(int i=0;i<=boton.length-1;i++){

            for(int j=0;j<=parallelGroupBoton.length-1;j++){
        
                if(campo[j].getText().isEmpty()){

                    parallelGroupBoton[j].addComponent(boton[i][j],  50,  50,  50);

                }else{

                    parallelGroupBoton[j].addComponent(boton[i][j],  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
                    
                }
        
            }
           
        }
        
        for(int j=0;j<=parallelGroupObjeto.length-1;j++){
        
            parallelGroupObjeto[j].addComponent(campo[j+boton[0].length], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);

        }
        
        for(int i=0;i<=objeto.length-1;i++){
            
            for(int j=0;j<=objeto[i].length-1;j++){
        
                parallelGroupObjeto[j].addComponent(objeto[i][j], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
            }
           
        }
        
        sequentialGroup.addGap(30);
        
        for(int i=0;i<=parallelGroupBoton.length-1;i++){
            
            sequentialGroup.addGroup(parallelGroupBoton[i]);     
            sequentialGroup.addGap(5);
            
        }
        
        for(int i=0;i<=parallelGroupObjeto.length-1;i++){
            
            sequentialGroup.addGroup(parallelGroupObjeto[i]);
            sequentialGroup.addGap(5);
            
        }
        
        sequentialGroup.addGap(30);
        parallelGroup.addGroup(sequentialGroup); 
        

        return parallelGroup; 

        
    }
    
     public GroupLayout.ParallelGroup alineamientoVertical(GroupLayout layout,Component[][] objeto, Component[][] boton, Component[] campo){
        
        GroupLayout.ParallelGroup parallelGroup= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING); 
        GroupLayout.SequentialGroup sequentialGroup= layout.createSequentialGroup();
        GroupLayout.ParallelGroup parallelGroupComponentes[] = new GroupLayout.ParallelGroup[boton.length+1];
        
        sequentialGroup.addGap(30);
        
        for(int i=0;i<=parallelGroupComponentes.length-1;i++){
        
            parallelGroupComponentes[i]=layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING); 
            
        }
        
        for(int j=0;j<=campo.length-1;j++){
        
            parallelGroupComponentes[0].addComponent(campo[j], javax.swing.GroupLayout.DEFAULT_SIZE,  javax.swing.GroupLayout.DEFAULT_SIZE,  Short.MAX_VALUE); 

        }
        
        for(int i=1;i<=parallelGroupComponentes.length-1;i++){
            
            for(int j=0;j<=objeto[i-1].length-1;j++){
        
                parallelGroupComponentes[i].addComponent(objeto[i-1][j], javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE); 
                
            }

            for(int j=0;j<=boton[i-1].length-1;j++){
        
                parallelGroupComponentes[i].addComponent(boton[i-1][j], javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE);
                
            }
            
        }

        for(int i=0;i<=parallelGroupComponentes.length-1;i++){

            sequentialGroup.addGroup(parallelGroupComponentes[i]);
            sequentialGroup.addGap(5);
            
        }
        
        sequentialGroup.addGap(30);

        parallelGroup.addGroup(sequentialGroup); 
        layout.setHorizontalGroup(parallelGroup); 

        return parallelGroup; 
        
    }
     

}
