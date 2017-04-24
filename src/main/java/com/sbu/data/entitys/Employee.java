package com.sbu.data.entitys;

import org.hibernate.validator.constraints.NotBlank;

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
    public String id;

    @NotBlank(message = "start date cannot be blank")
    public Date startdate;

    @NotBlank(message = "hourly rate cannot be blank")
    public int hourlyrate;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id")
    Person employee;


    public Employee(){

    }

    public Employee(String id, Date startdate, int hourlyrate) {
        this.id = id;
        this.startdate=startdate;
        this.hourlyrate=hourlyrate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyrate = hourlyRate;
    }

    public void setStartDate(Date startDate) {
        this.startdate = startDate;
    }

    public Date getStartDate() {
        return startdate;
    }

    public int getHourlyRate() {
        return hourlyrate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }
}
