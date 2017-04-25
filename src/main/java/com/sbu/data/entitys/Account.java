package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Account (
 Id INTEGER,
 DateOpened DATE,
 Type AccountType,
 Customer CustomerId,
 PRIMARY KEY (Id),
 FOREIGN KEY (Customer) REFERENCES Customer (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE )
 */
@Entity
public class Account {

    @JoinTable
    @NotBlank(message = "customerID may not be left blank")
    int customerid;

    @NotBlank(message = "dateOpened may not be left blank")
    Date dateopened;

    @NotBlank(message = "accountType may not be left blank")
    int type;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotBlank(message = "accountID may not be left blank")
    int accountid;




    public Account(){

    }
    public Account(int accountid, Date dateopened, int type, int customerid){
        this.accountid=accountid;
        this.dateopened=dateopened;
        this.type = type;
        this.customerid= customerid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Date getDateopened() {
        return dateopened;
    }

    public void setDateopened(Date dateopened) {
        this.dateopened = dateopened;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }


}
