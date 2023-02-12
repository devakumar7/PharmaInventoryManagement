package com.ctel.eval.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.eval.model.Customer;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepo userDao;

	@Autowired
	CustomerService custServ;

	@PostMapping("/saveCust")
	public ResponseEntity<?> saveUser(@RequestBody Customer customer) {
		String s = custServ.registerValidator(customer);
		return ResponseEntity.ok(s);
	}

	@PostMapping("/saveCusts")
	public ResponseEntity<?> saveUsers(@RequestBody List<Customer> customers) {
		List<String> s = custServ.multipleCustsRegisterValidator(customers);
		return ResponseEntity.ok(s);
	}

	@GetMapping("/viewCusts/{emailId}")
	public ResponseEntity<?> getUser(@PathVariable String emailId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Customer> userList = custServ.viewAllCusts(emailId);
		if (!userList.isEmpty()) {
			map.put("status", 1);
			map.put("data", userList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping("/viewCust/{cid}")
	public ResponseEntity<?> getUserById(@PathVariable Integer cid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Customer cust = custServ.viewCustDet(cid);
		if (cust == null) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Please Login to View Details or You are Not Registered with us");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		} else {
			map.put("status", 1);
			map.put("data", cust);
			return new ResponseEntity<>(map, HttpStatus.OK);

		}

	}

	@GetMapping("/deleteCust/{cid}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer cid) {
		String s = custServ.delCustDet(cid);
		return ResponseEntity.ok(s);
	}

	@PutMapping("/updateCust/{cid}")
	public ResponseEntity<?> updateUserById(@PathVariable Integer cid, @RequestBody Customer custDetail) {
		String s = custServ.updCustDet(cid, custDetail);
		return ResponseEntity.ok(s);
	}
}