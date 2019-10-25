package com.fhtpay.task.model;

import java.sql.Timestamp;

public class ThreatMetrixResultInfo {
	private String id;
	private String tradeNo;
	private String merNo;
	private String terNo;
	private String account_email;
	private String account_email_first_seen;
	private String account_email_last_event;
	private String account_email_last_update;
	private String account_email_result;
	private String account_email_score;
	private String account_email_worst_score;
	private String account_name;
	private String account_name_first_seen;
	private String account_name_last_event;
	private String account_name_last_update;
	private String account_name_result;
	private String account_name_score;
	private String account_name_worst_score;
	private String account_telephone;
	private String account_telephone_first_seen;
	private String account_telephone_last_event;
	private String account_telephone_last_update;
	private String account_telephone_result;
	private String account_telephone_score;
	private String account_telephone_worst_score;
	private String agent_type;
	private String api_call_datetime;
	private String api_version;
	private String browser;
	private String browser_language;
	private String browser_string;
	private String browser_string_hash;
	private String browser_version;
	private String cc_bin_number;
	private String cc_bin_number_brand;
	private String cc_bin_number_category;
	private String cc_bin_number_geo;
	private String cc_bin_number_org;
	private String cc_bin_number_type;
	private String css_image_loaded;
	private String detected_fl;
	private String device_first_seen;
	private String device_id;
	private String device_id_confidence;
	private String device_last_event;
	private String device_last_update;
	private String device_match_result;
	private String device_result;
	private String device_score;
	private String device_worst_score;
	private String dns_ip;
	private String dns_ip_city;
	private String dns_ip_geo;
	private String dns_ip_isp;
	private String dns_ip_latitude;
	private String dns_ip_longitude;
	private String dns_ip_organization;
	private String dns_ip_region;
	private String enabled_ck;
	private String enabled_fl;
	private String enabled_im;
	private String enabled_js;
	private String event_type;
	private String flash_lang;
	private String flash_os;
	private String flash_version;
	private String fuzzy_device_first_seen;
	private String fuzzy_device_id;
	private String fuzzy_device_id_confidence;
	private String fuzzy_device_last_event;
	private String fuzzy_device_last_update;
	private String fuzzy_device_match_result;
	private String fuzzy_device_result;
	private String fuzzy_device_score;
	private String fuzzy_device_worst_score;
	private String headers_name_value_hash;
	private String headers_order_string_hash;
	private String honeypot_fingerprint;
	private String honeypot_fingerprint_check;
	private String honeypot_fingerprint_match;
	private String http_referer;
	private String http_referer_domain;
	private String http_referer_url;
	private String image_loaded;
	private String input_ip_address;
	private String input_ip_city;
	private String input_ip_first_seen;
	private String input_ip_geo;
	private String input_ip_isp;
	private String input_ip_last_event;
	private String input_ip_last_update;
	private String input_ip_latitude;
	private String input_ip_longitude;
	private String input_ip_organization;
	private String input_ip_region;
	private String input_ip_result;
	private String input_ip_score;
	private String js_browser;
	private String js_browser_string;
	private String js_browser_string_hash;
	private String js_fonts_hash;
	private String js_fonts_number;
	private String js_os;
	private String mime_type_hash;
	private String mime_type_number;
	private String org_id;
	private String os;
	private String os_fonts_hash;
	private String os_fonts_number;
	private String os_version;
	private String page_time_on;
	private String plugin_flash;
	private String plugin_hash;
	private String plugin_number;
	private String plugin_windows_media_player;
	private String policy;
	private String policy_score;
	private String profiled_domain;
	private String profiled_url;
	private String profiling_datetime;
	private String reason_code;
	private String request_id;
	private String request_result;
	private String review_status;
	private String risk_rating;
	private String screen_color_depth;
	private String screen_dpi;
	private String screen_res;
	private String service_type;
	private String session_id;
	private String session_id_query_count;
	private String summary_risk_score;
	private String time_zone;
	private String time_zone_dst_offset;
	private String tmx_variables;
	private String true_ip;
	private String true_ip_city;
	private String true_ip_first_seen;
	private String true_ip_geo;
	private String true_ip_isp;
	private String true_ip_last_event;
	private String true_ip_last_update;
	private String true_ip_latitude;
	private String true_ip_longitude;
	private String true_ip_organization;
	private String true_ip_region;
	private String true_ip_result;
	private String true_ip_score;
	private String true_ip_worst_score;
	private String ua_browser;
	private String ua_os;
	private String ua_platform;
	private Timestamp transDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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
	public String getAccount_email() {
		return account_email;
	}
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}
	public String getAccount_email_first_seen() {
		return account_email_first_seen;
	}
	public void setAccount_email_first_seen(String account_email_first_seen) {
		this.account_email_first_seen = account_email_first_seen;
	}
	public String getAccount_email_last_event() {
		return account_email_last_event;
	}
	public void setAccount_email_last_event(String account_email_last_event) {
		this.account_email_last_event = account_email_last_event;
	}
	public String getAccount_email_last_update() {
		return account_email_last_update;
	}
	public void setAccount_email_last_update(String account_email_last_update) {
		this.account_email_last_update = account_email_last_update;
	}
	public String getAccount_email_result() {
		return account_email_result;
	}
	public void setAccount_email_result(String account_email_result) {
		this.account_email_result = account_email_result;
	}
	public String getAccount_email_score() {
		return account_email_score;
	}
	public void setAccount_email_score(String account_email_score) {
		this.account_email_score = account_email_score;
	}
	public String getAccount_email_worst_score() {
		return account_email_worst_score;
	}
	public void setAccount_email_worst_score(String account_email_worst_score) {
		this.account_email_worst_score = account_email_worst_score;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_name_first_seen() {
		return account_name_first_seen;
	}
	public void setAccount_name_first_seen(String account_name_first_seen) {
		this.account_name_first_seen = account_name_first_seen;
	}
	public String getAccount_name_last_event() {
		return account_name_last_event;
	}
	public void setAccount_name_last_event(String account_name_last_event) {
		this.account_name_last_event = account_name_last_event;
	}
	public String getAccount_name_last_update() {
		return account_name_last_update;
	}
	public void setAccount_name_last_update(String account_name_last_update) {
		this.account_name_last_update = account_name_last_update;
	}
	public String getAccount_name_result() {
		return account_name_result;
	}
	public void setAccount_name_result(String account_name_result) {
		this.account_name_result = account_name_result;
	}
	public String getAccount_name_score() {
		return account_name_score;
	}
	public void setAccount_name_score(String account_name_score) {
		this.account_name_score = account_name_score;
	}
	public String getAccount_name_worst_score() {
		return account_name_worst_score;
	}
	public void setAccount_name_worst_score(String account_name_worst_score) {
		this.account_name_worst_score = account_name_worst_score;
	}
	public String getAccount_telephone() {
		return account_telephone;
	}
	public void setAccount_telephone(String account_telephone) {
		this.account_telephone = account_telephone;
	}
	public String getAccount_telephone_first_seen() {
		return account_telephone_first_seen;
	}
	public void setAccount_telephone_first_seen(String account_telephone_first_seen) {
		this.account_telephone_first_seen = account_telephone_first_seen;
	}
	public String getAccount_telephone_last_event() {
		return account_telephone_last_event;
	}
	public void setAccount_telephone_last_event(String account_telephone_last_event) {
		this.account_telephone_last_event = account_telephone_last_event;
	}
	public String getAccount_telephone_last_update() {
		return account_telephone_last_update;
	}
	public void setAccount_telephone_last_update(
			String account_telephone_last_update) {
		this.account_telephone_last_update = account_telephone_last_update;
	}
	public String getAccount_telephone_result() {
		return account_telephone_result;
	}
	public void setAccount_telephone_result(String account_telephone_result) {
		this.account_telephone_result = account_telephone_result;
	}
	public String getAccount_telephone_score() {
		return account_telephone_score;
	}
	public void setAccount_telephone_score(String account_telephone_score) {
		this.account_telephone_score = account_telephone_score;
	}
	public String getAccount_telephone_worst_score() {
		return account_telephone_worst_score;
	}
	public void setAccount_telephone_worst_score(
			String account_telephone_worst_score) {
		this.account_telephone_worst_score = account_telephone_worst_score;
	}
	public String getAgent_type() {
		return agent_type;
	}
	public void setAgent_type(String agent_type) {
		this.agent_type = agent_type;
	}
	public String getApi_call_datetime() {
		return api_call_datetime;
	}
	public void setApi_call_datetime(String api_call_datetime) {
		this.api_call_datetime = api_call_datetime;
	}
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getBrowser_language() {
		return browser_language;
	}
	public void setBrowser_language(String browser_language) {
		this.browser_language = browser_language;
	}
	public String getBrowser_string() {
		return browser_string;
	}
	public void setBrowser_string(String browser_string) {
		this.browser_string = browser_string;
	}
	public String getBrowser_string_hash() {
		return browser_string_hash;
	}
	public void setBrowser_string_hash(String browser_string_hash) {
		this.browser_string_hash = browser_string_hash;
	}
	public String getBrowser_version() {
		return browser_version;
	}
	public void setBrowser_version(String browser_version) {
		this.browser_version = browser_version;
	}
	public String getCc_bin_number() {
		return cc_bin_number;
	}
	public void setCc_bin_number(String cc_bin_number) {
		this.cc_bin_number = cc_bin_number;
	}
	public String getCc_bin_number_brand() {
		return cc_bin_number_brand;
	}
	public void setCc_bin_number_brand(String cc_bin_number_brand) {
		this.cc_bin_number_brand = cc_bin_number_brand;
	}
	public String getCc_bin_number_category() {
		return cc_bin_number_category;
	}
	public void setCc_bin_number_category(String cc_bin_number_category) {
		this.cc_bin_number_category = cc_bin_number_category;
	}
	public String getCc_bin_number_geo() {
		return cc_bin_number_geo;
	}
	public void setCc_bin_number_geo(String cc_bin_number_geo) {
		this.cc_bin_number_geo = cc_bin_number_geo;
	}
	public String getCc_bin_number_org() {
		return cc_bin_number_org;
	}
	public void setCc_bin_number_org(String cc_bin_number_org) {
		this.cc_bin_number_org = cc_bin_number_org;
	}
	public String getCc_bin_number_type() {
		return cc_bin_number_type;
	}
	public void setCc_bin_number_type(String cc_bin_number_type) {
		this.cc_bin_number_type = cc_bin_number_type;
	}
	public String getCss_image_loaded() {
		return css_image_loaded;
	}
	public void setCss_image_loaded(String css_image_loaded) {
		this.css_image_loaded = css_image_loaded;
	}
	public String getDetected_fl() {
		return detected_fl;
	}
	public void setDetected_fl(String detected_fl) {
		this.detected_fl = detected_fl;
	}
	public String getDevice_first_seen() {
		return device_first_seen;
	}
	public void setDevice_first_seen(String device_first_seen) {
		this.device_first_seen = device_first_seen;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_id_confidence() {
		return device_id_confidence;
	}
	public void setDevice_id_confidence(String device_id_confidence) {
		this.device_id_confidence = device_id_confidence;
	}
	public String getDevice_last_event() {
		return device_last_event;
	}
	public void setDevice_last_event(String device_last_event) {
		this.device_last_event = device_last_event;
	}
	public String getDevice_last_update() {
		return device_last_update;
	}
	public void setDevice_last_update(String device_last_update) {
		this.device_last_update = device_last_update;
	}
	public String getDevice_match_result() {
		return device_match_result;
	}
	public void setDevice_match_result(String device_match_result) {
		this.device_match_result = device_match_result;
	}
	public String getDevice_result() {
		return device_result;
	}
	public void setDevice_result(String device_result) {
		this.device_result = device_result;
	}
	public String getDevice_score() {
		return device_score;
	}
	public void setDevice_score(String device_score) {
		this.device_score = device_score;
	}
	public String getDevice_worst_score() {
		return device_worst_score;
	}
	public void setDevice_worst_score(String device_worst_score) {
		this.device_worst_score = device_worst_score;
	}
	public String getDns_ip() {
		return dns_ip;
	}
	public void setDns_ip(String dns_ip) {
		this.dns_ip = dns_ip;
	}
	public String getDns_ip_city() {
		return dns_ip_city;
	}
	public void setDns_ip_city(String dns_ip_city) {
		this.dns_ip_city = dns_ip_city;
	}
	public String getDns_ip_geo() {
		return dns_ip_geo;
	}
	public void setDns_ip_geo(String dns_ip_geo) {
		this.dns_ip_geo = dns_ip_geo;
	}
	public String getDns_ip_isp() {
		return dns_ip_isp;
	}
	public void setDns_ip_isp(String dns_ip_isp) {
		this.dns_ip_isp = dns_ip_isp;
	}
	public String getDns_ip_latitude() {
		return dns_ip_latitude;
	}
	public void setDns_ip_latitude(String dns_ip_latitude) {
		this.dns_ip_latitude = dns_ip_latitude;
	}
	public String getDns_ip_longitude() {
		return dns_ip_longitude;
	}
	public void setDns_ip_longitude(String dns_ip_longitude) {
		this.dns_ip_longitude = dns_ip_longitude;
	}
	public String getDns_ip_organization() {
		return dns_ip_organization;
	}
	public void setDns_ip_organization(String dns_ip_organization) {
		this.dns_ip_organization = dns_ip_organization;
	}
	public String getDns_ip_region() {
		return dns_ip_region;
	}
	public void setDns_ip_region(String dns_ip_region) {
		this.dns_ip_region = dns_ip_region;
	}
	public String getEnabled_ck() {
		return enabled_ck;
	}
	public void setEnabled_ck(String enabled_ck) {
		this.enabled_ck = enabled_ck;
	}
	public String getEnabled_fl() {
		return enabled_fl;
	}
	public void setEnabled_fl(String enabled_fl) {
		this.enabled_fl = enabled_fl;
	}
	public String getEnabled_im() {
		return enabled_im;
	}
	public void setEnabled_im(String enabled_im) {
		this.enabled_im = enabled_im;
	}
	public String getEnabled_js() {
		return enabled_js;
	}
	public void setEnabled_js(String enabled_js) {
		this.enabled_js = enabled_js;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getFlash_lang() {
		return flash_lang;
	}
	public void setFlash_lang(String flash_lang) {
		this.flash_lang = flash_lang;
	}
	public String getFlash_os() {
		return flash_os;
	}
	public void setFlash_os(String flash_os) {
		this.flash_os = flash_os;
	}
	public String getFlash_version() {
		return flash_version;
	}
	public void setFlash_version(String flash_version) {
		this.flash_version = flash_version;
	}
	public String getFuzzy_device_first_seen() {
		return fuzzy_device_first_seen;
	}
	public void setFuzzy_device_first_seen(String fuzzy_device_first_seen) {
		this.fuzzy_device_first_seen = fuzzy_device_first_seen;
	}
	public String getFuzzy_device_id() {
		return fuzzy_device_id;
	}
	public void setFuzzy_device_id(String fuzzy_device_id) {
		this.fuzzy_device_id = fuzzy_device_id;
	}
	public String getFuzzy_device_id_confidence() {
		return fuzzy_device_id_confidence;
	}
	public void setFuzzy_device_id_confidence(String fuzzy_device_id_confidence) {
		this.fuzzy_device_id_confidence = fuzzy_device_id_confidence;
	}
	public String getFuzzy_device_last_event() {
		return fuzzy_device_last_event;
	}
	public void setFuzzy_device_last_event(String fuzzy_device_last_event) {
		this.fuzzy_device_last_event = fuzzy_device_last_event;
	}
	public String getFuzzy_device_last_update() {
		return fuzzy_device_last_update;
	}
	public void setFuzzy_device_last_update(String fuzzy_device_last_update) {
		this.fuzzy_device_last_update = fuzzy_device_last_update;
	}
	public String getFuzzy_device_match_result() {
		return fuzzy_device_match_result;
	}
	public void setFuzzy_device_match_result(String fuzzy_device_match_result) {
		this.fuzzy_device_match_result = fuzzy_device_match_result;
	}
	public String getFuzzy_device_result() {
		return fuzzy_device_result;
	}
	public void setFuzzy_device_result(String fuzzy_device_result) {
		this.fuzzy_device_result = fuzzy_device_result;
	}
	public String getFuzzy_device_score() {
		return fuzzy_device_score;
	}
	public void setFuzzy_device_score(String fuzzy_device_score) {
		this.fuzzy_device_score = fuzzy_device_score;
	}
	public String getFuzzy_device_worst_score() {
		return fuzzy_device_worst_score;
	}
	public void setFuzzy_device_worst_score(String fuzzy_device_worst_score) {
		this.fuzzy_device_worst_score = fuzzy_device_worst_score;
	}
	public String getHeaders_name_value_hash() {
		return headers_name_value_hash;
	}
	public void setHeaders_name_value_hash(String headers_name_value_hash) {
		this.headers_name_value_hash = headers_name_value_hash;
	}
	public String getHeaders_order_string_hash() {
		return headers_order_string_hash;
	}
	public void setHeaders_order_string_hash(String headers_order_string_hash) {
		this.headers_order_string_hash = headers_order_string_hash;
	}
	public String getHoneypot_fingerprint() {
		return honeypot_fingerprint;
	}
	public void setHoneypot_fingerprint(String honeypot_fingerprint) {
		this.honeypot_fingerprint = honeypot_fingerprint;
	}
	public String getHoneypot_fingerprint_check() {
		return honeypot_fingerprint_check;
	}
	public void setHoneypot_fingerprint_check(String honeypot_fingerprint_check) {
		this.honeypot_fingerprint_check = honeypot_fingerprint_check;
	}
	public String getHoneypot_fingerprint_match() {
		return honeypot_fingerprint_match;
	}
	public void setHoneypot_fingerprint_match(String honeypot_fingerprint_match) {
		this.honeypot_fingerprint_match = honeypot_fingerprint_match;
	}
	public String getHttp_referer() {
		return http_referer;
	}
	public void setHttp_referer(String http_referer) {
		this.http_referer = http_referer;
	}
	public String getHttp_referer_domain() {
		return http_referer_domain;
	}
	public void setHttp_referer_domain(String http_referer_domain) {
		this.http_referer_domain = http_referer_domain;
	}
	public String getHttp_referer_url() {
		return http_referer_url;
	}
	public void setHttp_referer_url(String http_referer_url) {
		this.http_referer_url = http_referer_url;
	}
	public String getImage_loaded() {
		return image_loaded;
	}
	public void setImage_loaded(String image_loaded) {
		this.image_loaded = image_loaded;
	}
	public String getInput_ip_address() {
		return input_ip_address;
	}
	public void setInput_ip_address(String input_ip_address) {
		this.input_ip_address = input_ip_address;
	}
	public String getInput_ip_city() {
		return input_ip_city;
	}
	public void setInput_ip_city(String input_ip_city) {
		this.input_ip_city = input_ip_city;
	}
	public String getInput_ip_first_seen() {
		return input_ip_first_seen;
	}
	public void setInput_ip_first_seen(String input_ip_first_seen) {
		this.input_ip_first_seen = input_ip_first_seen;
	}
	public String getInput_ip_geo() {
		return input_ip_geo;
	}
	public void setInput_ip_geo(String input_ip_geo) {
		this.input_ip_geo = input_ip_geo;
	}
	public String getInput_ip_isp() {
		return input_ip_isp;
	}
	public void setInput_ip_isp(String input_ip_isp) {
		this.input_ip_isp = input_ip_isp;
	}
	public String getInput_ip_last_event() {
		return input_ip_last_event;
	}
	public void setInput_ip_last_event(String input_ip_last_event) {
		this.input_ip_last_event = input_ip_last_event;
	}
	public String getInput_ip_last_update() {
		return input_ip_last_update;
	}
	public void setInput_ip_last_update(String input_ip_last_update) {
		this.input_ip_last_update = input_ip_last_update;
	}
	public String getInput_ip_latitude() {
		return input_ip_latitude;
	}
	public void setInput_ip_latitude(String input_ip_latitude) {
		this.input_ip_latitude = input_ip_latitude;
	}
	public String getInput_ip_longitude() {
		return input_ip_longitude;
	}
	public void setInput_ip_longitude(String input_ip_longitude) {
		this.input_ip_longitude = input_ip_longitude;
	}
	public String getInput_ip_organization() {
		return input_ip_organization;
	}
	public void setInput_ip_organization(String input_ip_organization) {
		this.input_ip_organization = input_ip_organization;
	}
	public String getInput_ip_region() {
		return input_ip_region;
	}
	public void setInput_ip_region(String input_ip_region) {
		this.input_ip_region = input_ip_region;
	}
	public String getInput_ip_result() {
		return input_ip_result;
	}
	public void setInput_ip_result(String input_ip_result) {
		this.input_ip_result = input_ip_result;
	}
	public String getInput_ip_score() {
		return input_ip_score;
	}
	public void setInput_ip_score(String input_ip_score) {
		this.input_ip_score = input_ip_score;
	}
	public String getJs_browser() {
		return js_browser;
	}
	public void setJs_browser(String js_browser) {
		this.js_browser = js_browser;
	}
	public String getJs_browser_string() {
		return js_browser_string;
	}
	public void setJs_browser_string(String js_browser_string) {
		this.js_browser_string = js_browser_string;
	}
	public String getJs_browser_string_hash() {
		return js_browser_string_hash;
	}
	public void setJs_browser_string_hash(String js_browser_string_hash) {
		this.js_browser_string_hash = js_browser_string_hash;
	}
	public String getJs_fonts_hash() {
		return js_fonts_hash;
	}
	public void setJs_fonts_hash(String js_fonts_hash) {
		this.js_fonts_hash = js_fonts_hash;
	}
	public String getJs_fonts_number() {
		return js_fonts_number;
	}
	public void setJs_fonts_number(String js_fonts_number) {
		this.js_fonts_number = js_fonts_number;
	}
	public String getJs_os() {
		return js_os;
	}
	public void setJs_os(String js_os) {
		this.js_os = js_os;
	}
	public String getMime_type_hash() {
		return mime_type_hash;
	}
	public void setMime_type_hash(String mime_type_hash) {
		this.mime_type_hash = mime_type_hash;
	}
	public String getMime_type_number() {
		return mime_type_number;
	}
	public void setMime_type_number(String mime_type_number) {
		this.mime_type_number = mime_type_number;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOs_fonts_hash() {
		return os_fonts_hash;
	}
	public void setOs_fonts_hash(String os_fonts_hash) {
		this.os_fonts_hash = os_fonts_hash;
	}
	public String getOs_fonts_number() {
		return os_fonts_number;
	}
	public void setOs_fonts_number(String os_fonts_number) {
		this.os_fonts_number = os_fonts_number;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getPage_time_on() {
		return page_time_on;
	}
	public void setPage_time_on(String page_time_on) {
		this.page_time_on = page_time_on;
	}
	public String getPlugin_flash() {
		return plugin_flash;
	}
	public void setPlugin_flash(String plugin_flash) {
		this.plugin_flash = plugin_flash;
	}
	public String getPlugin_hash() {
		return plugin_hash;
	}
	public void setPlugin_hash(String plugin_hash) {
		this.plugin_hash = plugin_hash;
	}
	public String getPlugin_number() {
		return plugin_number;
	}
	public void setPlugin_number(String plugin_number) {
		this.plugin_number = plugin_number;
	}
	public String getPlugin_windows_media_player() {
		return plugin_windows_media_player;
	}
	public void setPlugin_windows_media_player(String plugin_windows_media_player) {
		this.plugin_windows_media_player = plugin_windows_media_player;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public String getPolicy_score() {
		return policy_score;
	}
	public void setPolicy_score(String policy_score) {
		this.policy_score = policy_score;
	}
	public String getProfiled_domain() {
		return profiled_domain;
	}
	public void setProfiled_domain(String profiled_domain) {
		this.profiled_domain = profiled_domain;
	}
	public String getProfiled_url() {
		return profiled_url;
	}
	public void setProfiled_url(String profiled_url) {
		this.profiled_url = profiled_url;
	}
	public String getProfiling_datetime() {
		return profiling_datetime;
	}
	public void setProfiling_datetime(String profiling_datetime) {
		this.profiling_datetime = profiling_datetime;
	}
	public String getReason_code() {
		return reason_code;
	}
	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getRequest_result() {
		return request_result;
	}
	public void setRequest_result(String request_result) {
		this.request_result = request_result;
	}
	public String getReview_status() {
		return review_status;
	}
	public void setReview_status(String review_status) {
		this.review_status = review_status;
	}
	public String getRisk_rating() {
		return risk_rating;
	}
	public void setRisk_rating(String risk_rating) {
		this.risk_rating = risk_rating;
	}
	public String getScreen_color_depth() {
		return screen_color_depth;
	}
	public void setScreen_color_depth(String screen_color_depth) {
		this.screen_color_depth = screen_color_depth;
	}
	public String getScreen_dpi() {
		return screen_dpi;
	}
	public void setScreen_dpi(String screen_dpi) {
		this.screen_dpi = screen_dpi;
	}
	public String getScreen_res() {
		return screen_res;
	}
	public void setScreen_res(String screen_res) {
		this.screen_res = screen_res;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getSession_id_query_count() {
		return session_id_query_count;
	}
	public void setSession_id_query_count(String session_id_query_count) {
		this.session_id_query_count = session_id_query_count;
	}
	public String getSummary_risk_score() {
		return summary_risk_score;
	}
	public void setSummary_risk_score(String summary_risk_score) {
		this.summary_risk_score = summary_risk_score;
	}
	public String getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	public String getTime_zone_dst_offset() {
		return time_zone_dst_offset;
	}
	public void setTime_zone_dst_offset(String time_zone_dst_offset) {
		this.time_zone_dst_offset = time_zone_dst_offset;
	}
	public String getTmx_variables() {
		return tmx_variables;
	}
	public void setTmx_variables(String tmx_variables) {
		this.tmx_variables = tmx_variables;
	}
	public String getTrue_ip() {
		return true_ip;
	}
	public void setTrue_ip(String true_ip) {
		this.true_ip = true_ip;
	}
	public String getTrue_ip_city() {
		return true_ip_city;
	}
	public void setTrue_ip_city(String true_ip_city) {
		this.true_ip_city = true_ip_city;
	}
	public String getTrue_ip_first_seen() {
		return true_ip_first_seen;
	}
	public void setTrue_ip_first_seen(String true_ip_first_seen) {
		this.true_ip_first_seen = true_ip_first_seen;
	}
	public String getTrue_ip_geo() {
		return true_ip_geo;
	}
	public void setTrue_ip_geo(String true_ip_geo) {
		this.true_ip_geo = true_ip_geo;
	}
	public String getTrue_ip_isp() {
		return true_ip_isp;
	}
	public void setTrue_ip_isp(String true_ip_isp) {
		this.true_ip_isp = true_ip_isp;
	}
	public String getTrue_ip_last_event() {
		return true_ip_last_event;
	}
	public void setTrue_ip_last_event(String true_ip_last_event) {
		this.true_ip_last_event = true_ip_last_event;
	}
	public String getTrue_ip_last_update() {
		return true_ip_last_update;
	}
	public void setTrue_ip_last_update(String true_ip_last_update) {
		this.true_ip_last_update = true_ip_last_update;
	}
	public String getTrue_ip_latitude() {
		return true_ip_latitude;
	}
	public void setTrue_ip_latitude(String true_ip_latitude) {
		this.true_ip_latitude = true_ip_latitude;
	}
	public String getTrue_ip_longitude() {
		return true_ip_longitude;
	}
	public void setTrue_ip_longitude(String true_ip_longitude) {
		this.true_ip_longitude = true_ip_longitude;
	}
	public String getTrue_ip_organization() {
		return true_ip_organization;
	}
	public void setTrue_ip_organization(String true_ip_organization) {
		this.true_ip_organization = true_ip_organization;
	}
	public String getTrue_ip_region() {
		return true_ip_region;
	}
	public void setTrue_ip_region(String true_ip_region) {
		this.true_ip_region = true_ip_region;
	}
	public String getTrue_ip_result() {
		return true_ip_result;
	}
	public void setTrue_ip_result(String true_ip_result) {
		this.true_ip_result = true_ip_result;
	}
	public String getTrue_ip_score() {
		return true_ip_score;
	}
	public void setTrue_ip_score(String true_ip_score) {
		this.true_ip_score = true_ip_score;
	}
	public String getTrue_ip_worst_score() {
		return true_ip_worst_score;
	}
	public void setTrue_ip_worst_score(String true_ip_worst_score) {
		this.true_ip_worst_score = true_ip_worst_score;
	}
	public String getUa_browser() {
		return ua_browser;
	}
	public void setUa_browser(String ua_browser) {
		this.ua_browser = ua_browser;
	}
	public String getUa_os() {
		return ua_os;
	}
	public void setUa_os(String ua_os) {
		this.ua_os = ua_os;
	}
	public String getUa_platform() {
		return ua_platform;
	}
	public void setUa_platform(String ua_platform) {
		this.ua_platform = ua_platform;
	}
	public Timestamp getTransDate() {
		return transDate;
	}
	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}
	
	
	
	
}
