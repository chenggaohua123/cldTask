package com.fhtpay.common.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String retMsg;
	
    public BusinessException() {
        super();
    }
    
	public BusinessException(String message) {
        this.retMsg=message;
    }
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public String getRetMsg() {
		return retMsg;
	}
	
}
