/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author godoy
 */
public class InsertProductJT {
      public static boolean insert(String nombre, int precio, byte[] imagen, int stock_disponible, int stock_minimo_requerido) throws ClassNotFoundException {
     try(Connection connection = DB1config.getconnection1()) {
         // Verifica las conexiones
         if(connection != null) {
             System.out.println("Conectado con éxito...");
         } else {
             System.out.println("Error al conectar...");
         }

         // Obtiene el valor máximo del ID_PRODUCTO de la tabla
         String query = "SELECT MAX(id_producto) FROM dbo.productos";
         Statement stmt = connection.createStatement(); 
         ResultSet rs = stmt.executeQuery(query);
         int lastId = 0;
         if (rs.next()) {
             lastId = rs.getInt(1);
         }
         int newId = lastId + 1;

         System.out.println("Insertando datos...");
         String insertQuery = "INSERT INTO dbo.productos (id_producto,id_usuario,id_ubicacion,nombbre,precio,img,stock,stock_minimo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            pstmt.setInt(1, newId);
            pstmt.setInt(2, 2);
            pstmt.setInt(3, 1);
            pstmt.setString(4, nombre);
            pstmt.setInt(5, precio);
            pstmt.setBytes(6, imagen);
            pstmt.setInt(7, stock_disponible);
            pstmt.setInt(8, stock_minimo_requerido);
            int rowsInserted = pstmt.executeUpdate();

         if (rowsInserted > 0) {
             System.out.println("El producto ha sido insertado con éxito.");
             return true;
         } else {
             System.out.println("No se pudo insertar el producto.");
             return false;
         }
     } catch (SQLException e) {
         System.out.println("Error al insertar el producto: " + e.getMessage());
         return false;
     }
 }
}
