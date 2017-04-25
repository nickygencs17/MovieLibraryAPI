package com.sbu.data;

import com.sbu.data.entitys.Person;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by nicholasgenco on 4/23/17.
 */



@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {


    Person findByLastnameAndFirstname(String lastname, String firstname);
}
