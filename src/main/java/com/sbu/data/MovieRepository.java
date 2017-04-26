package com.sbu.data;

import com.sbu.data.entitys.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;




@Transactional
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    @Query(value = "SELECT * FROM Movie WHERE NAME = ?1", nativeQuery = true)
    Movie findByMovieName(String moviename);



    @Query(value = "SELECT * FROM Movie WHERE TYPE= ?1",nativeQuery = true)
    Iterable<Movie> findMoviesByType(String type);



    Movie findByNameLike(String Keyword);


    //"%Harry%Potter%"
    //
    //@Query(value = "SELECT * FROM Movie WHERE NAME IS LIKE ?1",nativeQuery = true)

    @Query(value = "SELECT * FROM Movie WHERE name REGEXP ?1",nativeQuery = true)
    Iterable<Movie> fingMoviesByCSVkeywors(String csvstringtosqlcommand);
}
