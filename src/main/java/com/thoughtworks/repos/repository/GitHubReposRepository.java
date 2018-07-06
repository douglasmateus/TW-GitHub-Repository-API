package com.thoughtworks.repos.repository;

import java.util.Collection;

import com.thoughtworks.repos.model.GitHubRepository;

/**
 * Provides Rest operations for the Repository entity.
 * @author douglas.mateus
 *
 */
public interface GitHubReposRepository {

	Collection<GitHubRepository> findAllRepositories();
	
	void findContributorsByRepository(Collection<GitHubRepository> repositories);
}
