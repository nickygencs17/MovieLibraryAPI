package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.CustomerController;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.sbu.services.ResponseUtil.build200;

/**
 Customers should be be easily able to browse your online movie rental system on the web and place orders to rent movies. In particular, they should be able to readily able to maintain a queue of movies they would like to see. While they will not be permitted to access the database directly, they should be able to retrieve the following information:

 A customer's currently held movies
 A customer's queue of movies it would like to see
 A customer's account settings
 A history of all current and past orders a customer has placed
 Movies available of a particular type
 Movies available with a particular keyword or set of keywords in the movie name
 Movies available starring a particular actor or group of actors
 Best-Seller list of movies
 Personalized movie suggestion list
 Customers should also be able to:

 Rate the movies they have rented
 */
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerService extends StorageService  {



    @Autowired
    private CustomerController customerController;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/{customerID}", method= RequestMethod.GET)
    public Response getCustomerMoviesByID(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = customerController.getCustomerMoviesById(customerID);
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/queue/{customerID}", method= RequestMethod.GET)
    public Response getCustomerQueueByID(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = customerController.getCustomerQueueById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/account/{customerID}", method= RequestMethod.GET)
    public Response getCustomerAccountByID(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = customerController.getCustomerAccountById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/orders/{customerID}", method= RequestMethod.GET)
    public Response getCustomerOrdersByID(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = customerController.getCustomerOrdersById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/orders/{type}", method= RequestMethod.GET)
    public Response getMoviesByType(@PathVariable("type") String type) throws IOException {
        JsonNode info = customerController.getMoviesByType(type);
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/bestSellers", method = RequestMethod.GET)
    public Response getBestSellers() throws Exception {
        //JSONArray slideContent
        JSONObject res = customerController.getMoviesBestSellers();
        return build200(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/suggestionMovies/{customerID}", method = RequestMethod.GET)
    public Response getSuggestionsById(@PathVariable("customerID") String customerID) throws Exception {
        //JSONArray slideContent
        JSONObject res = customerController.getSuggestions(customerID);
        return build200(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{movieID}", method = RequestMethod.POST)
    public Response postRating(@PathVariable("movieID") String movieID) throws Exception {
        //JSONArray slideContent
        JSONObject res = customerController.postRating(movieID);
        return build200(res);

    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/moviesByKeywords", method = RequestMethod.GET)
    public Response getMovieByKeyword(@RequestParam(value = "csvStringOfKeywords", required = false) String csvStringOfKeywords) throws Exception {
        //JSONArray slideContent
        List<String> keywordItems = Arrays.asList(csvStringOfKeywords.split("\\s*,\\s*"));
        JSONObject res = customerController.getMoviesByKeywords(keywordItems);
        return build200(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/moviesByActors", method = RequestMethod.GET)
    public Response getMovieByActor(@RequestParam(value = "csvStringOfActors", required = false) String csvStringOfActors) throws Exception {
        //JSONArray slideContent
        List<String> actors = Arrays.asList(csvStringOfActors.split("\\s*,\\s*"));
        JSONObject res = customerController.getMoviesByActors(actors);
        return build200(res);

    }



}
