package com.sendMessageFCM.utils;

public class Result {

	int code;
	String message;
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
		
	public Result(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + "]";
	}
	
}
