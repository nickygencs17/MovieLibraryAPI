package com.sbu.controller;

import com.sbu.data.CustomerRepository;
import com.sbu.data.OrderRepository;
import com.sbu.data.PersonRepository;
import com.sbu.data.entitys.Account;
import com.sbu.data.entitys.Customer;
import com.sbu.data.entitys.Order;
import com.sbu.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Order createOrder(Order order){
        return orderRepository.save(order);
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

        customerRepository.save(customer);
        Account account =accountRepository.findAccountByCustomer(customer.getId().toString());
        if(!account.getType().equals(type)){
            account.setType(type);
        }

    }
}
