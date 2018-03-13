package com.midiavox.mdvx;

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
		
		User user = new User();
		
		user.setId("112233");
		user.setName("Paul Lasalvia");
		
		return user;
	}
	
	
}
