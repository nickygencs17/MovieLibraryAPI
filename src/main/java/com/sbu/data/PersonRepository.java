package com.sbu.data;

import com.sbu.data.entitys.Person;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by nicholasgenco on 4/23/17.
 */


/**
 * Repository for the entity User.
 *
 * @see netgloo.models.UserBaseRepository
 */
@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> { }
