package com.ctel.eval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.eval.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

	@Query(value = "SELECT * FROM pharmaorder i WHERE i.cid = ?1", nativeQuery = true)
	List<Order> findByCustId(Integer cid);
}