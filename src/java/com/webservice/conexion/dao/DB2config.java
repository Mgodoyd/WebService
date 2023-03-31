package com.webservice.conexion.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author godoy
 */
public class DB2config {
    
    

    
    
     public static Connection getconnection2() throws ClassNotFoundException   {
       System.out.println("performing setup...");
       
        String connectionUrl =
                "jdbc:sqlserver://proyectoso2.database.windows.net:1433;"
                        + "database=dbGuatemala;"
                        + "user=adminuser@proyectoso2;"
                        + "password=Proyecto1;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
        System.out.println("selecting data...");
       // ResultSet resulSet = null;
       Connection connection= null;
        try  {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado a la db" + connection.getCatalog());
          // String selectSql= "SELECT * FROM employee";
         //  resultSet =  statement.execute(selectSql);
           
         /*  while(resulSet.next()){
               System.out.println(resulSet.getString(2));
         
           
           }*/
           
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
           System.out.println("Error al Conectar..." +e.toString());
        }
       // return getconnection2();
         return connection;
    }
    
   /* Connection connection= null;
    
   
    
     String cadena = "dbc:sqlserver://proyectoso2.database.windows.net:1433;database=dbGuatemala;user=adminuser@proyectoso2;password=Proyecto1;encrypt=true;trustServerCertificate=false;loginTimeout=30;";
    
    
    public  Connection connection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection(cadena);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error"+ e.toString());
        }
        return connection;
    }*/
}
