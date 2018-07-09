package com.thoughtworks.repos.util;

public enum HttpHeaderEnum {
	HEADERS("parameters"),
	AUTHORIZATION("Authorization"),
	HEADER_PARAMETERS("?access_token=%s");
	
	private String value;

	private HttpHeaderEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
