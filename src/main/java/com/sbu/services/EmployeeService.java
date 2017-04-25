package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.EmployeeController;
import com.sbu.data.*;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Employee;
import com.sbu.data.entitys.Order;
import com.sbu.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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


 Edit information for a customer
 Produce customer mailing lists
 Produce a list of movie suggestions for a given customer

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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;



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
        employeeRepository.count();
        return employeeRepository.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Response addOrder(@RequestBody Order order){

        Long orderNumber = orderRepository.count();
        order.setId(orderNumber.intValue());
        employeeController.createOrder(order);

        return build201(order.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/customer",method = RequestMethod.POST)
    public Response addCustomer(@RequestBody @Valid Customer customer) throws BadRequestException {

        if(!locationRepository.exists(customer.getCustomer().getLocation().getzipcode())){
            locationRepository.save(customer.getCustomer().getLocation());
        }

        employeeController.createCustomer(customer);


        if(userManager.userExists(customer.getEmail())){
            throw new BadRequestException();
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(ROLE_CUSTOMER);
        userManager.createUser(new User(customer.getEmail(), customer.getCustomer().getPassword(), roles));

        return build201(customer.getCustomer().getSsn());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.GET)
    public Response getCustomer(@PathVariable("customerID") String customerID){
        Customer customer = employeeController.getCustomerById(Long.valueOf(customerID));

        return build200(customer);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.DELETE)
    public Response deleteCustomer(@PathVariable("customerID") String customerID)  {
        employeeController.deleteCustomerById(Long.valueOf(customerID));
        return build200("Delete Ok");
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/mailList", method= RequestMethod.GET)
    public Response getMailingList() throws IOException {
        Iterable<Customer> customers = employeeController.getMailingList();
        return build200(customers);
    }



    //NOT IMPLMENTED



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recommendation/{customerID}", method= RequestMethod.GET)
    public Response getRecomendations(@PathVariable("customerID") String customerID){
        JsonNode info = employeeController.getRecommendationList(customerID);
        return build200(info);
    }




}
