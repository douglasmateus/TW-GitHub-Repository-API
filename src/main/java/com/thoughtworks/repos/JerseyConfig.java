/**
 * 
 */
package com.thoughtworks.repos;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.thoughtworks.repos.controller.ReposController;

/**
 * Jersey resource configuration component.
 * 
 * @author douglas.mateus
 */
@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ReposController.class);
	}
}
