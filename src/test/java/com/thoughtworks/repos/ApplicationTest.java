package com.thoughtworks.repos;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.thoughtworks.repos.model.Repository;
import com.thoughtworks.repos.repository.ReposRepository;
import com.thoughtworks.repos.service.impl.ReposServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
public class ApplicationTest {
	
    @MockBean
    private ReposRepository reposRepository;
    
    private Collection<Repository> repositories;
    
	@Before
	public void setUp() throws Exception {
		ReposServiceImpl reposServiceImpl = new ReposServiceImpl(reposRepository);
		
		repositories = new ArrayList<Repository>();
		Repository repository = new Repository();
		repository.setId(12345);
		repository.setName("rapidsMRS");
		repository.setLanguage("Java");
		repository.setStargazers(5);
		repository.setForks(3);
		repositories.add(repository);
	}
    
    
    @Test
	public void shouldRetrieveRepository() throws Exception {
		//ResponseEntity<Response> response = this.restTemplate.getForEntity("/repositories", Response.class);
		
		
	}
	
}
