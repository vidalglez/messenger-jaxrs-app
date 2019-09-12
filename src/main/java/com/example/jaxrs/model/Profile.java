package com.example.jaxrs.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Profile {

	private long id;
	private String profileName;
	private String firstName;
	private String lastName;
}
