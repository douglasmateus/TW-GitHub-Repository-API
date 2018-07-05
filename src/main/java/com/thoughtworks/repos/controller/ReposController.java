package com.thoughtworks.repos.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.repos.model.Response;
import com.thoughtworks.repos.service.ReposService;

/**
 * RepositoriesController provides REST operations for the repository resource 
 * @author douglas.mateus
 *
 */
@Component
@Consumes( { "text/xml", "application/json" })
@Produces( { "text/xml", "application/json" })
@Path("/repositories")
public class ReposController {

	@Autowired
	private ReposService reposService;
	
	/**
	 * Gets all languages
	 * @return thoughtworks repository structure
	 */
	@GET
	public Response getAllLanguages() {
		return reposService.findAllLanguages();
	}
}
