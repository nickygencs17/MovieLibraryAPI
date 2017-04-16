package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by nicholasgenco on 4/10/17.
 */
public class CustomerController extends StorageController {


    public JsonNode getCustomerMoviesById(String customerID) {

        JsonNode node = null;
        return node;
    }

    public JsonNode getCustomerQueueById(String customerID) {

        JsonNode node = null;
        return node;
    }

    public JsonNode getCustomerAccountById(String customerID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getCustomerOrdersById(String customerID) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMoviesByType(String type) {
        JsonNode node = null;
        return node;
    }


    public JSONObject getMoviesBestSellers() {
        return new JSONObject();
    }

    public JSONObject getSuggestions(String customerID) {
        return new JSONObject();
    }

    public JSONObject postRating(String movieID) {
        return new JSONObject();
    }

    public JSONObject getMoviesByKeywords(List<String> keywordItems) {
        return new JSONObject();
    }

    public JSONObject getMoviesByActors(List<String> actors) {
        return new JSONObject();
    }
}
