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
}
