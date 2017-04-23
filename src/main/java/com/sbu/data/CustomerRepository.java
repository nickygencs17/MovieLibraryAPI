package com.sbu.data;

import com.sbu.data.entitys.Customer;

import javax.transaction.Transactional;


@Transactional
public interface CustomerRepository extends PersonBaseRepository<Customer> { }
