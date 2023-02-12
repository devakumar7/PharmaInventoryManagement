package com.ctel.eval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctel.eval.model.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

	@Query(value = "SELECT * FROM inventory i WHERE i.prod_id = ?1", nativeQuery = true)
	List<Inventory> findProdId(Integer prodId);

	@Query(value = "SELECT * FROM inventory i WHERE i.seller_id = ?1", nativeQuery = true)
	List<Inventory> findSellerId(Integer sellerId);

	@Query(value = "SELECT * FROM inventory i WHERE i.seller_id = ?1 and i.prod_id = ?2", nativeQuery = true)
	Inventory findAllBySellerIdAndProdId(Integer sellerId, Integer prodId);
}