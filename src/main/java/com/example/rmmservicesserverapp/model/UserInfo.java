package com.example.rmmservicesserverapp.model;

import java.security.Principal;

public class UserInfo implements Principal {

	private String name;

	private User user;

	public UserInfo(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
	
	
	
