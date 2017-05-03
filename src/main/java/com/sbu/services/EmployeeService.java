package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.sbu.controller.EmployeeController;
import com.sbu.data.*;
import com.sbu.data.entitys.*;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.sbu.services.ResponseUtil.build200;
import static com.sbu.services.ResponseUtil.build201;

/**
 *

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

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MovieRepository movieRepository;

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
    public Response addOrder(@RequestBody Order order) throws FileNotFoundException, UnsupportedEncodingException {

        Long orderNumber = orderRepository.count();
        order.setId(orderNumber.intValue()+1);
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/recommendation/{customerID}", method= RequestMethod.GET)
    public Response getRecomendations(@PathVariable("customerID") String customerID){
        Set<Movie> info = employeeController.getSuggestions(customerID);
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/editCustomer/{type}", method = RequestMethod.PUT)
    public Response editCustomer(@RequestBody Customer customer, @PathVariable("type") String type) throws Exception {
        //JSONArray slideContent
        employeeController.editCustomer(customer,type);
        return build200("Edit Okay");
    }

      @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rental", method = RequestMethod.POST)
    public Response createRental(@RequestBody JsonNode node) throws FileNotFoundException, UnsupportedEncodingException {

        Account account = accountRepository.findAccountByCustomer(node.get("customerid").asText());
        Employee employee = employeeRepository.findOne(Long.parseLong(node.get("employeeID").asText()));
        Order order = orderRepository.findOne(Integer.parseInt(node.get("orderNumber").asText()));
        Movie movie = movieRepository.findOne(Integer.parseInt(node.get("movieID").asText()));


        Rental rental = new Rental(account,employee,order.getId(),movie);
        rental.setOrderid(order.getId());
        rentalRepository.save(rental);

        employeeController.printReciept(order,rental);

        return build201("Created");
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public Response createAccount(@RequestBody JsonNode node) throws ParseException {

        Long accountsnums = accountRepository.count();
        accountsnums++;
        DateFormat df = new StdDateFormat();
        Date startDate = df.parse(node.get("dateopened").asText());
        Customer customer = customerRepository.findOne(Long.parseLong(node.get("id").asText()));

        accountRepository.save(new Account(accountsnums,startDate,node.get("type").asText(),customer));


        return build201("Created");
    }



}
