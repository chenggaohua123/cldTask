package com.fhtpay.task.model.trace.ems;

public class DetailList {

	private String desc;
	private String status;
	private String time;
	private String timeZone;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	@Override
	public String toString() {
		return "DetailList [desc=" + desc + ", status=" + status + ", time="
				+ time + ", timeZone=" + timeZone + "]";
	}
	
}
