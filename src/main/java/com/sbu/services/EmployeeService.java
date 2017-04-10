package com.sbu.services;

import com.sbu.controller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nicholasgenco on 4/10/17.
 * Record an order
 Add, Edit and Delete information for a customer
 Produce customer mailing lists
 Produce a list of movie suggestions for a given customer (using the recommender system which uses information about the customer's past orders and that of nearest neighbors)
 */
@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeService {


    @Autowired
    private EmployeeController employeeController;



}
