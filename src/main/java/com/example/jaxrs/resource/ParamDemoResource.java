package com.example.jaxrs.resource;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/demoparam")
public class ParamDemoResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDemoParam(@MatrixParam("QueryParam") String queryParam,
			@HeaderParam("HeaderParam") String headerParam, @CookieParam("CookieParam") String cookieParam) {
		return String.format("@QueryParam QueryParam=%s, @HeaderParam HeaderParam=%s, @CookieParam CookieParam=%s",
				queryParam, headerParam, cookieParam);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		return String.format("Absolute Path: %s \nCookies: %s", uriInfo.getAbsolutePath().toString(),
				headers.getCookies().toString());
	}

}
