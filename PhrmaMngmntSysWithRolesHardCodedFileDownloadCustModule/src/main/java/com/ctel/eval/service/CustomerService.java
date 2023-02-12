package com.ctel.eval.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ctel.eval.model.Customer;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.repository.LoginStatusRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private LoginStatusRepo lsRepo;

	public String registerValidator(Customer cust) {

		if (!cust.getFirstName().isEmpty() && !cust.getLastName().isEmpty() && !cust.getAddress().isEmpty()
				&& cust.getContactNo() > 999999999 && cust.getContactNo() < 10000000000L
				&& custRepo.findByContactNo(cust.getContactNo()) == null && !cust.getCity().isEmpty()
				&& !cust.getState().isEmpty() && !cust.getCountry().isEmpty()
				&& (cust.getGender().equalsIgnoreCase("male") || cust.getGender().equalsIgnoreCase("female"))
				&& !cust.getEmailId().isEmpty() && cust.getPassword().length() > 8) {
			custRepo.save(cust);
			return "Saved Successfully";
		} else
			return "Check the input Values : First Name & Last Name cant be null Invalid Phone Number/Email & password length should be > 8 chars";
	}

	public List<String> multipleCustsRegisterValidator(List<Customer> customers) {

		String msg;
		List<String> msgs = new ArrayList<>();
		for (Customer cust : customers) {
			if (!cust.getFirstName().isEmpty() && !cust.getLastName().isEmpty() && !cust.getAddress().isEmpty()
					&& cust.getContactNo() > 999999999 && cust.getContactNo() < 10000000000L
					&& custRepo.findByContactNo(cust.getContactNo()) == null && !cust.getCity().isEmpty()
					&& !cust.getState().isEmpty() && !cust.getCountry().isEmpty()
					&& (cust.getGender().equalsIgnoreCase("male") || cust.getGender().equalsIgnoreCase("female"))
					&& !cust.getEmailId().isEmpty() && cust.getPassword().length() > 8) {
				custRepo.save(cust);
				msg = "Customer with EmailId " + cust.getEmailId() + " Saved Successfully";
			} else {
				msg = "Please Check the input Values for Customer with details " + cust.getEmailId()
						+ " : First Name & Last Name cant be null Invalid Phone Number/Email & password length should be > 8 chars";
			}
			msgs.add(msg);
		}
		return msgs;

	}

	public List<Customer> viewAllCusts(@PathVariable String emailId) {

		if (lsRepo.findByEmailId(emailId).getStatus() == 1
				&& custRepo.findByEmailId(emailId).getRole().equals("admin")) {
			return custRepo.findAll();
		} else {
			return null;
		}
	}

	public Customer viewCustDet(Integer cid) {
		Customer temp = custRepo.findById(cid).get();
		if (temp != null && lsRepo.findByEmailId(temp.getEmailId()) != null) {
			if ((lsRepo.findByEmailId(temp.getEmailId()).getStatus() == 1)
					|| (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1)) {
				return custRepo.findById(cid).get();
			} else
				return null;
		} else
			return null;
	}

	public String delCustDet(Integer cid) {
		if (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1) {
			custRepo.deleteById(cid);
			return "Deletion Successful";

		} else {
			return "You are not authorized to delete";
		}
	}

	public String updCustDet(Integer cid, Customer custDetail) {
		Customer cust = custRepo.findById(cid).get();
		if ((lsRepo.findByEmailId(cust.getEmailId()) != null
				&& lsRepo.findByEmailId(cust.getEmailId()).getStatus() == 1)
				|| (lsRepo.findByEmailId(custRepo.findByRole("admin").getEmailId()).getStatus() == 1)) {

			if (custDetail.getFirstName() != null)
				cust.setFirstName(custDetail.getFirstName());
			if (custDetail.getLastName() != null)
				cust.setLastName(custDetail.getLastName());
			if (custDetail.getAddress() != null)
				cust.setAddress(custDetail.getAddress());
			if (custDetail.getContactNo() != null)
				cust.setContactNo(custDetail.getContactNo());
			if (custDetail.getCity() != null)
				cust.setCity(custDetail.getCity());
			if (custDetail.getState() != null)
				cust.setState(custDetail.getState());
			if (custDetail.getCountry() != null)
				cust.setCountry(custDetail.getCountry());
			if (custDetail.getGender() != null)
				cust.setGender(custDetail.getGender());
			if (custDetail.getEmailId() != null) {
				cust.setEmailId(custDetail.getEmailId());
				lsRepo.findByEmailId(cust.getEmailId()).setEmailId(custDetail.getEmailId());
			}
			if (custDetail.getPassword() != null)
				cust.setPassword(custDetail.getPassword());
			if (custDetail.getRole() != null)
				cust.setRole(custDetail.getRole());

			custRepo.save(cust);
			lsRepo.save(lsRepo.findByEmailId(cust.getEmailId()));

			return "Customer Details Updated Successfully";
		} else
			return "Updation Failed, You are not Logged In or No Such Customer with provided Id Exists";
	}

}
