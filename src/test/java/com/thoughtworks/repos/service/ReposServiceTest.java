package com.thoughtworks.repos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.thoughtworks.repos.model.Contributor;
import com.thoughtworks.repos.model.Repository;
import com.thoughtworks.repos.model.Response;
import com.thoughtworks.repos.repository.ReposRepository;
import com.thoughtworks.repos.service.impl.ReposServiceImpl;

@RunWith(SpringRunner.class)
public class ReposServiceTest {
	
	private ReposRepository reposRepository;
	private ReposService reposService;
	
	@Before
	public void setup() {
		reposRepository = mock(ReposRepository.class);
		reposService = new ReposServiceImpl(reposRepository);
	}
	
	@Test
	public void shouldFindAllRepositories() {
		Collection<Repository> repositories = new ArrayList<Repository>();
		Repository firstRepository = mockRepository();
		firstRepository.setName("Mock Repo 1");
		firstRepository.setLanguage("Mock Language 1");
		Repository secondRepository = mockRepository();
		secondRepository.setName("Mock Repo 2");
		secondRepository.setLanguage("Mock Language 2");
		repositories.add(firstRepository);
		repositories.add(secondRepository);
		
		doReturn(repositories).when(reposRepository).findAllRepositories();
		
		Response response = reposService.findAllLanguages();
		
		assertThat(response).isNotNull();
		
		verify(reposRepository, times(1)).findAllRepositories();
		verify(reposRepository, times(1)).findContributorsByRepository(repositories);
		
		assertThat(response.getThoughtworks()).isNotNull();
	}

	private Repository mockRepository() {
		Repository repository = new Repository();
		repository.setId(new Long(UUID.randomUUID().toString()));
		repository.setForks(5);
		repository.setStargazers(2);
		List<Contributor> contributors = new ArrayList<Contributor>();
		Contributor contributor = new Contributor();
		contributor.setLogin("Mock Login");
		contributor.setContributions(12);
		contributors.add(contributor);
		repository.setContributors(contributors);
		return repository;
	}
	
	
}
