/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.webservice.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import com.webservice.conexion.dao.DeleteProductGT;
import com.webservice.conexion.dao.DeleteProductJT;
import com.webservice.conexion.dao.InsertProductGT;
import com.webservice.conexion.dao.InsertProductJT;
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
import com.webservice.conexion.dao.UpdateProductGT;
import com.webservice.conexion.dao.UpdateProductJt;
import jakarta.json.Json;
import jakarta.ws.rs.OPTIONS;
import static jakarta.ws.rs.client.Entity.json;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.sql.SQLException;



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
@Provider
public class CorsFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
      throws IOException {
    responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
    responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
  }

}
      //login Operador
@Path("/operador/{correo}/{contrasena}")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response autenticarOperador(@PathParam("correo")String correo, @PathParam("contrasena")String contrasena) throws ClassNotFoundException {
   LoginOperador autentication = new LoginOperador();
   if(autentication.autenticar(correo, contrasena)){
       jakarta.json.JsonObject json = Json.createObjectBuilder()
                        .add("mensaje", "Bienvenido Operador")
                        .add("id_usuario", 1)
                        .build();
      return Response.ok(json).build();
   }else{
       jakarta.json.JsonObject json = Json.createObjectBuilder()
                        .add("mensaje", "Error al iniciar sesión")
                        .build();
      return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
   }
}
    
     //login Administrador
   @Path("/administrador/{correo}/{contrasena}")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response autenticarAdministrador(@PathParam("correo") String correo, @PathParam("contrasena") String contrasena) throws ClassNotFoundException {
   LoginAdministrador autentication = new LoginAdministrador();
    if (autentication.autenticar(correo, contrasena)) {
        //int Id_usuario = autentication.getId_usuario(correo);
        jakarta.json.JsonObject json = Json.createObjectBuilder()
                .add("mensaje", "Bienvenido Administrador")
                .add("id_usuario", 2)
                .build();
        return Response.ok(json).build();
   } else {
       jakarta.json.JsonObject json = Json.createObjectBuilder()
                    .add("mensaje", "Error al iniciar sesión")
                    .build();
       return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
   }
}

    
@Path("/login/{role}/{correo}/{contrasena}")
@POST
@Produces(MediaType.APPLICATION_JSON)
public Response autenticar(@PathParam("role") String role, @PathParam("correo") String correo, @PathParam("contrasena") String contrasena) throws ClassNotFoundException {
   if (role.equals("operador")) {
       LoginOperador autentication = new LoginOperador();
       if (autentication.autenticar(correo, contrasena)) {
           return Response.ok("Bienvenido Operador", MediaType.APPLICATION_JSON).build();
       } else {
           return Response.ok("Error al iniciar sesion", MediaType.APPLICATION_JSON).build();
       }
   } else if (role.equals("administrador")) {
       LoginAdministrador autentication = new LoginAdministrador();
       if (autentication.autenticar(correo, contrasena)) {
           return Response.ok("Bienvenido Administrador", MediaType.APPLICATION_JSON).build();
       } else {
           return Response.ok("Error al iniciar sesion", MediaType.APPLICATION_JSON).build();
       }
   } else {
       return Response.status(Status.BAD_REQUEST).entity("Rol no válido").build();
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
    
    //cambio de stock de Jt
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
    
    //cambio de stock de gt
 @DELETE
    @Path("/gtdel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProductgt(@PathParam("id")int id) {
        //String json= null;
       try{
            boolean isDeleted = new ChangeProductgt().ChangeProductById(id);
            if(isDeleted){
                // json= new Gson().toJson(isDeleted); 
                 return Response.ok("Stock Actualizado",MediaType.APPLICATION_JSON).build();
            }else{
                 return Response.ok("No Existe Id para actualizar el Stock",MediaType.APPLICATION_JSON).build();
            }
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
    }
    
    //inserto datos en GT
    @POST
    @Path("/insert/gt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response InsertGT(@FormDataParam("NOMBRE") String nombre_, 
                             @FormDataParam("PRECIO") int precio_, 
                             @FormDataParam("IMG") byte[] imagen_, 
                             @FormDataParam("STOCK") int stock_disponible_, 
                             @FormDataParam("STOCK_MINIMO") int stock_minimo_requerido_) {
       // String json = null;
        try {
            InsertProductGT insertProductGT = new InsertProductGT();
            boolean isInserted = insertProductGT.insert(nombre_, precio_, imagen_, stock_disponible_, stock_minimo_requerido_);
            if (isInserted) {
                //json = new Gson().toJson(isInserted);
                return Response.ok("Producto Insertado Correctamente" , MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok("Error al insertar el producto", MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error del servidor...\n" + ex.toString()).build();
        }
    }
    
    //inserto datos en JT
    @POST
    @Path("/insert/jt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response InsertJT(@FormDataParam("NOMBRE") String nombre_, 
                             @FormDataParam("PRECIO") int precio_, 
                             @FormDataParam("IMG") byte[] imagen_, 
                             @FormDataParam("STOCK") int stock_disponible_, 
                             @FormDataParam("STOCK_MINIMO") int stock_minimo_requerido_) {
       // String json = null;
        try {
            InsertProductJT insertProductJT = new InsertProductJT();
            boolean isInserted = insertProductJT.insert(nombre_, precio_, imagen_, stock_disponible_, stock_minimo_requerido_);
            if (isInserted) {
               // json = new Gson().toJson(isInserted);
                return Response.ok("Producto Insertado Correctamente" , MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok("Error al insertar el producto", MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error del servidor...\n" + ex.toString()).build();
        }
    }
    
     //eliminando datos en GT
    @DELETE
    @Path("/gt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteGt(@PathParam("id")int id) {
    //   String json= null;
       try{
            boolean isDeleted = new DeleteProductGT().delete(id);
            if(isDeleted){
                // json= new Gson().toJson(isDeleted); 
                 return Response.ok("Producto Eliminado correctamente",MediaType.APPLICATION_JSON).build();
            }else{
                 return Response.ok("No Existe Id para eliminar el producto" + "  " + id ,MediaType.APPLICATION_JSON).build();
            }
       }
       catch(Exception ex){
           return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n"+ ex.toString()).build();
       }
    }
    
      //eliminando datos en JT
    @DELETE
    @Path("/jt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteJt(@PathParam("id")int id) {
        try {
            boolean isDeleted = new DeleteProductJT().delete(id);
            if(isDeleted) {
                return Response.ok("Producto Eliminado correctamente", MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok("No Existe el id para eliminar el producto" + "  " + id, MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error al obtener data...\n" + ex.toString()).build();
        }
    }
    
     //actualizo datos en JT
    @PUT
    @Path("/update/gt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response UpdatGT( @PathParam("id") int id, 
                             @FormDataParam("NOMBRE") String nombre_, 
                             @FormDataParam("PRECIO") int precio_, 
                             @FormDataParam("IMG") byte[] imagen_, 
                             @FormDataParam("STOCK") int stock_disponible_, 
                             @FormDataParam("STOCK_MINIMO") int stock_minimo_requerido_) {
       // String json = null;
        try {
            UpdateProductGT updateProductGT = new UpdateProductGT();
            boolean isInserted = updateProductGT.update(id,nombre_, precio_, imagen_, stock_disponible_, stock_minimo_requerido_);
            if (isInserted) {
               // json = new Gson().toJson(isInserted);
                return Response.ok("Producto actualizado Correctamente" , MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok("Error al actualizar el producto", MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error del servidor...\n" + ex.toString()).build();
        }
    }
    
     //actualizo datos en JT
    @PUT
    @Path("/update/jt/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response UpdatJT( @PathParam("id") int id, 
                             @FormDataParam("NOMBRE") String nombre_, 
                             @FormDataParam("PRECIO") int precio_, 
                             @FormDataParam("IMG") byte[] imagen_, 
                             @FormDataParam("STOCK") int stock_disponible_, 
                             @FormDataParam("STOCK_MINIMO") int stock_minimo_requerido_) {
       // String json = null;
        try {
            UpdateProductJt updateProductJT = new UpdateProductJt();
            boolean isInserted = updateProductJT.update(id,nombre_, precio_, imagen_, stock_disponible_, stock_minimo_requerido_);
            if (isInserted) {
               // json = new Gson().toJson(isInserted);
                return Response.ok("Producto actualizado Correctamente" , MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok("Error al actualizar el producto", MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error del servidor...\n" + ex.toString()).build();
        }
    }
}



