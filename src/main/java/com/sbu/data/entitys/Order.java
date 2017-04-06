package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Order (
 Id INTEGER,
 DateTime DATETIME,
 ReturnDate DATE,
 PRIMARY KEY (Id) )
 */
public class Order {
    @NotBlank(message = "id cannot be blank")
    int id;

    @NotBlank(message = "dateTime cannot be blank")
    Date dateTime;

    @NotBlank(message = "return date cannot be blank")
    Date returnDate;

    public Order(){

    }


    public Order(int id, Date dateTime, Date returnDate){

        this.id= id;
        this.dateTime= dateTime;
        this.returnDate =returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
