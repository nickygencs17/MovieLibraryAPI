package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sbu.main.MySQLNotConnectedException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Nicholas Genco on 3/1/17.
 * this class will talk to client
 */
@Component
public class StorageController {


    public boolean patchCustomer(String id, String patch) throws IOException, JsonPatchException, ParseException, MySQLNotConnectedException{
        return true;
    }

    public boolean patchOrder(String id, String patch) throws IOException, JsonPatchException, ParseException, MySQLNotConnectedException{
        return true;
    }

    public boolean patchActor(String id, String patch) throws IOException, JsonPatchException, ParseException, MySQLNotConnectedException {
        return true;
    }

    public boolean patchMovie(String id, String patch) throws IOException, JsonPatchException, ParseException, MySQLNotConnectedException {
        return true;
    }

    public boolean patchEmployee(String id, String patch) throws IOException, JsonPatchException, ParseException, MySQLNotConnectedException {
        return true;
    }

    public JsonNode getActorByID(String id) throws MySQLNotConnectedException,IOException{
        JsonNode node = null;
        return node;
    }
    public JsonNode getOrderByID(String id) throws MySQLNotConnectedException,IOException {
        JsonNode node = null;
        return node;
    }
    public JsonNode getCustomerByID(String id) throws MySQLNotConnectedException,IOException{
        JsonNode node = null;
        return node;
    }
    public JsonNode getMovieByID(String id) throws MySQLNotConnectedException,IOException{
        JsonNode node = null;
        return node;
    }
    public JsonNode getEmployeeByID(String id) throws MySQLNotConnectedException,IOException{
        JsonNode node = null;
        return node;
    }

    public JsonNode getActors() throws MySQLNotConnectedException,ParseException {
        JsonNode node = null;
        return node;
    }
    public JsonNode getOrders() throws MySQLNotConnectedException,ParseException {
        JsonNode node = null;
        return node;
    }
    public JsonNode getCustomers() throws MySQLNotConnectedException,ParseException {
        JsonNode node = null;
        return node;
    }
    public JsonNode getMovies() throws MySQLNotConnectedException,ParseException {
        JsonNode node = null;
        return node;
    }
    public JsonNode getEmployees() throws MySQLNotConnectedException,ParseException {
        JsonNode node = null;
        return node;
    }

    public String createCustomer(JSONObject customerJSON) throws MySQLNotConnectedException {
        return "id";
    }
    public String createEmployee(JSONObject customerJSON) throws MySQLNotConnectedException {
        return "id";
    }
    public String createOrder(JSONObject customerJSON) throws MySQLNotConnectedException {
        return "id";
    }
    public String createMovie(JSONObject customerJSON) throws MySQLNotConnectedException {
        return "id";
    }
    public String createActor(JSONObject customerJSON) throws MySQLNotConnectedException {
        return "id";
    }
}
