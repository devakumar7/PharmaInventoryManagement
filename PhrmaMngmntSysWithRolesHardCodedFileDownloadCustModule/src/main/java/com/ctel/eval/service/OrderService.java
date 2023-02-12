package com.ctel.eval.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctel.eval.model.Customer;
import com.ctel.eval.model.Order;
import com.ctel.eval.model.Orderproduct;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.repository.InventoryRepo;
import com.ctel.eval.repository.LoginStatusRepo;
import com.ctel.eval.repository.OrderRepo;
import com.ctel.eval.repository.OrderproductRepo;
import com.ctel.eval.repository.ProductRepo;

@Service
public class OrderService {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private OrderRepo ordRepo;

	@Autowired
	private OrderproductRepo ordProdRepo;

	@Autowired
	private InventoryRepo invRepo;

	@Autowired
	private ProductRepo prodRepo;

	@Autowired
	private LoginStatusRepo lsRepo;

	public String orderValidator(Order ord) {

		String msg = null;

		Double bill = 0.0;

		Customer temp = custRepo.findById(ord.getCid()).get();

		List<Orderproduct> list = ord.getPdtList();

		System.out.println(ord.getCid());
		System.out.println("jaffa" + ord.getPdtList().toString());

		for (Orderproduct l : list) {

			// Checking Whether Product with ProdId is available form Seller Id in Inventory
			// ==> If exists get its Qty

			if (invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).getQty() > l.getQty()) {

				System.out.println("Required Order Quantity is available in Inventory");

				// Calculating Bill and Printing Invoice and Updating Order Repo

				l.setPrice(l.getQty() * prodRepo.findById(l.getPid()).get().getPrice());

				l.setProductName(prodRepo.findById(l.getPid()).get().getProdName());

				l.setOrderTemp(ord);

				ordProdRepo.save(l);

				bill = bill + (l.getQty() * prodRepo.findById(l.getPid()).get().getPrice());

				// Calculating StockOut and Quantity and Updating Inventory Repo

				invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).setStockOut(l.getQty().intValue()
						+ invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).getStockOut());
				invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).setQty(
						invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).getQty() - l.getQty());
				invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).setRecordedDate(LocalDateTime.now());

				invRepo.save(invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()));

				msg = "Part of your order with Product Id " + l.getPid() + " was processed";
			} else {
				msg = "Required Qty is not available. So cant process your order";
			}
		}
		ord.setCreatedDate(LocalDateTime.now());
		ord.setBill(bill);
		ordRepo.save(ord);
		return msg;
	}

	/*
	 * public String distributorOrderValidator(Order ord) {
	 * 
	 * String msg = null;
	 * 
	 * Double bill = 0.0;
	 * 
	 * Customer temp = custRepo.findById(ord.getCid()).get();
	 * 
	 * List<Orderproduct> list = ord.getPdtList();
	 * 
	 * System.out.println(ord.getCid());
	 * 
	 * for (Orderproduct l : ord.getPdtList()) {
	 * 
	 * // Checking Whether Product with ProdId is available form Seller Id in
	 * Inventory // ==> If exists get its Qty
	 * 
	 * if (invRepo.findAllBySellerIdAndProdId(ord.getSellerId(),
	 * l.getPid()).getQty() > l.getQty()) {
	 * 
	 * System.out.println("Required Order Quantity is available in Inventory");
	 * 
	 * // Calculating Bill and Printing Invoice and Updating Order Repo
	 * 
	 * l.setPrice(l.getQty() * prodRepo.findById(l.getPid()).get().getPrice());
	 * 
	 * l.setProductName(prodRepo.findById(l.getPid()).get().getProdName());
	 * 
	 * l.setOrderTemp(ord);
	 * 
	 * ordProdRepo.save(l);
	 * 
	 * bill = bill + (l.getQty() * prodRepo.findById(l.getPid()).get().getPrice());
	 * 
	 * // Calculating StockOut and Quantity and Updating Inventory Repo
	 * 
	 * invRepo.findAllBySellerIdAndProdId(ord.getSellerId(),
	 * l.getPid()).setStockOut(l.getQty().intValue() +
	 * invRepo.findAllBySellerIdAndProdId(ord.getSellerId(),
	 * l.getPid()).getStockOut());
	 * invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).setQty(
	 * invRepo.findAllBySellerIdAndProdId(ord.getSellerId(), l.getPid()).getQty() -
	 * l.getQty()); invRepo.findAllBySellerIdAndProdId(ord.getSellerId(),
	 * l.getPid()).setRecordedDate(LocalDateTime.now());
	 * 
	 * invRepo.save(invRepo.findAllBySellerIdAndProdId(ord.getSellerId(),
	 * l.getPid()));
	 * 
	 * msg = "Part of your order with Product Id " + l.getPid() + " was processed";
	 * } else { msg = "Required Qty is not available. So cant process your order"; }
	 * } ord.setCreatedDate(LocalDateTime.now()); ord.setBill(bill);
	 * ordRepo.save(ord); return msg; }
	 */
}