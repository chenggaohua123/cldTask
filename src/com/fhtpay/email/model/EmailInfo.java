package com.fhtpay.email.model;

public class EmailInfo {
	private String id;
	private String emailHost;
	private String emailAccount;
	private String emailPassword;
	private String emailPort;
	private String enabled;
	private String sendCount;
	private String emailType;
	private String sendCountLimit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public String getEmailAccount() {
		return emailAccount;
	}
	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getEmailPort() {
		return emailPort;
	}
	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getSendCount() {
		return sendCount;
	}
	public void setSendCount(String sendCount) {
		this.sendCount = sendCount;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getSendCountLimit() {
		return sendCountLimit;
	}
	public void setSendCountLimit(String sendCountLimit) {
		this.sendCountLimit = sendCountLimit;
	}
	
	
	
}
