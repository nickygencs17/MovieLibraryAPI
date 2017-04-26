package com.sbu.data;

import com.sbu.data.entitys.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by nicholasgenco on 4/25/17.
 */
@Transactional
public interface ActorRepository extends CrudRepository<Actor, Long> {

    @Query(value = "SELECT * FROM Actor WHERE NAME = ?1", nativeQuery = true)
    Actor getByName(String actorName);
}