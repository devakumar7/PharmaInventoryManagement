package com.ctel.eval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seller")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sid;

	@Column(name = "seller_name")
	private String sellerName;

	@Column(name = "store_name")
	private String storeName;

	private String address;

	@Column(name = "contact_no")
	private Long contactNo;

	private String city;
	private String state;
	private String country;

	@Column(name = "pan_no")
	private String panNo;

	@Column(name = "email_id")
	private String emailId;

	private String password;

	@Override
	public String toString() {
		return "Seller [sid=" + sid + ", sellerName=" + sellerName + ", storeName=" + storeName + ", address=" + address
				+ ", contactNo=" + contactNo + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", panNo=" + panNo + ", emailId=" + emailId + ", password=" + password + "]";
	}

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}