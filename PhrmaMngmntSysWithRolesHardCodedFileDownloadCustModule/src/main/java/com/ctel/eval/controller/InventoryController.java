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

import com.ctel.eval.model.Inventory;
import com.ctel.eval.repository.InventoryRepo;
import com.ctel.eval.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	private InventoryRepo userDao;

	@Autowired
	private InventoryService invService;

	@PostMapping(value = "saveInv", consumes = { "application/json" })
	public ResponseEntity<?> saveUser(@RequestBody Inventory inv, @PathVariable Integer sid) {
		String s = invService.invValidator(inv, sid);
		return ResponseEntity.ok(s);
	}

	// @RequestMapping("/viewUsers")
	@GetMapping("/viewInvItems/{emailId}")
	public ResponseEntity<?> getUsers(@PathVariable String emailId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Inventory> invList = invService.viewAllInvItems(emailId);
		if (!invList.isEmpty()) {
			map.put("status", 1);
			map.put("data", invList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping("/viewInvItems/{sid}")
	public ResponseEntity<?> getUserById(@PathVariable Integer sid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Inventory> invList = invService.viewInvItem(sid);
		if (!invList.isEmpty()) {
			map.put("status", 1);
			map.put("data", invList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "You are not logged in or No such seller Id exists");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/deleteInvItem/{iid}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer iid) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		/*
		 * try { userDao.deleteById(phone); map.put("status", 1); map.put("message",
		 * "Record is deleted successfully!"); return new ResponseEntity<>(map,
		 * HttpStatus.OK); }
		 */
		try {
			Inventory inv = userDao.findById(iid).get();
			userDao.delete(inv);
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

	@PutMapping("/updateInvItem/{iid}")
	public ResponseEntity<?> updateUserById(@PathVariable Integer iid, @RequestBody Inventory invItemDetail) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Optional<Inventory> resInvItem = userDao.findById(iid);
			Inventory invItem = resInvItem.get();
			invItem.setIid(iid);
			invItem.setProdName(invItemDetail.getProdName());
			invItem.setProdId(invItemDetail.getProdId());
			invItem.setSellerId(invItemDetail.getSellerId());
			invItem.setStockOut(invItemDetail.getStockOut());
			invItem.setRecordedDate(invItemDetail.getRecordedDate());
			invItem.setQty(invItemDetail.getQty());

			userDao.save(invItem);
			map.put("status", 1);
			map.put("data", userDao.findById(iid));
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

}