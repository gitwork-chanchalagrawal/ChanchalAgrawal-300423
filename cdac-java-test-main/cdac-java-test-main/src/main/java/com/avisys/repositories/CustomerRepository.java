package com.avisys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avisys.cim.entities.Customer;

@Repository   //it is a Spring-managed bean that handles database interactions.
//CustomerRepository interface extends the JpaRepository interface provided by Spring Data JPA.
//which will give access to a number of CRUD methods like findAll(), findById(), save(), etc.
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
	//@Query allows to write a custom JPQL query to search the Customer table based on the given parameters.
    @Query("SELECT c FROM Customer c WHERE (:firstName is null OR lower(c.firstName) LIKE lower(concat('%', :firstName, '%'))) " +
            "AND (:lastName is null OR lower(c.lastName) LIKE lower(concat('%', :lastName, '%'))) " +
            "AND (:mobileNumber is null OR c.mobileNumber = :mobileNumber)")
    List<Customer> search(String firstName, String lastName, String mobileNumber);
}
