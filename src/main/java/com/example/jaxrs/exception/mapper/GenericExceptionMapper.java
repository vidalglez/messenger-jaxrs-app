package com.example.jaxrs.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.example.jaxrs.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 500, "http://localhost:8080/");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

}
