package com.thoughtworks.repository.service;

import com.thoughtworks.repository.model.Response;

public interface RepositoryService {

	/**
	 * Retrieves all languages.
	 * 
	 * @return Response.
	 */
	public Response findAllLanguages();
}
