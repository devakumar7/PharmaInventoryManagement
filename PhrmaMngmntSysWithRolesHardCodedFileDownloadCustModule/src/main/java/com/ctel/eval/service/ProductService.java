package com.ctel.eval.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctel.eval.model.Product;
import com.ctel.eval.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo prodRepo;

	public String registerValidator(Product pdt) {

		if (pdt.getProdName() != null && pdt.getPrice() > 0) {

			pdt.setMfgDate(LocalDateTime.now());

			LocalDateTime time = LocalDateTime.now().plusMonths(6);

			pdt.setExpDate(time);
			prodRepo.save(pdt);
			return "Saved Successfully";
		} else
			return "Check the input Values : Product Id/Name cant be null and Price cant be 0 or less";
	}
	// userDao.save(pdt);
	// && !prodRepo.findById(pdt.getPid()).isPresent()
}
