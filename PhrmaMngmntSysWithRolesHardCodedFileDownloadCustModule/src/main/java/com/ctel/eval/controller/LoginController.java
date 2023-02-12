
package com.ctel.eval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.eval.model.Customer;
import com.ctel.eval.model.LoginRequest;
import com.ctel.eval.model.LoginStatus;
import com.ctel.eval.model.Seller;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.repository.LoginStatusRepo;
import com.ctel.eval.repository.SellerRepo;

@RestController
public class LoginController {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private SellerRepo slrRepo;

	@Autowired
	private LoginStatusRepo lsRepo;

	@RequestMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest req) {

		Customer custObjForLoggedInPerson = custRepo.findByEmailId(req.getEmailId());

		Seller slrObjForloggedInPerson = slrRepo.findByEmailId(req.getEmailId());

		LoginStatus ls = lsRepo.findByEmailId(req.getEmailId());

		String msg;

		if (custObjForLoggedInPerson != null && slrObjForloggedInPerson != null
				&& req.getPassword().equals(custObjForLoggedInPerson.getPassword())
				&& req.getPassword().equals(slrObjForloggedInPerson.getPassword())) {
			if (ls != null) {
				ls.setStatus(1);
				lsRepo.save(ls);
			} else {
				LoginStatus lsn = new LoginStatus();
				lsn.setEmailId(req.getEmailId());
				lsn.setStatus(1);
				lsRepo.save(lsn);
			}
			msg = "Logged In Successfully & You have both Customer and Seller Previliges";

		} else if (custObjForLoggedInPerson != null && req.getPassword().equals(custObjForLoggedInPerson.getPassword())
				&& slrObjForloggedInPerson == null) {
			if (ls != null) {
				ls.setStatus(1);
				lsRepo.save(ls);
			} else {
				LoginStatus lsn = new LoginStatus();
				lsn.setEmailId(req.getEmailId());
				lsn.setStatus(1);
				lsRepo.save(lsn);
			}
			msg = "Logged in as Customer";
		} else if (slrObjForloggedInPerson != null && (req.getPassword().equals(slrObjForloggedInPerson.getPassword())
				&& custObjForLoggedInPerson == null)) {
			if (ls != null) {
				ls.setStatus(1);
				lsRepo.save(ls);
			} else {
				LoginStatus lsn = new LoginStatus();
				lsn.setEmailId(req.getEmailId());
				lsn.setStatus(1);
				lsRepo.save(lsn);
			}
			msg = "Logged in as Seller";
		} else {
			msg = "Invalid Credentials";
		}
		return ResponseEntity.ok(msg);
	}

	@RequestMapping("/logout/{emailId}")
	public ResponseEntity<?> logoutUser(@PathVariable String emailId) {
		if (lsRepo.findByEmailId(emailId) != null && lsRepo.findByEmailId(emailId).getStatus() == 1) {
			lsRepo.findByEmailId(emailId).setStatus(0);
			lsRepo.save(lsRepo.findByEmailId(emailId));
			return ResponseEntity.ok("Logged Out Successfully");
		} else {
			return ResponseEntity.ok("Invalid Credentials");
		}
	}
}