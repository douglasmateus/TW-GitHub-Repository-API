package com.thoughtworks.repository.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repository.exception.EntityNotFoundException;
import com.thoughtworks.repository.model.Contributor;
import com.thoughtworks.repository.model.Languages;
import com.thoughtworks.repository.model.Repository;
import com.thoughtworks.repository.model.Response;
import com.thoughtworks.repository.model.Thoughtworks;
import com.thoughtworks.repository.model.TopRepositories;
import com.thoughtworks.repository.service.RepositoryService;

/**
 * Basic repository service implementation.
 * 
 * @author douglas.mateus
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {

	public static final String PERSONAL_ACCESS_TOKENS = "25c48a58d1ef0dcd3550bef0db323cc5e96ddc79";
	public static final String THOUGHTWORKS_REPOSITORY = "https://api.github.com/orgs/thoughtworks/repos?access_token="+PERSONAL_ACCESS_TOKENS;
	
	
	private Logger logger = Logger.getLogger(RepositoryServiceImpl.class);
	
	@Override
	public Response findAllLanguages() {
		
		Collection<Repository> repositories = findAllRepositories();
		this.findContributorsByRepository(repositories);
		return this.buildReponse(repositories);
	}

	@SuppressWarnings("unchecked")
	private Collection<Repository> findAllRepositories() {
		Collection<Repository> repositories = new ArrayList<Repository>();
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders = this.createHeaders();
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
	public void findContributorsByRepository(Collection<Repository> repositories) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders = this.createHeaders();
		logger.info("Starting the Contributors search for each repository.");
    	for (Repository repository : repositories) {
    		String url = "https://api.github.com/repos/thoughtworks/"+repository.getName()+"/contributors?access_token="+PERSONAL_ACCESS_TOKENS;
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

	public Response buildReponse(Collection<Repository> repositories) {
		Response response = new Response();
		Thoughtworks thoughtworks = new Thoughtworks();
		List<Languages> languages = new ArrayList<Languages>();
		
		logger.info("Create Languages");
		Map<String, Languages> languagesMap = createLanguagesMap(repositories);		
		for (String keySet : languagesMap.keySet()) {
			languages.add(languagesMap.get(keySet));
		}
		thoughtworks.setLanguages(languages);

		logger.info("Create Top Repositories");
		List<TopRepositories> topRepositories = createTopRepositories(languages);
		thoughtworks.setTopRepositories(topRepositories);
		
		response.setThoughtworks(thoughtworks);
		
		return response;
	}

	private List<TopRepositories> createTopRepositories(List<Languages> languages) {
		List<TopRepositories> topRepositories = new ArrayList<TopRepositories>();
		Collections.sort(languages);
		int sizeLanguages = languages.size() > 3 ? 3 : languages.size();
		for (int i = 0; i < sizeLanguages; i++) {
			TopRepositories topRepository = new TopRepositories();
			topRepository.setLanguage(languages.get(i).getLanguage());
			topRepository.setPosition(i + 1);
			topRepository.setContributors(languages.get(i).getContributors());
			topRepository.setStars(languages.get(i).getStars());
			topRepository.setForks(languages.get(i).getForks());
			topRepository.setRepository(languages.get(i).getRepositories());
			
			List<Contributor> topContributors = languages.get(i).getTopContributors();
			Collections.sort(topContributors);
			int sizeTopContributors = topContributors.size() > 3 ? 3 : topContributors.size();
			List<Contributor> top3Contributors = new ArrayList<Contributor>();
			for (int j = 0; j < sizeTopContributors; j++) {
				top3Contributors.add(topContributors.get(j));
			}
			topRepository.setTopContributors(top3Contributors);
			topRepositories.add(topRepository);
		}
		return topRepositories;
	}

	private Map<String, Languages> createLanguagesMap(Collection<Repository> repositories) {
		Map<String, Languages> languagesMap = new HashMap<String, Languages>();
		for (Repository repository : repositories) {
			String repositoryName = repository.getName();
			long stargazers = repository.getStargazers();
			long forks = repository.getForks();
			long contributors = repository.getContributors() != null && repository.getContributors().size() > 0
					? repository.getContributors().size() : 0;
			List<Contributor> topContributors = repository.getContributors();
			Languages languages = new Languages();
			if (repository.getLanguage() == null) {
				logger.info("Repository " + repositoryName + " contains language name null.");
			} else if (languagesMap.containsKey(repository.getLanguage())) {
				languages = languagesMap.get(repository.getLanguage());
				stargazers = stargazers + languages.getStars();
				forks = forks + languages.getForks();
				contributors = contributors + languages.getContributors();

				languages.setRepositories(languages.getRepositories() + ", " + repositoryName);
				languages.setStars(stargazers);
				languages.setForks(forks);
				languages.setContributors(contributors);
				if (languages.getTopContributors() == null && topContributors != null && topContributors.size() > 0) {
					languages.setTopContributors(topContributors);
				} else if (topContributors != null && topContributors.size() > 0) {
					languages.getTopContributors().addAll(topContributors);
				}
				languagesMap.put(repository.getLanguage(), languages);
			} else {
				languages.setLanguage(repository.getLanguage());
				languages.setRepositories(repositoryName);
				languages.setStars(stargazers);
				languages.setForks(forks);
				languages.setContributors(contributors);
				languages.setTopContributors(topContributors);
				languagesMap.put(repository.getLanguage(), languages);
			}
		}
		return languagesMap;
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = -1643427945865752875L;
			{
				set("Authorization", PERSONAL_ACCESS_TOKENS);
			}
		};
		return headers;
	}
}
