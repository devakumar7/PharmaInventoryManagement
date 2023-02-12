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

import com.ctel.eval.model.Seller;
import com.ctel.eval.repository.SellerRepo;
import com.ctel.eval.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	SellerRepo userDao;

	@Autowired
	SellerService slrServ;

	// @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@PostMapping("/saveSeller")
	public ResponseEntity<?> saveUser(@RequestBody Seller slr) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String s=slrServ.registerValidator(slr);
		map.put("status", 1);
		map.put("message", s);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// @RequestMapping("/viewUsers")
	@GetMapping("/viewSellers")
	public ResponseEntity<?> getUser() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Seller> slrList = (List<Seller>) userDao.findAll();
		if (!slrList.isEmpty()) {
			map.put("status", 1);
			map.put("data", slrList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	// @RequestMapping(value = "/viewUser/{phone}", method = RequestMethod.GET)
	@RequestMapping("/viewSlr/{sid}")
	// @ResponseBody
	public ResponseEntity<?> getUserById(@PathVariable Integer sid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		/*
		 * try { Optional<User> resUsr = userDao.findById(phone); User
		 * viewUser=resUsr.get(); map.put("status", 1); map.put("data", viewUser);
		 * return new ResponseEntity<>(map, HttpStatus.OK); }
		 */

		try {
			Optional<Seller> viewSlr = userDao.findById(sid);
			map.put("status", 1);
			map.put("data", viewSlr);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		/*
		 * try { User viewUser=userDao.findById(phone).get(); map.put("status", 1);
		 * map.put("data", viewUser); return new ResponseEntity<>(map, HttpStatus.OK); }
		 */
		catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/deleteSlr/{sid}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer sid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		/*
		 * try { userDao.deleteById(phone); map.put("status", 1); map.put("message",
		 * "Record is deleted successfully!"); return new ResponseEntity<>(map,
		 * HttpStatus.OK); }
		 */
		try {
			Seller slr = userDao.findById(sid).get();
			userDao.delete(slr);
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

	@PutMapping("/updateSlr/{sid}")
	public ResponseEntity<?> updateUserById(@PathVariable Integer sid, @RequestBody Seller slrDetail) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Optional<Seller> resSlr = userDao.findById(sid);
			Seller slr = resSlr.get();

			slr.setSid(slrDetail.getSid());
			slr.setSellerName(slrDetail.getSellerName());
			slr.setStoreName(slrDetail.getStoreName());
			slr.setAddress(slrDetail.getAddress());
			slr.setContactNo(slrDetail.getContactNo());
			slr.setCity(slrDetail.getCity());
			slr.setState(slrDetail.getState());
			slr.setCountry(slrDetail.getCountry());
			slr.setPanNo(slrDetail.getPanNo());
			slr.setEmailId(slrDetail.getEmailId());
			slr.setPassword(slrDetail.getPassword());

			userDao.save(slr);
			map.put("status", 1);
			map.put("data", userDao.findById(sid));
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

}