package com.fhtpay.task.model.warn;

public class TransWarnEmailInfo {
	private int id;
	private String phoneNo;
	private String email;
	private int systemId;
	private String userName;
	private String createDate;
	private int warnId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getWarnId() {
		return warnId;
	}
	public void setWarnId(int warnId) {
		this.warnId = warnId;
	}
}
