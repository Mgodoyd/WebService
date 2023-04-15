/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.webservice.rest;

import com.google.gson.Gson;
import com.webservice.conexion.dao.ChangeProductgt;
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
import com.webservice.conexion.dao.ChangeProductjt;
import com.webservice.entidades.Contenedor;
import com.webservice.conexion.dao.LoginAdministrador;
import com.webservice.entidades.Product1;
import com.webservice.entidades.Productgt;
import com.webservice.entidades.Productjt;
import com.webservice.entidades.UserLogin;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import com.webservice.conexion.dao.LoginOperador;
import static jakarta.ws.rs.client.Entity.json;

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
    
      //login Operador
    @Path("/operador/{correo}/{contrasena}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarOperador(@PathParam("correo")String correo, @PathParam("contrasena")String contrasena) throws ClassNotFoundException {
       LoginOperador autentication = new  LoginOperador();
       if(autentication.autenticar(correo, contrasena)){
          return Response.ok("Bienvenido Operador",MediaType.APPLICATION_JSON).build();
       }else{
           return Response.ok("Error al iniciar sesion").build();
       }
    } 
    
    
     //login Administrador
    @Path("/administrador/{correo}/{contrasena}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarAdministrador(@PathParam("correo")String correo, @PathParam("contrasena")String contrasena) throws ClassNotFoundException {
       LoginAdministrador autentication = new  LoginAdministrador();
       if(autentication.autenticar(correo, contrasena)){
          return Response.ok("Bienvenido Administrador",MediaType.APPLICATION_JSON).build();
       }else{
           return Response.ok("Error al iniciar sesion").build();
       }
    } 
    
    
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
       catch(ClassNotFoundException ex){
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
       catch(ClassNotFoundException ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
    }
    
    //borrar produtos de Jt
    @DELETE
    @Path("/jtdel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProductjt(@PathParam("id")int id) {
        String json= null;
       try{
            boolean isDeleted = new ChangeProductjt().ChangeProductById(id);
            if(isDeleted){
                 json= new Gson().toJson(isDeleted); 
                 return Response.ok("Stock Actualizado"+" " + json.toString(),MediaType.APPLICATION_JSON).build();
            }else{
                 return Response.ok("No Existe Id para actualizar el Stock" + " "+ json.toString(),MediaType.APPLICATION_JSON).build();
            }
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
      
    }
    
    //borrar productos de gt
 @DELETE
    @Path("/gtdel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProductgt(@PathParam("id")int id) {
        String json= null;
       try{
            boolean isDeleted = new ChangeProductgt().ChangeProductById(id);
            if(isDeleted){
                 json= new Gson().toJson(isDeleted); 
                 return Response.ok("Eliminado correctamente"+" " + json.toString(),MediaType.APPLICATION_JSON).build();
            }else{
                 return Response.ok("Error al Eliminar" + " "+ json.toString(),MediaType.APPLICATION_JSON).build();
            }
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
    }
}
