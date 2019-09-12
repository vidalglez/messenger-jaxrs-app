package com.example.jaxrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.jaxrs.database.DatabaseClass;
import com.example.jaxrs.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("guitar", new Profile(1, "guitar", "Gustavo", "Cerati") );
		//this.addProfile(new Profile(0, "guitar", "Gustavo", "Cerati"));
		//this.addProfile(new Profile(0, "vocal", "Enrique", "Bunbury"));
		
	}
	
	public List<Profile> getProfiles(){		
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
