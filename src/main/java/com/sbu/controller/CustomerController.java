package com.sbu.controller;

import com.sbu.data.*;
import com.sbu.data.entitys.Account;
import com.sbu.data.entitys.Movie;
import com.sbu.data.entitys.Order;
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

    @Autowired
    RentalRepository rentalRepository;


    public Set<Movie> getCustomerMoviesById(String customerID) {

        Account account = getCustomerAccountById(customerID);

        if(account==null){
            throw new ResourceNotFoundException("acount");
        }
        Iterable<String> movieIds = rentalRepository.findMovieIDsbyAccountID(account.getId().toString());

        return getMovies(movieIds);
    }

    public Iterable<Movie> getCustomerQueueById(String customerID) {

        Account account = getCustomerAccountById(customerID);

        if(account==null){
            throw new ResourceNotFoundException("acount");
        }
        Iterable<String> movidIds =movieQRepository.findMovieQbyAccountID(account.getId().toString());

        return getMovies(movidIds);
    }


    public Account getCustomerAccountById(String customerID) {
        return accountRepository.findAccountByCustomer(customerID);
    }

    public Set<Order> getCustomerOrdersById(String customerID) {
        Account account = getCustomerAccountById(customerID);

        if(account==null){
            throw new ResourceNotFoundException("acount");
        }
        Iterable<Integer> orderIds = rentalRepository.findOrderIDsbyAccountID(account.getId().toString());

        return getOrders(orderIds);

    }

    public Iterable<Movie> getMoviesByType(String type) {
        return movieRepository.findMoviesByType(type);
    }


    public Set<Movie> getMoviesBestSellers() {
        Iterable<String> movieIDs= rentalRepository.getMostRentedMovies();
        Set<Movie> movies = new HashSet();

        for(String movieId: movieIDs){
            movies.add(movieRepository.findOne(Integer.valueOf(movieId)));
        }
        return  movies;

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
