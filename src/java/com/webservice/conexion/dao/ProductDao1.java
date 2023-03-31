/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;

import com.webservice.entidades.Product1;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.webservice.conexion.dao.DB1config;

/**
 *
 * @author godoy
 */
public class ProductDao1 {
    
    public List<Product1> getAll() throws ClassNotFoundException{
      List<Product1> products2 = new ArrayList<Product1>();
      
      ResultSet resultSet = null;
      
       try (Connection connection= DB1config.getconnection1();) {
            
              if(connection !=null){
               System.out.println("Conectado con exito...");   
           }else{
               System.out.println("Error al conectar...");   
           }
            
            java.sql.Statement statement =  connection.createStatement();
            // Create and execute a SELECT SQL statement.
           // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from dbo.PRODUCTS;";
            resultSet =   statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                Product1 pd = new Product1();
    pd.setId(resultSet.getInt("id"));
    pd.setName( resultSet.getString("product"));
    pd.setPrecio(resultSet.getInt("precio"));
    pd.setImg(resultSet.getBytes("img"));
                
                
        products2.add(pd);
      
        System.out.println("Data read from the database: " + pd.toString());  
            }
          
        }
        catch (SQLException e) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, e.toString());
        }
       
        return products2;
}

 /*public Product get(Product product){
        return (Product) productdao1.get(product.getId(),product.getName(),product.getPrecio(),product.getImg());
    }*/

}
