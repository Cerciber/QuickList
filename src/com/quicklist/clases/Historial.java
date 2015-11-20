/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quicklist.clases;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cesaraugusto
 */
public class Historial {
    
    public static void Insertar(Statement declaracion, String usuario, String accion) {

        try {
            
            declaracion.executeQuery("insert into T_Log values("+usuario+",getdate(),'"+accion+"');");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
}
