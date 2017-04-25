package com.sbu.data.entitys;

import javax.persistence.*;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE AppearedIn (
 ActorId INTEGER,
 MovieId CHAR(20),
 PRIMARY KEY (ActorId, MovieId),
 FOREIGN KEY (ActorId) REFERENCES Actor (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 FOREIGN KEY (MovieId) REFERENCES Movie (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE )
 */
@Entity
public class AppearedIn {

    @Id
    int actorid;

    @OneToOne
    @JoinColumn(name = "movieid")
    Movie movie;


    public int getActorid() {
        return actorid;
    }

    public void setActorid(int actorid) {
        this.actorid = actorid;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
