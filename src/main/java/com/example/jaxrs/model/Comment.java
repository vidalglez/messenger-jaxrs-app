package com.example.jaxrs.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	private long id;
	private String author;
	private String comment;
	private Date created;
	
	
}
