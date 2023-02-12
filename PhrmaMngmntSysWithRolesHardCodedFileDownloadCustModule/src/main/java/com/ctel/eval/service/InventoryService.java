package com.ctel.eval.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctel.eval.model.Inventory;
import com.ctel.eval.model.Product;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.repository.InventoryRepo;
import com.ctel.eval.repository.LoginStatusRepo;
import com.ctel.eval.repository.ProductRepo;
import com.ctel.eval.repository.SellerRepo;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepo invRepo;

	@Autowired
	private ProductRepo prodRepo;

	@Autowired
	private SellerRepo slrRepo;

	@Autowired
	private LoginStatusRepo lsRepo;

	@Autowired
	private CustomerRepo custRepo;

	public String invValidator(Inventory inv, Integer sid) {

		if (lsRepo.findByEmailId(slrRepo.findById(sid).get().getEmailId()).getStatus() == 1
				|| lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1) {

			System.out.println("Logged In as Seller or Admin");

			if (prodRepo.findById(inv.getProdId()).get().getPid() == inv.getProdId()
					&& slrRepo.findById(inv.getSellerId()).get().getSid() == inv.getSellerId()
					&& prodRepo.findById(inv.getProdId()).get().getProdName().equals(inv.getProdName())) {

				inv.setRecordedDate(LocalDateTime.now());

				invRepo.save(inv);
				return "Inventory Items Updated against your SellerId";

			} else
				return "ProductId doesnt exist/Seller Id doesnt exist";
		} else
			return "You are not logged in Please Login to Update your Inventory";
	}

	public List<Inventory> viewAllInvItems(String emailId) {
		if (lsRepo.findByEmailId(emailId).getStatus() == 1
				&& custRepo.findByEmailId(emailId).getRole().equals("admin")) {
			return invRepo.findAll();
		} else {
			return null;
		}
	}

	public List<Inventory> viewInvItem(Integer sid) {
		if (lsRepo.findByEmailId(slrRepo.findById(sid).get().getEmailId()).getStatus() == 1
				|| custRepo.findByEmailId(slrRepo.findById(sid).get().getEmailId()).getRole().equals("admin")) {
			return invRepo.findSellerId(sid);
		} else {
			return null;
		}
	}
}