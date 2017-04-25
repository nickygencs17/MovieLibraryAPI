package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.data.AccountRepository;
import com.sbu.data.MovieQRepository;
import com.sbu.data.MovieRepository;
import com.sbu.data.OrderRepository;
import com.sbu.data.entitys.Account;
import com.sbu.data.entitys.Movie;
import com.sbu.exceptions.ResourceNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nicholasgenco on 4/10/17.
 */
@Component
public class CustomerController extends StorageController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieQRepository movieQRepository;


    public JsonNode getCustomerMoviesById(String customerID) {

        JsonNode node = null;
        return node;
    }

    public Iterable<Movie> getCustomerQueueById(String customerID) {

        Account account = getCustomerAccountById(customerID);
        Set<Movie> movies = new HashSet<>();
        if(account==null){
            throw new ResourceNotFoundException("acount");
        }
        Iterable<String> movidIds =movieQRepository.findMovieQbyAccountID(account.getId().toString());

        for (String movieq: movidIds){
            movies.add(movieRepository.findOne(Integer.parseInt(movieq)));
        }
       return movies;
    }

    public Account getCustomerAccountById(String customerID) {
        return accountRepository.findAccountByCustomer(customerID);
    }

    public JsonNode getCustomerOrdersById(String customerID) {
        JsonNode node = null;
        return node;
    }

    public Iterable<Movie> getMoviesByType(String type) {
        return movieRepository.findMoviesByType(type);
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
