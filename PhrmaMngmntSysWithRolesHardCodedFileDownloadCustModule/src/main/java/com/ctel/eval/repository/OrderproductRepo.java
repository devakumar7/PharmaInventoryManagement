package com.ctel.eval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.eval.model.Orderproduct;

@Repository
public interface OrderproductRepo extends JpaRepository<Orderproduct, Integer> {

	@Query(value = "SELECT * FROM orderproduct i WHERE i.oid = ?1", nativeQuery = true)
	List<Orderproduct> findByOid(Integer oid);
}