package com.sbu.data;

/**
 * Created by nicholasgenco on 4/23/17.
 */
import com.sbu.data.entitys.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository for the entity User and its subclasses, extending the
 * CrudRepository interface provided by spring data jpa.
 * The following methods are some of the ones available from CrudRepository:
 * save, delete, deleteAll, findOne and findAll.
 *
 * All methods in this repository will be available in the UserRepository,
 * in the PersonRepository and in the CompanyRepository.
 *
 * @author netgloo
 */
@NoRepositoryBean
public interface PersonBaseRepository<T extends Person>
        extends CrudRepository<T, Long> {




} // UserBaseRepository
