package com.example.jaxrs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Message {

	private long id;
	private String message;
	private String author;
	private Date created;
	private Map<Long, Comment> comments = new HashMap<>();
	private List<Link> links = new ArrayList<>();
	
	public Message(long id, String message, String author, Date created) {
		this.id= id;
		this.message = message;
		this.author = author;
		this.created = created;
	}
	
	@XmlTransient
	public Map<Long, Comment> getComments(){
		return comments;
	}
	
	public void addLink(String url, String type) {
		links.add(new Link(url, type));
	}

}
