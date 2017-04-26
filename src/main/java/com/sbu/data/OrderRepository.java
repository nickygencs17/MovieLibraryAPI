package com.sbu.data;

/**
 * Created by nicholasgenco on 4/24/17.
 */

import com.sbu.data.entitys.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(value = "SELECT ID FROM `Order` WHERE RETURNDATE IS NULL", nativeQuery = true)
    List<Integer> getIdsOfNotreturned();
}
