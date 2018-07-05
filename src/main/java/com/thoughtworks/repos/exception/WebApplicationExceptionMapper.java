package com.thoughtworks.repos.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.thoughtworks.repos.model.ErrorMessage;
import com.thoughtworks.repos.util.CoverageIgnore;

/**
 * Converts an WebApplicationException into a 404 HTTP response.
 * 
 * @author douglas.mateus
 */
@CoverageIgnore
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException wae) {
		return Response.fromResponse(wae.getResponse())
				.entity(new ErrorMessage(wae.getResponse().getStatus(), wae.getMessage())).build();
	}

}
