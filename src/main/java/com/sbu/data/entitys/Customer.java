package com.sbu.data.entitys;

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
    public Customer(String ssn, String lastname,String firstname, String password, String address, int zipcode, int telephone, String email, int rating) {
        super(ssn, lastname, firstname, password, address, zipcode, telephone);
        this.rating=rating;
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
