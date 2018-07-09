package com.thoughtworks.repos.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.GitHubRepository;
import com.thoughtworks.repos.repository.GitHubReposRepository;
import com.thoughtworks.repos.service.impl.GitHubReposServiceImpl;
import com.thoughtworks.repos.util.HeaderEnum;
import com.thoughtworks.repos.util.Headers;

/**
 * Rest implementation to access GitHub API.
 * @author douglas.mateus
 *
 */
@Component
public class GitHubReposRepositoryImpl implements GitHubReposRepository{

	/**
	 * Thoughtworks Url repositories 
	 */
	public static final String THOUGHTWORKS_REPOSITORY = "https://api.github.com/orgs/thoughtworks/repos";

	/**
	 * Thoughtworks Url repository contributors
	 */
	private static final String THOUGHTWORKS_CONTRIBUTORS = "https://api.github.com/repos/thoughtworks/%s/contributors";
	
	private RestTemplate restTemplate;
	
	private Logger logger = Logger.getLogger(GitHubReposServiceImpl.class);
	
	@Override
	public List<GitHubRepository> findAllRepositories() {
		logger.info("Start to find all thoughtworks repositories.");
		restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>(HeaderEnum.HEADERS.getValue(), Headers.createHeaders());
		return restTemplate.exchange(THOUGHTWORKS_REPOSITORY, HttpMethod.GET, entity, new ParameterizedTypeReference<List<GitHubRepository>>() {}).getBody();
	}
	
	@Override
	public void setContributorsByRepository(List<GitHubRepository> repositories) {
		logger.info("Starting the Contributors search for each repository.");
    	for (GitHubRepository repository : repositories) {
    		String url = String.format(THOUGHTWORKS_CONTRIBUTORS, repository.getName());
    		restTemplate = new RestTemplate();
    		HttpEntity<String> entity = new HttpEntity<>(HeaderEnum.HEADERS.getValue(), Headers.createHeaders());
    		List<Contributor> contributors = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Contributor>>() {}).getBody();
		    if (contributors == null) {
				String message = "Repository " + repository.getName() + " has no Contributors.";
				logger.info(message);
			} else {
				repository.setContributors(contributors);
			}
		}
	}
}
