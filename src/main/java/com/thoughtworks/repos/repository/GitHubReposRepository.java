package com.thoughtworks.repos.repository;

import java.util.List;

import com.thoughtworks.repos.model.GitHubRepository;

/**
 * Provides Rest operations for the Repository entity.
 * @author douglas.mateus
 *
 */
public interface GitHubReposRepository {

	List<GitHubRepository> findAllRepositories();
	
	void setContributorsByRepository(List<GitHubRepository> repositories);
}
