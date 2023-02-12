package com.ctel.eval.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
@Table(name = "pharmaorder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer oid;

	// @JsonIgnore
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderTemp")
	private List<Orderproduct> pdtList;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "created_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdDate;

	private Integer cid;

	@Column(name = "seller_id")
	private Integer sellerId;

	private Double bill;

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", pdtList=" + pdtList + ", createdDate=" + createdDate + ", cid=" + cid
				+ ", sellerId=" + sellerId + ", bill=" + bill + "]";
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Orderproduct> getPdtList() {
		return pdtList;
	}

	public void setPdtList(List<Orderproduct> pdtList) {
		this.pdtList = pdtList;
	}

	public Integer getOid() {
		return oid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
}