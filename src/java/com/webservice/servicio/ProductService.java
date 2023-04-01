/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.servicio;

import com.webservice.conexion.dao.ProductDao;
import com.webservice.conexion.dao.ProductDao1;
import com.webservice.conexion.dao.ProductDaoidJuti;
import com.webservice.conexion.dao.ProductDaoidgt;
import com.webservice.entidades.Product;
import com.webservice.entidades.Product1;
import com.webservice.entidades.Productgt;
import com.webservice.entidades.Productjt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author godoy
 */
public class ProductService {
    private ProductDao productdao;
    private ProductDao1 productdao1;
    private ProductDaoidJuti idjuti;
    private ProductDaoidgt idguate;
    
    public ProductService(){
        productdao = new ProductDao();
         productdao1= new ProductDao1();
         idjuti = new ProductDaoidJuti();
         idguate= new ProductDaoidgt();
    }
    
    
    public List<Product> getAll() throws ClassNotFoundException {
       
            return productdao.getAll();
             
    }
     public List<Product1> getAll1() throws ClassNotFoundException {
       
            return productdao1.getAll();
    }
     
     
     public List<Productjt> getAll2(int id) throws ClassNotFoundException {
       
            return idjuti.getAll2(id);
    }
      public List<Productgt> getAll3(int id) throws ClassNotFoundException {
       
            return idguate.getAll3(id);
             
    }
    
    public Product get(Product product){
        return (Product) productdao.get(product.getId(),product.getName(),product.getPrecio(),product.getImg());
    }
    
    public Product get(Product1 product){
        return (Product) productdao.get(product.getId(),product.getName(),product.getPrecio(),product.getImg());
    }
    
    

}
