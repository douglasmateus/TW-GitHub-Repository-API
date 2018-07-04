package com.thoughtworks.repos.util;

import org.springframework.http.HttpHeaders;

public class Headers {
	
	/**
	 * GitHub Personal Access Tokens 
	 */
	public static final String PERSONAL_ACCESS_TOKENS = "ba158d565c9d9f3fd73099995d2ce4000968d1ff";
	
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
