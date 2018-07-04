package com.thoughtworks.repos.service;

import com.thoughtworks.repos.model.Response;

public interface ReposService {

	/**
	 * Provides business operations for the Repository entity.
	 * 
	 * @return Response.
	 */
	public Response findAllLanguages();
}
