package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.EmployeeController;
import com.sbu.data.EmployeeRepository;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Employee;
import com.sbu.data.entitys.Order;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/storage/employee")
public class EmployeeService extends StorageService {


    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private final InMemoryUserDetailsManager userManager;
    @Autowired
    private EmployeeRepository employeeRepository;


    private static final GrantedAuthority ROLE_EMPLOYEE = new SimpleGrantedAuthority("ROLE_EMPLOYEE");
    private static final GrantedAuthority ROLE_CUSTOMER = new SimpleGrantedAuthority("ROLE_CUSTOMER");
    private static final GrantedAuthority ROLE_MANAGER = new SimpleGrantedAuthority("ROLE_MANAGER");

    @Autowired
    public EmployeeService(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.userManager = inMemoryUserDetailsManager;
    }


    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Employee> getAllUsers() {
        // This returns a JSON or XML with the users
        return employeeRepository.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Response addOrder(@RequestBody @Valid Order order){
        JSONObject json = employeeController.createOrder(order);
        return build201(json);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/customer",method = RequestMethod.POST)
    public Response addCustomer(@RequestBody @Valid Customer customer)  {
        JSONObject json = employeeController.createCustomer(customer);

        if(userManager.userExists(customer.getEmail())){

        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(ROLE_CUSTOMER);
        //userManager.createUser(new User(customer.getEmail(), customer.getPassword(), roles));


        return build201(json);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.GET)
    public Response getCustomer(@PathVariable("customerID") String customerID){
        JsonNode info = employeeController.getCustomerById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.DELETE)
    public Response deleteCustomer(@PathVariable("customerID") String customerID)  {
        JsonNode info = employeeController.deleteCustomerById(customerID);
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/mailList", method= RequestMethod.GET)
    public Response getMailingList() throws IOException {
        JsonNode info = employeeController.getMailingList("new");
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recommendation/{customerID}", method= RequestMethod.DELETE)
    public Response getRecomendations(@PathVariable("customerID") String customerID){
        JsonNode info = employeeController.getRecommendationList(customerID);
        return build200(info);
    }




}
