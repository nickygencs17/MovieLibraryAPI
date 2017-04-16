package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.EmployeeController;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Order;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static com.sbu.services.ResponseUtil.build200;
import static com.sbu.services.ResponseUtil.build201;

/**
 Customer Representatives should be able to:

 Record an order
 Add, Edit and Delete information for a customer
 Produce customer mailing lists
 Produce a list of movie suggestions for a given customer
 (using the recommender system which uses information about the customer's
 past orders and that of nearest neighbors)
 */
@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeService extends StorageService {


    @Autowired
    private EmployeeController employeeController;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Response addOrder(@RequestBody @Valid Order order) throws IOException, ParseException {
        JSONObject json = employeeController.createOrder(order);
        return build201(json);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Response addCustomer(@RequestBody @Valid Customer customer) throws IOException, ParseException {
        JSONObject json = employeeController.createCustomer(customer);
        return build201(json);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.GET)
    public Response getCustomer(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = employeeController.getCustomerById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.DELETE)
    public Response deleteCustomer(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = employeeController.deleteCustomerById(customerID);
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/mailList}", method= RequestMethod.GET)
    public Response getMailingList() throws IOException {
        JsonNode info = employeeController.getMailingList("new");
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recommendation/{customerID}", method= RequestMethod.DELETE)
    public Response getRecomendations(@PathVariable("customerID") String customerID) throws IOException {
        JsonNode info = employeeController.getRecommendationList(customerID);
        return build200(info);
    }




}
