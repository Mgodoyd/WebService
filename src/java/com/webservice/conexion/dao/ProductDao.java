/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;


import java.util.ArrayList;
import java.util.List;
import com.webservice.conexion.dao.DB2config;
import com.webservice.conexion.dao.DB1config;
import com.webservice.entidades.Product;
import static internal.com.sun.activation.registries.LogSupport.log;
import java.beans.Statement;
import static java.lang.Math.log;
import static java.lang.StrictMath.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author godoy
 */
public class ProductDao  {
    
    public List<Product> getAll() throws ClassNotFoundException{
        List<Product> products = new ArrayList<Product>();
       
       /* try{
            
            
           // Connection connection= this.connection;
          Connection connection= DB2config.getconnection2();
        
          if(connection !=null){
               System.out.println("Conectado con exito...");   
           }else{
               System.out.println("Error al conectar...");   
           }
            
          System.out.println("Read data");
    PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM dbo.PRODUCTS;"); //where id >= 1
    ResultSet resultSet = readStatement.executeQuery();
    if (!resultSet.next()) {
        System.out.println("There is no data in the database!" + resultSet.next());
        return null;
    }
   
   
     while(resultSet.next()){
    Product pd = new Product();
    pd.setId(resultSet.getInt("id"));
    pd.setName( resultSet.getString("product"));
    pd.setPrecio(resultSet.getInt("precio"));
    pd.setImg(resultSet.getBytes("img"));
    
    products.add(pd);
      
    System.out.println("Data read from the database: " + pd.toString());  
   }
//    return (List<Product>) pd;
        }
        catch(SQLException ex){
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, ex.toString());
        }*/
       
        ResultSet resultSet = null;
        
        try (Connection connection= DB2config.getconnection2();) {
            
             if(connection !=null){
               System.out.println("Conectado con exito...");   
           }else{
               System.out.println("Error al conectar...");   
           }
             
             
            
            java.sql.Statement statement =  connection.createStatement();
            
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from dbo.PRODUCTS;";
            resultSet = statement.executeQuery(selectSql);
         

            // Print results from select statement
            while (resultSet.next()) {
                Product pd = new Product();
    pd.setId(resultSet.getInt("id"));
    pd.setName( resultSet.getString("product"));
    pd.setPrecio(resultSet.getInt("precio"));
    pd.setImg(resultSet.getBytes("img"));
                
                
        products.add(pd);
      
        System.out.println("Data read from the database: " + pd.toString());  
            }
            
            
        }
        catch (SQLException e) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, e.toString());
        }
       
        return products;
         
    }

 

    public Product get(int id, String name, int precio, Byte[] img) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
