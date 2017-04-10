package com.sbu.services;

import com.sbu.controller.ManagerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nicholasgenco on 4/10/17.Add, Edit and Delete movies
 Add, Edit and Delete information for an employee
 Obtain a sales report (i.e. the overall income from all active subscriptions) for a particular month
 Produce a comprehensive listing of all movies
 Produce a list of movie rentals by movie name, movie type or customer name
 Determine which customer representative oversaw the most transactions (rentals)
 Produce a list of most active customers
 Produce a list of most actively rented movies
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerService {


    @Autowired
    private ManagerController managerController;



}
