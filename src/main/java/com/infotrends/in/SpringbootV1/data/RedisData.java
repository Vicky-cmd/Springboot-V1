package com.infotrends.in.SpringbootV1.data;

import java.io.Serializable;

public class RedisData implements Serializable {

	private String username;
	private String email;
	private String fullname;
	
	
	
	public RedisData() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
