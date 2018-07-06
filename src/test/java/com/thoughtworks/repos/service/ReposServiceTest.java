package com.thoughtworks.repos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.GitHubRepository;
import com.thoughtworks.repos.model.TWGitHubResponse;
import com.thoughtworks.repos.repository.GitHubReposRepository;
import com.thoughtworks.repos.service.impl.GitHubReposServiceImpl;

@RunWith(SpringRunner.class)
public class ReposServiceTest {
	
	private GitHubReposRepository reposRepository;
	private GitHubReposService reposService;
	
	@Before
	public void setup() {
		reposRepository = mock(GitHubReposRepository.class);
		reposService = new GitHubReposServiceImpl(reposRepository);
	}
	
	@Test
	public void shouldFindAllRepositories() {
		Collection<GitHubRepository> gitHubRepositories = new ArrayList<GitHubRepository>();
		GitHubRepository firstRepository = mockRepository();
		firstRepository.setId(Long.valueOf(1));
		GitHubRepository secondRepository = mockRepository();
		secondRepository.setId(Long.valueOf(2));;
		gitHubRepositories.add(firstRepository);
		gitHubRepositories.add(secondRepository);
		
		doReturn(gitHubRepositories).when(reposRepository).findAllRepositories();
		
		TWGitHubResponse response = reposService.findAllLanguages();
		
		assertThat(response).isNotNull();
		
		verify(reposRepository, times(1)).findAllRepositories();
		verify(reposRepository, times(1)).findContributorsByRepository(gitHubRepositories);
		
		assertThat(response.getThoughtworksRepositories()).isNotNull();
	}

	private GitHubRepository mockRepository() {
		GitHubRepository repository = new GitHubRepository(1,"Mock Repo","Mock Language",2,5);
		List<Contributor> contributors = new ArrayList<Contributor>();
		contributors.add(new Contributor("mockLogin", 12));
		repository.setContributors(contributors);
		return repository;
	}
	
	
}
