package com.sbu.data.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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


    @Id
    int accountid;

    @OneToOne
    @JoinColumn(name = "custrepid")
    Employee  employee;

    @OneToOne
    @JoinColumn(name = "orderid")
    Order order;


    @OneToOne
    @JoinColumn(name = "movieid")
    Movie movie;


    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
