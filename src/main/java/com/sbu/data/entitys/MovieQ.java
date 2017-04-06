package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE MovieQ (
 AccountId INTEGER,
 MovieId CHAR(20),
 PRIMARY KEY (AccountId, MovieId),
 FOREIGN KEY (AccountId) REFERENCES Account (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 FOREIGN KEY (MovieId) REFERENCES Movie (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE )
 */
public class MovieQ {

    @NotBlank(message = "accountId cannot be blank")
    int accountId;

    @NotBlank(message = "movieId cannot be blank" )
    String movieID;

    public MovieQ(){

    }

    public MovieQ(int accountId, String movieID){
        this.accountId =accountId;
        this.movieID=movieID;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
}
