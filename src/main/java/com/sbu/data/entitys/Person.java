package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;

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
@Inheritance
public class Person {

    @Id
    @NotBlank(message = "ssn field may not be left blank")
    public String ssn;

    @NotBlank(message = "firstname field may not be left blank")
    public String firstname;

    @NotBlank(message = "lastname field may not be left blank")
    public String lastname;

    @NotBlank(message = "address field may not be left blank")
    public String address;

    @NotBlank(message = "zipcode may not be left blank")
    public int zipcode;

    @NotBlank(message = "telephone may not be left blank")
    public int telephone;


    @NotBlank(message = "password cannot be blank")
    public String password;

    public Person() {
    }

    public Person(String ssn, String lastname,String firstname, String password, String address, int zipcode, int telephone){
        this.password = password;
        this.ssn =ssn;
        this.lastname =lastname;
        this.firstname=firstname;
        this.address=address;
        this.zipcode=zipcode;
        this.telephone=telephone;
    }

    public void setSsn(String ssn) {
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

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
