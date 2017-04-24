package com.sbu.data;

import com.sbu.data.entitys.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> { }
