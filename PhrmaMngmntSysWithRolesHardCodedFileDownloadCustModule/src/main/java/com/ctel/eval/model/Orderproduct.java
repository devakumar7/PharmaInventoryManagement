package com.ctel.eval.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orderproduct {

	@Column(name = "prod_name")
	private String productName;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer sno;
	@Column
	private Integer pid;
	@Column
	private Long qty;
	@Column
	private Double price;

	// @JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "oid")
	private Order orderTemp;

	@Override
	public String toString() {
		return "Orderproduct [productName=" + productName + ", sno=" + sno + ", pid=" + pid + ", qty=" + qty
				+ ", price=" + price + ", orderTemp=" + orderTemp + "]";
	}

	public Order getOrderTemp() {
		return orderTemp;
	}

	public void setOrderTemp(Order orderTemp) {
		this.orderTemp = orderTemp;
	}

	public Orderproduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}
}
