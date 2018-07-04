package com.thoughtworks.repos.repository;

import java.util.Collection;

import com.thoughtworks.repos.model.Repository;

/**
 * Provides Rest operations for the Repository entity.
 * @author douglas.mateus
 *
 */
public interface ReposRepository {

	Collection<Repository> findAllRepositories();
	
	void findContributorsByRepository(Collection<Repository> repositories);
}
