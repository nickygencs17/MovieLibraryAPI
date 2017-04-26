package com.sbu.data.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by nicholasgenco on 4/5/17.
 *
 * CREATE TABLE Movie (
 Id INTEGER,
 Name CHAR(20) NOT NULL,
 Type CHAR(20) NOT NULL,
 Rating INTEGER,
 DistrFee CURRENCY,
 NumCopies INTEGER,
 PRIMARY KEY (Id) )
 */
@Entity
public class Movie {

    @Id
    Integer ID;

    @NotNull
    String name;

    String type;

    int rating;

    String distrfee;

    int numcopies;

    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDistrfee() {
        return distrfee;
    }

    public void setDistrfee(String distrfee) {
        this.distrfee = distrfee;
    }

    public int getNumcopies() {
        return numcopies;
    }

    public void setNumcopies(int numcopies) {
        this.numcopies = numcopies;
    }

    public Movie(){

    }


}
