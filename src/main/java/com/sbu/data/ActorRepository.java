package com.sbu.data;

import com.sbu.data.entitys.Actor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nicholasgenco on 3/1/17.
 */

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delet


public interface ActorRepository extends CrudRepository<Actor, Long> {

}



