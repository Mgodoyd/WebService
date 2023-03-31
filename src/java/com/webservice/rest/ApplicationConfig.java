/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webservice.rest;


import java.util.Set;
import jakarta.ws.rs.core.Application;
/**
 *
 * @author godoy
 */
@jakarta.ws.rs.ApplicationPath("web")
public class ApplicationConfig extends Application
{
  @Override
  public Set<Class<?>> getClasses(){
      Set<Class<?>> resources = new java.util.HashSet<>();
      addRestResourceClasses(resources);
      return resources;
  }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.webservice.rest.ProductResource.class);
    }
}
