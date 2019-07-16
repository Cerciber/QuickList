/*
 * ArrastrarObjeto.java
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

import static com.quicklist.clases.Configuracion.cargarConfiguracion;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author pabloycesar
 */
public class MoverObjeto {

    int LugarPresionadoY;
    int LugarPresionadoX;

    int[] conf = cargarConfiguracion();

    public MoverObjeto(final Component objeto) {

        try {

            for (int i = 0; i <= ((JPanel) objeto).getComponentCount() - 1; i++) {

                ((JPanel) objeto).getComponent(i).addKeyListener(new java.awt.event.KeyAdapter() {

                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {

                        movimientoFlechas(evt, objeto);

                    }

                });

                try {

                    System.out.println(((JScrollPane) ((JPanel) objeto).getComponent(i)).getViewport().getView());
                    ((JScrollPane) ((JPanel) objeto).getComponent(i)).getViewport().getView().
                            addKeyListener(new java.awt.event.KeyAdapter() {

                                @Override
                                public void keyPressed(java.awt.event.KeyEvent evt) {

                                    movimientoFlechas(evt, objeto);

                                }

                            });

                } catch (ClassCastException ex) {
                } catch (NullPointerException ex) {
                }

            }

        } catch (ClassCastException ex) {
        }

        objeto.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {

                LugarPresionadoY = evt.getY();
                LugarPresionadoX = evt.getX();

            }

        });

        objeto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {

                int localizacionY = objeto.getLocation().y + evt.getY() - LugarPresionadoY;
                int localizacionX = objeto.getLocation().x + evt.getX() - LugarPresionadoX;

                if (localizacionY <= 0 && localizacionY >= objeto.getParent().getHeight() - objeto.getHeight()) {

                    objeto.setLocation(objeto.getLocation().x, objeto.getLocation().y + evt.getY() - LugarPresionadoY);
                }

                if (localizacionY >= 0) {

                    objeto.setLocation(objeto.getLocation().x, 0);

                }

                if (localizacionY <= objeto.getParent().getHeight() - objeto.getHeight()) {

                    objeto.setLocation(objeto.getLocation().x, objeto.getParent().getHeight() - objeto.getHeight());

                }

                if (localizacionX <= 0 && localizacionX >= objeto.getParent().getParent().getParent().getWidth() - objeto.getWidth()) {

                    objeto.setLocation(objeto.getLocation().x + evt.getX() - LugarPresionadoX, objeto.getLocation().y);
                }

                if (localizacionX >= 0) {

                    objeto.setLocation(0, objeto.getLocation().y);

                }

                if (localizacionX <= objeto.getParent().getParent().getParent().getWidth() - objeto.getWidth()) {

                    objeto.setLocation(objeto.getParent().getParent().getParent().getWidth() - objeto.getWidth(), objeto.getLocation().y);

                }
            }
        });

    }

    public void movimientoFlechas(java.awt.event.KeyEvent evt, Component panel) {

        if (conf[9] == 1) {

            if (evt.getKeyCode() == KeyEvent.VK_LEFT && panel.getLocation().x < 0 - conf[10]) {

                panel.setLocation(panel.getLocation().x + conf[10], panel.getLocation().y);

            } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {

                panel.setLocation(0, panel.getLocation().y);

            }

            if (evt.getKeyCode() == KeyEvent.VK_RIGHT && panel.getLocation().x + panel.getWidth() > panel.getParent().getParent().getParent().getWidth() + conf[10]) {

                panel.setLocation(panel.getLocation().x - conf[10], panel.getLocation().y);

            } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

                panel.setLocation(panel.getParent().getParent().getParent().getWidth() - panel.getWidth(), panel.getLocation().y);

            }

            if (evt.getKeyCode() == KeyEvent.VK_DOWN && panel.getLocation().y + panel.getHeight() > panel.getParent().getHeight() + conf[10]) {

                panel.setLocation(panel.getLocation().x, panel.getLocation().y - conf[10]);

            } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                panel.setLocation(panel.getLocation().x, panel.getParent().getHeight() - panel.getHeight());

            }

            if (evt.getKeyCode() == KeyEvent.VK_UP && panel.getLocation().y < 0 - conf[10]) {

                panel.setLocation(panel.getLocation().x, panel.getLocation().y + conf[10]);

            } else if (evt.getKeyCode() == KeyEvent.VK_UP) {

                panel.setLocation(panel.getLocation().x, 0);

            }

        }

    }

}
