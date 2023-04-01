/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;

import static com.microsoft.sqlserver.jdbc.StringUtils.isEmpty;
import static com.sun.xml.wss.impl.policy.PolicyUtils.isEmpty;
import com.webservice.entidades.Product1;
import com.webservice.entidades.Productjt;
import static jakarta.faces.component.UIInput.isEmpty;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.glassfish.soteria.Utils.isEmpty;

/**
 *
 * @author godoy
 */
public class ProductDaoidJuti {
    public List<Productjt> getAll2(int id) throws ClassNotFoundException{
        List<Productjt> idjutiapa = new ArrayList<Productjt>();
      
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
            String selectSql = "SELECT * from dbo.PRODUCTS WHERE id="+ id + ";";
            resultSet =   statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                Productjt pdj = new Productjt();
    pdj.setId(resultSet.getInt("id"));
    pdj.setName( resultSet.getString("product"));
    pdj.setPrecio(resultSet.getInt("precio"));
    pdj.setImg(resultSet.getBytes("img"));
        idjutiapa.add(pdj);
        
             System.out.println("Data read from the database: " + pdj.toString());  
       
            }
            
          
        }
        catch (SQLException e) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, e.toString());
        }
       
        return idjutiapa;
    } 


    /*public List<Product1> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
