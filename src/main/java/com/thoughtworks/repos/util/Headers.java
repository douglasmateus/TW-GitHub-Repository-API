package com.thoughtworks.repos.util;

import org.springframework.http.HttpHeaders;

public class Headers {
	
	/**
	 * Creates a HttpHeaders with Personal Access Tokens necessary to limited
	 * access GitHub repository
	 * 
	 * @return HttpHeaders
	 */
	public static HttpHeaders createHeaders(String token) {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = -1643427945865752875L;
			{
				set(HeaderEnum.AUTHORIZATION.getValue(), token);
			}
		};
		return headers;
	}
}
