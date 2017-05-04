package com.sbu.controller;

import com.sbu.data.CustomerRepository;
import com.sbu.data.LocationRepository;
import com.sbu.data.OrderRepository;
import com.sbu.data.PersonRepository;
import com.sbu.data.entitys.*;
import com.sbu.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by nicholasgenco on 4/10/17.
 */
@Component
public class EmployeeController extends StorageController {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    public LocationRepository locationRepository;

    public Order createOrder(Order order) {
        orderRepository.save(order);


        return order;
    }

    public Customer createCustomer(Customer customer) {
        personRepository.save(customer.getCustomer());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerID) {

        return customerRepository.findOne(customerID);
    }

    public void deleteCustomerById(Long customerID) {


        if(customerRepository.exists(customerID))
        {
            customerRepository.delete(customerID);
        }
        else{
            throw new ResourceNotFoundException("CUSTOMER");
        }

    }

    public Iterable<Customer> getMailingList() {
       return customerRepository.findAll();

    }


    public void editCustomer(Customer customer, String type) {

        Location location = locationRepository.findOne(customer.getCustomer().getLocation().getzipcode());
        if (location==null){
            Location newLocation = new Location(customer.getCustomer().getLocation().getzipcode(),
                    customer.getCustomer().getLocation().getCity(),
                    customer.getCustomer().getLocation().getState());
            locationRepository.save(newLocation);
        }
        Person person = personRepository.findOne(Long.parseLong(customer.getId().toString()));
        person.setLocation(locationRepository.findOne(customer.getCustomer().getLocation().getzipcode()));
        person.setAddress(customer.getCustomer().getAddress());
        person.setFirstname(customer.getCustomer().getFirstname());
        person.setLastname(customer.getCustomer().getLastname());
        person.setPassword(customer.getCustomer().getPassword());
        person.setTelephone(customer.getCustomer().getTelephone());
        personRepository.save(person);

        Customer oldCustomer = customerRepository.findOne(customer.getId());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setRating(customer.getRating());
        oldCustomer.setCreditcardnumber(customer.getCreditcardnumber());
        oldCustomer.setCustomer(person);

        customerRepository.save(oldCustomer);

        Account account =accountRepository.findAccountByCustomer(customer.getId().toString());
        if(!account.getType().equals(type)){
            account.setType(type);
            accountRepository.save(account);
        }

    }

    public void printReciept(Order order, Rental rental) throws FileNotFoundException, UnsupportedEncodingException {
        File orderDir = new File("orders");

        // if the directory does not exist, create it
        if (!orderDir.exists()) {
            System.out.println("creating directory: " + orderDir.getName());
            boolean result = false;

            try{
                orderDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                throw new RuntimeException();
            }
            if(result) {
                System.out.println("DIR created");
            }
        }

        String filename = "OrderNumber_" + order.getId();

        PrintWriter writer = new PrintWriter("orders/"+filename, "UTF-8");
        writer.println("Order Number: "+ order.getId());
        writer.println("OrderDate:" + order.getDatetime());
        writer.println("AccountNumber: "+rental.getAccountid());
        writer.println("Employee Number: "+rental.getEmployee());
        writer.println("Movie Number: "+rental.getMovie());
        writer.close();
    }
}
