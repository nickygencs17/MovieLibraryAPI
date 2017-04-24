package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by nicholasgenco on 4/3/17.
 */
@Entity
@Table(name = "Customer")
public class Customer {


    @NotBlank(message = "email may not be left blank")
    public String email;

    @NotBlank
    private String creditcardnumber;

    @Id
    private String id;

    @NotBlank(message = "rating may not be left blank")
    public int rating;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id")
    Person customer;



    public Customer(){

    }

    public Customer(String id,String email, int rating,String creditcardnumber) {
        this.id = id;
        this.rating=rating;
        this.email = email;
        this.creditcardnumber = creditcardnumber;
    }

    public int getRating() {
        return rating;
    }


    public String getEmail() {
        return email;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
