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

    @Query(value = "SELECT ACCOUNTID AS `value_occurrence` FROM Rental GROUP BY ACCOUNTID ORDER BY `value_occurrence` DESC LIMIT 5",
    nativeQuery = true)
    Iterable<Integer> getAccountIDsWithMostTransActions();

    @Query(value = "SELECT CUSTREPID FROM Rental GROUP BY CUSTREPID ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    Integer getEmployeeIDWithMostTrasactions();


    @Query(value="SELECT MovieId FROM Rental GROUP BY MovieId ORDER BY COUNT(*) DESC;",nativeQuery = true)
    Iterable<String> getMostRentedMovies();
}
