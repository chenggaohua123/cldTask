package com.fhtpay.task.model.trace.ems;

public class DestCpList {

	private String country;
	private String cpCode;
	private String cpName;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCpCode() {
		return cpCode;
	}
	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	@Override
	public String toString() {
		return "DestCpList [country=" + country + ", cpCode=" + cpCode
				+ ", cpName=" + cpName + "]";
	}
	
}
