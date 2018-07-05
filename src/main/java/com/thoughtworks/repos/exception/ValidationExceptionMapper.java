package com.thoughtworks.repos.exception;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.thoughtworks.repos.model.ErrorMessage;
import com.thoughtworks.repos.util.CoverageIgnore;

/**
 * Converts an ValidationException into a 400 HTTP response.
 * 
 * @author douglas.mateus
 *
 */
@CoverageIgnore
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException ve) {
		return Response.status(Status.BAD_REQUEST)
				.entity(new ErrorMessage(Status.BAD_REQUEST.getStatusCode(), "Invalid entity data.")).build();
	}

}
