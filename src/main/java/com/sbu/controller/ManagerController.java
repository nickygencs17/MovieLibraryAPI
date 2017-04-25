package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.data.*;
import com.sbu.data.entitys.*;
import com.sbu.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

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

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RentalRepository rentalRepository;


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

    public Iterable<Movie> getMoviesByMovieType(String movieType) {
        return movieRepository.findMoviesByType(movieType);

    }






    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Set<Movie> getMoviesByCustomerName(String firstname, String lastName) {
        Person person = personRepository.findByLastnameAndFirstname(lastName,firstname);
        Customer customer=customerRepository.findOne(person.getSsn());
        Account account= accountRepository.findAccountByCustomer(customer.getId().toString());
        if(account==null){
            throw new ResourceNotFoundException("acount");
        }
        Iterable<String> movieIds = rentalRepository.findMovieIDsbyAccountID(account.getId().toString());

        return getMovies(movieIds);
    }


    public JsonNode getSalesReport() {
        JsonNode node = null;
        return node;
    }

    public Movie getMoviesByMovieName(String movieName) {
        return movieRepository.findByMovieName(movieName);
    }

    public JsonNode getEmployeeWithMostTransaction() {
        JsonNode node = null;
        return node;
    }


    public JsonNode getCustomerWithMostTransactions() {
        JsonNode node = null;
        return node;
    }

    public JsonNode getMostRentedMovies() {
        JsonNode node = null;
        return node;
    }
}
