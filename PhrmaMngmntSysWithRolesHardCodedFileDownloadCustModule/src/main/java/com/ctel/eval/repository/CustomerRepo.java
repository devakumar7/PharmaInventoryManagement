package com.ctel.eval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.eval.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT * FROM customer i WHERE i.email_id = ?1", nativeQuery = true)
	Customer findByEmailId(String emailId);

	@Query(value = "SELECT * FROM customer i WHERE i.contact_no = ?1", nativeQuery = true)
	Customer findByContactNo(Long contactNo);

	@Query(value = "SELECT * FROM customer i WHERE i.role = ?1", nativeQuery = true)
	Customer findByRole(String role);

	@Query(value = "SELECT * FROM customer i WHERE i.cid = ?1", nativeQuery = true)
	Customer findByCustId(Integer cid);

}