package com.midiavox.mdvx.services.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public User(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String id, String password){
		this.name = name;
		this.id = id;
		this.password = password;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "password")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}