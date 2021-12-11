package com.shankarsan.auto.battery.enums;

public enum ErrorMessageEnum {
	
	SUCCESS("OK","Successfully processed."),
	INVALID_INPUT("ERR-03","Invalid input provided."),
	SERVICE_DOWN("ERR-002", "Service is temporarily down. Please try again later."),
	NO_DATA_RECEIVED("ERR-001", "No data recieved from database");
	
	private String errorCode;
	private String errorMessage;
	
	private ErrorMessageEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}

}
