package com.example.jaxrs.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.example.jaxrs.beans.MessageFilterBean;
import com.example.jaxrs.model.Message;
import com.example.jaxrs.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
//@Consumes(value= {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value= {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {

	// @Inject
	MessageService service = new MessageService();;

	/*
	 * public MessageResource(MessageService service) { this.service = service; }
	 */

	@GET
	// @Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		if (year > 0) {
			return service.getAllMessagesByYear(year);
		}

		if (start >= 0 && size > 0) {
			return service.getPaginatedMessages(start, size);
		}
		return service.getAllMessages();
	}

	@GET
	@Path("filterbean")
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return service.getAllMessagesByYear(filterBean.getYear());
		}

		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return service.getPaginatedMessages(filterBean.getStart(), filterBean.getSize());
		}
		return service.getAllMessages();
	}

	@GET
	@Path("/{id}")
	// @Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("id") long id, @Context UriInfo uriInfo) {
		Message message = service.getMessage(id);
		message.addLink(this.getUriForSelf(uriInfo, message), "self");
		//TODO: Missing to add profiles link
		message.addLink(this.getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForSelf(UriInfo uri, Message message) {
		return uri.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build()
				.toString();
	}

	private String getUriForComments(UriInfo uri, Message message) {
		return uri.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentsResource.class)
				.resolveTemplate("messageId", message.getId()).build().toString();
	}

	@POST
	// public Message addMessage(Message message) {
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = service.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(message.getId())).build();
		return Response.created(uri).status(Status.CREATED).entity(newMessage).build();
		// return Response.status(Status.CREATED).entity(newMessage).build();
		// return service.addMessage(message);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addXMLMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = service.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(message.getId())).build();
		return Response.created(uri).status(Status.CREATED).entity(newMessage).build();
		// return Response.status(Status.CREATED).entity(newMessage).build();
		// return service.addMessage(message);
	}

	@PUT
	@Path("/{id}")
	public Message updateMessage(@PathParam("id") long id, Message message) {
		message.setId(id);
		return service.updateMessage(message);
	}

	@DELETE
	@Path("/{id}")
	public Message deleteMessage(@PathParam("id") long id) {
		return service.removeMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentsResource getCommentResource() {
		return new CommentsResource();
	}

}
