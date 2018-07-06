package com.thoughtworks.repos.util;

import org.springframework.http.HttpHeaders;

public class Headers {
	
	/**
	 * GitHub Personal Access Tokens 
	 */
	public static final String PERSONAL_ACCESS_TOKENS = "339884e127408e7fff81d8291ba9b161f0304e9f";
	
	/**
	 * Creates a HttpHeaders with Personal Access Tokens necessary to limited
	 * access GitHub repository
	 * 
	 * @return HttpHeaders
	 */
	public static HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = -1643427945865752875L;
			{
				set("Authorization", PERSONAL_ACCESS_TOKENS);
			}
		};
		return headers;
	}
}
