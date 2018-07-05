package com.thoughtworks.repos.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.thoughtworks.repos.model.ErrorMessage;
import com.thoughtworks.repos.util.CoverageIgnore;

/**
 * Converts an EntityNotFoundException into a corresponding HTTP response.
 * 
 * @author douglas.mateus
 *
 */
@CoverageIgnore
@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

	@Override
	public Response toResponse(EntityNotFoundException enfe) {
		return Response.status(Status.NOT_FOUND)
				.entity(new ErrorMessage(Status.NOT_FOUND.getStatusCode(), enfe.getMessage())).build();
	}
}
