package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.data.CustomerRepository;
import com.sbu.data.EmployeeRepository;
import com.sbu.data.MovieRepository;
import com.sbu.data.PersonRepository;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Employee;
import com.sbu.data.entitys.Movie;
import com.sbu.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nicholasgenco on 4/10/17.
 */
@Component
public class ManagerController extends StorageController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PersonRepository personRepository;


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MovieRepository movieRepository;


    public Employee createEmployee(Employee employee) {
        personRepository.save(employee.getEmployee());
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long employeeID) {
        return employeeRepository.findOne(employeeID);
    }

    public void deleteEmployee(Long employeeID) {

        if(employeeRepository.exists(employeeID))
        {
            employeeRepository.delete(employeeID);
        }
        else{
            throw new ResourceNotFoundException("CUSTOMER");
        }

    }


    public void deleteMovie(Integer movieID) {
        if(movieRepository.exists(movieID))
        {
            movieRepository.delete(movieID);
        }
        else{
            throw new ResourceNotFoundException("CUSTOMER");
        }
    }

    public Movie getMovieById(Integer movieID) {
       return movieRepository.findOne(movieID);
    }

    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }





    public JsonNode getSalesReport() {
        JsonNode node = null;
        return node;
    }

    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Iterable<Customer> getMoviesByCustomerName(String customerName) {
        return customerRepository.findAll();
    }
    public JsonNode getMoviesByMovieType(String movieType) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMoviesByMovieName(String movieName) {
        JsonNode node = null;
        return node;
    }

    public JsonNode getEmployeeWithMostTransaction() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getEmployeeMostRentedMovie() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getCustomerWithMostTransactions() {
        JsonNode node = null;
        return node;
    }
}
