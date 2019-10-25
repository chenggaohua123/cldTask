package com.fhtpay.task.service.messageservice;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.core.task.TaskExecutor;

import com.fhtpay.common.exception.BusinessException;
import com.fhtpay.task.model.warn.BankCurrencyInfo;
import com.fhtpay.task.model.warn.MessageInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.service.emailservice.EmailSendImpl;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class MessageSendImpl {
	
	private static Log logger = LogFactory.getLog(EmailSendImpl.class);
	
	public static String sendUrl = "http://42.121.125.79:360/sms.aspx";
	public static String LANZUrl = "http://dxjk.51lanz.com/LANZGateway/DirectSendSMSs.asp";
	public static String LANZUserId = "868628";
	public static String LANZAccount = "fuhuitong";
	public static String LANZPassWord = "E57D3DCCD827FE45A65B6DD6FAD5B2D1157189A1";
	
	private TaskExecutor taskExecutor;
	
	private TransactionManageDao transactionManageDao;
	
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	public void threadSendMessage(final MessageInfo messageInfo) {
		taskExecutor.execute(new Runnable(){
			public void run(){
				try {
					sendMessage(messageInfo);
				} catch(BusinessException e) {
					logger.info(e.getMessage());
				}
			}
		});
	}
	
	public void sendMessage(MessageInfo messageInfo){
		sendLANZSMS(LANZUserId, LANZAccount, LANZPassWord, messageInfo.getMobile(), messageInfo.getContent(), "", "", "");
	}
	
	public static void main(String[] args) {
		MessageSendImpl mes = new MessageSendImpl();
		TransWarnInfo warn = new TransWarnInfo();
		warn.setActiveTime("4");
		warn.setBankId("12");
		warn.setBankName("中行");
		warn.setCurrencyId("14");
		warn.setCycle("6");
		warn.setEmails("likun@sina.com");
		warn.setId("2");
		warn.setRespMsg("failed");
		warn.setTime("2");
		warn.setType("0");
		BankCurrencyInfo currency = new BankCurrencyInfo();
		currency.setCurrencyName("A23");
		currency.setId("14");
//		String message = mes.getMessageInfo("蓝付-交易预警:{currencyName}通道在{activeTime}内有{time}订单同一原因交易失败了【数风朝科技】", warn, currency);
		String message = mes.getMessageInfo("请注意：交易预警【铂赢奥】", warn, currency);
		System.out.println(message);
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setMobile("13128854459");
		messageInfo.setContent(message);
		mes.sendMessage(messageInfo);
	}
	
	public String getDefaultMessage(TransWarnInfo warn){
		StringBuffer sb = new StringBuffer();
		sb.append("FHT交易预警:");
		if(warn.getMerNo()!=null && !("".equals(warn.getMerNo()))){
			sb.append("商户号为{merNo}");
			if("0".equals(warn.getType())){
				sb.append("的商户在{activeTime}分钟里已有{time}笔订单交易失败,失败原因{respMsg}");
			}else if("1".equals(warn.getType())){
				sb.append("的商户在{activeTime}分钟里已有{time}笔订单连续交易失败");
			}else if("2".equals(warn.getType())){
				sb.append("的商户已有{activeTime}分钟没有交易订单了");
			}else if("3".equals(warn.getType())){
				sb.append("的商户在{activeTime}分钟里已有{time}笔订单为高风险订单交易");
			}
		}else{
			sb.append("{bankName}");
			if("0".equals(warn.getType())){
				sb.append("通道在{activeTime}分钟里已有{time}笔订单交易失败,失败原因{respMsg}");
			}else if("1".equals(warn.getType())){
				sb.append("通道在{activeTime}分钟里已有{time}笔订单连续交易失败");
			}else if("2".equals(warn.getType())){
				sb.append("通道已有{activeTime}分钟没有交易订单了");
			}else if("3".equals(warn.getType())){
				sb.append("通道在{activeTime}分钟里已有{time}笔订单为高风险订单交易");
			}
		}
		sb.append("【铂赢奥】");
		return sb.toString();
	}
	
	/**
	 * 重新包装短信内容
	 */
	public String getMessage(String message, TransWarnInfo warn, BankCurrencyInfo currency){
		StringBuffer sb = new StringBuffer();
		String content = getMessageInfo(message, warn, currency);
		int inde = content.lastIndexOf("【");
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//		sb.append(content.substring(0, inde)).append(",异常时间:").append(format.format(new Date())).append(content.substring(inde, content.length()));
		sb.append("BR交易预警:").append(content.substring(content.indexOf(":")+1, inde)).append(",异常时间:").append(format.format(new Date())).append("【铂赢奥】");
		return sb.toString();
	}
	
	/**
	 * 短信内容
	 */
	public String getMessageInfo(String message, TransWarnInfo warn, BankCurrencyInfo currency){
		StringBuffer sb = new StringBuffer();
		try{
			int spliIndex1 = message.indexOf("{");
			int spliIndex2 = message.indexOf("}");
			if(spliIndex1>0){
				String str = message.substring(0, spliIndex1);
				logger.info("内容:"+str);
				String content1 = getTransWarnValues(message.substring(spliIndex1+1, spliIndex2), warn, currency);
				logger.info(message.substring(spliIndex1+1, spliIndex2)+"获得的信息:"+content1);
				sb.append(str).append(content1);
			}
			if(spliIndex1==0){
				String content2 = getTransWarnValues(message.substring(spliIndex1+1, spliIndex2), warn, currency);
				logger.info(message.substring(spliIndex1+1, spliIndex2)+"获得的信息:"+content2);
				sb.append(content2);
			}
			if(!(spliIndex2>0)){
				logger.info("没有匹配字符返回信息:"+message);
				return message;
			}
			String repMes = getMessageInfo(message.substring(spliIndex2+1, message.length()), warn, currency);
			sb.append(repMes);
			logger.info("获取内容:"+sb.toString());
		}catch(Exception e){
			logger.info(e.getMessage());
			logger.info("获取短信信息异常");
		}
		return sb.toString();
	}
	
	/**
	 * 获取值
	 */
	@SuppressWarnings("rawtypes")
	public static String getTransWarnValues(String name, TransWarnInfo warn, BankCurrencyInfo currency) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String upName = name;
		String methodName = "get"+upName.replaceFirst(upName.substring(0, 1), name.toUpperCase().substring(0, 1));
		if(warn!=null){
			Class warnClass = warn.getClass();
			Method[] warnMethods = warnClass.getMethods();
			if(null!=warnMethods && warnMethods.length>0){
				for(Method method : warnMethods){
					if(method.getName()!=null){
						if(method.getName().equals(methodName)){
							return (String) method.invoke(warn, new Class[]{});
						}
					}
				}
			}
		}
		
		//通道取值
//		Class currencyClass = currency.getClass();
//		Method[] currencyMethods = currencyClass.getMethods();
//		if(null!=currencyMethods && currencyMethods.length>0){
//			for(Method method : currencyMethods){
//				if(method.getName()!=null){
//					if(method.getName()!=null){
//						if(method.getName().equals(methodName)){
//							return (String) method.invoke(currency, new Class[]{});
//						}
//					}
//				}
//			}
//		}
		return null;
	}
	
	/**
	 * 发送短信
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
