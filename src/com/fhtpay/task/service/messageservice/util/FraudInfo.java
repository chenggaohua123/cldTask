package com.fhtpay.task.service.messageservice.util;

import java.sql.Timestamp;




public class FraudInfo {
	//请求字段
	private String merNo;
	private String terNo;
	private String orderNo;
	private String cardBin;
	private String ipAddress;
	private String hashCardNo;
	private String shipCountry;
	private String shipCity;
	private String shipState;
	private String shipAddress;
	private String billCountry;
	private String billCity;
	private String billState;
	private String billAddress;
	private String email;
	private String phone;
	private String shipFirstName;
	private String shipLastName;
	private String billFirstName;
	private String billLastName;
	private String zip;
	private String sourceAmount;
	private String sourceCurrency;
	private String website;
	private String cardType;
	private Timestamp createDate;
	//自定义字段
	private String merchantDefineValue1;
	private String merchantDefineValue2;
	private String merchantDefineValue3;
	private String merchantDefineValue4;
	private String merchantDefineValue5;
	private String merchantDefineValue6;
	private String merchantDefineValue7;
	private String merchantDefineValue8;
	private String merchantDefineValue9;
	private String merchantDefineValue10;
	
	//返回字段
	private String ret;
	private String msg;
	private String fraudScore;
	private String ipCountry;
	private String countryMatch;
	private String highRiskCountry;
	private String distance;
	private String ipAccuracyRadius;
	private String ipCity;
	private String ipRegion;
	private String ipRegionName;
	private String ipPostalCode;
	private String ipMetroCode;
	private String ipAreaCode;
	private String countryCode;
	private String ipCountryName;
	private String ipContinentCode;
	private String ipLatitude;
	private String ipLongitude;
	private String ipTimeZone;
	private String ipAsnum;
	private String ipUserType;
	private String ipNetSpeedCell;
	private String ipDomain;
	private String ipIsp;
	private String ipOrg;
	private String ipCityConf;
	private String ipRegionConf;
	private String ipPostalConf;
	private String ipCountryConf;
	private String anonymousProxy;
	private String proxyScore;
	private String isTransProxy;
	private String ipCorporateProxy;
	private String freeMail;
	private String carderEmail;
	private String binMatch;
	private String binCountry;
	private String binNameMatch;
	private String binName;
	private String binPhoneMatch;
	private String binPhone;
	private String prepaid;
	private String custPhoneInBillingLoc;
	private String shipForward;
	private String cityPostalMatch;
	private String shipCityPostalMatch;
	private String queriesRemaining;
	private String maxmindID;
	private String minfraudVersion;
	private String serviceLevel;
	private String err;
	
	// 预警参数
	private String uid;
	private String police1;
	private String police2;
	private String police3;
	private String police4;
	private String police5;
	
	//通道Id
	private String currencyId;
	//银行Id
	private String bankId;
	
	
	private String webInfo;
	private String card_type="unknown";
	private String bank="unknown";
	private String country_code="unknown";
	
	//ThreatMetrix 信息 
	private String tmScore;//ThreatMetrix 分数
	private String merchantOrderNo;
	private String exactID;//设备ID
	
	//0 maxMind 1 ThreatMetrix 2 不走
	private int isRisk;
	
	
	private String language;
	
	
	
	
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getExactID() {
		return exactID;
	}

	public void setExactID(String exactID) {
		this.exactID = exactID;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public int getIsRisk() {
		return isRisk;
	}

	public void setIsRisk(int isRisk) {
		this.isRisk = isRisk;
	}

	public String getTmScore() {
		return tmScore;
	}

	public void setTmScore(String tmScore) {
		this.tmScore = tmScore;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getWebInfo() {
		return webInfo;
	}

	public void setWebInfo(String webInfo) {
		this.webInfo = webInfo;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getTerNo() {
		return terNo;
	}

	public void setTerNo(String terNo) {
		this.terNo = terNo;
	}

	public String getMerchantDefineValue1() {
		return merchantDefineValue1;
	}

	public void setMerchantDefineValue1(String merchantDefineValue1) {
		this.merchantDefineValue1 = merchantDefineValue1;
	}

	public String getMerchantDefineValue2() {
		return merchantDefineValue2;
	}

	public void setMerchantDefineValue2(String merchantDefineValue2) {
		this.merchantDefineValue2 = merchantDefineValue2;
	}

	public String getMerchantDefineValue3() {
		return merchantDefineValue3;
	}

	public void setMerchantDefineValue3(String merchantDefineValue3) {
		this.merchantDefineValue3 = merchantDefineValue3;
	}

	public String getMerchantDefineValue4() {
		return merchantDefineValue4;
	}

	public void setMerchantDefineValue4(String merchantDefineValue4) {
		this.merchantDefineValue4 = merchantDefineValue4;
	}

	public String getMerchantDefineValue5() {
		return merchantDefineValue5;
	}

	public void setMerchantDefineValue5(String merchantDefineValue5) {
		this.merchantDefineValue5 = merchantDefineValue5;
	}

	public String getMerchantDefineValue6() {
		return merchantDefineValue6;
	}

	public void setMerchantDefineValue6(String merchantDefineValue6) {
		this.merchantDefineValue6 = merchantDefineValue6;
	}

	public String getMerchantDefineValue7() {
		return merchantDefineValue7;
	}

	public void setMerchantDefineValue7(String merchantDefineValue7) {
		this.merchantDefineValue7 = merchantDefineValue7;
	}

	public String getMerchantDefineValue8() {
		return merchantDefineValue8;
	}

	public void setMerchantDefineValue8(String merchantDefineValue8) {
		this.merchantDefineValue8 = merchantDefineValue8;
	}

	public String getMerchantDefineValue9() {
		return merchantDefineValue9;
	}

	public void setMerchantDefineValue9(String merchantDefineValue9) {
		this.merchantDefineValue9 = merchantDefineValue9;
	}

	public String getMerchantDefineValue10() {
		return merchantDefineValue10;
	}

	public void setMerchantDefineValue10(String merchantDefineValue10) {
		this.merchantDefineValue10 = merchantDefineValue10;
	}
	
	
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	
	public String getSourceAmount() {
		return sourceAmount;
	}

	public void setSourceAmount(String sourceAmount) {
		this.sourceAmount = sourceAmount;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	
	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getWebsite() {
		return website;
	}
	
	
	public void setWebsite(String website) {
		this.website = website;
	}

	
	
	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFraudScore() {
		return fraudScore;
	}

	public void setFraudScore(String fraudScore) {
		this.fraudScore = fraudScore;
	}



	public String getIpCountry() {
		return ipCountry;
	}

	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}

	public String getCountryMatch() {
		return countryMatch;
	}

	public void setCountryMatch(String countryMatch) {
		this.countryMatch = countryMatch;
	}

	public String getHighRiskCountry() {
		return highRiskCountry;
	}

	public void setHighRiskCountry(String highRiskCountry) {
		this.highRiskCountry = highRiskCountry;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getIpAccuracyRadius() {
		return ipAccuracyRadius;
	}

	public void setIpAccuracyRadius(String ipAccuracyRadius) {
		this.ipAccuracyRadius = ipAccuracyRadius;
	}

	public String getIpCity() {
		return ipCity;
	}

	public void setIpCity(String ipCity) {
		this.ipCity = ipCity;
	}

	public String getIpRegion() {
		return ipRegion;
	}

	public void setIpRegion(String ipRegion) {
		this.ipRegion = ipRegion;
	}

	public String getIpRegionName() {
		return ipRegionName;
	}

	public void setIpRegionName(String ipRegionName) {
		this.ipRegionName = ipRegionName;
	}

	public String getIpPostalCode() {
		return ipPostalCode;
	}

	public void setIpPostalCode(String ipPostalCode) {
		this.ipPostalCode = ipPostalCode;
	}

	public String getIpMetroCode() {
		return ipMetroCode;
	}

	public void setIpMetroCode(String ipMetroCode) {
		this.ipMetroCode = ipMetroCode;
	}

	public String getIpAreaCode() {
		return ipAreaCode;
	}

	public void setIpAreaCode(String ipAreaCode) {
		this.ipAreaCode = ipAreaCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getIpCountryName() {
		return ipCountryName;
	}

	public void setIpCountryName(String ipCountryName) {
		this.ipCountryName = ipCountryName;
	}

	public String getIpContinentCode() {
		return ipContinentCode;
	}

	public void setIpContinentCode(String ipContinentCode) {
		this.ipContinentCode = ipContinentCode;
	}

	public String getIpLatitude() {
		return ipLatitude;
	}

	public void setIpLatitude(String ipLatitude) {
		this.ipLatitude = ipLatitude;
	}

	public String getIpLongitude() {
		return ipLongitude;
	}

	public void setIpLongitude(String ipLongitude) {
		this.ipLongitude = ipLongitude;
	}

	public String getIpTimeZone() {
		return ipTimeZone;
	}

	public void setIpTimeZone(String ipTimeZone) {
		this.ipTimeZone = ipTimeZone;
	}

	public String getIpAsnum() {
		return ipAsnum;
	}

	public void setIpAsnum(String ipAsnum) {
		this.ipAsnum = ipAsnum;
	}

	public String getIpUserType() {
		return ipUserType;
	}

	public void setIpUserType(String ipUserType) {
		this.ipUserType = ipUserType;
	}

	public String getIpNetSpeedCell() {
		return ipNetSpeedCell;
	}

	public void setIpNetSpeedCell(String ipNetSpeedCell) {
		this.ipNetSpeedCell = ipNetSpeedCell;
	}

	public String getIpDomain() {
		return ipDomain;
	}

	public void setIpDomain(String ipDomain) {
		this.ipDomain = ipDomain;
	}

	public String getIpIsp() {
		return ipIsp;
	}

	public void setIpIsp(String ipIsp) {
		this.ipIsp = ipIsp;
	}

	public String getIpOrg() {
		return ipOrg;
	}

	public void setIpOrg(String ipOrg) {
		this.ipOrg = ipOrg;
	}

	public String getIpCityConf() {
		return ipCityConf;
	}

	public void setIpCityConf(String ipCityConf) {
		this.ipCityConf = ipCityConf;
	}

	public String getIpRegionConf() {
		return ipRegionConf;
	}

	public void setIpRegionConf(String ipRegionConf) {
		this.ipRegionConf = ipRegionConf;
	}

	public String getIpPostalConf() {
		return ipPostalConf;
	}

	public void setIpPostalConf(String ipPostalConf) {
		this.ipPostalConf = ipPostalConf;
	}

	public String getIpCountryConf() {
		return ipCountryConf;
	}

	public void setIpCountryConf(String ipCountryConf) {
		this.ipCountryConf = ipCountryConf;
	}

	public String getAnonymousProxy() {
		return anonymousProxy;
	}

	public void setAnonymousProxy(String anonymousProxy) {
		this.anonymousProxy = anonymousProxy;
	}

	public String getProxyScore() {
		return proxyScore;
	}

	public void setProxyScore(String proxyScore) {
		this.proxyScore = proxyScore;
	}

	public String getIsTransProxy() {
		return isTransProxy;
	}

	public void setIsTransProxy(String isTransProxy) {
		this.isTransProxy = isTransProxy;
	}

	public String getIpCorporateProxy() {
		return ipCorporateProxy;
	}

	public void setIpCorporateProxy(String ipCorporateProxy) {
		this.ipCorporateProxy = ipCorporateProxy;
	}

	public String getFreeMail() {
		return freeMail;
	}

	public void setFreeMail(String freeMail) {
		this.freeMail = freeMail;
	}

	public String getCarderEmail() {
		return carderEmail;
	}

	public void setCarderEmail(String carderEmail) {
		this.carderEmail = carderEmail;
	}

	public String getBinMatch() {
		return binMatch;
	}

	public void setBinMatch(String binMatch) {
		this.binMatch = binMatch;
	}

	public String getBinCountry() {
		return binCountry;
	}

	public void setBinCountry(String binCountry) {
		this.binCountry = binCountry;
	}

	public String getBinNameMatch() {
		return binNameMatch;
	}

	public void setBinNameMatch(String binNameMatch) {
		this.binNameMatch = binNameMatch;
	}

	public String getBinName() {
		return binName;
	}

	public void setBinName(String binName) {
		this.binName = binName;
	}

	public String getBinPhoneMatch() {
		return binPhoneMatch;
	}

	public void setBinPhoneMatch(String binPhoneMatch) {
		this.binPhoneMatch = binPhoneMatch;
	}

	public String getBinPhone() {
		return binPhone;
	}

	public void setBinPhone(String binPhone) {
		this.binPhone = binPhone;
	}

	public String getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(String prepaid) {
		this.prepaid = prepaid;
	}

	public String getCustPhoneInBillingLoc() {
		return custPhoneInBillingLoc;
	}

	public void setCustPhoneInBillingLoc(String custPhoneInBillingLoc) {
		this.custPhoneInBillingLoc = custPhoneInBillingLoc;
	}

	public String getShipForward() {
		return shipForward;
	}

	public void setShipForward(String shipForward) {
		this.shipForward = shipForward;
	}

	public String getCityPostalMatch() {
		return cityPostalMatch;
	}

	public void setCityPostalMatch(String cityPostalMatch) {
		this.cityPostalMatch = cityPostalMatch;
	}

	public String getShipCityPostalMatch() {
		return shipCityPostalMatch;
	}

	public void setShipCityPostalMatch(String shipCityPostalMatch) {
		this.shipCityPostalMatch = shipCityPostalMatch;
	}

	public String getQueriesRemaining() {
		return queriesRemaining;
	}

	public void setQueriesRemaining(String queriesRemaining) {
		this.queriesRemaining = queriesRemaining;
	}

	public String getMaxmindID() {
		return maxmindID;
	}

	public void setMaxmindID(String maxmindID) {
		this.maxmindID = maxmindID;
	}

	public String getMinfraudVersion() {
		return minfraudVersion;
	}

	public void setMinfraudVersion(String minfraudVersion) {
		this.minfraudVersion = minfraudVersion;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public String getHashCardNo() {
		return hashCardNo;
	}

	public void setHashCardNo(String hashCardNo) {
		this.hashCardNo = hashCardNo;
	}
	
	
	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	
	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}
	public String getShipState() {
		return shipState;
	}

	public void setShipState(String shipState) {
		this.shipState = shipState;
	}
	
	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	
	public String getBillCountry() {
		return billCountry;
	}

	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}
	
	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}
	
	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getShipFirstName() {
		return shipFirstName;
	}

	public void setShipFirstName(String shipFirstName) {
		this.shipFirstName = shipFirstName;
	}
	
	public String getShipLastName() {
		return shipLastName;
	}

	public void setShipLastName(String shipLastName) {
		this.shipLastName = shipLastName;
	}
	
	public String getBillFirstName() {
		return billFirstName;
	}

	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}
	
	public String getBillLastName() {
		return billLastName;
	}

	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPolice1() {
		return police1;
	}

	public void setPolice1(String police1) {
		this.police1 = police1;
	}

	public String getPolice2() {
		return police2;
	}

	public void setPolice2(String police2) {
		this.police2 = police2;
	}

	public String getPolice3() {
		return police3;
	}

	public void setPolice3(String police3) {
		this.police3 = police3;
	}

	public String getPolice4() {
		return police4;
	}

	public void setPolice4(String police4) {
		this.police4 = police4;
	}

	public String getPolice5() {
		return police5;
	}

	public void setPolice5(String police5) {
		this.police5 = police5;
	}
}