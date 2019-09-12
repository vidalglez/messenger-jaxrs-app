package com.example.jaxrs.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jaxrs.model.Comment;
import com.example.jaxrs.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentsResource {

	private CommentService service = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return service.getAllCommentsByMessageId(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getCommentById(@PathParam("messageId") long  messageId, @PathParam("commentId") long commentId){
		return service.getComment(messageId, commentId);
	}
	
	
}
