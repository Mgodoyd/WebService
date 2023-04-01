/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.webservice.rest;

import com.google.gson.Gson;
import com.webservice.entidades.Product;
import com.webservice.servicio.ProductService;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.webservice.conexion.dao.DB2config;
import com.webservice.entidades.Contenedor;
import com.webservice.entidades.Product1;
import com.webservice.entidades.Productgt;
import com.webservice.entidades.Productjt;
import jakarta.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author godoy
 */
/*@SuppressWarnings("serial")
@WebServlet("/LoginServlet")*/
@Path("/Product")
public class ProductResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PrductResource
     */
    public ProductResource() {
    }

    /**
     * Retrieves representation of an instance of com.webservice.rest.PrductResource
     * @return an instance of java.lang.String
     */
    
    //Obtiene todos los datos 
    @GET
    @Path("/All")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        String json= null;
        String json2= null;
       try{
           List<Product> products = new ProductService().getAll();
           List<Product1> products2 = new ProductService().getAll1();
          // json= new Gson().toJson(products); 
          // json2= new Gson().toJson(products2); 
       /*    String[] str = new String[2];
           str[0] = json;
           str[1] = json2;*/
          Contenedor conten = new Contenedor(products,products2);
           Gson gson= new Gson();
           String jsonString= gson.toJson(conten);
           return Response.ok(jsonString,MediaType.APPLICATION_JSON).build();
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
      
    }
    
    //Obtiene datos de Gt 
    @GET
    @Path("/gt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsgt() {
        String json= null;
       try{
           List<Product> products = new ProductService().getAll();
          json= new Gson().toJson(products); 
          return Response.ok(json,MediaType.APPLICATION_JSON).build();
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
      
    }
    
    //Obtiene datos de JT 
    @GET
    @Path("/jt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsjt() {
        String json= null;
       try{
           List<Product1> products2 = new ProductService().getAll1();
          json= new Gson().toJson(products2); 
          return Response.ok(json,MediaType.APPLICATION_JSON).build();
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
      
    }
    
    //Obtiene producto de Jutiapa
     @GET
    @Path("/jt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsjtid(@PathParam("id")int id) {
        String json= null;
       try{
             List<Productjt> idjutiapa = new ProductService().getAll2(id);
          json= new Gson().toJson(idjutiapa); 
          return Response.ok(json,MediaType.APPLICATION_JSON).build();
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
      
    }
    
    
    //Obtiene producto de Guatemala
     @GET
    @Path("/gt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsgtid(@PathParam("id")int id) {
        String json= null;
       try{
             List<Productgt> idguatemala = new ProductService().getAll3(id);
          json= new Gson().toJson(idguatemala); 
          return Response.ok(json,MediaType.APPLICATION_JSON).build();
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
      
    }
    
    
    /**
     * PUT method for updating or creating an instance of PrductResource
     * @param content representation for the resource
     */
   /* @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }*/
}
