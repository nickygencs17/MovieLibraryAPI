package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.data.entitys.Employee;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by nicholasgenco on 4/10/17.
 */
@Component
public class ManagerController extends StorageController {
    public JSONObject createEmployee(Employee employee) {
        return new JSONObject();
    }

    public JsonNode getEmployee(String employeeID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode deleteEmployee(String employeeID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getSalesReport() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMovies() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMoviesByCustomerName(String customerName) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMoviesByMovieType(String movieType) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMoviesByMovieName(String movieName) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getEmployeeWithMostTransaction() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getEmployeeMostRentedMovie() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getCustomerWithMostTransactions() {
        JsonNode node = null;
        return node;
    }

}
