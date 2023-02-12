package com.ctel.eval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctel.eval.model.Customer;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.repository.LoginStatusRepo;

@Service
public class LoginService {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private LoginStatusRepo lsRepo;

	public Boolean checkLoginStatus(Integer cid) {
		Customer temp = custRepo.findById(cid).get();
		if (temp != null && lsRepo.findByEmailId(temp.getEmailId()) != null) {
			if ((lsRepo.findByEmailId(temp.getEmailId()).getStatus() == 1)
					|| (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1)) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public Integer checkLoginStatusAndRole(Integer cid) {
		// 0 ==> Invalid cid
		// 1 ==> Distributor/Admin
		// 2 ==> Customer/Admin
		Customer temp = custRepo.findById(cid).get();

		// if (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()) != null)

		System.out.println(lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()));

		System.out.println(lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus());

		if (temp != null && lsRepo.findByEmailId(temp.getEmailId()) != null) {
			if ((lsRepo.findByEmailId(temp.getEmailId()).getStatus() == 1
					&& temp.getRole().equalsIgnoreCase("distributor"))
					|| (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1)) {
				System.out.println("Distributor/Admin Previliges");
				return 1;
			} else if ((lsRepo.findByEmailId(temp.getEmailId()).getStatus() == 1
					&& temp.getRole().equalsIgnoreCase("customer"))
					|| (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1)) {
				System.out.println("Customer/Admin Previliges");
				return 2;
			} else
				return 0;
		} else
			return 0;
	}
}
