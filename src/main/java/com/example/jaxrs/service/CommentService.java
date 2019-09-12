package com.example.jaxrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.jaxrs.database.DatabaseClass;
import com.example.jaxrs.model.Comment;
import com.example.jaxrs.model.ErrorMessage;
import com.example.jaxrs.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllCommentsByMessageId(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<>(comments.values());
	}
	
	
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage error = new ErrorMessage("Not Found", 404, "http://localhost:8080/");
		Response response =  Response.status(Status.NOT_FOUND).entity(error).build();
		
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		
		Comment comment = comments.get(commentId);
		if(comment == null) {
			throw new NotFoundException(response);
		}
		return comments.get(commentId);
	}
}
