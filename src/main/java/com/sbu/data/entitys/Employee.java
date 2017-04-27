package com.sbu.data.entitys;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nicholasgenco on 4/3/17.
 * CREATE TABLE Employee (
 ID EmpId,
 SSN INTEGER,
 StartDate DATE,
 HourlyRate INTEGER,
 PRIMARY KEY (ID),
 FOREIGN KEY (SSN) REFERENCES Person (SSN))
 ON DELETE NO ACTION
 ON UPDATE CASCADE )
 */

@Entity
@Table(name = "Employee")
public class Employee {


    @Id
    public Long id;

    public Long ssn;

    @Temporal(TemporalType.DATE)
    public Date startdate;


    public int hourlyrate;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id")
    Person employee;





    public Employee(){

    }

    public Employee(Long id,Long ssn, Date startdate, int hourlyrate) {

        this.id = id;
        this.ssn = ssn;
        this.startdate=startdate;
        this.hourlyrate=hourlyrate;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public int getHourlyrate() {
        return hourlyrate;
    }

    public void setHourlyrate(int hourlyrate) {
        this.hourlyrate = hourlyrate;
    }


}
