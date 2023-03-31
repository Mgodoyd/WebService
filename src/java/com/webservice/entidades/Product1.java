/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.entidades;

/**
 *
 * @author godoy
 */
public class Product1 {
    private int id;
    private String product;
    private int precio;
    private byte[] img;
   
    
    public Product1() {
    }

    public Product1(int id, String  product, int precio, byte[] img) {
        this.id = id;
        this.product = product;
        this.precio = precio;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getName() {
        return product;
    }

    public void setName(String  product) {
        this.product= product;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
    
     public String toString() {
        return "Products{" +
                "id=" + id +
                ", Producto='" + product + '\'' +
                ", Precio='" + precio + '\'' +
                ", Image=" + img +
                '}';
    }
    
    
    
}
