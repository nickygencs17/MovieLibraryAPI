package com.sbu.data.entitys;

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
        public Account(Long id, Date dateopened, String type, Customer customer) {
            this.id = id;
            this.dateopened = dateopened;
            this.type = type;
            this.customer = customer;
        }

    public Account() {
    }

    @Id
            @Column(name="ID", nullable=false, unique=true)
            // Require Generator config
            private Long id;

            @Temporal(TemporalType.DATE)
            private Date dateopened;

            private String type;

            @OneToOne(fetch = FetchType.EAGER)
            @JoinColumn(name = "customer")
            Customer customer;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getDateopened() {
            return dateopened;
        }

        public void setDateopened(Date dateopened) {
            this.dateopened = dateopened;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
}
