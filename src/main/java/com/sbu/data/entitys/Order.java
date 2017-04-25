package com.sbu.data.entitys;


import javax.persistence.*;
import java.util.Date;


/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Order (
 Id INTEGER,
 DateTime DATETIME,
 ReturnDate DATE,
 PRIMARY KEY (Id) )

 {
 "datetime": "2017-04-24 21:59:21",
 "id": 0,
 "returndate": "2017-04-24"
 }
 */
@Entity
@Table(name = "`Order`")
public class Order {

    @Id
    int id;

    @Temporal(TemporalType.DATE)
    private Date returndate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime")
    private Date datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Date getDatetime() {
        return datetime;
    }
    public Date getReturndate() {
        return returndate;
    }


}
