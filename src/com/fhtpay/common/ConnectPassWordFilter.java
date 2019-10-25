package com.fhtpay.common;

import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;

public class ConnectPassWordFilter  extends FilterAdapter {
	@Override
	public ConnectionProxy connection_connect(FilterChain chain, Properties info)
			throws SQLException {
		String jdbcPass = (String)info.get("password");
		String user = (String)info.get("user");
		try {
			String newpass = Des.decrypt(jdbcPass);
			String newuser = Des.decrypt(user);
			info.setProperty("password", newpass);
			info.setProperty("user", newuser);
			chain.connection_connect(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.connection_connect(chain, info);
	}

}
