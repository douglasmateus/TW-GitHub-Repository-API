/**
 * 
 */
package com.thoughtworks.repository;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.thoughtworks.repository.controller.RepositoriesController;

/**
 * Jersey resource configuration component.
 * 
 * @author douglas.mateus
 */
@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(RepositoriesController.class);
	}
}
