package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.ManagerController;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Employee;
import com.sbu.data.entitys.Movie;
import com.sbu.exceptions.BadRequestException;
import org.json.simple.parser.ParseException;
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
 * Created by nicholasgenco on 4/10/17.
 Edit movies
 Edit information for an employee

 Obtain a sales report (i.e. the overall income from all active subscriptions) for a particular month

 Produce a list of movie rentals by movie name, movie type or customer name
 Determine which customer representative oversaw the most transactions (rentals)
 Produce a list of most active customers
 Produce a list of most actively rented movies
 *
 */

@RestController
@RequestMapping("/storage/manager")
public class ManagerService extends StorageService {


    @Autowired
    private ManagerController managerController;

    @Autowired
    private final InMemoryUserDetailsManager userManager;


    private static final GrantedAuthority ROLE_EMPLOYEE = new SimpleGrantedAuthority("ROLE_EMPLOYEE");


    public ManagerService(InMemoryUserDetailsManager userManager) {
        this.userManager = userManager;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public Response addEmployee(@RequestBody @Valid Employee employee) throws IOException, ParseException, BadRequestException {


        managerController.createEmployee(employee);

       if(userManager.userExists(employee.getId().toString())){
         throw new BadRequestException("SSN already exists");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(ROLE_EMPLOYEE);
        userManager.createUser(new User(employee.getSsn().toString(), employee.getEmployee().getPassword(), roles));

        return build201("Created");
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/movie",method = RequestMethod.POST)
    public Response addMovie(@RequestBody @Valid Movie movie) throws IOException, ParseException, BadRequestException {

        managerController.createMovie(movie);

        return build201("Created");
    }






    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movie/{movieID}", method= RequestMethod.GET)
    public Response getMovie(@PathVariable("movieID") String movieID) throws IOException {
        Movie movie= managerController.getMovieById(Integer.valueOf(movieID));
        return build200(movie);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movie/{movieID}", method= RequestMethod.DELETE)
    public Response deleteMovie(@PathVariable("movieID") String movieID) throws IOException {
        managerController.deleteMovie(Integer.valueOf(movieID));
        return build200("Delete Okay");
    }




    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{employeeID}", method= RequestMethod.GET)
    public Response getEmployee(@PathVariable("employeeID") String employeeID) throws IOException {
        Employee employee= managerController.getEmployee(Long.valueOf(employeeID));
        return build200(employee);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{employeeID}", method= RequestMethod.DELETE)
    public Response deleteEmployee(@PathVariable("employeeID") String employeeID) throws IOException {
        managerController.deleteEmployee(Long.valueOf(employeeID));
        return build200("Delete Okay");
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/sales",method= RequestMethod.GET)
    public Response getSalesReport() throws IOException {
        JsonNode info = managerController.getSalesReport();
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies",method= RequestMethod.GET)
    public Response getAllMovies() throws IOException {
        Iterable<Movie> movies= managerController.getMovies();
        return build200(movies);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/customer/{customerName}",method= RequestMethod.GET)
    public Response getMoviesByCustomerName(@PathVariable("customerName") String  customerName) throws IOException {
        Iterable<Customer> info = managerController.getMoviesByCustomerName(customerName);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/type/{movieType}",method= RequestMethod.GET)
    public Response getMoviesByMovieType(@PathVariable("movieType") String movieType) throws IOException {
        JsonNode info = managerController.getMoviesByMovieType(movieType);
        return build200(info);
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/name/{movieName}",method= RequestMethod.GET)
    public Response getMoviesByMovieName(@PathVariable("movieName") String movieName) throws IOException {
        JsonNode info = managerController.getMoviesByMovieName(movieName);
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/employee",  method = RequestMethod.GET)
    public Response getEmployeeWithMostTransaction(){
        JsonNode info = managerController.getEmployeeWithMostTransaction();
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/mostRented",  method = RequestMethod.GET)
    public Response getMostRentedMovie() {
        JsonNode info = managerController.getEmployeeMostRentedMovie();
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/mostTransactions", method = RequestMethod.GET)
    public Response getCustomerWithMostTransactions () {
        JsonNode info = managerController.getCustomerWithMostTransactions();
        return build200(info);
    }

}
