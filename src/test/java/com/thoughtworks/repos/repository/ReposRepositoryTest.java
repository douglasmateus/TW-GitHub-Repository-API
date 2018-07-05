package com.thoughtworks.repos.repository;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReposRepository.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReposRepositoryTest {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/thoughtworks/repositories"),
				HttpMethod.GET, entity, String.class);

		String expected = "{thoughtworks:{languages:{language:Ruby,contributors:163,stars:688,forks:255,repositories:mongoid},topRepositories:{language:Ruby,position:1,contributors:163,stars:688,forks:255,repository:mongoid,topContributors:{login:durran,contributions:2314}}}}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
