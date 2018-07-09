package com.thoughtworks.repos.util;

import org.springframework.http.HttpHeaders;

public class HttpUtil {
	
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
				set(HttpHeaderEnum.AUTHORIZATION.getValue(), token);
			}
		};
		return headers;
	}
	
	/**
	 * Creates a valid url to access GitHub repository
	 * @param url
	 * @param token
	 * @param repositoryName
	 * @return
	 */
	public static String buildUrl(String url, String token, String repositoryName) {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(url);
		strBuff.append(HttpHeaderEnum.HEADER_PARAMETERS.getValue());
		if (repositoryName == null) {
			return String.format(strBuff.toString(), token);
		}
		return String.format(strBuff.toString(), repositoryName, token);
	}
}
