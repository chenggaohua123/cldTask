package com.fhtpay.task.model.trace.ems;

import java.util.Arrays;
import java.util.List;

public class Section1 {

	private String companyName;
	private String countryName;
	private String cpCode;
	private DetailList[] detailList;
	private String url;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCpCode() {
		return cpCode;
	}
	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}
	public DetailList[] getDetailList() {
		return detailList;
	}
	public void setDetailList(DetailList[] detailList) {
		this.detailList = detailList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Section1 [companyName=" + companyName + ", countryName="
				+ countryName + ", cpCode=" + cpCode + ", detailList="
				+ Arrays.toString(detailList) + ", url=" + url + "]";
	}
	
}
