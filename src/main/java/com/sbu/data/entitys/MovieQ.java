package com.sbu.data.entitys;

import javax.persistence.*;

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
@Entity
public class MovieQ {

    @Id
    int accountid;

    @OneToOne
    @JoinColumn(name = "movieid")
    Movie movie;

    @OneToMany(cascade=CascadeType.ALL)
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

}
