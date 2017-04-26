package com.sbu.controller;

import com.sbu.data.*;
import com.sbu.data.entitys.Account;
import com.sbu.data.entitys.Actor;
import com.sbu.data.entitys.Movie;
import com.sbu.data.entitys.Order;
import com.sbu.exceptions.ResourceNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    AppearedInRepository appearedInRepository;


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

    public Iterable<Movie> getMoviesByKeywords(List<String> keywordItems) {
        StringBuilder sb = new StringBuilder();

        for(String keyword:keywordItems){
            sb.append(keyword);
            sb.append("|");
        }
        sb.deleteCharAt(sb.length()-1);


        return movieRepository.fingMoviesByCSVkeywors(sb.toString());
    }

    public Set<Movie> getMoviesByActors(List<String> actorsNames) {

        Set<Actor> actors = new HashSet<>();

        for(String name: actorsNames){
            actors.add(actorRepository.getByName(name));
        }



        List<String> movieIds = new ArrayList<>();
        for(Actor a: actors){
            movieIds.addAll(appearedInRepository.getAppearedInbyActorID(a.getId().toString()));
        }

        HashMap<String, Integer> elementCountMap = countOccurences(movieIds);
        Set<Movie> movies = new HashSet<>();

        for(String s: elementCountMap.keySet()) {
            if(elementCountMap.get(s)==actorsNames.size()) {
                movies.add(movieRepository.findOne(Integer.parseInt(s)));
            }

        }


        return movies;

    }
    public HashMap<String, Integer> countOccurences(List<String> movieIds) {

        HashMap<String, Integer> elementCountMap = new HashMap<String, Integer>();


        for (String i : movieIds)
        {
            if(elementCountMap.containsKey(i))
            {
                elementCountMap.put(i, elementCountMap.get(i)+1);
            }
            else
            {
                elementCountMap.put(i, 1);
            }
        }
        return elementCountMap;
    }

}


