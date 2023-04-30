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
    
    //findByMobileNumber is a custom method for second requirement
    List<Customer> findByMobileNumber(String mobileNumber);
    
    //for fifth requirement
    void deleteByMobileNumber(String mobileNumber);
    
    
    //sixth requirement
    @Modifying
    @Query(value = "INSERT INTO customer_mobile_number (customer_id, mobile_number) VALUES (:customerId, :mobileNumber)", nativeQuery = true)
    void addMobileNumber(@Param("customerId") Long customerId, @Param("mobileNumber") String mobileNumber);

    
    ////sixth requirement
    @Modifying
    @Query(value = "DELETE FROM customer_mobile_number WHERE customer_id = :customerId AND mobile_number = :mobileNumber", nativeQuery = true)
    void deleteMobileNumber(@Param("customerId") Long customerId, @Param("mobileNumber") String mobileNumber);
}
