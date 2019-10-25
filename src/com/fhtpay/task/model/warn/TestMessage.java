package com.fhtpay.task.model.warn;

public class TestMessage {

//	private static String userId = "6744";
//	private String account= "okpay";
//	private String passWord = "111111";
	private static String mobile = "13128854459;13265701755";
//	private String mobile = "13510690062";
	private static String LANZUserId = "851338";
	private static String LANZAccount = "shufengchao";
	private static String LANZPassWord = "F8F8EC7FF0CF717FB99D9EC61013F7C85D525A2C";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SMSSendUtil.sendLANZSMS(LANZUserId, LANZAccount, LANZPassWord, mobile, "xxx", "", "", "");
	}

}
