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
import org.apache.commons.lang3.ArrayUtils;
import static org.glassfish.soteria.Utils.isEmpty;

/**
 *
 * @author godoy
 */
public class ProductDaoidJuti { //mostrar los productos de Jutiapa
    public List<Productjt> getAll2(int id) throws ClassNotFoundException{
        List<Productjt> idjutiapa = new ArrayList<Productjt>();
      
        ResultSet resultSet = null;
      
       try (Connection connection= DB1config.getconnection1();) {
            //valida conexiones
              if(connection !=null){
               System.out.println("Conectado con exito...");   
              }else{
               System.out.println("Error al conectar...");   
              }
            
            java.sql.Statement statement =  connection.createStatement();
            
            String selectSql = "SELECT * from dbo.PRODUCTOS WHERE ID_PRODUCTO="+ id + ";";
            resultSet =   statement.executeQuery(selectSql);

            // selecciona los productos de JT
            while (resultSet.next()) {
                Productjt pdj = new Productjt();
                pdj.setId_producto(resultSet.getInt("id_producto"));
                pdj.setId_usuario(resultSet.getInt("id_usuario"));
                pdj.setId_ubicacion(resultSet.getInt("id_ubicacion"));
                pdj.setNombre(resultSet.getString("nombbre"));
                pdj.setPrecio(resultSet.getInt("precio"));
                byte[] imgBytes = resultSet.getBytes("img");
                Byte[] img = ArrayUtils.toObject(imgBytes);
                pdj.setImg(img);
                pdj.setStock(resultSet.getInt("stock"));
                pdj.setStock_minimo(resultSet.getInt("stock_minimo"));
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
