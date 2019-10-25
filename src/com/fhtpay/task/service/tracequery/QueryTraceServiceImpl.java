package com.fhtpay.task.service.tracequery;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fhtpay.common.Tools;
import com.fhtpay.task.model.trace.ems.DestCpList;
import com.fhtpay.task.model.trace.ems.DetailList;
import com.fhtpay.task.model.trace.ems.EMSTraceInfo;
import com.fhtpay.task.model.trace.ems.LatestTrackingInfo;
import com.fhtpay.task.model.trace.ems.OriginCpListInfo;
import com.fhtpay.task.model.trace.ems.Section1;
import com.fhtpay.task.model.trace.ems.Section2;

public class QueryTraceServiceImpl {
	
	private static Log logger = LogFactory.getLog(QueryTraceServiceImpl.class);
	
	private static String QUERYEMSURL = "https://slw16.global.cainiao.com/trackSyncQueryRpc/queryAllLinkTrace.json?";
	
	private static String QUERYEMSPARAM1 = "callback=jQuery181024272202060587356_1480908003576";
	
	private static String QUERYEMSPARAM2 = "&originCp=POSTEYB";
	
	public static Map<String, String> EMSSTATEMAP = new HashMap<String, String>();
	
	public static Map<String, Integer> EMSSTATUSMAP = new HashMap<String, Integer>();
	
	public static HashMap<String, String> DATEREG = new HashMap<String, String>();
	
	static {
		EMSSTATEMAP.put("PICKEDUP", "揽收");
		EMSSTATEMAP.put("SHIPPING", "运输中");
		EMSSTATEMAP.put("DEPART_FROM_ORIGINAL_COUNTRY", "离开发件国");
		EMSSTATEMAP.put("ARRIVED_AT_DEST_COUNTRY", "到达目的国");
		EMSSTATEMAP.put("SIGNIN", "妥投");
		EMSSTATEMAP.put("WAIT4PICKUP", "待揽收");
		EMSSTATEMAP.put("WAIT4SIGNIN", "到达待取");
		EMSSTATEMAP.put("ORDER_NOT_EXISTS", "查询不到");
		EMSSTATEMAP.put("SIGNIN_EXC", "妥投失败");
		EMSSTATEMAP.put("RETURN", "包裹退回");
		EMSSTATEMAP.put("SHIPPING_OVER_TIME", "运输过久");
		EMSSTATEMAP.put("DEPART_FROM_ORIGINAL_COUNTRY_EXC", "交航失败");
		EMSSTATEMAP.put("ARRIVED_AT_DEST_COUNTRY_EXC", "清关失败");
		EMSSTATEMAP.put("NOT_LAZADA_ORDER", "Not Found");
		
		EMSSTATUSMAP.put("SIGNIN", 1);
		
		DATEREG.put(
				"^\\d{4}-{1}\\d{2}-{1}\\d{2}\\s{1}\\d{2}:{1}\\d{2}:{1}\\d{2}$",
				"yyyy-MM-dd HH:mm:ss");
		DATEREG.put(
				"^\\d{4}/{1}\\d{2}/{1}\\d{2}\\s{1}\\d{2}:{1}\\d{2}:{1}\\d{2}$",
				"yyyy/MM/dd HH:mm:ss");
	}

	public EMSTraceInfo queryEMSTraceInfo(String traceNo){
		try {
			logger.info("查询物流信息单号为:"+traceNo);
			DefaultHttpClient httpClient = Tools.getHttpClient();
			HttpGet get = new HttpGet(QUERYEMSURL+QUERYEMSPARAM1+"&mailNo="+traceNo+QUERYEMSPARAM2);
			HttpResponse response = httpClient.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			logger.info("查询订单:"+traceNo+"的物流结果为:"+result);
			return jsonToBeanOfEMS(formatterResult(result));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.info("查询订单:"+traceNo+"HTTPS链接异常");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("查询订单:"+traceNo+"HTTPSIO异常");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("将查询结果的JSON转换为javabean失败");
		}
		logger.info("查询订单:"+traceNo+"信息失败");
		return null;
	}
	
	public String formatterResult(String result){
		return result.substring(result.indexOf("(")+1, result.indexOf(")")).replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("【", "").replaceAll("】", "");
	}
	
	@SuppressWarnings({ "static-access", "rawtypes" })
	public EMSTraceInfo jsonToBeanOfEMS(String result){
		try {
			JSONObject jsonObject = new JSONObject().fromObject(result);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("destCpList", DestCpList.class);
			classMap.put("latestTrackingInfo", LatestTrackingInfo.class);
			classMap.put("originCpList", OriginCpListInfo.class);
			classMap.put("section1", Section1.class);
			classMap.put("section2", Section2.class);
			classMap.put("detailList", DetailList.class);
			EMSTraceInfo traceInfo = (EMSTraceInfo) JSONObject.toBean(jsonObject, EMSTraceInfo.class, classMap);
			logger.info("转换为JAVABEAN的结果:"+traceInfo.toString());
			return traceInfo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("将查询结果的JSON转换为javabean失败");
		}
		return null;
	}
	
	public static Map<String, String> getEMSSTATEMAP() {
		return EMSSTATEMAP;
	}

	public static void setEMSSTATEMAP(Map<String, String> eMSSTATEMAP) {
		EMSSTATEMAP = eMSSTATEMAP;
	}
	
	public Timestamp formatterDate(String date) throws ParseException{
		if(date!=null && !("".equals(date))){
			Set<String> keys = DATEREG.keySet();
			for(String key : keys){
				Pattern pattern = Pattern.compile(key);
				Matcher matcher = pattern.matcher(date);
				if(matcher.matches()){
					SimpleDateFormat sdf = new SimpleDateFormat(DATEREG.get(key));
					return new Timestamp(sdf.parse(date).getTime());
				}
			}
		}
		return null;
	}
	
}
