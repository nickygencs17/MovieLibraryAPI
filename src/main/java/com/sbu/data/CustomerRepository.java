package com.sbu.data;

import com.sbu.data.entitys.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


/**
 * Created by nicholasgenco on 4/23/17.
 */

@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {


    @Query(value = "SELECT * FROM Customer WHERE FIRSTNAME = ?1 AND LASTNAME =?2",nativeQuery = true)
    Customer getByName(String firstname, String lastName);

}
