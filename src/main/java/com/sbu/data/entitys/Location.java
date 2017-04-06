package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Location (
 ZipCode INTEGER,
 City CHAR(20) NOT NULL,
 State CHAR(20) NOT NULL,
 PRIMARY KEY (ZipCode) )
 */
public class Location {

    @NotBlank(message = "zipCode cannot be blank")
    int zipCode;

    @NotBlank(message = "city cannot be blank")
    String city;

    @NotBlank(message = "State Cannot be blank")
    String state;


    public Location(){

    }

    public Location(int zipCode, String city, String state){
        this.city=city;
        this.zipCode=zipCode;
        this.state=state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
