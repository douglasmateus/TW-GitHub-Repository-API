package com.thoughtworks.repos.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.GitHubRepository;
import com.thoughtworks.repos.repository.GitHubReposRepository;
import com.thoughtworks.repos.service.impl.GitHubReposServiceImpl;
import com.thoughtworks.repos.util.HttpHeaderEnum;
import com.thoughtworks.repos.util.HttpUtil;

/**
 * Rest implementation to access GitHub API.
 * @author douglas.mateus
 *
 */
@Component
public class GitHubReposRepositoryImpl implements GitHubReposRepository{

	@Value("${api.github.personalaccesstoken}")
	private String token;
	
	@Value("${api.github.thoughtworks.url}")
	private String thoughtworksUrl;
	
	@Value("${api.github.thoughtworks.contributors.url}")
	private String thoughtworksContributorsUrl;
	
	private RestTemplate restTemplate;
	
	private Logger logger = Logger.getLogger(GitHubReposServiceImpl.class);
	
	@Override
	public List<GitHubRepository> findAllRepositories() {
		logger.info("Start to find all thoughtworks repositories.");
		restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>(HttpHeaderEnum.HEADERS.getValue(), HttpUtil.createHeaders(token));
		return restTemplate.exchange(HttpUtil.buildUrl(thoughtworksUrl, token, null), HttpMethod.GET, entity, new ParameterizedTypeReference<List<GitHubRepository>>() {}).getBody();
	}
	
	@Override
	public void setContributorsByRepository(List<GitHubRepository> repositories) {
		logger.info("Starting the Contributors search for each repository.");
    	for (GitHubRepository repository : repositories) {
    		restTemplate = new RestTemplate();
    		HttpEntity<String> entity = new HttpEntity<>(HttpHeaderEnum.HEADERS.getValue(), HttpUtil.createHeaders(token));
    		List<Contributor> contributors = restTemplate.exchange(HttpUtil.buildUrl(thoughtworksContributorsUrl, token, repository.getName()), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Contributor>>() {}).getBody();
		    if (contributors == null) {
				String message = "Repository " + repository.getName() + " has no Contributors.";
				logger.info(message);
			} else {
				repository.setContributors(contributors);
			}
		}
	}
}
