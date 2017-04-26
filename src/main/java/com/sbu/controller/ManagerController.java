package com.sbu.controller;

import com.sbu.data.*;
import com.sbu.data.entitys.*;
import com.sbu.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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

    @Autowired
    ActorRepository actorRepository;


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


    public int getSalesReport(int month) {

        int sum = 0;
        Iterable<Account> accounts = accountRepository.findByDatesBetween(month);
        for(Account a: accounts) {
           if (a.getType().equals("limited")) {
               sum+=10;
           } else if (a.getType().equals("unlimited-1")) {
               sum+=15;
           } else if (a.getType().equals("unlimited-2")) {
               sum+=20;
           } else if (a.getType().equals("unlimited-3")) {
               sum+=25;
           }
        }

       return sum;
    }

    public Movie getMoviesByMovieName(String movieName) {
        return movieRepository.findByMovieName(movieName);
    }

    public Employee getEmployeeWithMostTransaction() {
        Integer emploeeId = rentalRepository.getEmployeeIDWithMostTrasactions();
        return employeeRepository.findOne(Long.parseLong(emploeeId.toString()));
    }


    public Set<Customer> getCustomerWithMostTransactions() {

       Iterable<Integer> accountIDs =rentalRepository.getAccountIDsWithMostTransActions();

       Iterable<Account> accounts= getAccounts(accountIDs);


       Set<Customer> customers = new HashSet<>();

       for(Account account: accounts){
           customers.add(account.getCustomer());
       }

        return customers;

    }

    public Iterable<Movie> getMostRentedMovies() {
        Iterable<String> movieIDs= rentalRepository.getMostRentedMovies();
        Set<Movie> movies = new HashSet();

        for(String movieId: movieIDs){
            movies.add(movieRepository.findOne(Integer.valueOf(movieId)));
        }
        return  movies;
    }

    public void editEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void editMovie(Movie movie) {

        Movie oldMovie = movieRepository.findOne(Integer.parseInt(movie.getName()));
        oldMovie.setRating(movie.getRating());
        oldMovie.setName(movie.getName());
        oldMovie.setDistrfee(movie.getDistrfee());
        oldMovie.setNumcopies(movie.getNumcopies());
        oldMovie.setType(movie.getType());
        movieRepository.save(oldMovie);
    }

    public void addActor(Actor actor) {
        actorRepository.save(actor);
    }
}
