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
import com.thoughtworks.repos.model.GitHubRepository;
import com.thoughtworks.repos.model.Language;
import com.thoughtworks.repos.model.TWGitHubResponse;
import com.thoughtworks.repos.model.ThoughtworksRepositories;
import com.thoughtworks.repos.model.TopRepository;
import com.thoughtworks.repos.repository.GitHubReposRepository;
import com.thoughtworks.repos.service.GitHubReposService;

/**
 * Basic Repository service implementation.
 * 
 * @author douglas.mateus
 */
@Service
public class GitHubReposServiceImpl implements GitHubReposService {

	private static final int MAX_TOP_LANGUAGES_CONTRIBUTORS = 3;

	private GitHubReposRepository reposRepository;
	
	private Logger logger = Logger.getLogger(GitHubReposServiceImpl.class);
	
	public GitHubReposServiceImpl(@Autowired GitHubReposRepository reposRepository) {
		this.reposRepository = reposRepository;
	}

	/**
	 * Retrieves TWGitHubResponse entity
	 * @return TWGitHubResponse
	 */
	@Override
	public TWGitHubResponse findAllLanguages() {
		TWGitHubResponse tWGitHubResponse = new TWGitHubResponse();
		ThoughtworksRepositories thoughtworksRepositories = new ThoughtworksRepositories();
		List<Language> languages = new ArrayList<Language>();
		
		logger.info("Find All Repositories");
		List<GitHubRepository> repositories = reposRepository.findAllRepositories();
		reposRepository.setContributorsByRepository(repositories);
		
		logger.info("Organize repositories by languages");
		Map<String, Language> languagesMap = this.createLanguages(repositories);
		for (String keySet : languagesMap.keySet()) {
			languages.add(languagesMap.get(keySet));
		}
		thoughtworksRepositories.setLanguages(languages);

		logger.info("Sort Top Repositories");
		List<TopRepository> topRepositories = this.createTopRepositories(languages);
		thoughtworksRepositories.setTopRepositories(topRepositories);
		tWGitHubResponse.setThoughtworksRepositories(thoughtworksRepositories);
		
		return tWGitHubResponse;
	}

	/**
	 * Retrieves a map with all valid languages
	 * @param repositories
	 * @return Map<String, Languages>
	 */
	public Map<String, Language> createLanguages(Collection<GitHubRepository> repositories) {
		Map<String, Language> languagesMap = new HashMap<String, Language>();
		for (GitHubRepository repository : repositories) {
			String repositoryName = repository.getName();
			long stars = repository.getStargazers_count();
			long forks = repository.getForks_count();
			long contributors = repository.getContributors() != null && repository.getContributors().size() > 0 ? repository.getContributors().size() : 0;
			List<Contributor> topContributors = repository.getContributors();
			if (repository.getLanguage() == null) {
				logger.info("Repository " + repositoryName + " contains language name null.");
			} else if (languagesMap.containsKey(repository.getLanguage())) {
				
				Language  language = languagesMap.get(repository.getLanguage());
				stars = stars + language.getStars();
				forks = forks + language.getForks();
				contributors = contributors + language.getContributors();
				String repositoriesNames = language.getRepositories() + ", " + repositoryName;

				language.setRepositories(repositoriesNames);
				language.setStars(stars);
				language.setForks(forks);
				language.setContributors(contributors);
				if (language.getTopContributors() == null && topContributors != null && topContributors.size() > 0) {
					language.setTopContributors(topContributors);
				} else if (topContributors != null && topContributors.size() > 0) {
					language.getTopContributors().addAll(topContributors);
				}
				languagesMap.put(repository.getLanguage(), language);
			} else {
				languagesMap.put(repository.getLanguage(), new Language(repository.getLanguage(), contributors, stars, forks, repositoryName, topContributors));
			}
		}
		return languagesMap;
	}

	/**
	 * Retrieves a list with the 3 top languages and yours 3 top contributors 
	 * @param languages
	 * @return List<TopRepositories>
	 */
	public List<TopRepository> createTopRepositories(List<Language> languages) {
		List<TopRepository> topRepositories = new ArrayList<TopRepository>();
		Collections.sort(languages);
		int sizeLanguages = languages.size() > MAX_TOP_LANGUAGES_CONTRIBUTORS ? MAX_TOP_LANGUAGES_CONTRIBUTORS : languages.size();
		for (int i = 0; i < sizeLanguages; i++) {
			String language = languages.get(i).getLanguage();
			int position = i + 1;
			long contributors = languages.get(i).getContributors();
			long stars = languages.get(i).getStars();
			long forks = languages.get(i).getForks();
			String repositories = languages.get(i).getRepositories();
			List<Contributor> top3Contributors = new ArrayList<Contributor>();
			
			if (languages.get(i).getTopContributors() == null) {
				logger.info("Language "+ language +" has no Contributors.");
			} else {
				List<Contributor> topContributors = languages.get(i).getTopContributors();
				Collections.sort(topContributors);
				int sizeTopContributors = topContributors.size() > MAX_TOP_LANGUAGES_CONTRIBUTORS ? MAX_TOP_LANGUAGES_CONTRIBUTORS : topContributors.size();
				for (int j = 0; j < sizeTopContributors; j++) {
					top3Contributors.add(topContributors.get(j));
				}
			}
			topRepositories.add(new TopRepository(language, contributors, stars, forks, repositories, top3Contributors, position));
		}
		return topRepositories;
	}
}
