package com.midiavox.mdvx;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.midiavox.mdvx.services.v1.User;

public class BusinessManager {
	private static final Logger log = Logger.getLogger(BusinessManager.class.getName());
	private static BusinessManager INSTANCE = new BusinessManager();
	
	public static BusinessManager getInstance(){
		return INSTANCE;
	}
	
	private BusinessManager(){
		
	}
	
	public User findUser(String userId) {
		log.info("BusinessManager::findUser started");
			
		return DataManager.getInstance().findUserById(userId);
	}
	
	//loginUser(password)
	public User loginUser(String login, String password){
		log.info("BusinessManager::loginUser started");
		return DataManager.getInstance().findUserByPassword(login, password);
	}
	public List<User> findUsers(){
		
		return DataManager.getInstance().findAllUsers();
		
	}
	
	public User addUser(User user){
		DataManager.getInstance().insertUser(user);
		return user;
	}
	
	
	public User updateUserAttribute(String userId, String attribute, String value){
		
		User user = new User();
		
		user.setId(userId);
		
		if(attribute.equals("name"))
			user.setName(value);
		
		return user;
	}
	
	public void deleteUser(String userId) {
		
		DataManager.getInstance().deleteUser(userId);
	}
	
}
