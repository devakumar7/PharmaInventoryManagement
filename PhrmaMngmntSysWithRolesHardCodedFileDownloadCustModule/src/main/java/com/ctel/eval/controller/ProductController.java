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

import com.ctel.eval.model.Product;
import com.ctel.eval.repository.ProductRepo;
import com.ctel.eval.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductRepo userDao;

	@Autowired
	private ProductService prodServ;

	// @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@PostMapping(value = "saveProd", consumes = { "application/json" })
	public ResponseEntity<?> saveUser(@RequestBody Product pdt) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String s = prodServ.registerValidator(pdt);
		map.put("status", 1);
		map.put("message", s);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// @RequestMapping("/viewUsers")
	@GetMapping("/viewProds")
	public ResponseEntity<?> getUser() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Product> prodList = (List<Product>) userDao.findAll();
		if (!prodList.isEmpty()) {
			map.put("status", 1);
			map.put("data", prodList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	// @RequestMapping(value = "/viewUser/{phone}", method = RequestMethod.GET)
	@RequestMapping("/viewProd/{pid}")
	// @ResponseBody
	public ResponseEntity<?> getUserById(@PathVariable Integer pid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		/*
		 * try { Optional<User> resUsr = userDao.findById(phone); User
		 * viewUser=resUsr.get(); map.put("status", 1); map.put("data", viewUser);
		 * return new ResponseEntity<>(map, HttpStatus.OK); }
		 */

		try {
			Optional<Product> viewProd = userDao.findById(pid);
			map.put("status", 1);
			map.put("data", viewProd);
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

	@GetMapping("/deleteProd/{pid}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer pid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		/*
		 * try { userDao.deleteById(phone); map.put("status", 1); map.put("message",
		 * "Record is deleted successfully!"); return new ResponseEntity<>(map,
		 * HttpStatus.OK); }
		 */
		try {
			Product pdt = userDao.findById(pid).get();
			userDao.delete(pdt);
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

	@PutMapping("/updateProd/{pid}")
	public ResponseEntity<?> updateUserById(@PathVariable Integer pid, @RequestBody Product pdtDetail) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Optional<Product> resPdt = userDao.findById(pid);
			Product pdt = resPdt.get();
			pdt.setPid(pid);
			pdt.setProdName(pdtDetail.getProdName());
			pdt.setManufactureName(pdtDetail.getManufactureName());
			pdt.setPrice(pdtDetail.getPrice());

			userDao.save(pdt);
			map.put("status", 1);
			map.put("data", userDao.findById(pid));
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

}