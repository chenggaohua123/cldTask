package com.fhtpay.task.model.suspicious;

public class test {
	private int count1;
	private int count2;
	public int getCount1() {
		return count1;
	}
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	public int getCount2() {
		return count2;
	}
	public void setCount2(int count2) {
		this.count2 = count2;
	}
	@Override
	public String toString() {
		return "test [count1=" + count1 + ", count2=" + count2
				+ ", getCount1()=" + getCount1() + ", getCount2()="
				+ getCount2() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
