package com.sbu.data.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Actor (
 Id INTEGER,
 Name CHAR(20) NOT NULL,
 Age INTEGER NOT NULL,
 M/F CHAR(1) NOT NULL,
 Rating INTEGER,
 PRIMARY KEY (Id) )
 */


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Actor")
public class Actor {


    @Id
    int actorID;

    String name;

    int age;

    char sex;

    int rating;

    public Actor(){

    }

    public Actor(int actorID, String name, int age, char sex, int rating){
        this.actorID=actorID;
        this.name= name;
        this.age=age;
        this.sex=sex;
        this.rating=rating;
    }


    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }









}
