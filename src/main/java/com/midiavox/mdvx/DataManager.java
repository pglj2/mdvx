package com.midiavox.mdvx;

import java.sql.*;
import java.util.List;

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
	public User insertUser(User user){
		
		return user;
		
	}
	
	public User findUserById(String userIdString){
		if(userIdString == null)
			return null;
		
		
		return null;
	}
	
	public List<User> findAllUsers(){
		
		try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String userName = "sa";
            String password = "password";
            String url = "jdbc:microsoft:sqlserver://localhost:1433"+";databaseName=MidiaVoxAgentes";
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement s1 = con.createStatement();
            ResultSet rs = s1.executeQuery("SELECT * FROM dbo.Agente;");
            String[] result = new String[20];
            if(rs!=null){
                while (rs.next()){
                    for(int i = 0; i <result.length ;i++)
                    {
                        for(int j = 0; j <result.length;j++)
                        {
                            result[j]=rs.getString(i);
                        System.out.println(result[j]);
                    }
                    }
                }
            }

            //String result = new result[20];

        } catch (Exception e)
        {
            e.printStackTrace();
        }
		return null;
		
	}
	
}
