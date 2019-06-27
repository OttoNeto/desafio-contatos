/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.main;

import br.com.desafio.connection.DB_Connection;

/**
 *
 * @author otto_
 */
public class main {
     public static void main(String[] args) {
        
        try {
            DB_Connection db = new DB_Connection();
            System.out.println(db.get_connection());
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }
}
