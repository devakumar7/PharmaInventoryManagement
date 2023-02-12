package com.ctel.eval.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.ctel.eval.model.Order;
import com.ctel.eval.repository.OrderRepo;
import com.ctel.eval.service.LoginService;
import com.ctel.eval.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderRepo userDao;

	@Autowired
	private OrderService ordService;

	@Autowired
	private LoginService ls;

	@PostMapping("saveOrd")
	public ResponseEntity<?> saveUser(@RequestBody Order ord) {

		Integer i = ls.checkLoginStatusAndRole(ord.getCid());
		if (i == 1 || i == 2) {
			// Customer/Admin
			String s = ordService.orderValidator(ord);
			return ResponseEntity.ok(s);
		} /*
			 * else if (i == 2) { // Distributor/Admin String s =
			 * ordService.distributorOrderValidator(ord); return ResponseEntity.ok(s); }
			 */ else
			return ResponseEntity.ok("You are not logged in to place orders");

	}

	@GetMapping("/viewOrds")
	public ResponseEntity<?> getUser() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Order> ordList = (List<Order>) userDao.findAll();
		if (!ordList.isEmpty()) {
			map.put("status", 1);
			map.put("data", ordList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping("/viewOrd/{cid}")
	public ResponseEntity<?> getUserById(@PathVariable Integer cid) {
		Boolean b = ls.checkLoginStatus(cid);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (b == true) {
			List<Order> ordList = userDao.findByCustId(cid);
			map.put("message", "To check Order Details please use the reference OrderId from the below list");
			map.put("data", ordList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/deleteOrd/{oid}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer oid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Order ord = userDao.findById(oid).get();
			userDao.delete(ord);
			map.put("status", 1);
			map.put("message", "Record is deleted successfully!");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateOrd/{oid}")
	public ResponseEntity<?> updateUserById(@PathVariable Integer oid, @RequestBody Order ordDetail) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Optional<Order> resOrd = userDao.findById(oid);
			Order ord = resOrd.get();
			ord.setPdtList(ordDetail.getPdtList());
			ord.setOid(oid);
			ord.setCid(ordDetail.getCid());
			ord.setBill(ordDetail.getBill());
			ord.setSellerId(ordDetail.getSellerId());
			ord.setCreatedDate(ordDetail.getCreatedDate());

			userDao.save(ord);
			map.put("status", 1);
			map.put("data", userDao.findById(oid));
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

}