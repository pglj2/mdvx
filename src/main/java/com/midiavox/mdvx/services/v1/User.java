package com.midiavox.mdvx.services.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	

	public User (String name,String password, String email){
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "password")
	private String password;
	
	@XmlElement(name = "email")
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}