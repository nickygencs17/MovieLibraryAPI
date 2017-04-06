package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

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
public class Account {

    @NotBlank(message = "customerID may not be left blank")
    int customerId;

    @NotBlank(message = "dateOpened may not be left blank")
    Date dateOpened;

    @NotBlank(message = "accountType may not be left blank")
    int accountType;

    @NotBlank(message = "accountID may not be left blank")
    int accountId;


    public Account(){

    }
    public Account(int accountId, Date dateOpened, int accountType, int customerId){
        this.accountId=accountId;
        this.dateOpened=dateOpened;
        this.accountType=accountType;
        this.customerId= customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
