package com.example.jaxrs.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.jaxrs.exception.DataNotFoundException;
import com.example.jaxrs.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 404, "http://localhost:8080/");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
