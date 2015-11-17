/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.funciones;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 *
 * @author cesaraugusto
 */
public class ConvertirFoto {

    public static int Entero(File fichero) throws FileNotFoundException, IOException {

        FileInputStream ficheroStream = new FileInputStream(fichero);
        byte contenido[] = new byte[(int) fichero.length()];
        ficheroStream.read(contenido);

        for (int i = 0; i <= contenido.length - 1; i++) {

            System.out.println(contenido[i]);

        }

        return 0;

    }

}
