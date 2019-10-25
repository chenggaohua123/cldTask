package com.fhtpay.common;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * jsonp解析工具
 * @author honghao
 * @version 创建时间：2019年7月18日  下午12:14:45
 */
public class JSONPParserUtil {

	public static Map<?, ?> parseJSONP(String jsonp) {
		if(StringUtils.isEmpty(jsonp)){
			return null;
		}
		int startIndex = jsonp.indexOf("(");
		int endIndex = jsonp.lastIndexOf(")");
		String json = jsonp.substring(startIndex + 1, endIndex);
		return JSON.parseObject(json);
	}

	public static void main(String[] args) {
		String jsonp = "jpCallback({\"respMsg\":\"The order don't exist!\",\"respCode\":03})";
		Map<?, ?> map = parseJSONP(jsonp);
		System.out.println(map);
		System.out.println(parseJSONP(null));
	}

}