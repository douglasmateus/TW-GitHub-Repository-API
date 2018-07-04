package com.thoughtworks.repos.util;

import org.springframework.http.HttpHeaders;

public class Headers {
	
	/**
	 * GitHub Personal Access Tokens 
	 */
	public static final String PERSONAL_ACCESS_TOKENS = "f13cd04efe8b1b87515a8443b3f5fd9f93bc8fac";
	
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
