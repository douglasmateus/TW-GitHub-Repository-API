package com.thoughtworks.repository.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.repository.model.Response;
import com.thoughtworks.repository.service.RepositoryService;

/**
 * RepositoriesController provides REST operations for the repository resource 
 * @author douglas.mateus
 *
 */
@Component
@Consumes( { "text/xml", "application/json" })
@Produces( { "text/xml", "application/json" })
@Path("/repositories")
public class RepositoriesController {

	private Logger logger = Logger.getLogger(RepositoriesController.class);
	
	@Autowired
	private RepositoryService repositoryService;
	
	/**
	 * Gets all repositories
	 * @return all repositories
	 */
	@GET
	public Response getAllLanguages() {
		
		logger.info("Starting findAllLanguages");
		return repositoryService.findAllLanguages();
	}
}
