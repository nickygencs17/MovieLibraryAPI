package com.sbu.data;

/**
 * Created by nicholasgenco on 4/24/17.
 */

import com.sbu.data.entitys.Location;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;



@Transactional
public interface LocationRepository extends CrudRepository<Location, Integer> { }
