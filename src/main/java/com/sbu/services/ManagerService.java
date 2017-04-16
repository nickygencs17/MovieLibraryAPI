package com.sbu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.ManagerController;
import com.sbu.data.entitys.Employee;
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
public class ManagerService extends StorageService {


    @Autowired
    private ManagerController managerController;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public Response addEmployee(@RequestBody @Valid Employee employee) throws IOException, ParseException {
        JSONObject json = managerController.createEmployee(employee);
        return build201(json);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.GET)
    public Response getEmployee(@PathVariable("customerID") String employeeID) throws IOException {
        JsonNode info = managerController.getEmployee(employeeID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerID}", method= RequestMethod.DELETE)
    public Response deleteEmployee(@PathVariable("customerID") String employeeID) throws IOException {
        JsonNode info = managerController.deleteEmployee(employeeID);
        return build200(info);
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
        JsonNode info = managerController.getMovies();
        return build200(info);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/customer/{customerName}",method= RequestMethod.GET)
    public Response getMoviesByCustomerName(@PathVariable("customerName") String  customerName) throws IOException {
        JsonNode info = managerController.getMoviesByCustomerName(customerName);
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
