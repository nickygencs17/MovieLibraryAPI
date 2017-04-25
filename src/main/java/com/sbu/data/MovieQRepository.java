package com.sbu.data;

/**
 * Created by nicholasgenco on 4/25/17.
 */
import com.sbu.data.entitys.MovieQ;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;



@Transactional
public interface MovieQRepository extends CrudRepository<MovieQ, Integer> {


    @Query(value = "SELECT MOVIEID FROM MovieQ WHERE ACCOUNTID= ?1",nativeQuery = true)
    Iterable<String> findMovieQbyAccountID(String accountID);
}
