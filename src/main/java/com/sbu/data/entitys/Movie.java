package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Currency;

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
public class Movie {

    @NotBlank(message = "movieID may not be left blank")
    int movieID;

    @NotBlank(message = "movie name may not be left blank")
    String movieName;

    @NotBlank(message = "movietype may not be left blank")
    String movieType;

    @NotBlank(message = "rating may not be left blank")
    int rating;

    @NotBlank(message = "currency may not be left blank")
    Currency currency;

    @NotBlank(message = "numcopies may not be left blank")
    int numCopies;


    public Movie(){

    }

    public Movie(int movieID, String movieName, String movieType, int rating, Currency currency, int numCopies){
        this.movieID =movieID;
        this.movieName = movieName;
        this.movieType =movieType;
        this.rating = rating;
        this.currency = currency;
        this.numCopies= numCopies;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }
}
