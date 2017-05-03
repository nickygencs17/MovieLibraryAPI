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
@Table(name = "appearedin")
public class AppearedIn {

    @Id
    int actorid;

    public AppearedIn() {
    }

    @ManyToOne
    @JoinColumn(name = "movieid")
    Movie movie;

    public AppearedIn(int actorid, Movie movie) {
        this.actorid = actorid;
        this.movie = movie;
    }

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
