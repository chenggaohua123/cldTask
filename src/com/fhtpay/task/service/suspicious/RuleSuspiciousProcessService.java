package com.fhtpay.task.service.suspicious;

import java.util.List;

import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;

public abstract class RuleSuspiciousProcessService {
	
	public abstract List<SuspiciousTransDeatailInfo> processRole(RulesInfo ruleInfo, SuspiciousMerTerInfo merTerInfo, String susType);
}
