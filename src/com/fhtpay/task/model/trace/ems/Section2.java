package com.fhtpay.task.model.trace.ems;

import java.util.Arrays;


public class Section2 {

	private DetailList[] detailList;

	public DetailList[] getDetailList() {
		return detailList;
	}

	public void setDetailList(DetailList[] detailList) {
		this.detailList = detailList;
	}

	@Override
	public String toString() {
		return "Section2 [detailList=" + Arrays.toString(detailList) + "]";
	}
	
}
