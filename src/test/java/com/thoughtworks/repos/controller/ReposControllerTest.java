package com.thoughtworks.repos.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.Language;
import com.thoughtworks.repos.model.TWGitHubResponse;
import com.thoughtworks.repos.model.ThoughtworksRepositories;
import com.thoughtworks.repos.model.TopRepository;

public class ReposControllerTest {

	@InjectMocks
	private ReposController reposControllerTest;
	
	@Test
	public void shouldReturnSuccessAllLanguages() {
		
		TWGitHubResponse twGitHubResponse = mockThoughtworksRepositories();
		
		TWGitHubResponse response = reposControllerTest.getAllLanguages();
		
		assertThat(twGitHubResponse.getThoughtworksRepositories()).isIn(response.getThoughtworksRepositories());
		
		
		
	}

	private TWGitHubResponse mockThoughtworksRepositories() {
		TWGitHubResponse twGitHubResponse = new TWGitHubResponse();
		ThoughtworksRepositories thoughtworksRepositories = new ThoughtworksRepositories();
		List<Language> languages = new ArrayList<Language>();
		List<Contributor> topContributors = new ArrayList<Contributor>();
		topContributors.add(new Contributor("durran", 2314));
		topContributors.add(new Contributor("jnunemaker", 802));
		topContributors.add(new Contributor("alexeyv", 355));
		languages.add(new Language("Ruby", 163, 688, 255, "cruisecontrol.rb, cruisecontrol.rb-contrib, letshelp.it", topContributors));
		thoughtworksRepositories.setLanguages(languages);
		
		List<TopRepository> topRepositories = new ArrayList<TopRepository>();
		topContributors = new ArrayList<Contributor>();
		topRepositories.add(new TopRepository("Ruby", 163, 688, 255, "cruisecontrol.rb, cruisecontrol.rb-contrib, letshelp.it", topContributors, 1));
		thoughtworksRepositories.setTopRepositories(topRepositories);
		twGitHubResponse.setThoughtworksRepositories(thoughtworksRepositories);
		
		return twGitHubResponse;
	}
}
