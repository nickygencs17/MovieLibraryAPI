package com.sbu.data;

/**
 * Created by nicholasgenco on 4/24/17.
 */

import com.sbu.data.entitys.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT * FROM Account WHERE CUSTOMER= ?1", nativeQuery = true)
    Account findAccountByCustomer(String customer);

    @Query(value = "SELECT * FROM Account WHERE MONTH(DATEOPENED) = ?1",nativeQuery = true)
    List<Account> findByDatesBetween(int month);
}
