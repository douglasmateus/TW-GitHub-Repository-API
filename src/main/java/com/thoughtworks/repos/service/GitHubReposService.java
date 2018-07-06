package com.thoughtworks.repos.service;

import com.thoughtworks.repos.model.TWGitHubResponse;

public interface GitHubReposService {

	/**
	 * Provides business operations for the Repository entity.
	 * 
	 * @return Response.
	 */
	public TWGitHubResponse findAllLanguages();
}
