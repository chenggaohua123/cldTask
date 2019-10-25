package com.fhtpay.task.model.suspicious;

public class SuspiciousTransInfo {
	private int id;
	private String IPAddress;
	private String email;
	private String c;
	private String cardFullPhone;
	private String cardFullName;
	private String grAddress;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getCardFullPhone() {
		return cardFullPhone;
	}
	public void setCardFullPhone(String cardFullPhone) {
		this.cardFullPhone = cardFullPhone;
	}
	public String getCardFullName() {
		return cardFullName;
	}
	public void setCardFullName(String cardFullName) {
		this.cardFullName = cardFullName;
	}
	public String getGrAddress() {
		return grAddress;
	}
	public void setGrAddress(String grAddress) {
		this.grAddress = grAddress;
	}
}
