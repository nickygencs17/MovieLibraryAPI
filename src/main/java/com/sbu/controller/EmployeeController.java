package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Order;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by nicholasgenco on 4/10/17.
 */
@Component
public class EmployeeController extends StorageController {


    public JSONObject createOrder(Order order) {
        return new JSONObject();
    }

    public JSONObject createCustomer(Customer customer) {
        return new JSONObject();
    }

    public JsonNode getCustomerById(String customerID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode deleteCustomerById(String customerID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMailingList(String customerID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getRecommendationList(String s) {
        JsonNode node = null;
        return node;
    }


}
