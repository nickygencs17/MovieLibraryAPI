package com.sbu.data;

/**
 * Created by nicholasgenco on 4/24/17.
 */

import com.sbu.data.entitys.Order;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;



@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer> { }