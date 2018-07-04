package com.thoughtworks.repos.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repos.exception.EntityNotFoundException;
import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.Repository;
import com.thoughtworks.repos.repository.ReposRepository;
import com.thoughtworks.repos.service.impl.ReposServiceImpl;
import com.thoughtworks.repos.util.Headers;

/**
 * Rest implementation to access GitHub API.
 * @author douglas.mateus
 *
 */
@Component
public class ReposRepositoryImpl implements ReposRepository{

	/**
	 * Thoughtworks Url repositories 
	 */
	public static final String THOUGHTWORKS_REPOSITORY = "https://api.github.com/orgs/thoughtworks/repos?access_token="+Headers.PERSONAL_ACCESS_TOKENS;
	
	private Logger logger = Logger.getLogger(ReposServiceImpl.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Repository> findAllRepositories() {
		Collection<Repository> repositories = new ArrayList<Repository>();
		HttpHeaders httpHeaders = Headers.createHeaders();
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<? extends List<LinkedHashMap<String,Object>>> responseEntity = restTemplate.exchange(THOUGHTWORKS_REPOSITORY,HttpMethod.GET,new HttpEntity<Object>(httpHeaders),(Class<? extends List<LinkedHashMap<String,Object>>>)List.class);
	    List<LinkedHashMap<String,Object>> reposMap = (List<LinkedHashMap<String,Object>>) responseEntity.getBody();
	    
    	if(reposMap == null) {
            logger.info("Repositories not found.");
            throw new EntityNotFoundException("Repositories not found.");
        } else {
        	for(LinkedHashMap<String, Object> map : reposMap) {
            	Repository repository = new Repository();
            	repository.setId(Long.valueOf(map.get("id").toString()));
            	repository.setName(map.get("name").toString());
            	repository.setStargazers(map.get("stargazers_count") != null ? Long.valueOf(map.get("stargazers_count").toString()) : 0);
            	repository.setLanguage(map.get("language") != null ? map.get("language").toString() : null);
            	repository.setForks(map.get("forks_count") != null? Long.valueOf(map.get("forks_count").toString()) : 0);
				repositories.add(repository);
            	System.out.println("Repository: id="+map.get("id")+", Name="+map.get("name")+", Stargazers="+map.get("stargazers_count")+", Language="+map.get("language")+", Forks="+map.get("forks_count"));
            }
        }
		return repositories;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void findContributorsByRepository(Collection<Repository> repositories) {
		HttpHeaders httpHeaders = Headers.createHeaders();
		logger.info("Starting the Contributors search for each repository.");
    	for (Repository repository : repositories) {
    		String url = "https://api.github.com/repos/thoughtworks/"+repository.getName()+"/contributors?access_token="+Headers.PERSONAL_ACCESS_TOKENS;
    		RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<? extends List<LinkedHashMap<String,Object>>> responseEntity = restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<Object>(httpHeaders),(Class<? extends List<LinkedHashMap<String,Object>>>)List.class);
		    List<LinkedHashMap<String,Object>> contributorsMap = (List<LinkedHashMap<String,Object>>) responseEntity.getBody();
			
		    if (contributorsMap == null) {
				String message = "Repository " + repository.getName() + " has no Contributors.";
				logger.info(message);
			} else {
				List<Contributor> contributors = new ArrayList<Contributor>();
				for(LinkedHashMap<String, Object> map : contributorsMap) {
					Contributor contributor = new Contributor();
					contributor.setLogin(map.get("login").toString());
					contributor.setContributions(map.get("contributions") != null ? Long.valueOf(map.get("contributions").toString()) : 0);
					contributors.add(contributor);
				}
				repository.setContributors(contributors);
			}
		}
	}
}
