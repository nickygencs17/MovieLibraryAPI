package com.sbu.data;

/**
 * Created by nicholasgenco on 4/25/17.
 */

import com.sbu.data.entitys.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;



@Transactional
public interface RentalRepository extends CrudRepository<Rental, Integer> {


    @Query(value = "SELECT MOVIEID FROM Rental WHERE ACCOUNTID= ?1",nativeQuery = true)
    Iterable<String> findMovieIDsbyAccountID(String accountID);

    @Query(value = "SELECT ORDERID FROM Rental WHERE ACCOUNTID= ?1",nativeQuery = true)
    Iterable<Integer> findOrderIDsbyAccountID(String s);
}
