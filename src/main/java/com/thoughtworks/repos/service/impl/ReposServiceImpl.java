package com.thoughtworks.repos.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.Languages;
import com.thoughtworks.repos.model.Repository;
import com.thoughtworks.repos.model.Response;
import com.thoughtworks.repos.model.Thoughtworks;
import com.thoughtworks.repos.model.TopRepositories;
import com.thoughtworks.repos.repository.ReposRepository;
import com.thoughtworks.repos.service.ReposService;

/**
 * Basic Repository service implementation.
 * 
 * @author douglas.mateus
 */
@Service
public class ReposServiceImpl implements ReposService {

	private ReposRepository reposRepository;
	
	private Logger logger = Logger.getLogger(ReposServiceImpl.class);
	
	public ReposServiceImpl(@Autowired ReposRepository reposRepository) {
		this.reposRepository = reposRepository;
	}

	@Override
	public Response findAllLanguages() {
		
		Collection<Repository> repositories = reposRepository.findAllRepositories();
		reposRepository.findContributorsByRepository(repositories);
		return buildReponse(repositories);
	}

	public Response buildReponse(Collection<Repository> repositories) {
		Response response = new Response();
		Thoughtworks thoughtworks = new Thoughtworks();
		List<Languages> languages = new ArrayList<Languages>();
		
		logger.info("Create Languages");
		Map<String, Languages> languagesMap = this.createLanguages(repositories);		
		for (String keySet : languagesMap.keySet()) {
			languages.add(languagesMap.get(keySet));
		}
		thoughtworks.setLanguages(languages);

		logger.info("Create Top Repositories");
		List<TopRepositories> topRepositories = this.createTopRepositories(languages);
		thoughtworks.setTopRepositories(topRepositories);
		
		response.setThoughtworks(thoughtworks);
		
		return response;
	}
	
	public Map<String, Languages> createLanguages(Collection<Repository> repositories) {
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

	public List<TopRepositories> createTopRepositories(List<Languages> languages) {
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
}
