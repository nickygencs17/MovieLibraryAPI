package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Rental (
 AccountId INTEGER,
 CustRepId EmpId,
 OrderId INTEGER,
 MovieId CHAR(20),
 PRIMARY KEY (AccountId, CustRepId, OrderId, MovieId),
 FOREIGN KEY (AccountId) REFERENCES Account (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 FOREIGN KEY (CustRepId) REFERENCES Employee (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 FOREIGN KEY (OrderId) REFERENCES Order (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE
 FOREIGN KEY (MovieId) REFERENCES Movie (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE )
 */
public class Rental {

    @NotBlank(message = "accountID field may not be left blank")
    int accountID;

    @NotBlank(message = "orderID field may not be left blank")
    int orderID;

    @NotBlank(message = "customerID field may not be left blank")
    int customerID;

    @NotBlank(message = "movieID field may not be left blank")
    String movieID;


    public Rental(){

    }

    public Rental( int accountID, int orderID, int customerID, String movieID){
        this.accountID=accountID;
        this.orderID=orderID;
        this.customerID=customerID;
        this.movieID= movieID;

    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
}
