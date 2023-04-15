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
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author godoy
 */
public class ProductDao1 { //obtiene los productos de Jutiapa
                                  
    public List<Product1> getAll() throws ClassNotFoundException{
      List<Product1> products2 = new ArrayList<Product1>();
      
      ResultSet resultSet = null;
      
       try (Connection connection= DB1config.getconnection1();) {
             //valida la conexion
              if(connection !=null){
               System.out.println("Conectado con exito...");   
              }else{
               System.out.println("Error al conectar...");   
              }
            
            java.sql.Statement statement =  connection.createStatement();
            String selectSql = "SELECT * from dbo.PRODUCTOS;";
            resultSet =   statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                Product1 pd = new Product1();
                pd.setId_producto(resultSet.getInt("id_producto"));
                pd.setId_usuario(resultSet.getInt("id_usuario"));
                pd.setId_ubicacion(resultSet.getInt("id_ubicacion"));
                pd.setNombre(resultSet.getString("nombbre"));
                pd.setPrecio(resultSet.getInt("precio"));
                byte[] imgBytes = resultSet.getBytes("img");
                Byte[] img = ArrayUtils.toObject(imgBytes);
                pd.setImg(img);
                pd.setStock(resultSet.getInt("stock"));
                pd.setStock_minimo(resultSet.getInt("stock_minimo"));
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

    public List<Product1> getAll4(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
