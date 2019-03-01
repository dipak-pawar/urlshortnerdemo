package com.avpa.demo.urlshortner.minifyurl;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

class ShortenUrlContract {
	
	@NotNull
	private String url;

	String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
	}
	
}

class GenericResponse {
	@JsonProperty
	private String returnCode;
	@JsonProperty
	private String returnText;
	@JsonProperty
	private String message;
	
	GenericResponse() {
		
	}
	
	GenericResponse(String returnCode, String returnText, String message) {
		this.returnCode = returnCode;
		this.returnText = returnText;
		this.message = message;
	}
	
	String getReturnCode() {
		return returnCode;
	}
	void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	String getReturnText() {
		return returnText;
	}
	void setReturnText(String returnText) {
		this.returnText = returnText;
	}
	String getMessage() {
		return message;
	}
	void setMessage(String message) {
		this.message = message;
	}
}
