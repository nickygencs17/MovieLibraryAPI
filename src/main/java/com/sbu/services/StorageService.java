package com.sbu.services;


import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

/**
 * Created by Nicholas Genco on 3/1/17.
 * this is our restendpoint Class
 */
@CrossOrigin
@RestController
@RequestMapping("/storage")
public class StorageService {




    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Response getInfo(){
        return build200(" Get Info/Hello 305");
    }
    
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public Response createState(@RequestBody String body) {

        return build200("Post Info/ Hello 305");
    }

    @RequestMapping(value = "/state/{id}", method = RequestMethod.GET)
    public Response getSateById(@PathVariable String id) {

        return build200(id);
    }


    public Response build400(String message){

        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }

    public Response build401(){
        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    public Response build404(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response build204(){
        return Response.noContent().build();
    }

   public Response build200(Object obj){
        return Response.status(Response.Status.OK).entity(obj).build();
   }

   public Response build500(String message){return Response.serverError().entity(message).build();}
    


}


