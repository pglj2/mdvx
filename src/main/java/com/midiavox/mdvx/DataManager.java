package com.midiavox.mdvx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;

import com.midiavox.mdvx.services.v1.User;

public class DataManager {
	private static final Logger log = Logger.getLogger(DataManager.class.getName());
	
	private static DataManager INSTANCE;
	
	public static DataManager getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new DataManager();
		}

		return INSTANCE;
	}
	private DataManager(){

	}
	
	public ResultSet connectionDB(int query, User user){
		try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ResultSet result = null;
        System.out.println("Driver Loaded");
        
        String url2 = "jdbc:sqlserver://DESKTOP-4NMBH5O\\SQLEXPRESS:52497;databaseName=MidiaVoxAgentes;";
        
        Connection con = DriverManager.getConnection(url2, "pglj2", "gogo");
        //String url2 = "jdbc:sqlserver://SIEBEL16;databaseName=mdvx";
        //Connection con = DriverManager.getConnection(url2, "midiavox", "aagt");
        System.out.println("Connection OK");
        Statement s1 = con.createStatement();
        System.out.println("Statemente OK");
        switch(query){
        case 1: //findAllUsers and findUserById
        	 result = s1.executeQuery("SELECT * FROM AGENTE;");
        	 break;
        case 2:
        	result = s1.executeQuery("UPDATE AGENTE SET Password= '"+user.getPassword()+"', Email= '"+user.getEmail()+"' WHERE Name = '"+user.getName()+"';");
        	break;
        case 3: //insertUser
        	result = s1.executeQuery("INSERT INTO AGENTE VALUES ('"+user.getName()+"', '"+user.getPassword()+"', '"+user.getEmail()+"');");
        	break;
        case 4: //deleteUser
        	result = s1.executeQuery("DELETE FROM AGENTE WHERE Name='"+user.getName()+"';");
        	break;
        }
        
        return result;
        
		} catch(Exception e){
			
		}
		return null;
		
	}
	
	public User insertUser(User user){
		this.connectionDB(3, user);	
		log.info("un "+ user.getName());
		return user;
		
	}
	
	public User updateUser(User user){
		this.connectionDB(2, user);
		return user;
	}
	
	public void deleteUser(String userIdString){
		//User u = this.findUserById(userIdString);
		User u = new User(userIdString, null, null); 
		this.connectionDB(4, u);
	}
	
	public User findUserById(String userIdString){
		//User user = new User();
		ResultSet rs = this.connectionDB(1, null);
        if(rs!=null){
        	try {
				while(rs.next()){
					if(rs.getString("name").equals(userIdString)){
					User user = new User((rs.getString("name")), (rs.getString("password")),(rs.getString("email")) );
					log.info("Username:"+user.getName()+" email:"+user.getEmail());
					return user;
					}
					//users.add(u);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return null;
	}
	
	public User findUserByPassword(String login, String password){
		log.info("DataManager::findUserByPassword started");
		ResultSet rs = this.connectionDB(1, null);
		if(rs!=null){
			try{
				while(rs.next()){
					if(rs.getString("name").equals(login) && rs.getString("password").equals(password)){
						User user = new User((rs.getString("name")),(rs.getString("password")),(rs.getString("email")));
						log.info("The user:"+user.getName()+/*" password:"+user.getPassword() +*/" LoggedIn");
						return user;
					}
				}
			} catch(SQLException e){
				e.printStackTrace();
			};
			
			
		}
		
		return null;
	}
	//or Email
	public User findUserByLoginOrEmail(String login, String email){
		log.info("DataManager::findUserByPassword started");
		ResultSet rs = this.connectionDB(1, null);
		if(rs!=null){
			try{
				while(rs.next()){
					if(rs.getString("name").equals(login) || rs.getString("password").equals(email)){
						User user = new User((rs.getString("name")),(rs.getString("password")),(rs.getString("email")));
						log.info("The user:"+user.getName()+" email:"+user.getEmail() +" LoggedIn");
						return user;
					}
				}
			} catch(SQLException e){
				e.printStackTrace();
			};
			
			
		}
		
		return null;
	}
	
	public List<User> findAllUsers(){
		List<User> users = new ArrayList<User>();
			ResultSet rs = this.connectionDB(1, null);
            if(rs!=null){
            	try {
					while(rs.next()){
						
						User u = new User((rs.getString("name")),(rs.getString("password")) , (rs.getString("email")));
						users.add(u);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            return users;

		
	}
	
}
