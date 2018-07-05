/**
 * 
 */
package com.thoughtworks.repos;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.thoughtworks.repos.controller.ReposController;
import com.thoughtworks.repos.exception.EntityNotFoundExceptionMapper;
import com.thoughtworks.repos.exception.ValidationExceptionMapper;
import com.thoughtworks.repos.exception.WebApplicationExceptionMapper;

/**
 * Jersey resource configuration component.
 * 
 * @author douglas.mateus
 */
@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ReposController.class);
		register(EntityNotFoundExceptionMapper.class);
		register(ValidationExceptionMapper.class);
		register(WebApplicationExceptionMapper.class);
	}
}
