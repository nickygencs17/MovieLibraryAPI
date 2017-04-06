package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

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
public class AppearedIn {


        @NotBlank(message = "actorId cannot be blank")
        int actorId;

        @NotBlank(message = "movieId cannot be blank" )
        String movieID;

        public AppearedIn(){

        }

        public AppearedIn(int actorId, String movieID){
            this.actorId =actorId;
            this.movieID=movieID;
        }

        public int getActorId() {
            return actorId;
        }

        public void setActorId(int accountId) {
            this.actorId = accountId;
        }

        public String getMovieID() {
            return movieID;
        }

        public void setMovieID(String movieID) {
            this.movieID = movieID;
        }


}
