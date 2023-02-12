package com.ctel.eval.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login_status")
public class LoginStatus {

	@Id
	private String emailId;

	private Integer status;
	

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LoginStatus(String emailId, Integer status) {
		super();
		this.emailId = emailId;
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LoginStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

}