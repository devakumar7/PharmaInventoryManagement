package com.ctel.eval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.eval.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	@Query(value = "SELECT * FROM product p WHERE p.prodName = ?1", nativeQuery = true)
	Product findProductName(String prodName);
}