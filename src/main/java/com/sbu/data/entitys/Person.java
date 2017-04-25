package com.sbu.data.entitys;

import javax.persistence.*;

/**
 * Created by nicholasgenco on 4/3/17.
 * SSN INTEGER,
 LastName CHAR(20) NOT NULL,
 FirstName CHAR(20) NOT NULL,
 Address CHAR(20),
 ZipCode INTEGER,
 Telephone INTEGER,
 */
@Entity
@Table(name = "Person")
public class Person {

    @Id
    public Long ssn;

    public String firstname;

    public String lastname;

    public String address;


    public String telephone;


    public String password;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Zipcode")
    Location location;

    public Person() {
    }

    public Person(Long ssn, String lastname,String firstname, String password, String address, String telephone){
        this.password = password;
        this.ssn =ssn;
        this.lastname =lastname;
        this.firstname=firstname;
        this.address=address;
        this.telephone=telephone;
    }



    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getSsn() {
        return ssn;
    }


    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
