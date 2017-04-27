package com.sbu.data.entitys;

import javax.persistence.*;

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
@Entity
public class Rental {


    public Rental(Account accountid, Employee employee, int orderid, Movie movie) {
        this.accountid = accountid;
        this.orderid = orderid;
        this.employee = employee;
        this.movie = movie;
    }

    public Rental(){

    }
    @ManyToOne(targetEntity=Account.class)
    @JoinColumn(name = "accountid")
    Account accountid;

    @ManyToOne(targetEntity=Employee.class)
    @JoinColumn(name = "custrepid")
    Employee  employee;

    @Id
    int orderid;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }



    @ManyToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movieid")
    Movie movie;

    public Account getAccountid() {
        return accountid;
    }

    public void setAccountid(Account accountid) {
        this.accountid = accountid;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
