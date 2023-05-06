/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

/**
 *
 * @author godoy
 */
public class ChangeProductjt { //actualizacion del stock Jutiapa
     public boolean ChangeProductById(int id) throws ClassNotFoundException, SQLException {
       
          
       ResultSet resultSet= null;
        
       try (Connection connection= DB1config.getconnection1(); Connection connection2 = DB2config.getconnection2();) {
              //valida conexiones
              if(connection !=null){
               System.out.println("Conectado con exito...");   
              }else{
               System.out.println("Error al conectar...");   
              }
              
              if(connection2 !=null){
               System.out.println("Conectado con exito 2...");   
              }else{
               System.out.println("Error al conectar 2...");   
              }
            
              //obtiene el id de la base de datos de Jutiapa
            String selectSql1 = "SELECT * from dbo.PRODUCTOS WHERE ID_PRODUCTO="+ id + ";";
            java.sql.Statement statement2 =  connection.createStatement();
            resultSet = statement2.executeQuery(selectSql1);
        
        if (resultSet.next()) { //si existe el id en la base de datos de Jutiapa entonces almaceno esos valores y los aguardo en variables
            System.out.println("Id" + " " + id + " " + "encontrado en la db:" + connection);
            
                int productId = resultSet.getInt("id_producto");
                int userId = resultSet.getInt("id_usuario");
                int ubicacionId = resultSet.getInt("id_ubicacion");
                String productName = resultSet.getString("nombbre");
                int productPrice = resultSet.getInt("precio");
                byte[] productImage = resultSet.getBytes("img");
                int stock= resultSet.getInt("stock");
                int stock_minimo = resultSet.getInt("stock_minimo");
           
             // Consultar la base de datos 2 para ver si el producto con el mismo nombre existe
            PreparedStatement pstmt = connection2.prepareStatement("SELECT * FROM dbo.PRODUCTOS WHERE NOMBBRE=?");
            pstmt.setString(1, productName);
            ResultSet resultSet2 = pstmt.executeQuery();

         if (resultSet2.next()) {
            // El producto ya existe en la segunda base de datos, actualizar su stock
               pstmt = connection2.prepareStatement("UPDATE dbo.PRODUCTOS SET STOCK = STOCK + 1 WHERE NOMBBRE = ?;");
               pstmt.setString(1,productName);
               System.out.println("Stock agregado correctamente en la db2");
        } else {
           // El producto no existe en la base de datos 2, insertar un nuevo registro
                pstmt = connection2.prepareStatement("INSERT INTO dbo.PRODUCTOS(ID_PRODUCTO,ID_USUARIO,ID_UBICACION,NOMBBRE,PRECIO,IMG,STOCK,STOCK_MINIMO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

                // Obtener el último ID_PRODUCTO insertado en la base de datos 2 y agregarle 1 para obtener el siguiente ID
                Statement stmt = connection2.createStatement();
                ResultSet resultSet3 = stmt.executeQuery("SELECT TOP 1 ID_PRODUCTO FROM dbo.PRODUCTOS ORDER BY ID_PRODUCTO DESC");

                int nextId = 1;
                if (resultSet3.next()) {
                    nextId = resultSet3.getInt("ID_PRODUCTO") + 1;
                }

                pstmt.setInt(1, nextId);
                pstmt.setInt(2, userId);
                pstmt.setInt(3, 2);
                pstmt.setString(4, productName);
                pstmt.setInt(5, productPrice);
                pstmt.setBytes(6, productImage);
                pstmt.setInt(7, 1 );
                pstmt.setInt(8, stock_minimo);
                System.out.println("Producto insertado correctamente en la db2");
            }
             //inserta o actualiza los datos en la segunda base de datos
             int rowsInsert = pstmt.executeUpdate();

      if(rowsInsert > 0){
        try {
        // Crear una sentencia SQL para obtener el próximo valor de la secuencia
        String sequenceSql = "SELECT NEXT VALUE FOR seq_movimientos;";
        PreparedStatement sequenceStmt = connection.prepareStatement(sequenceSql);
        ResultSet sequenceResult = sequenceStmt.executeQuery();

        // Obtener el valor de la secuencia
        int idMovimiento = 0;
        if (sequenceResult.next()) {
            idMovimiento = sequenceResult.getInt(1);
        } else {
            throw new SQLException("No se pudo obtener el valor de la secuencia.");
        }

        // Crear una sentencia SQL para insertar un nuevo registro en la tabla de movimientos
        String insertSql = "INSERT INTO MOVIMIENTOS (ID_MOVIMIENTO, ID_PRODUCTO, ID_USUARIO, ID_UBICACION, CANTIDAD_MOVIDA, UBICACION_ALMACEN_ANTERIOR, UBICACION_ALMACEN_NUEVO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertSql);
        insertStmt.setInt(1, idMovimiento);
        insertStmt.setInt(2, productId);
        insertStmt.setInt(3, 1);
        insertStmt.setInt(4, 1);
        insertStmt.setInt(5, 1);
        insertStmt.setString(6, "JUTIAPA");
        insertStmt.setString(7, "GUATEMALA");

        // Ejecutar la sentencia SQL para insertar el nuevo registro
        int rowsAffected = insertStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Nuevo registro insertado en la tabla de movimientos con ID " + idMovimiento);

            // Actualizar el stock del producto
            String updateSql = "UPDATE PRODUCTOS SET STOCK = STOCK - 1 WHERE ID_PRODUCTO =" + productId + ";";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);

            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Stock actualizado correctamente para el producto " + productName);
            } else {
                System.out.println("No se pudo actualizar el stock del producto con ID " + productId);
            }

        } else {
            System.out.println("No se pudo insertar el nuevo registro en la tabla de movimientos.");
        }
    } catch (SQLException ex) {
        System.out.println("Error al insertar un nuevo registro en la tabla de movimientos: " + ex.getMessage());
    }
} else {
    System.out.println("No se encontraron datos para insertar en la base de datos 2.");
}
              
  }
     }
       catch (SQLException e) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, e.toString());
        }
           return true;
    }
}
