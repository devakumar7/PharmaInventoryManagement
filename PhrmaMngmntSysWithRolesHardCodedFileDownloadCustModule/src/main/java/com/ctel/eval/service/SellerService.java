package com.ctel.eval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctel.eval.model.Seller;
import com.ctel.eval.repository.SellerRepo;

@Service
public class SellerService {
	@Autowired
	private SellerRepo slrRepo;

	public String registerValidator(Seller slr) {
		if (!slr.getSellerName().isEmpty() && !slr.getStoreName().isEmpty() && !slr.getAddress().isEmpty()
				&& slr.getContactNo() > 999999999 && slr.getContactNo() <= 9999999999L && !slr.getCity().isEmpty()
				&& !slr.getState().isEmpty() && !slr.getCountry().isEmpty() && !slr.getPanNo().isEmpty()
				&& !slr.getEmailId().isEmpty() && slr.getPassword().length() > 8) {
			slrRepo.save(slr);
			return "Saved Successfully";
		} else
			return "Check the input Values : First Name & Last Name cant be null and Price cant be 0 or less";
	}

	// userDao.save(pdt);

}
