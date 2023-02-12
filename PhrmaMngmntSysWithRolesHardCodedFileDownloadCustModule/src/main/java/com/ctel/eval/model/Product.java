package com.ctel.eval.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer pid;

	@Column(name = "prod_name")
	private String prodName;

	@Column(name = "manufacture_name")
	private String manufactureName;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "mfg_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime mfgDate;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "exp_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime expDate;

	private Double price;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer pid, String prodName, String manufactureName, LocalDateTime mfgDate, LocalDateTime expDate,
			Double price) {
		super();
		this.pid = pid;
		this.prodName = prodName;
		this.manufactureName = manufactureName;
		this.mfgDate = mfgDate;
		this.expDate = expDate;
		this.price = price;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public LocalDateTime getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(LocalDateTime mfgDate) {
		this.mfgDate = mfgDate;
	}

	public LocalDateTime getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDateTime expDate) {
		this.expDate = expDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", prodName=" + prodName + ", manufactureName=" + manufactureName + ", mfgDate="
				+ mfgDate + ", expDate=" + expDate + ", price=" + price + "]";
	}
}