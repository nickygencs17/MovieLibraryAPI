package com.sbu.data.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Location (
 ZipCode INTEGER,
 City CHAR(20) NOT NULL,
 State CHAR(20) NOT NULL,
 PRIMARY KEY (ZipCode) )
 */
@Entity
@Table(name = "Location")
public class Location {

    @Id
    int zipcode;

    String city;

    String state;


    public Location(){

    }

    public Location(int zipcode, String city, String state){
        this.city=city;
        this.zipcode=zipcode;
        this.state=state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getzipcode() {
        return zipcode;
    }

    public void setzipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
