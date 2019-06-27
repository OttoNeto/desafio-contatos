/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author otto_
 */
public class DB_Connection {
    
     private Connection connection = null;
     public Connection get_connection() {

        
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cadastro?useSSL=false","root","12345");
            
        }catch (Exception e) {
            System.out.println(e);

        }
        return connection;
    }
     
     public void close(){
         try{
          if(connection != null && !connection.isClosed()){
              connection.close();
          }
         }catch(Exception e){
             e.printStackTrace();
         }
     }
}
