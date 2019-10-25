package com.fhtpay.task.model.warn;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class SMSSendUtil {
	
	private static String sendUrl = "http://42.121.125.79:360/sms.aspx";
	private static String LANZUrl = "http://www.lanz.net.cn/LANZGateway/DirectSendSMSs.asp";
	private static Log logger = LogFactory.getLog(SMSSendUtil.class);
	
	/**
	 * 发送短信
	 * @param userid
	 * @param account
	 * @param password
	 * @param mobile
	 * @param content
	 * @param sendTime
	 * @param action
	 * @param extno
	 */
	public static void sendSMS(String userid, String account, String password,
			String mobile, String content, String sendTime, String action,
			String extno) {
		content+="【万开网络】";
		DefaultHttpClient httpclient = getHttpClient();
		HttpPost post = new HttpPost(sendUrl);
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("userid",userid));
        nvps.add(new BasicNameValuePair("account", account));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("mobile", mobile));
        nvps.add(new BasicNameValuePair("content", content));
        nvps.add(new BasicNameValuePair("sendTime", sendTime));
        nvps.add(new BasicNameValuePair("action", action));
        nvps.add(new BasicNameValuePair("extno", extno));
        post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        HttpResponse response;
		try {
			response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			String respXML = EntityUtils.toString(response.getEntity());
			logger.info("返回状态吗："+statusLine);
			logger.info("返回参数："+respXML);
			Map<String, String> respMap=  parseXmlResult(respXML);
			if(null != respMap && null != respMap.get("returnstatus")){
				String status = respMap.get("returnstatus");
				if("Success".equals(status)){
					logger.info("短信发送内容："+content);
					logger.info("短信发送成功，发送号码为："+mobile);
				}else{
					logger.info("短信发送异常，发送号码为："+mobile+"错误信息为："+respMap.get("message"));
				}
				
			}else{
				logger.info("短信发送异常，发送号码为："+mobile);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送短信
	 * @param userid
	 * @param account
	 * @param password
	 * @param mobile
	 * @param content
	 * @param sendDate
	 * @param sendTime
	 * @param postFixNum
	 */
	public static void sendLANZSMS(String userid, String account, String password,
			String mobile, String content, String sendDate, String sendTime,
			String postFixNum) {
//		content+="【数风朝】";
		DefaultHttpClient httpclient = getHttpClient();
		HttpPost post = new HttpPost(LANZUrl);
		post.addHeader("Content-Type","application/x-www-form-urlencoded;charset=GB2312");
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("UserID",userid));
        nvps.add(new BasicNameValuePair("Account", account));
        nvps.add(new BasicNameValuePair("Password", password));
        nvps.add(new BasicNameValuePair("Phones", mobile));
        nvps.add(new BasicNameValuePair("Content",content));
        nvps.add(new BasicNameValuePair("SendDate", sendDate));
        nvps.add(new BasicNameValuePair("SendTime", sendTime));
        nvps.add(new BasicNameValuePair("PostFixNum", postFixNum));
        nvps.add(new BasicNameValuePair("SMSType", "1"));
        try {
			post.setEntity(new UrlEncodedFormEntity(nvps,"GB2312"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        
        HttpResponse response;
		try {
			response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			String respXML = EntityUtils.toString(response.getEntity());
			logger.info("返回状态吗："+statusLine);
			logger.info("返回参数："+respXML);
			Map<String, String> respMap=  parseLANZXmlResult(respXML);
			if(null != respMap && null != respMap.get("ErrorNum")){
				String status = respMap.get("ErrorNum");
				if("0".equals(status)){
					logger.info("短信发送内容："+content);
					logger.info("短信发送成功，发送号码为："+respMap.get("PhonesSend"));
					logger.info("短信拒绝发送号码为："+respMap.get("ErrPhones"));
				}else{
					logger.info("短信发送异常，发送号码为："+mobile+"错误信息为："+respMap.get("message"));
				}
				
			}else{
				logger.info("短信发送异常，发送号码为："+mobile);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *获取HTTP客户端
	 * @return
	 */
	private static DefaultHttpClient getHttpClient() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		X509TrustManager xtm = new X509TrustManager(){    
	             public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
	             public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
	             public X509Certificate[] getAcceptedIssuers() { return null; } 
	     };
	     
	    //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext 
         SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[]{xtm}, null); 
		} catch (NoSuchAlgorithmException e1) {
		} catch (KeyManagementException e) {
		} 
         //创建SSLSocketFactory 
         SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
          
         httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
		return httpclient;
	}
	
	/**
	 * 解析返回数据
	 * @param xmlResult
	 * @return
	 */
	private static Map<String, String> parseXmlResult(String xmlResult){
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		Map<String, String> respMap = new HashMap<String, String>();
		try {
			doc = builder.build(new StringReader(xmlResult));
			Element root = doc.getRootElement();
			respMap.put("returnstatus",root.getChild("returnstatus") != null? root.getChild("returnstatus").getText():"");
			respMap.put("message",root.getChild("message") != null? root.getChild("message").getText():"");
			return respMap;
		}catch(IOException e){
			
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析返回数据
	 * @param xmlResult
	 * @return
	 */
	private static Map<String, String> parseLANZXmlResult(String xmlResult){
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		Map<String, String> respMap = new HashMap<String, String>();
		try {
			doc = builder.build(new StringReader(xmlResult));
			Element root = doc.getRootElement();
			respMap.put("ErrorNum",root.getChild("ErrorNum") != null? root.getChild("ErrorNum").getText():"");
			respMap.put("PhonesSend",root.getChild("PhonesSend") != null? root.getChild("PhonesSend").getText():"");
			respMap.put("ErrPhones",root.getChild("ErrPhones") != null? root.getChild("ErrPhones").getText():"");
			return respMap;
		}catch(IOException e){
			
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return null;
	}
}
