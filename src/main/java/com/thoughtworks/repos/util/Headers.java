package com.thoughtworks.repos.util;

import org.springframework.http.HttpHeaders;

public class Headers {
	
	/**
	 * GitHub Personal Access Tokens 
	 */
	public static final String PERSONAL_ACCESS_TOKENS = "f47b59dd400ed7f2c8fee2536c676c93f4cb3bca";
	
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
				set(HeaderEnum.AUTHORIZATION.getValue(), PERSONAL_ACCESS_TOKENS);
			}
		};
		return headers;
	}
	
	public static String createUrl(String url) {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(url);
		strBuff.append(HeaderEnum.HEADER_PARAMETERS.getValue());
		return strBuff.toString();
	}
}
