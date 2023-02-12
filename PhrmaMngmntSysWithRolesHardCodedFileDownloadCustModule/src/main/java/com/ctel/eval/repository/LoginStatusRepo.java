package com.ctel.eval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.eval.model.Customer;
import com.ctel.eval.model.LoginStatus;

@Repository
public interface LoginStatusRepo extends JpaRepository<LoginStatus, Integer> {

	@Query(value = "SELECT * FROM login_status i WHERE i.email_id = ?1", nativeQuery = true)
	LoginStatus findByEmailId(String emailId);

}