package com.thoughtworks.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
public class SpringBootApplicationTest {

	@Test
	public void serverError() throws Exception {
		DefaultResponseCreator responseCreator = MockRestResponseCreators.withServerError();
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
	}
	
	@Test
	public void withStatus() throws Exception {
		DefaultResponseCreator responseCreator = MockRestResponseCreators.withStatus(HttpStatus.FORBIDDEN);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
	}
}
