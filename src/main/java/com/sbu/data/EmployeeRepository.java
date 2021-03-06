package com.sbu.data;

import com.sbu.data.entitys.Employee;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


/**
 * Created by nicholasgenco on 4/23/17.
 */


@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> { }
