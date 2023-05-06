/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.conexion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author godoy
 */
public class DeleteProductJT {
    
    public static boolean delete(int id) throws ClassNotFoundException {
    try (Connection connection = DB1config.getconnection1()) {
        // Verifica las conexiones
        if (connection != null) {
            System.out.println("Conectado con éxito...");
        } else {
            System.out.println("Error al conectar...");
        }

        System.out.println("Deleting Data...");

        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM dbo.PRODUCTOS WHERE id_producto = ?;");
        deleteStatement.setInt(1, id);
        
        int rowsDeleted = deleteStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Producto Eliminado correctamente");
            return true;
        } else {
            System.out.println(" Error al eliminar el producto");
            return false;
        }
    } catch (SQLException e) {
        System.out.println("No existe el producto con el id : " + id + "   " + e.getMessage());
        return false;
    }
      
    }
}
