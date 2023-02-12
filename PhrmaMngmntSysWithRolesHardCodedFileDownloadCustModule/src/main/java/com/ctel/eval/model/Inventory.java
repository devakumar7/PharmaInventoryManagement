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
@Table(name = "inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer iid;

	@Column(name = "prod_name")
	private String prodName;

	@Column(name = "prod_id")
	private Integer prodId;

	@Column(name = "seller_id")
	private Integer sellerId;

	@Column(name = "stock_out")
	private Integer stockOut;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "recorded_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime recordedDate;

	private Long qty;

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(Integer iid, String prodName, Integer prodId, Integer sellerId, Integer stockOut,
			LocalDateTime recordedDate, Long qty) {
		super();
		this.iid = iid;
		this.prodName = prodName;
		this.prodId = prodId;
		this.sellerId = sellerId;
		this.stockOut = stockOut;
		this.recordedDate = recordedDate;
		this.qty = qty;
	}

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getStockOut() {
		return stockOut;
	}

	public void setStockOut(Integer stockOut) {
		this.stockOut = stockOut;
	}

	public LocalDateTime getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(LocalDateTime recordedDate) {
		this.recordedDate = recordedDate;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Inventory [iid=" + iid + ", prodName=" + prodName + ", prodId=" + prodId + ", sellerId=" + sellerId
				+ ", stockOut=" + stockOut + ", recordedDate=" + recordedDate + ", qty=" + qty + "]";
	}

}