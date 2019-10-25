package com.fhtpay.task.service.messageservice.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;*/
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

/*import com.fhtpay.model.BankForm;
import com.fhtpay.model.BankInfo;
import com.fhtpay.model.TradeDetail;*/


public class Tools {

	// 用于记录订单号，在1毫秒时间内，20个订单不重复
	private static LinkedList<String> linkeList = new LinkedList<String>();
	
	/**
	 * 实现：MD5加密工具，支持中文
	 * @param str 需要加密的参数
	 * @return 加密后的密文
	 * @author YANG
	 * @date 2015-07-07
	 */
	public static String getMD5Info(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.exit(-1);
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for(int i = 0; i < byteArray.length; i++) {
			if(Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}
	/**
	 * 实现：SHA加密工具
	 * @param str 需要加密的参数
	 * @return 加密后的密文
	 */
	public static String getHashEncryption(String str) {
		String result = null;
		try {
			MessageDigest mesd = MessageDigest.getInstance("SHA-1");
			result = byte2hexString(mesd.digest(str.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return result;
		}  catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	public static String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
	public static String getSHA256(String str) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = str.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(bt);
			strDes = bytes2Hex(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		return strDes;	
	}
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	/**
	 * 实现：如果字符串超长，截取字符串长度
	 * @param msg 字符串
	 * @param length 截取长度
	 * @return 截取后的字符串
	 */
	public static final String getString(String msg, int length) {
		if(null!=msg) {
			if(msg.length()>length) {
				msg = msg.substring(0, length);
			}
		}
		return msg;
	}
	/**
     * 实现：验证字符串参数是否为数字（整数或者小数）
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNumber(String str) {
    	String pattern = "[0-9]+(.[0-9]+)?";
    	return str.matches(pattern);
    }
    /**
     * 实现：判断字符串是否是纯数字组成
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNumbers(String str) {
    	String pattern= "^[0-9]*$";
    	return str.matches(pattern);
    }
    /**
     * 实现：保留两位有效小数
     * @param d double类型数字
     * @return 结果
     */
    public static double getTwoDecimal(Double d) {
    	BigDecimal bigDecimal = new BigDecimal(d);
    	double f1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    	return f1;
    }
    
    /**
     * 实现：保留两位有效小数
     * @param d double类型数字
     * @return 结果
     */
    public static String gettTwoDecemalForString(String d) {
    	DecimalFormat df = new DecimalFormat("#.00");
    	return df.format(d);
    }
    /**
     * 实现：根据卡号返回信用卡类型
     * @param cardNo 卡号
     * @return 信用卡类型
     */
    public static String getCardType(String cardNo) {
    	String cardType=null;
    	if (cardNo.startsWith("4")) {
            cardType = "visa";
        } else if (cardNo.startsWith("5")||cardNo.startsWith("2")) {
        	if(cardNo.startsWith("2")){
        		try {
					if(Integer.parseInt(cardNo.substring(0,6))>=222100&&Integer.parseInt(cardNo.substring(0,6))<=272099){
						cardType = "master";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}else{
        		cardType = "master";
        	}
        } else if (cardNo.startsWith("35") || cardNo.startsWith("2131") || cardNo.startsWith("1800")) {
            cardType = "jcb";
        } else if (cardNo.startsWith("34") || cardNo.startsWith("37")) {
            cardType = "ae";
        }
    	return cardType;
    }
    /**
     * 实现：截取卡号的前六后四
     * @param cardNo 卡号
     * @return 截取后的卡号
     */
    public static String getCardNoPrix(String cardNo) {
    	String result = null;
    	if(cardNo!=null) {
    		if(cardNo.length() > 10) {
    			result = cardNo.substring(0,6)+"***"+cardNo.substring(cardNo.length()- 4, cardNo.length());
    		} else {
    			result = cardNo;
    		}
    	}
    	return result;
    }
    /**
     * 实现：验证邮箱的格式是否正确
     * @param str 邮箱
     * @return 验证结果
     */
    public static boolean isEmail(String str){
    	Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    	Matcher m = p.matcher(str);
    	return m.find();
    }
    /**
     * 实现：处理网址，去掉http://与www.
     * @param url
     * @return 网址
     */
    public static String getWebsite(String url) {
        String regEx = null;
        try {
            if (url.startsWith("https")) {
                regEx = url.split("//")[1].split("/")[0];//试一试"y\\"
            } else if (url.startsWith("http")) {
                regEx = url.split("//")[1].split("/")[0];//试一试"y\\"
            } else {
                regEx = url;
            }
            if(regEx.startsWith("www.")){
            	regEx=regEx.replace("www.", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return regEx;
    }
    
    /**
     * 实现：获取交易流水号
     * @return 流水号
     */
    public static String getTradeNo() {
    	String tradeNo = null;
    	SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
    	tradeNo = format.format(new Date()) + String.format("%1$01d", (int)(Math.random()*9));
    	do {
    		tradeNo = "FHT" + format.format(new Date()) + String.format("%1$01d", (int)(Math.random()*9));
        } while(linkeList.contains(tradeNo));
    	linkeList.push(tradeNo);
        if(linkeList.size()>20) {
        	linkeList.pollLast();
        }
    	return tradeNo;
    }
    
    /**
     * 实现：把银行帐户信息转换后返回
     * @param bank 银行账户信息
     * @return 转换后台的银行账户
     */
    /*public static BankForm getBankAccount(String bank) {
    	BankForm form = new BankForm();
    	form.setAccount("");
    	form.setPassword("");
    	return form;
    }*/
    /**
     * 实现：封装返回商户网店参数信息
     * @param trade 交易明细数据
     * @return 返回对象
     */
   /* public static Map<String,String> getReturnMerchantParam(TradeDetail trade, String md5Key) {
    	Map<String,String> retParam = new HashMap<String, String>();
		String md5String = MD5Encryption.getMD5Info(trade.getTradeNo()
				+ trade.getMerOrderNo() + md5Key + trade.getSucceed()
				+ trade.getBankInfo() + trade.getSourcecurrency()+trade.getSourceamount()).toUpperCase();
		retParam.put("tradeNo", trade.getTradeNo());
		retParam.put("orderNo", trade.getMerOrderNo());
		retParam.put("succeed", String.valueOf(trade.getSucceed()));
		retParam.put("bankInfo", trade.getBankInfo());
		retParam.put("currency", trade.getSourcecurrency());
		retParam.put("amount", String.valueOf(trade.getSourceamount()));
		retParam.put("md5String", md5String);
		// 后台返回支付结果
		httpClientReturn(trade.getReturnURL(), retParam);
		return retParam;
    }*/
    /**
     * 实现：使用HTTPCLIENT返回网店支付结果
     * @param trade 返回信息
     */
   /* public static int httpClientReturn(String url, Map<String, String> param) {
    	HttpClient httpClient = new HttpClient();
    	PostMethod postMethod = new PostMethod(url);
    	NameValuePair[] data = {
    		new NameValuePair("tradeNo", param.get("tradeNo")),
    		new NameValuePair("orderNo", param.get("orderNo")),
    		new NameValuePair("succeed", param.get("succeed")),
    		new NameValuePair("bankInfo", param.get("bankInfo")),
    		new NameValuePair("currency", param.get("currency")),
    		new NameValuePair("amount", param.get("amount")),
    		new NameValuePair("md5String", param.get("md5String"))};
    	postMethod.setRequestBody(data);
    	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
    	try {
			int statusCode = httpClient.executeMethod(postMethod);
			return statusCode;
		} catch (HttpException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
    }
    */
    /**
	 *获取HTTP客户端,使用代理方式
	 * @return
	 */
	public static DefaultHttpClient getHttpClient(String ipAddRess,int port) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpHost proxy = new HttpHost(ipAddRess, port);  
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy); 
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { xtm }, null);
		} catch (NoSuchAlgorithmException e1) {
		} catch (KeyManagementException e) {
		}
		// 创建SSLSocketFactory
		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		httpclient.getConnectionManager().getSchemeRegistry()
				.register(new Scheme("https", 443, socketFactory));
		return httpclient;
	}
    
    /**
	 *获取HTTP客户端
	 * @return
	 */
	public static DefaultHttpClient getHttpClient() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { xtm }, null);
		} catch (NoSuchAlgorithmException e1) {
		} catch (KeyManagementException e) {
		}
		// 创建SSLSocketFactory
		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		httpclient.getConnectionManager().getSchemeRegistry()
				.register(new Scheme("https", 443, socketFactory));
		return httpclient;
	}
    
	//以下是wrapClient方法

	/**
	* 获取可信任https链接，以避免不受信任证书出现peer not authenticated异常
	*
	* @param base
	* @return
	*/
//	public static HttpClient wrapClient(HttpClient base) {
//		try {
//			SSLContext sslContext = SSLContexts.custom().build();
//		    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
//		            new String[]{"TLSv1.2"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//		    PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager( RegistryBuilder.<ConnectionSocketFactory>create()
//		            .register("http", PlainConnectionSocketFactory.getSocketFactory())
//		            .register("https", sslConnectionSocketFactory)
//		            .build()
//		            );
//		    clientConnectionManager.setMaxTotal(100);
//	        clientConnectionManager.setDefaultMaxPerRoute(20);
//	        HttpClient client = HttpClients.custom().setConnectionManager(clientConnectionManager).build();
//	        return client;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return null;
//		}
//	}
	
	public static DefaultHttpClient getHttpClientTLSv12() {
		try {
			
			TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}
						public void checkClientTrusted(X509Certificate[] certs, String authType) {}
						public void checkServerTrusted(X509Certificate[] certs, String authType) {}
					}
				};
				SSLContext sc = SSLContext.getInstance("TLSv1.2");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				SSLSocketFactory socketFactory = new SSLSocketFactory(sc, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				DefaultHttpClient httpclient = new DefaultHttpClient();
				httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
	        return httpclient;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		HttpClient client=getHttpClientTLSv12();
//		client=wrapClient(client);
		HttpPost post = new HttpPost("https://www.baidu.com");
		try {
			client.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取银行的扩展信息
	 * @param key
	 * @param bankInfo
	 * @return
	 */
	/*public static String getOtherBankInfoByKey(String key, BankInfo bankInfo) {
		String otherStr = bankInfo.getOtherInfo();
		if (key == null || key.trim().equals("") || otherStr == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(key.trim());
		sb.append(">");
		sb.append("(.*)");
		sb.append("</");
		sb.append(key.trim());
		sb.append(">");

		Pattern p = Pattern.compile(sb.toString());
		Matcher m = p.matcher(otherStr);
		if (m.find()) {
			return m.group(1).trim();
		}
		return "";
	}*/
	/**
	 * 实现：随机生成持卡人查询码
	 * @return 查询码
	 */
	public static String getRandomQueryCode() {
		StringBuffer code = new StringBuffer();
		Random r = new Random();
		for(int i=0;i<10;i++){
			code.append(r.nextInt(10));
		}
		return code.toString();
	}
	
	public static Map<String,String> getCoutries(){
		Map<String,String> countryMap = new TreeMap<String, String>();
		countryMap.put("AF", "AFGHANISTAN");
		countryMap.put("AX", "ALAND ISLANDS");
		countryMap.put("AL", "ALBANIA");
		countryMap.put("DZ", "ALGERIA");
		countryMap.put("AS", "AMERICAN SAMOA");
		countryMap.put("AD", "ANDORRA");
		countryMap.put("AO", "ANGOLA");
		countryMap.put("AI", "ANGUILLA");
		countryMap.put("AQ", "ANTARCTICA");
		countryMap.put("AG", "ANTIGUA AND BARBUDA");
		countryMap.put("AR", "ARGENTINA");
		countryMap.put("AM", "ARMENIA");
		countryMap.put("AW", "ARUBA");
		countryMap.put("AU", "AUSTRALIA");
		countryMap.put("AT", "AUSTRIA");
		countryMap.put("AZ", "AZERBAIJAN");
		countryMap.put("BS", "BAHAMAS");
		countryMap.put("BH", "BAHRAIN");
		countryMap.put("BD", "BANGLADESH");
		countryMap.put("BB", "BARBADOS");
		countryMap.put("BY", "BELARUS");
		countryMap.put("BE", "BELGIUM");
		countryMap.put("BZ", "BELIZE");
		countryMap.put("BJ", "BENIN");
		countryMap.put("BM", "BERMUDA");
		countryMap.put("BT", "BHUTAN");
		countryMap.put("BO", "BOLIVIA");
		countryMap.put("BA", "BOSNIA AND HERZEGOVINA");
		countryMap.put("BW", "BOTSWANA");
		countryMap.put("BV", "BOUVET ISLAND");
		countryMap.put("BR", "BRAZIL");
		countryMap.put("IO", "BRITISH INDIAN OCEAN TERRITORY");
		countryMap.put("BN", "BRUNEI DARUSSALAM");
		countryMap.put("BG", "BULGARIA");
		countryMap.put("BF", "BURKINA FASO");
		countryMap.put("BI", "BURUNDI");
		countryMap.put("KH", "CAMBODIA");
		countryMap.put("CM", "CAMEROON");
		countryMap.put("CA", "CANADA");
		countryMap.put("CV", "CAPE VERDE");
		countryMap.put("KY", "CAYMAN ISLANDS");
		countryMap.put("CF", "CENTRAL AFRICAN REPUBLIC");
		countryMap.put("TD", "CHAD");
		countryMap.put("CL", "CHILE");
		countryMap.put("CN", "CHINA");
		countryMap.put("CX", "CHRISTMAS ISLAND");
		countryMap.put("CC", "COCOS (KEELING) ISLANDS");
		countryMap.put("CO", "COLOMBIA");
		countryMap.put("KM", "COMOROS");
		countryMap.put("CG", "CONGO");
		countryMap.put("CD", "CONGO, THE DEMOCRATIC REPUBLIC OF THE");
		countryMap.put("CK", "COOK ISLANDS");
		countryMap.put("CR", "COSTA RICA");
		countryMap.put("CI", "COTE D'IVOIRE");
		countryMap.put("HR", "CROATIA");
		countryMap.put("CU", "CUBA");
		countryMap.put("CY", "CYPRUS");
		countryMap.put("CZ", "CZECH REPUBLIC");
		countryMap.put("DK", "DENMARK");
		countryMap.put("DJ", "DJIBOUTI");
		countryMap.put("DM", "DOMINICA");
		countryMap.put("DO", "DOMINICAN REPUBLIC");
		countryMap.put("EC", "ECUADOR");
		countryMap.put("EG", "EGYPT");
		countryMap.put("SV", "EL SALVADOR");
		countryMap.put("GQ", "EQUATORIAL GUINEA");
		countryMap.put("ER", "ERITREA");
		countryMap.put("EE", "ESTONIA");
		countryMap.put("ET", "ETHIOPIA");
		countryMap.put("FK", "FALKLAND ISLANDS (MALVINAS)");
		countryMap.put("FO", "FAROE ISLANDS");
		countryMap.put("FJ", "FIJI");
		countryMap.put("FI", "FINLAND");
		countryMap.put("FR", "FRANCE");
		countryMap.put("GF", "FRENCH GUIANA");
		countryMap.put("PF", "FRENCH POLYNESIA");
		countryMap.put("TF", "FRENCH SOUTHERN TERRITORIES");
		countryMap.put("GA", "GABON");
		countryMap.put("GM", "GAMBIA");
		countryMap.put("GE", "GEORGIA");
		countryMap.put("DE", "GERMANY");
		countryMap.put("GH", "GHANA");
		countryMap.put("GI", "GIBRALTAR");
		countryMap.put("GR", "GREECE");
		countryMap.put("GL", "GREENLAND");
		countryMap.put("GD", "GRENADA");
		countryMap.put("GP", "GUADELOUPE");
		countryMap.put("GU", "GUAM");
		countryMap.put("GT", "GUATEMALA");
		countryMap.put("GG", "GUERNSEY");
		countryMap.put("GN", "GUINEA");
		countryMap.put("GW", "GUINEA-BISSAU");
		countryMap.put("GY", "GUYANA");
		countryMap.put("HT", "HAITI");
		countryMap.put("HM", "HEARD ISLAND AND MCDONALD ISLANDS");
		countryMap.put("VA", "HOLY SEE (VATICAN CITY STATE)");
		countryMap.put("HN", "HONDURAS");
		countryMap.put("HK", "HONG KONG");
		countryMap.put("HU", "HUNGARY");
		countryMap.put("IS", "ICELAND");
		countryMap.put("IN", "INDIA");
		countryMap.put("ID", "INDONESIA");
		countryMap.put("IR", "IRAN, ISLAMIC REPUBLIC OF");
		countryMap.put("IQ", "IRAQ");
		countryMap.put("IE", "IRELAND");
		countryMap.put("IM", "ISLE OF MAN");
		countryMap.put("IL", "ISRAEL");
		countryMap.put("IT", "ITALY");
		countryMap.put("JM", "JAMAICA");
		countryMap.put("JP", "JAPAN");
		countryMap.put("JE", "JERSEY");
		countryMap.put("JO", "JORDAN");
		countryMap.put("KZ", "KAZAKHSTAN");
		countryMap.put("KE", "KENYA");
		countryMap.put("KI", "KIRIBATI");
		countryMap.put("KP", "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF");
		countryMap.put("KR", "KOREA, REPUBLIC OF");
		countryMap.put("KW", "KUWAIT");
		countryMap.put("KG", "KYRGYZSTAN");
		countryMap.put("LA", "LAO PEOPLE'S DEMOCRATIC REPUBLIC");
		countryMap.put("LV", "LATVIA");
		countryMap.put("LB", "LEBANON");
		countryMap.put("LS", "LESOTHO");
		countryMap.put("LR", "LIBERIA");
		countryMap.put("LY", "LIBYAN ARAB JAMAHIRIYA");
		countryMap.put("LI", "LIECHTENSTEIN");
		countryMap.put("LT", "LITHUANIA");
		countryMap.put("LU", "LUXEMBOURG");
		countryMap.put("MO", "MACAO");
		countryMap.put("MK", "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF  ");
		countryMap.put("MG", "MADAGASCAR");
		countryMap.put("MW", "MALAWI");
		countryMap.put("MY", "MALAYSIA");
		countryMap.put("MV", "MALDIVES");
		countryMap.put("ML", "MALI");
		countryMap.put("MT", "MALTA");
		countryMap.put("MH", "MARSHALL ISLANDS");
		countryMap.put("MQ", "MARTINIQUE");
		countryMap.put("MR", "MAURITANIA");
		countryMap.put("MU", "MAURITIUS");
		countryMap.put("YT", "MAYOTTE");
		countryMap.put("MX", "MEXICO");
		countryMap.put("FM", "MICRONESIA, FEDERATED STATES OF");
		countryMap.put("MD", "MOLDOVA, REPUBLIC OF");
		countryMap.put("MC", "MONACO");
		countryMap.put("MN", "MONGOLIA");
		countryMap.put("ME", "MONTENEGRO");
		countryMap.put("MS", "MONTSERRAT");
		countryMap.put("MA", "MOROCCO");
		countryMap.put("MZ", "MOZAMBIQUE");
		countryMap.put("MM", "MYANMAR");
		countryMap.put("NA", "NAMIBIA");
		countryMap.put("NR", "NAURU");
		countryMap.put("NP", "NEPAL");
		countryMap.put("NL", "NETHERLANDS");
		countryMap.put("AN", "NETHERLANDS ANTILLES");
		countryMap.put("NC", "NEW CALEDONIA");
		countryMap.put("NZ", "NEW ZEALAND");
		countryMap.put("NI", "NICARAGUA");
		countryMap.put("NE", "NIGER");
		countryMap.put("NG", "NIGERIA");
		countryMap.put("NU", "NIUE");
		countryMap.put("NF", "NORFOLK ISLAND");
		countryMap.put("MP", "NORTHERN MARIANA ISLANDS");
		countryMap.put("NO", "NORWAY");
		countryMap.put("OM", "OMAN");
		countryMap.put("PK", "PAKISTAN");
		countryMap.put("PW", "PALAU");
		countryMap.put("PS", "PALESTINIAN TERRITORY, OCCUPIED");
		countryMap.put("PA", "PANAMA");
		countryMap.put("PG", "PAPUA NEW GUINEA");
		countryMap.put("PY", "PARAGUAY");
		countryMap.put("PE", "PERU");
		countryMap.put("PH", "PHILIPPINES");
		countryMap.put("PN", "PITCAIRN");
		countryMap.put("PL", "POLAND");
		countryMap.put("PT", "PORTUGAL");
		countryMap.put("PR", "PUERTO RICO");
		countryMap.put("QA", "QATAR");
		countryMap.put("RE", "REUNION");
		countryMap.put("RO", "ROMANIA");
		countryMap.put("RU", "RUSSIAN FEDERATION");
		countryMap.put("RW", "RWANDA");
		countryMap.put("SH", "SAINT HELENA");
		countryMap.put("KN", "SAINT KITTS AND NEVIS");
		countryMap.put("LC", "SAINT LUCIA");
		countryMap.put("PM", "SAINT PIERRE AND MIQUELON");
		countryMap.put("VC", "SAINT VINCENT AND THE GRENADINES");
		countryMap.put("WS", "SAMOA");
		countryMap.put("SM", "SAN MARINO");
		countryMap.put("ST", "SAO TOME AND PRINCIPE");
		countryMap.put("SA", "SAUDI ARABIA");
		countryMap.put("SN", "SENEGAL");
		countryMap.put("RS", "SERBIA");
		countryMap.put("SC", "SEYCHELLES");
		countryMap.put("SL", "SIERRA LEONE");
		countryMap.put("SG", "SINGAPORE");
		countryMap.put("SK", "SLOVAKIA");
		countryMap.put("SI", "SLOVENIA");
		countryMap.put("SB", "SOLOMON ISLANDS");
		countryMap.put("SO", "SOMALIA");
		countryMap.put("ZA", "SOUTH AFRICA");
		countryMap.put("GS", "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS");
		countryMap.put("ES", "SPAIN");
		countryMap.put("LK", "SRI LANKA");
		countryMap.put("SD", "SUDAN");
		countryMap.put("SR", "SURINAME");
		countryMap.put("SJ", "SVALBARD AND JAN MAYEN");
		countryMap.put("SZ", "SWAZILAND");
		countryMap.put("SE", "SWEDEN");
		countryMap.put("CH", "SWITZERLAND");
		countryMap.put("SY", "SYRIAN ARAB REPUBLIC");
		countryMap.put("TW", "TAIWAN");
		countryMap.put("TJ", "TAJIKISTAN");
		countryMap.put("TZ", "TANZANIA, UNITED REPUBLIC OF");
		countryMap.put("TH", "THAILAND");
		countryMap.put("TL", "TIMOR-LESTE");
		countryMap.put("TG", "TOGO");
		countryMap.put("TK", "TOKELAU");
		countryMap.put("TO", "TONGA");
		countryMap.put("TT", "TRINIDAD AND TOBAGO");
		countryMap.put("TN", "TUNISIA");
		countryMap.put("TR", "TURKEY");
		countryMap.put("TM", "TURKMENISTAN");
		countryMap.put("TC", "TURKS AND CAICOS ISLANDS");
		countryMap.put("TV", "TUVALU");
		countryMap.put("UG", "UGANDA");
		countryMap.put("UA", "UKRAIN");
		countryMap.put("AE", "UNITED ARAB EMIRATES");
		countryMap.put("GB", "UNITED KINGDOM");
		countryMap.put("US", "UNITED STATES");
		countryMap.put("UM", "UNITED STATES MINOR OUTLYING ISLANDS");
		countryMap.put("UY", "URUGUAY");
		countryMap.put("UZ", "UZBEKISTAN");
		countryMap.put("VU", "VANUATU");
		countryMap.put("VE", "VENEZUELA");
		countryMap.put("VN", "VIET NAM");
		countryMap.put("VG", "VIRGIN ISLANDS, BRITISH");
		countryMap.put("VI", "VIRGIN ISLANDS, U.S.");
		countryMap.put("WF", "WALLIS AND FUTUNA");
		countryMap.put("EH", "WESTERN SAHARA");
		countryMap.put("YE", "YEMEN");
		countryMap.put("ZM", "ZAMBIA");
		countryMap.put("ZW", "ZIMBABWE");	
		return countryMap;
	}
	/**
	 * 实现：将返回结果参数解析成map
	 * @param str 返回结果参数
	 * @return 结果map
	 */
	public static Map<String, String> convertStr(String str) {
		Map<String, String> map = new HashMap<String, String>();
		String[] array = str.split("&");
		for(int i=0;i<array.length;i++) {
			String[] args = array[i].split("=");
			if(args.length>0) {
				if(args.length==2) {
					map.put(args[0], args[1]);
				} else {
					map.put(args[0], "");
				}
			}
		}
		return map;
	}
	public static String getRandomWebsite() {
		Random rand = new Random();
		Long key = Long.valueOf((rand.nextInt(5)+1));
		Map<Long, String> map = new HashMap<Long, String>();
		map.put(1L, "wesieify.com");
		map.put(2L, "refineyours.com");
		map.put(3L, "thingserss.com");
//		map.put(4L, "voluertey.com");
		map.put(4L, "wesieify.com");
		map.put(5L, "enjoy-shopp.com");
		return map.get(key);
	}
}