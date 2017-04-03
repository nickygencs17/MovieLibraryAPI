package com.sbu.data;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nicholasgenco on 4/3/17.
 */
public class Customer extends Person {

    @NotBlank(message = "email may not be left blank")
    public String email;

    @NotBlank(message = "rating may not be left blank")
    public int rating;



    public Customer(){

    }
    public Customer(String ssn, String lastname, String firstname, String address, int zipcode, int telephone, String email, int rating) {
        super(ssn, lastname, firstname, address, zipcode, telephone);
        this.rating=rating;
        this.email = email;
    }
}
