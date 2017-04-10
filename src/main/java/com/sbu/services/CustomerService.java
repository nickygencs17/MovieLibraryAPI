package com.sbu.services;

import com.sbu.controller.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nicholasgenco on 4/10/17.
 * A customer's currently held movies
 A customer's queue of movies it would like to see
 A customer's account settings
 A history of all current and past orders a customer has placed
 Movies available of a particular type
 Movies available with a particular keyword or set of keywords in the movie name
 Movies available starring a particular actor or group of actors
 Best-Seller list of movies
 Personalized movie suggestion list
 */
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerService {



    @Autowired
    private CustomerController customerController;



}
