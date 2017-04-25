package com.sbu.data.entitys;

/**
 * Created by nicholasgenco on 4/6/17.
 * CREATE TABLE Rental (
 AccountId INTEGER,
 CustRepId EmpId,
 OrderId INTEGER,
 MovieId CHAR(20),
 PRIMARY KEY (AccountId, CustRepId, OrderId, MovieId),
 FOREIGN KEY (AccountId) REFERENCES Account (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 FOREIGN KEY (CustRepId) REFERENCES Employee (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 FOREIGN KEY (OrderId) REFERENCES Order (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE
 FOREIGN KEY (MovieId) REFERENCES Movie (Id)
 ON DELETE NO ACTION
 ON UPDATE CASCADE )
 */
public class Rental {





}
