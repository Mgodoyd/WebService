/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;

import static com.microsoft.sqlserver.jdbc.StringUtils.isEmpty;
import static com.sun.xml.wss.impl.policy.PolicyUtils.isEmpty;
import com.webservice.entidades.Product1;
import com.webservice.entidades.Productgt;
import com.webservice.entidades.Productjt;
import static jakarta.faces.component.UIInput.isEmpty;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author godoy
 */
public class ProductDaoidgt {  //para obtener la lista de productos de GT
    public List<Productgt> getAll3(int id) throws ClassNotFoundException{
        List<Productgt> idguatemala = new ArrayList<Productgt>();
      
        ResultSet resultSet = null;
      
       try (Connection connection= DB2config.getconnection2();) {
               //verifica las conexiones
              if(connection !=null){
               System.out.println("Conectado con exito...");   
              }else{
               System.out.println("Error al conectar...");   
              }
            
            java.sql.Statement statement =  connection.createStatement();
            
            String selectSql = "SELECT * from dbo.PRODUCTOS WHERE ID_PRODUCTO="+ id + ";";
            resultSet =   statement.executeQuery(selectSql);

            //selecciona los productos
            while (resultSet.next()) {
                Productgt pdg = new Productgt();
         
                pdg.setId_producto(resultSet.getInt("id_producto"));
                pdg.setId_usuario(resultSet.getInt("id_usuario"));
                pdg.setId_ubicacion(resultSet.getInt("id_ubicacion"));
                pdg.setNombre(resultSet.getString("nombbre"));
                pdg.setPrecio(resultSet.getInt("precio"));
                byte[] imgBytes = resultSet.getBytes("img");
                Byte[] img = ArrayUtils.toObject(imgBytes);
                pdg.setImg(img);
                pdg.setStock(resultSet.getInt("stock"));
                pdg.setStock_minimo(resultSet.getInt("stock_minimo"));
                idguatemala.add(pdg);
        
             System.out.println("Data read from the database: " + pdg.toString());  
            }
        }
        catch (SQLException e) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, e.toString());
        }
        return idguatemala;
    } 

    /*public List<Product1> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
