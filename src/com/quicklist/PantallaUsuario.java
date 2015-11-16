/*
 * PantallaUsuario.java
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
import com.quicklist.clases.Consulta;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.quicklist.funciones.MoverObjeto;
import com.quicklist.funciones.Arreglo;
import com.quicklist.funciones.EventosMenu;
import com.quicklist.funciones.AnimacionObjetos;
import com.quicklist.funciones.UbicarLista;
import com.quicklist.funciones.DarIcono;
import com.quicklist.funciones.DatosUsuario;
import com.quicklist.funciones.Validaciones;
import java.awt.Dimension;

public final class PantallaUsuario extends javax.swing.JPanel {

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
     * Arreglo que contiene la configuración actual de la aplicación
     */
    int[] conf = cargarConfiguracion();
    
    /**
     * Arreglo que contiene los textos de mas de 30 caracteres en la pantalla
     */
    public JTextArea[][] TextArea;
    
    /**
     * Arreglo que contiene los textos de menos de 30 caracteres en la pantalla
     */
    public JLabel[][] label;
    
    /**
     * Arreglo que contiene la combinación de los labels y los textArea 
     * empleados
     */
    public Component[][] componente;
    
    /** Variable que verifica si se deben mostrar los botones de las tablas */
    boolean mostrarBotones=true;
    
    /**
     * Arreglo que contiene los textAreas empleados en las tablas
     */
    JScrollPane[][] scrollPane;
    
    /** 
     * Objeto que contiene todos los datos necesarios para gentionar las 
     * tablas en las busquedas
     */
    Consulta consulta;
    
    /**
     * Metodo constructor de la clase para menus
     * @param tipo
     * @param menu
     * @param vinculo
     * @param retorno
     * @param nombrePantalla
     * @param usuario
     * @param ID
     * @param declaracion 
     */
    public PantallaUsuario(String tipo, String[] menu, String[] vinculo, 
            String retorno, String nombrePantalla, String usuario, 
            Statement declaracion, String[] ID) {
        
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
        new DatosUsuario(usuario, tipo, declaracion,jLabel1, jLabel2, jLabel3);
        
        /* Se cargan los datos del menu de opciones de la pantalla actual */
        crearMenu(menu, vinculo);
        
        /* Se oculta la sección de busqueda de la tabla */
        jPanel5.setVisible(false);
        
        /**
         * Permite que el usuario pueda mover el panel que contiene la tabla
         * dentro del frame con el mouse y con las flechas del teclado
         */
        new MoverObjeto(jPanel8);
        
    }
    
    /**
     * Metodo constructor de la clase para tablas
     * @param tipo
     * @param menu
     * @param nombreBoton
     * @param nombreIcono
     * @param vinculo
     * @param nombreColumna
     * @param retorno
     * @param nombrePantalla
     * @param usuario
     * @param ID
     * @param declaracion 
     * @param consulta 
     */
    public PantallaUsuario(String tipo, String[][] menu, String[] nombreBoton, 
            String[] nombreIcono, String[] nombreColumna, String[] vinculo, 
            String retorno, String nombrePantalla, String usuario, 
            Statement declaracion, String[] ID, Consulta consulta) {
        
        /*
         * Se asignan los valores de los parametros de forma global
         */
        this.tipo = tipo;
        this.retorno = retorno;
        this.nombrePantalla = nombrePantalla;
        this.usuario = usuario;
        this.declaracion = declaracion;
        this.ID = ID;
        this.consulta = consulta;
        
        initComponents();   //Se crean los componentes graficos
        
        /* Se cargan y se ubican los datos del usuario */
        new DatosUsuario(usuario, tipo, declaracion, jLabel1, jLabel2, jLabel3);
        
        /* Se cargan los datos de la tabla de la pantalla actual */
        crearMenu(menu,vinculo,nombreBoton,nombreIcono,nombreColumna);
        
        /**
         * Permite que el usuario pueda mover el panel que contiene la tabla
         * dentro del frame con el mouse y con las flechas del teclado
         */
        new MoverObjeto(jPanel8);
        
        /** Se asigna el dato de la busqueda que se desea realizar */
        jTextField3.setText(consulta.busqueda);
        
        /** Se asigna el numero de registros por pagina */
        jTextField4.setText(consulta.nRegistrosPagina + "");
        
        /** 
         * Se asigna el numero de pagina inicial, el numero de pagina actual 
         * y el numero de pagina final 
         */
        jLabel6.setText("1 - " + consulta.paginaActual + " - "
                        + consulta.paginaFinal);
        
        /** Se asigna el icono de busqueda en el boton correspondiente */
        DarIcono.darIcono(jButton7, "Buscar");
        
    }    
    
    /** 
     * Este metodo permite dar el el primer movimiento de objetos al loguearse 
     * en el sistema
     */
    public void movimientoInicial(){
        
        /*
         * Se crea un arreglo de componentes para alamcenar todos los objetos 
         * que se van a animar
         */
        Component[] componentes = new Component[objeto.length + 2];
        
        
        componentes[0] = jPanel2;   //Se añade el panel superior
        componentes[1] = jPanel3;   //Se añade el panel inferior
        
        /* Se añaden los demas objetos a los que se les dió la animación */
        for (int i = 2; i <= componentes.length - 1; i++) {
        
            componentes[i] = objeto[i - 2];
            
        }
        
        /* 
         * Permite dar un movimiento inicial a los objetos del arreglo en 
         * forma secuencial
         */
        new AnimacionObjetos().Izquierda(componentes, velocidad);
    
    }
    
    /** 
     * Este metodo permite dar el  movimiento de objetos contenidos en el 
     * panel central
     */
    public void movimientoSecuencial(){
        
        /* 
         * Permite dar un movimiento inicial a los objetos del arreglo en 
         * forma secuencial
         */
        new AnimacionObjetos().Izquierda(objeto, velocidad);
    
    }
    
    /** 
     * Este metodo permite cargar los datos del menu de la pantalla actual 
     * @param menu
     * @param vinculo
     */
    public void crearMenu(String[] menu, String[] vinculo){
        
        /** 
         * Se crea un areglo de botones para contener cada uno de los 
         * elementos del menú 
         */
        JButton[] boton = new JButton[menu.length];
        
        /** 
         * Se crea un areglo de componentes para contener cada uno de los 
         * elementos del menú 
         */
        objeto = new Component[menu.length];

        /** Se asigna el diseño predeterminado a los botones */
        for (int i = 0; i <= boton.length - 1; i++) {

            boton[i] = new JButton();   //Instanciación
            
            /* Dar fuente, tipo de letra y tamaño */
            boton[i].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 
                                               conf[2]));
            
            /* Dar Fondo */
            boton[i].setBackground(jButton5.getBackground());
            
            /* Dar Color de letra */
            boton[i].setForeground(jButton5.getForeground());
            
            /* Dar texto correspondiente a un item del menú */
            boton[i].setText(menu[i]);

            /* 
             * Se asigna el botón actual al arreglo de componentes 
             * para la animación 
             */
            objeto[i] = boton[i];

        }

        /** Se ubican los elementos creados en el panel en forma de tabla */
        new UbicarLista(jPanel8,boton);
        
        /* 
         * Se asignan los eventos en los botones para realizar el cambio 
         * de pantalla 
         */
        new EventosMenu(objeto, velocidad, boton, this, vinculo, 
                nombrePantalla, tipo, usuario, declaracion, ID);

    }
    
    /** 
     * Este método permite ejecutar la busqueda en la base de datos 
     * modificando el estado de la tabla
     * @param nombreBoton
     * @param nombreIcono
     * @param vinculo
     * @param nombreColumna
     * @return
     */
    public String[][] buscar(String[] nombreBoton, String[] nombreIcono, 
                             String[] nombreColumna, String[] vinculo){
                
        /* Se gualda la localización de la tabla actual */
        consulta.posicion = jPanel8.getLocation();
        
        /* 
         * Se verifica que el limitante de registros no este vacio ni sea 
         * igual a 0 
         */
        if (!jTextField4.getText().isEmpty() && !"0".
                equals(jTextField4.getText())) {

            /* 
             * Se verifica si el numero ingresado en el limitante se ha 
             * cambiado 
             */
            if(consulta.nRegistrosPagina != Integer
                    .parseInt(jTextField4.getText())){
                
                /* Se reinicia el paginado de la tabla */
                consulta.registroInicial = 0; 
                consulta.paginaActual = 1; 
                
            }
            
            /* Se asigna el numero de registros por tabla */
            consulta.nRegistrosPagina = Integer.parseInt(jTextField4.getText());

        }

        /* Se asigna el filtro que se desea realizar */
        consulta.busqueda(jTextField3.getText()); 
        
        /* Se realiza la busqueda en la base de datos */
        String[][]menu = consulta.ejecutarConsulta();
        
        /* Se recarga la pantalla con la nueva consulta realizada */
        PantallaUsuario p = new PantallaUsuario(tipo, menu, nombreBoton, 
                nombreIcono, nombreColumna, vinculo, retorno, nombrePantalla, 
                usuario, declaracion, ID, consulta);
        
        consulta.panelContenedor.removeAll();   //Se remueve el panel actual
        consulta.panelContenedor.add(p);    //Se asigna el nuevo panel
        consulta.panelContenedor.validate();    //Se vualve a cargar la pantalla

        /* Se sintenta verificar si hay registros para mostrar */
        try {

            /* Se verifica si hay registros para mostrar */
            if (menu[0][0] != null) {
                
                /* Se asigna la localización del panel anterior */
                p.jPanel8.setLocation(consulta.posicion);
                
            }

        }catch(ArrayIndexOutOfBoundsException ex){}

        /* Se asigna el color de la columna seleccionada para resaltarla */
        EventosMenu.darColorColumna(p.componente, 
                consulta.nColumnaSeleccionada);
                
        /* Se retorna la variable con la consulta realizada */
        return menu;
        
    }
    
    /** 
     * Este metodo permite cargar los datos de la tabla de la pantalla actual 
     * @param menu
     * @param vinculo
     * @param nombreBoton
     * @param nombreIcono
     * @param nombreColumna
     */
    public void crearMenu(String[][] menu, String[] vinculo, 
            String[] nombreBoton, String[] nombreIcono, 
            String[] nombreColumna) {
        
        /* 
         * Se crea un arreglo para almacenar el identificador de cada registro 
         * de la tabla 
         */
        String[] ID = new String[menu.length]; 
        
        /* Se crea un srreglo bidimencional para verificar si exsisten registros */
        String[][] menu2;

        /* Se intenta asignar la longitud del arreglo*/
        try{

            /* Se asigna la longitud dependiendo del numero de registros */
            menu2 = new String[menu.length][menu[0].length-1];

        }catch(ArrayIndexOutOfBoundsException ex){

            /* se le da una longitud de 1 x 1*/
            menu2 = new String[1][1];
            
            /*Se le almacena el mensaje de notificación*/
            menu2[0][0]="No existen registros para mostrar";
            
            /* Se desactiva el verificador de mostrar botones */
            mostrarBotones=false;

        }

        /* 
         * Se asignan los datos de la cosulta en la variable en caso de que
         * hallan 
         */
        for (int i = 0; i <= menu.length - 1; i++) {

            /* Se asigna el identificador del registro */
            ID[i] = menu[i][0];

            /* Se asignan los campos del registro */
            for (int j = 1; j <= menu[0].length - 1; j++) {

                menu2[i][j - 1] = menu[i][j];

            }
            
        }
        
        /*Se asigna el resultado de la variable de verificación en la real*/
        menu = menu2;  

        /* Se definen las longitudes de los elemtos de la pantalla de acuerdo 
         * a las dimenciones de los registros 
         */
        label = new JLabel[menu.length][menu[0].length];
        scrollPane = new JScrollPane[menu.length][menu[0].length];
        TextArea = new JTextArea[menu.length][menu[0].length];
        componente = new Component[menu.length][menu[0].length];
        objeto = new Component[(menu.length*menu[0].length)+(menu.length*nombreBoton.length)];

        /*
         * Se efectuan dos ciclos repetitivos para recorrer cada uno de los 
         * campos de la consulta
         */
        for (int i = 0; i <=label.length - 1; i++) {

            for (int j = 0; j <= label[i].length - 1; j++) {

                /* 
                 * Se verifica si la longitud del texto del campo el mayor a 30
                 */
                if (menu[i][j].length() < 30) {

                    label[i][j] = new JLabel();     //Instanciación

                    /* Se le da el diseño predeterminado al label */
                    
                    /* Se asigna el color de fondo */
                    label[i][j].setBackground(new java.awt.Color(204, 255, 
                                                                 255));
                    
                    /* Se asigna la fuente, el tipo de letra y el tamaño */
                    label[i][j].setFont(new java.awt.Font("Berlin Sans FB Demi",
                                                          1, conf[3]));
                    
                    /* Se asigna el color de letra */
                    label[i][j].setForeground(new java.awt.Color(0, 102, 102));
                    
                    /* Se asigna el alineamiento al centro */
                    label[i][j].setHorizontalAlignment(javax.swing
                            .SwingConstants.CENTER);
                    
                    /* Se asigna el dato del campo */
                    label[i][j].setText(menu[i][j]);
                    
                    /* Se asigna el tipo de borde */
                    label[i][j].setBorder(javax.swing.BorderFactory
                            .createBevelBorder(javax.swing.border
                                    .BevelBorder.RAISED));
                    
                    /* Opacar */
                    label[i][j].setOpaque(true);
                    
                    /* Se asigna el label en el arreglo de componentes */
                    componente[i][j]=label[i][j];

                } else {

                    scrollPane[i][j] = new JScrollPane();     //Instanciación
                    
                    /* Asignar el tipo de borde */
                    scrollPane[i][j].setBorder(jScrollPane1.getBorder());

                    TextArea[i][j] = new JTextArea();     //Instanciación

                    /* Color de fondo */
                    TextArea[i][j].setBackground(jTextArea1.getBackground());
                    
                    /* numero de columnas */
                    TextArea[i][j].setColumns(jTextArea1.getColumns());
                    
                    /* Fuente, tipo de letra y tamaño */
                    TextArea[i][j].setFont(new java.awt
                            .Font("Berlin Sans FB Demi", 1, conf[3]));
                    
                    /* Color de letra */
                    TextArea[i][j].setForeground(jTextArea1.getForeground());
                    
                    /* Autoajustar texto */
                    TextArea[i][j].setLineWrap(jTextArea1.getLineWrap());
                    
                    /* numero de filas */
                    TextArea[i][j].setRows(jTextArea1.getRows());
                    
                    /* Opacar */
                    TextArea[i][j].setOpaque(true);
                    
                    /* Se asigna el dato del campo */
                    TextArea[i][j].setText(menu[i][j]);
                    
                    /* Estilo de envolvimiento de texto */
                    TextArea[i][j].setWrapStyleWord(jTextArea1
                            .getWrapStyleWord());
                    
                     /* No seleccionable */
                    TextArea[i][j].setFocusable(false);

                    /* Dar tama{o minimo */
                    scrollPane[i][j].setMinimumSize(new Dimension(100, 100));
                    
                    /* Asisgnar el textArea al scrollPane */
                    scrollPane[i][j].setViewportView(TextArea[i][j]);
                    
                    /* Se asigna el scrollPane al arreglo de componentes */
                    componente[i][j] = scrollPane[i][j];

                }

            }

        }

        /* 
         * Se crea un arreglo para contener los botones de opción de cada 
         * registro 
         */
        JButton[][] boton = new JButton[menu.length][nombreBoton.length];
        
        /* 
         * Se crea un arreglo para contener los titulos de los campos de los
         * registros
         */
        JButton[] columna = new JButton [menu[0].length + nombreBoton.length];

        /* 
         * Se efectuan dos ciclos repetitivos para recorrer el arreglo 
         * de botones
         */
        for (int i = 0; i <= menu.length - 1; i++) {

            for (int j = 0; j <= nombreBoton.length - 1; j++) {

                boton[i][j] = new JButton();    //Instanciación
                
                /* Fuente, tipo y tamaño */
                boton[i][j].setFont(jButton6.getFont());
                
                /* Color de fondo */
                boton[i][j].setBackground(jButton6.getBackground());
                
                /* Color de letra */
                boton[i][j].setForeground(jButton6.getForeground());
                
                /* Asignar icono */
                DarIcono.darIcono(boton[i][j],nombreIcono[j]);
                
                /* Mostrar u ocultar */
                boton[i][j].setVisible(mostrarBotones);
                
                /* Mesaje de información */
                boton[i][j].setToolTipText(nombreBoton[j]);

            }

        }

        /* Se crea un ciclo repetitivo para recorrer los botones de titulo */
        for (int i = 0; i <= columna.length - 1; i++) {

            columna[i] = new JButton();     //Instanciación
            
            /* Color de fondo */
            columna[i].setBackground(new java.awt.Color(0, 102, 102));
            
            /* Fuente, tipo y tamaño */
            columna[i].setFont(new java.awt.Font("Berlin Sans FB Demi", 1, conf[2]));
            
            /* Color de letra */
            columna[i].setForeground(new java.awt.Color(204, 255, 255));
            
            /* No opacar */
            columna[i].setOpaque(false); 
            
            /* Asinar nombre de la columna */
            columna[i].setText(nombreColumna[i]);
            
            /* Mostrar u ocultar */
            columna[i].setVisible(mostrarBotones);

            /* Varificar si el boton esta vacío */
            if ("".equals(nombreColumna[i])) {
                
                /* Dar color de fondo */
                columna[i].setBackground(new java.awt.Color(190, 255, 255));
                
                /* No seleccionable */
                columna[i].setFocusable(false);
                
            } else {
                
                /* 
                 * Se crea una variable para ubicar los botones de titulo 
                 * seleccionables 
                 */
                final int i2 = i - boton[0].length;
                
                /* 
                 * Se crea un arreglo temporal para contener los 
                 * identificadores 
                 */
                final String[] ID1 = this.ID;

                /* 
                 * Se asigna el evento para organizar los registros por ese 
                 * campo 
                 */
                columna[i].addActionListener(new java.awt.event
                        .ActionListener() {
                            
                    @Override
                    public void actionPerformed(java.awt.event
                            .ActionEvent evt) {

                        /* Se gusrda el dato del ultimo campo seleccionado */
                        consulta.columnaSeleccionadaAnterior = consulta
                                .columnaSeleccionada;
                        
                        /* Se asigna la columna que se seleccionó */
                        consulta.columnaSeleccionada(consulta.campos[i2 + 1]);
                        
                        /* Se asigna el numero de la columna seleccionada */
                        consulta.nColumnaSeleccionada = i2;

                        /* Gusrdar la posición actual del panel */
                        consulta.posicion = jPanel8.getLocation();

                        /* 
                         * Se verifica si se volvió a seleccionar la misma 
                         * fila
                         */
                        if (consulta.columnaSeleccionadaAnterior
                                .equals(consulta.columnaSeleccionada)) {

                            /* Si la orientación es acendente */
                            if ("ASC".equals(consulta.orientacion)) {
                                
                                /* Dar orientacion descendente */
                                consulta.orientacion("DESC");
                                
                            } else {
                                
                                /* Dar orientacion ascendente */
                                consulta.orientacion("ASC");
                            
                            }

                        } else {
                            
                            /* Se elimina el filtro de la consulta */
                            consulta.busqueda = "";
                        
                        }

                        /* Realizar la busqueda en la base de datos */
                        String menu[][] = consulta.ejecutarConsulta();

                        /* Volver a crear la tabla en el panel */
                        PantallaUsuario p = new PantallaUsuario(tipo, menu, 
                                nombreBoton, nombreIcono, nombreColumna, 
                                vinculo, retorno, nombrePantalla, usuario, 
                                declaracion, ID1, consulta);
                        
                        consulta.panelContenedor.removeAll(); // Remover panel
                        consulta.panelContenedor.add(p);    //Añadir nuevo panel
                        consulta.panelContenedor.validate();    //Actualizar
                        
                        /* Asignar la posición guardada anteriormente */
                        p.jPanel8.setLocation(consulta.posicion);
                        
                        /* Resaltar la columna seleccionada */
                        EventosMenu.darColorColumna(p.componente, 
                                consulta.nColumnaSeleccionada);

                    }
                    
                });
                
            }
            
        }

        /* Evento del botón buscar */
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                /* Se realiza la busqueda en la base de datos empleando el 
                 * filtro de información 
                 */
                buscar(nombreBoton,nombreIcono,nombreColumna,vinculo);

            }
        });

        /* 
         * Evento del boton pagina anterior que se encarga de configurar 
         * la consulta para cargar la información anterior a la actual 
         */
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                /* Se verifica que se pueda retroceder en la consulta */
                if (consulta.registroInicial - consulta.nRegistrosPagina >= 0) {

                    /* Se le resta un valor a la pagina actual */
                    consulta.paginaActual--;
                    
                    /* 
                     * Se le restan los registros necesarios al inicial para 
                     * cargar la pagina anterior 
                     */
                    consulta.registroInicial -= consulta.nRegistrosPagina;
                    
                    /* Se realiza la busqueda con los cambios realizados */
                    buscar(nombreBoton, nombreIcono, nombreColumna, vinculo);

                }

            }
        });

        /* 
         * Evento del boton pagina posterior que se encarga de configurar 
         * la consulta para cargar la información anterior a la actual 
         */
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                /* Se le suma un valor a la pagina actual */
                consulta.paginaActual++;
                
                /* 
                 * Se le suman los registros necesarios al iniciar para 
                 * cargar la pagina posterior 
                 */
                consulta.registroInicial += consulta.nRegistrosPagina;
                
                /* Se realiza la busqueda con los cambios realizados */
                String[][] menu = buscar(nombreBoton, nombreIcono, 
                        nombreColumna, vinculo);

                /* Se verifica si no hay registros a mostrar */
                if (menu.length == 0) {

                    /* Se regrsa a la pagina anterior */
                    consulta.paginaActual--;
                    consulta.registroInicial-=consulta.nRegistrosPagina;
                    buscar(nombreBoton,nombreIcono,nombreColumna,vinculo);

                }

            }
        });

        /* Se ubican los elementos en el panel en forma de tabla */
        new UbicarLista(jPanel8, componente, boton, columna);
        
        /* Se asigna una longitud de 2 para contener el panel de la tabla y 
         * el panel de busqueda para su posterior animación
         */
        objeto = new Component[2];
        
        /* Se asigna el panel de la tabla */
        objeto[0] = jPanel8;
        
        /* Se asigna el panl de busqueda */
        objeto[1] = jPanel5;

        /* Se asignan los eventos del cambio de pantalla */
        new EventosMenu(objeto, velocidad, boton, this.ID, ID, this, vinculo, 
                nombrePantalla, tipo, usuario, declaracion);

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
        jPanel5 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(240, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(611, 106));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setOpaque(false);
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
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

        jPanel5.setOpaque(false);

        jTextField3.setBackground(new java.awt.Color(204, 255, 255));
        jTextField3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 153, 153));
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

        jButton7.setBackground(new java.awt.Color(0, 102, 102));
        jButton7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(204, 255, 255));
        jButton7.setAutoscrolls(true);
        jButton7.setOpaque(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 102, 102));
        jButton9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(204, 255, 255));
        jButton9.setText("<");
        jButton9.setAutoscrolls(true);
        jButton9.setOpaque(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 102, 102));
        jButton10.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(204, 255, 255));
        jButton10.setText(">");
        jButton10.setAutoscrolls(true);
        jButton10.setOpaque(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTextField4.setBackground(new java.awt.Color(204, 255, 255));
        jTextField4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(0, 153, 153));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("10");
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

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setText("Div. pág.");

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("1-3-10");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9)
                                .addComponent(jButton10)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(557, 70));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButton3.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 102));
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
        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("Salir");
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
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
        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("?");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jPanel4.add(jButton2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 836, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(438, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setOpaque(false);

        jPanel8.setOpaque(false);

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 255, 255));
        jButton5.setText("Texto");
        jButton5.setAutoscrolls(true);
        jButton5.setOpaque(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(204, 255, 255));
        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Realizar el informe de requerimientos");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 255, 255));
        jButton6.setText("Texto");
        jButton6.setAutoscrolls(true);
        jButton6.setOpaque(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(153, 255, 51));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextArea1.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 102, 102));
        jTextArea1.setLineWrap(true);
        jTextArea1.setTabSize(0);
        jTextArea1.setText("Realizar hjhkjhkjhkjhkjhkjhkjhkjhkjhkjhkj el informe de requerimientos teniendo en cuenta cada uno de  los asepectos descritos en las normas IEEE");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));
        jPanel9.setLayout(new java.awt.CardLayout());

        jRadioButton1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 204, 204));
        jRadioButton1.setText("Opcion");
        jRadioButton1.setToolTipText("");
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jRadioButton1, "card2");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6)
                            .addComponent(jButton5))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel15))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
       /* 
         * Se animan los objetos para que salgan del panel y se realiza 
         * el cambio de pantalla
         */
        new AnimacionObjetos().RIzquierda(objeto, velocidad, this, 
                                     "EditarMisDatos", nombrePantalla, tipo, 
                                     usuario, ID, declaracion);
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
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
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        /* Se abre la ventana de configuración de la aplicación */
        Configuracion c = new Configuracion();  //Instanciación
        c.setSize(800, 600);    //Tamaño de ventana
        c.setLocationRelativeTo(null);      //Ubicar al centro
        c.setVisible(true);     //Dar visivilidad
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  
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
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        
        /**
         * Se abre el Frame corespondiente para gestionar la foto del 
         * usuario actual
         */
        Foto foto = new Foto(jLabel3, declaracion, usuario, tipo);
        foto.setLocationRelativeTo(null);   //se ubica al centro
        foto.setVisible(true);      //se le da visivilidad
        
    }//GEN-LAST:event_jPanel6MousePressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        
        /* Dar una longitud maxima de caracteres de 2147483647 */
        Validaciones.longitud(evt, jTextField3.getText().length(), 
                                2147483647);
        
        /* Restringir el caracter 39 (comilla simple) */
        Validaciones.restringirCaracter(evt, evt.getKeyChar(), (char) 39);
        
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        

        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
