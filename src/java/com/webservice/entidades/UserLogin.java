/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.entidades;

/**
 *
 * @author godoy
 */   //creación de variables de las columnas de la base de datos
public class UserLogin {
    private int id_usuario;
    private String correo;
    private String contraseña;
    private String rol;

    public UserLogin() {
    }

    public UserLogin(int id_usuario, String correo, String contraseña, String rol) {
        this.id_usuario = id_usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
