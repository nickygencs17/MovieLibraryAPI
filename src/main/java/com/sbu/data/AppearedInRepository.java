package com.sbu.data;

/**
 * Created by nicholasgenco on 4/25/17.
 */

import com.sbu.data.entitys.AppearedIn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nicholasgenco on 4/25/17.
 */
@Transactional
public interface AppearedInRepository extends CrudRepository<AppearedIn, Long> {

    @Query(value = "SELECT MOVIEID FROM AppearedIn WHERE ACTORID = ?1", nativeQuery = true)
    List<String> getAppearedInbyActorID(String actorName);
}