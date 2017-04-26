package com.sbu.controller;

import com.sbu.data.*;
import com.sbu.data.entitys.Account;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Movie;
import com.sbu.data.entitys.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nicholas Genco on 3/1/17.
 * this class will talk to client
 */
@Component
public class StorageController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RentalRepository rentalRepository;



    public Set<Movie> getMovies(Iterable<String>moiveids){
        Set<Movie> movies = new HashSet<>();
        for (String movieid: moiveids){
            movies.add(movieRepository.findOne(Integer.parseInt(movieid)));
        }
        return movies;
    }

    public Set<Order> getOrders(Iterable<Integer>orderIds){
        Set<Order> orders = new HashSet<>();
        for (int orderid: orderIds){
            orders.add(orderRepository.findOne(orderid));
        }
        return orders;
    }

    public Set<Account> getAccounts(Iterable<Integer>accountIds){
        Set<Account> accounts = new HashSet<>();
        for (Integer accountid: accountIds){
            accounts.add(accountRepository.findOne(Long.parseLong(accountid.toString())));
        }
        return accounts;
    }

    public Set<Movie> getSuggestions(String customerID) {
        Customer customer = customerRepository.findOne(Long.parseLong(customerID));
        Account account = accountRepository.findAccountByCustomer(customer.getCustomer().getSsn().toString());
        List<String> movieIds = rentalRepository.findMovieIDsbyAccountID(account.getId().toString());

        Set<String> types = new HashSet<>();

        for(String movieid:movieIds){
            Movie movie = movieRepository.findOne(Integer.parseInt(movieid));
            types.add(movie.getType());
        }

        Iterable<Movie> allmovies = movieRepository.findAll();
        Set<Movie> unseeMovies = new HashSet<>();

        for(Movie m: allmovies){
            if(!movieIds.contains(m.getID().toString())){
                if(types.contains(m.getType())){
                    unseeMovies.add(m);
                }
            }
        }
        return unseeMovies;

    }

}
