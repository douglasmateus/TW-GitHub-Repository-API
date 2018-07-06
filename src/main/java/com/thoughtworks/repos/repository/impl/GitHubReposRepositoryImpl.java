package com.thoughtworks.repos.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repos.exception.EntityNotFoundException;
import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.GitHubRepository;
import com.thoughtworks.repos.repository.GitHubReposRepository;
import com.thoughtworks.repos.service.impl.GitHubReposServiceImpl;
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
	public static final String THOUGHTWORKS_REPOSITORY = "https://api.github.com/orgs/thoughtworks/repos?access_token="+Headers.PERSONAL_ACCESS_TOKENS;

	/**
	 * Thoughtworks Url repository contributors
	 */
	private static final String THOUGHTWORKS_CONTRIBUTORS = "https://api.github.com/repos/thoughtworks/%s/contributors?access_token="+Headers.PERSONAL_ACCESS_TOKENS;
	
	private Logger logger = Logger.getLogger(GitHubReposServiceImpl.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public Collection<GitHubRepository> findAllRepositories() {
		Collection<GitHubRepository> repositories = new ArrayList<GitHubRepository>();
		//HttpHeaders httpHeaders = Headers.createHeaders();
	    RestTemplate restTemplate = new RestTemplate();
	    //@CoverageIgnore
	    //ResponseEntity<? extends List<LinkedHashMap<String,Object>>> responseEntity = restTemplate.exchange(THOUGHTWORKS_REPOSITORY,HttpMethod.GET,new HttpEntity<Object>(httpHeaders),(Class<? extends List<LinkedHashMap<String,Object>>>)List.class);
	    List<LinkedHashMap<String, Object>> reposMap = restTemplate.getForObject(THOUGHTWORKS_REPOSITORY, List.class);

	    //List<LinkedHashMap<String,Object>> reposMap = (List<LinkedHashMap<String,Object>>) responseEntity.getBody();
	    
    	if(reposMap == null) {
            logger.info("Repositories not found.");
            throw new EntityNotFoundException("Repositories not found.");
        } else {
        	for(LinkedHashMap<String, Object> map : reposMap) {
            	Long id = Long.valueOf(map.get("id").toString());
            	String name = map.get("name").toString();
            	long stargazers = map.get("stargazers_count") != null ? Long.valueOf(map.get("stargazers_count").toString()) : 0;
            	String language = map.get("language") != null ? map.get("language").toString() : null;
            	long forks = map.get("forks_count") != null? Long.valueOf(map.get("forks_count").toString()) : 0;
				repositories.add(new GitHubRepository(id, name, language, stargazers, forks));
            }
        }
		return repositories;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void findContributorsByRepository(Collection<GitHubRepository> repositories) {
		//HttpHeaders httpHeaders = Headers.createHeaders();
		logger.info("Starting the Contributors search for each repository.");
    	for (GitHubRepository repository : repositories) {
    		String url = String.format(THOUGHTWORKS_CONTRIBUTORS, repository.getName());
    		RestTemplate restTemplate = new RestTemplate();
    		//@CoverageIgnore
    		//ResponseEntity<? extends List<LinkedHashMap<String,Object>>> responseEntity = restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<Object>(httpHeaders),(Class<? extends List<LinkedHashMap<String,Object>>>)List.class);
    		List<LinkedHashMap<String, Object>> contributorsMap = restTemplate.getForObject(url, List.class);

		    //List<LinkedHashMap<String,Object>> contributorsMap = (List<LinkedHashMap<String,Object>>) responseEntity.getBody();
			
		    if (contributorsMap == null) {
				String message = "Repository " + repository.getName() + " has no Contributors.";
				logger.info(message);
			} else {
				List<Contributor> contributors = new ArrayList<Contributor>();
				for(LinkedHashMap<String, Object> map : contributorsMap) {
					String login = map.get("login").toString();
					long contributions = map.get("contributions") != null ? Long.valueOf(map.get("contributions").toString()) : 0;
					Contributor contributor = new Contributor(login, contributions);
					contributors.add(contributor);
				}
				repository.setContributors(contributors);
			}
		}
	}
}
