package com.example.jaxrs.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.jaxrs.database.DatabaseClass;
import com.example.jaxrs.exception.DataNotFoundException;
import com.example.jaxrs.model.Comment;
import com.example.jaxrs.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		Message m1 = new Message(1, "dev", "servidordenadie", new Date());
		
		Map<Long, Comment> comments = new HashMap<>();
		comments.put(1L, new Comment(1, "Commment 1 for servidor de nadie", "testuser", new Date()));
		comments.put(2L, new Comment(2, "Commment 2 for servidor de nadie", "testuser", new Date()));
		m1.setComments(comments);
		messages.put(1L, m1);
		
		
		Message m2 = new Message(2, "dev", "servidordenadie", new Date());
		comments = new HashMap<>();
		comments.put(1L, new Comment(1, "Commment 1 for servidor de nadie", "testuser", new Date()));
		comments.put(2L, new Comment(2, "Commment 2 for servidor de nadie", "testuser", new Date()));
		m2.setComments(comments);
		
		messages.put(2L, m2);
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	
	public List<Message> getAllMessagesByYear(int year) {
		List<Message> messagesByYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesByYear.add(message);
			}
		}
		return messagesByYear;
	}
	
	public List<Message> getPaginatedMessages(int start, int size){
		List<Message> paginatedMessages = new ArrayList<>(messages.values());
		if(start + size > paginatedMessages.size()) {
			return new ArrayList<>();
		}
		return paginatedMessages.subList(start, start + size);
	}
	

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException(String.format("Message with id %d was not found", id));
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}

}
