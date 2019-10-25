package com.fhtpay.task.service.emailservice.warn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.task.model.warn.BankCurrencyInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;

public class EmmailUtils {
	
	private static Log logger = LogFactory.getLog(EmmailUtils.class);
	
	public String getDefaultMessage(TransWarnInfo warn){
		StringBuffer sb = new StringBuffer();
		sb.append("铂赢奥-交易预警:");
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
		sb.append("【数风朝科技】");
		return sb.toString();
	}

	public String getMessage(String message, TransWarnInfo warn, BankCurrencyInfo currency){
		StringBuffer sb = new StringBuffer();
		String content = getMessageInfo(message, warn, currency);
		int inde = content.lastIndexOf("【");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append(content.substring(0, inde)).append(",异常时间:").append(format.format(new Date()));
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
	
}
