package com.sbu.services;

import com.sbu.controller.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
