package com.thoughtworks.repos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.research.ws.wadl.Response;
import com.thoughtworks.repos.model.ErrorMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
public class SpringBootApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void shouldListAllLanguages() throws Exception {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/repositories", String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		List<Response> body = this.parseJsonArray(response.getBody(), Response.class);
		
		assertThat(body).isNotEmpty();
		
		assertThat(body).hasSize(1);
	}
	
	public void shouldAvoidAccess() {
		ResponseEntity<ErrorMessage> response = this.restTemplate.getForEntity("/repositories", ErrorMessage.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

		ErrorMessage body = response.getBody();

		assertThat(body).isNotNull();

		assertThat(body.getCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
	}
	
	private <T> List<T> parseJsonArray(String json, Class<T> resultClass) throws Exception {
		return objectMapper.readValue(json,
				objectMapper.getTypeFactory().constructCollectionType(List.class, resultClass));
	}
	
	
}
