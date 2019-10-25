package com.fhtpay.task.service.tracequery;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;

public class WaybillstateChangesService extends BaseTaskJobDetail {

	@Override
	public void process(TaskInfo taskInfo) {
		WaybillstateChange waybillstate = (WaybillstateChange) TaskMain.getInstance().getBeanFactory().getBean("waybillstate");
		waybillstate.upateEMSGoodspressOperationStatus();
	}

}
