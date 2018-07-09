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
import com.thoughtworks.repos.model.Languages;
import com.thoughtworks.repos.model.TWGitHubResponse;
import com.thoughtworks.repos.model.ThoughtworksRepositories;
import com.thoughtworks.repos.model.TopRepositories;
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
	 * Retrieves Response entity
	 * @return Response
	 */
	@Override
	public TWGitHubResponse findAllLanguages() {
		TWGitHubResponse response = new TWGitHubResponse();
		ThoughtworksRepositories thoughtworks = new ThoughtworksRepositories();
		List<Languages> languages = new ArrayList<Languages>();
		
		logger.info("Find All Repositories");
		List<GitHubRepository> repositories = reposRepository.findAllRepositories();
		reposRepository.setContributorsByRepository(repositories);
		
		logger.info("Organize repositories by languages");
		Map<String, Languages> languagesMap = this.createLanguages(repositories);
		for (String keySet : languagesMap.keySet()) {
			languages.add(languagesMap.get(keySet));
		}
		thoughtworks.setLanguages(languages);

		logger.info("Sort Top Repositories");
		List<TopRepositories> topRepositories = this.createTopRepositories(languages);
		thoughtworks.setTopRepositories(topRepositories);
		
		response.setThoughtworksRepositories(thoughtworks);
		
		return response;
	}

	/**
	 * Retrieves a map with all valid languages
	 * @param repositories
	 * @return Map<String, Languages>
	 */
	public Map<String, Languages> createLanguages(Collection<GitHubRepository> repositories) {
		Map<String, Languages> languagesMap = new HashMap<String, Languages>();
		for (GitHubRepository repository : repositories) {
			String repositoryName = repository.getName();
			long stargazers = repository.getStargazers_count();
			long forks = repository.getForks_count();
			long contributors = repository.getContributors() != null && repository.getContributors().size() > 0 ? repository.getContributors().size() : 0;
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

	/**
	 * Retrieves a list with the 3 top languages and yours 3 top contributors 
	 * @param languages
	 * @return List<TopRepositories>
	 */
	public List<TopRepositories> createTopRepositories(List<Languages> languages) {
		List<TopRepositories> topRepositories = new ArrayList<TopRepositories>();
		Collections.sort(languages);
		int sizeLanguages = languages.size() > MAX_TOP_LANGUAGES_CONTRIBUTORS ? MAX_TOP_LANGUAGES_CONTRIBUTORS : languages.size();
		for (int i = 0; i < sizeLanguages; i++) {
			TopRepositories topRepository = new TopRepositories();
			topRepository.setLanguage(languages.get(i).getLanguage());
			topRepository.setPosition(i + 1);
			topRepository.setContributors(languages.get(i).getContributors());
			topRepository.setStars(languages.get(i).getStars());
			topRepository.setForks(languages.get(i).getForks());
			topRepository.setRepository(languages.get(i).getRepositories());
			if (languages.get(i).getTopContributors() == null) {
				logger.info("Language "+ languages.get(i).getLanguage() +" has no Contributors.");
			} else {
				List<Contributor> topContributors = languages.get(i).getTopContributors();
				Collections.sort(topContributors);
				int sizeTopContributors = topContributors.size() > MAX_TOP_LANGUAGES_CONTRIBUTORS ? MAX_TOP_LANGUAGES_CONTRIBUTORS : topContributors.size();
				List<Contributor> top3Contributors = new ArrayList<Contributor>();
				for (int j = 0; j < sizeTopContributors; j++) {
					top3Contributors.add(topContributors.get(j));
				}
				topRepository.setTopContributors(top3Contributors);
			}
			topRepositories.add(topRepository);
		}
		return topRepositories;
	}
}
