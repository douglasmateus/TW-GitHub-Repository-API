package com.thoughtworks.repos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.thoughtworks.repos.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReposRepositoryTest {
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void shouldFindAllRepositories() throws JSONException {

		ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:3000/thoughtworks/repositories", String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
