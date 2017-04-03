package com.sbu.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sbu.controller.StorageController;
import com.sbu.main.Constants;
import com.sbu.main.MySQLNotConnectedException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicholas Genco on 3/1/17.
 * this is our restendpoint Class
 */
@CrossOrigin
@RestController
@RequestMapping("/storage")
public class StorageService {

    @Autowired
    private StorageController storageController;

    //1
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Response insertCustomer(@RequestBody String body) {
        Object customerJSON = null;
        try{
            customerJSON = new JSONParser().parse(body);
        } catch (ParseException e) {
            return build400("Invalid Json");
        }
        String id;
        try{
            id = storageController.createCustomer((JSONObject) customerJSON);
        }
        catch (MySQLNotConnectedException e){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }


        Map<String, Object> response = new HashMap<>();
        response.put("id",id);
        return build200(response);
    }
    //2
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Response insertEmployee(@RequestBody String body) {
        Object employeeJSON = null;
        try{
            employeeJSON = new JSONParser().parse(body);
        } catch (ParseException e) {
            return build400("Invalid Json");
        }
        String id;
        try{
            id = storageController.createEmployee((JSONObject) employeeJSON);
        }
        catch (MySQLNotConnectedException e){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id",id);
        return build200(response);
    }

    //3
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public Response insertMovie(@RequestBody String body) {
        Object movieJSON = null;
        try{
            movieJSON = new JSONParser().parse(body);
        } catch (ParseException e) {
            return build400("Invalid Json");
        }
        String id;
        try{
            id = storageController.createMovie((JSONObject) movieJSON);
        }
        catch (MySQLNotConnectedException e){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }


        Map<String, Object> response = new HashMap<>();
        response.put("id",id);
        return build200(response);
    }

    //4
    @RequestMapping(value = "/actor", method = RequestMethod.POST)
    public Response insertActor(@RequestBody String body) {
        Object actorJSON = null;
        try{
            actorJSON = new JSONParser().parse(body);
        } catch (ParseException e) {
            return build400("Invalid Json");
        }
        String id;
        try{
            id = storageController.createActor((JSONObject) actorJSON);
        }
        catch (MySQLNotConnectedException e){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }


        Map<String, Object> response = new HashMap<>();
        response.put("id",id);
        return build200(response);
    }

    //5
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Response insertOrder(@RequestBody String body) {
        Object orderJSON = null;
        try{
            orderJSON = new JSONParser().parse(body);
        } catch (ParseException e) {
            return build400("Invalid Json");
        }
        String id;
        try{
            id = storageController.createOrder((JSONObject) orderJSON);
        }
        catch (MySQLNotConnectedException e){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }


        Map<String, Object> response = new HashMap<>();
        response.put("id",id);
        return build200(response);
    }

    //6
    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public Response getActor(){
        try {
            Object result;
            try {
                result = storageController.getActors();
            }catch (MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("actors", result);
            return build200(response);
        } catch (ParseException e) {
            return build500("Error parsing retrieved state");
        }
    }

    //7
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Response getCustomer(){
        try {
            Object result;
            try {
                result = storageController.getCustomers();
            }catch (MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("customers", result);
            return build200(response);
        } catch (ParseException e) {
            return build500("Error parsing retrieved state");
        }
    }

    //8
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public Response getOrder(){
        try {
            Object result;
            try {
                result = storageController.getOrders();
            }catch (MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("orders", result);
            return build200(response);
        } catch (ParseException e) {
            return build500("Error parsing retrieved state");
        }
    }

    //9
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Response getEmployee(){
        try {
            Object result;
            try {
                result = storageController.getEmployees();
            }catch (MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("employees", result);
            return build200(response);
        } catch (ParseException e) {
            return build500("Error parsing retrieved state");
        }
    }

    //10
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public Response getMovie(){
        try {
            Object result;
            try {
                result = storageController.getMovies();
            }catch (MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("movies", result);
            return build200(response);
        } catch (ParseException e) {
            return build500("Error parsing retrieved state");
        }
    }

    //11
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Response getOrderById(@PathVariable String id) {
        JsonNode result = null;
        try{
            try {
                result = storageController.getOrderByID(id);
            }
            catch(MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }
        } catch (IOException e) {
            return build500("Error parsing retrieved state");
        }
        if (result == null){
            return build404();
        }
        return build200(result);
    }

    //12
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Response getCustomerById(@PathVariable String id) {

        JsonNode result = null;
        try{
            try {
                result = storageController.getCustomerByID(id);
            }
            catch(MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }
        } catch (IOException e) {
            return build500("Error parsing retrieved state");
        }
        if (result == null){
            return build404();
        }
        return build200(result);
    }

    //13
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public Response getMovieById(@PathVariable String id) {

        JsonNode result = null;
        try{
            try {
                result = storageController.getMovieByID(id);
            }
            catch(MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }
        } catch (IOException e) {
            return build500("Error parsing retrieved state");
        }
        if (result == null){
            return build404();
        }
        return build200(result);
    }

    //14
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Response getEmployeeById(@PathVariable String id) {

        JsonNode result = null;
        try{
            try {
                result = storageController.getEmployeeByID(id);
            }
            catch(MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }
        } catch (IOException e) {
            return build500("Error parsing retrieved state");
        }
        if (result == null){
            return build404();
        }
        return build200(result);
    }

    //15
    @RequestMapping(value = "/actor/{id}", method = RequestMethod.GET)
    public Response getActorById(@PathVariable String id) {

        JsonNode result = null;
        try{
            try {
                result = storageController.getActorByID(id);
            }
            catch(MySQLNotConnectedException e){
                return build500(Constants.MYSQL_NOT_CONNECTED);
            }
        } catch (IOException e) {
            return build500("Error parsing retrieved state");
        }
        if (result == null){
            return build404();
        }
        return build200(result);
    }


    //21
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PATCH)
    public Response patchCustomer(@RequestBody String patch, @PathVariable String id) {
        boolean result;
        try{
            result = storageController.patchCustomer(id,patch);
        } catch (IOException e) {
            return build400("Invalid JSON Patch");
        } catch (JsonPatchException | ParseException e) {
            return build400("Could not apply provided JSON Patch");
        } catch (MySQLNotConnectedException e ){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }
        if (result){
            return build204();
        }else{
            return build404();
        }
    }
    //22
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PATCH)
    public Response patchEmployee(@RequestBody String patch, @PathVariable String id) {
        boolean result;
        try{
            result = storageController.patchEmployee(id,patch);
        } catch (IOException e) {
            return build400("Invalid JSON Patch");
        } catch (JsonPatchException | ParseException e) {
            return build400("Could not apply provided JSON Patch");
        } catch (MySQLNotConnectedException e ){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }
        if (result){
            return build204();
        }else{
            return build404();
        }
    }

    //23
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.PATCH)
    public Response patchMovie(@RequestBody String patch, @PathVariable String id) {
        boolean result;
        try{
            result = storageController.patchMovie(id,patch);
        } catch (IOException e) {
            return build400("Invalid JSON Patch");
        } catch (JsonPatchException | ParseException e) {
            return build400("Could not apply provided JSON Patch");
        } catch (MySQLNotConnectedException e ){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }
        if (result){
            return build204();
        }else{
            return build404();
        }
    }

    //24
    @RequestMapping(value = "/actor/{id}", method = RequestMethod.PATCH)
    public Response patchActor(@RequestBody String patch, @PathVariable String id){
        boolean result;
        try{
            result = storageController.patchActor(id,patch);
        } catch (IOException e) {
            return build400("Invalid JSON Patch");
        } catch (JsonPatchException | ParseException e) {
            return build400("Could not apply provided JSON Patch");
        } catch (MySQLNotConnectedException e ){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }
        if (result){
            return build204();
        }else{
            return build404();
        }
    }

    //25
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PATCH)
    public Response patchOrder(@RequestBody String patch, @PathVariable String id) {
        boolean result;
        try{
            result = storageController.patchOrder(id,patch);
        } catch (IOException e) {
            return build400("Invalid JSON Patch");
        } catch (JsonPatchException | ParseException e) {
            return build400("Could not apply provided JSON Patch");
        } catch (MySQLNotConnectedException e ){
            return build500(Constants.MYSQL_NOT_CONNECTED);
        }
        if (result){
            return build204();
        }else{
            return build404();
        }
    }



    public Response build400(String message){ return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); }

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


