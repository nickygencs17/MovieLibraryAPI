package com.sbu.data;

import com.sbu.data.entitys.Movie;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;




@Transactional
public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
