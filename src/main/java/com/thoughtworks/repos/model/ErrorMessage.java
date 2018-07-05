package com.thoughtworks.repos.model;

import java.io.Serializable;

/**
 * DTO for exception handling.
 * 
 * @author douglas.mateus
 *
 */
public class ErrorMessage implements Serializable {
	private static final long serialVersionUID = -6565901435533050277L;
	private int code;
	private String message;

	public ErrorMessage() {
	}

	public ErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
