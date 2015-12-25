/** Class containing all utility methods invoked in OnlineLibController class
 * 
 */
package com.egen.codechallenge.app.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.egen.codechallenge.dao.UserDAO;
import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */

@SuppressWarnings("resource")
public class OnlineLibUtils {
	
	@Autowired
	private static UserDAO userDAO;
	
	static{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		userDAO = (UserDAO)context.getBean("userDAO");
		userDAO.createTable();
	}
	
	@SuppressWarnings("resource")
	public static String createUser(String firstName, String lastName, int age, User.Gender gender, String phoneNumber, String zipCode){
		List<User> registeredUsers = (List<User>) userDAO.findAll();
		if(!registeredUsers.isEmpty()){
			for(User u:registeredUsers)	{
				if(u.getFirstName().equalsIgnoreCase(firstName) && u.getLastName().equalsIgnoreCase(lastName))
					return "User already exist in the system. The User Id is "+u.getId();
			}
		}
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setZipCode(zipCode);
		userDAO.save(user);
		return "User "+user.getFirstName()+" "+user.getLastName() + " is created successfully in the system. The User Id is "+user.getId();
	}
	
	public static List<User> getAllUsers(){
		return userDAO.findAll();
	}
}
