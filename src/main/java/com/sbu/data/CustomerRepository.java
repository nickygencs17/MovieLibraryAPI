package com.sbu.data;

import com.sbu.data.entitys.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


/**
 * Created by nicholasgenco on 4/23/17.
 */

@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
