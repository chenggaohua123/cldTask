package com.fhtpay.task.model.trace.ems;

import java.util.Arrays;

public class EMSTraceInfo {

	private boolean allowRetry;
	private String cachedTime;
	private DestCpList[] destCpList;
	private String destCountry;
	private boolean hasRefreshBtn;
	private LatestTrackingInfo latestTrackingInfo; 
	private String mailNo;
	private String originCountry;
	private OriginCpListInfo[] originCpList;
	private Section1 section1;
	private Section2 section2;
	private int shippingTime; 
	private String status;
	private String statusDesc;
	private String success;
	private String syncQuery;
	public boolean isAllowRetry() {
		return allowRetry;
	}
	public void setAllowRetry(boolean allowRetry) {
		this.allowRetry = allowRetry;
	}
	public String getCachedTime() {
		return cachedTime;
	}
	public void setCachedTime(String cachedTime) {
		this.cachedTime = cachedTime;
	}
	public DestCpList[] getDestCpList() {
		return destCpList;
	}
	public void setDestCpList(DestCpList[] destCpList) {
		this.destCpList = destCpList;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public boolean isHasRefreshBtn() {
		return hasRefreshBtn;
	}
	public void setHasRefreshBtn(boolean hasRefreshBtn) {
		this.hasRefreshBtn = hasRefreshBtn;
	}
	public LatestTrackingInfo getLatestTrackingInfo() {
		return latestTrackingInfo;
	}
	public void setLatestTrackingInfo(LatestTrackingInfo latestTrackingInfo) {
		this.latestTrackingInfo = latestTrackingInfo;
	}
	public String getMailNo() {
		return mailNo;
	}
	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public OriginCpListInfo[] getOriginCpList() {
		return originCpList;
	}
	public void setOriginCpList(OriginCpListInfo[] originCpList) {
		this.originCpList = originCpList;
	}
	public Section1 getSection1() {
		return section1;
	}
	public void setSection1(Section1 section1) {
		this.section1 = section1;
	}
	public Section2 getSection2() {
		return section2;
	}
	public void setSection2(Section2 section2) {
		this.section2 = section2;
	}
	public int getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(int shippingTime) {
		this.shippingTime = shippingTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getSyncQuery() {
		return syncQuery;
	}
	public void setSyncQuery(String syncQuery) {
		this.syncQuery = syncQuery;
	}
	@Override
	public String toString() {
		return "EMSTraceInfo [allowRetry=" + allowRetry + ", cachedTime="
				+ cachedTime + ", destCpList=" + Arrays.toString(destCpList)
				+ ", destCountry=" + destCountry + ", hasRefreshBtn="
				+ hasRefreshBtn + ", latestTrackingInfo=" + latestTrackingInfo
				+ ", mailNo=" + mailNo + ", originCountry=" + originCountry
				+ ", originCpList=" + Arrays.toString(originCpList)
				+ ", section1=" + section1 + ", section2=" + section2
				+ ", shippingTime=" + shippingTime + ", status=" + status
				+ ", statusDesc=" + statusDesc + ", success=" + success
				+ ", syncQuery=" + syncQuery + "]";
	}
	
}
