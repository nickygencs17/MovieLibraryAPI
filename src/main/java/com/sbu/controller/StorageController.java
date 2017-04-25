package com.sbu.controller;

import com.sbu.data.MovieRepository;
import com.sbu.data.OrderRepository;
import com.sbu.data.entitys.Movie;
import com.sbu.data.entitys.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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

}
