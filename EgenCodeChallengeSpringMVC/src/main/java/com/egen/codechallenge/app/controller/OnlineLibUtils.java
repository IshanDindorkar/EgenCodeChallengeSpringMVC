/** Class containing all utility methods invoked in OnlineLibController class
 * 
 */
package com.egen.codechallenge.app.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */

public class OnlineLibUtils {
	
	@Autowired
	private static DataSource dataSource;
	
	@SuppressWarnings("resource")
	public static String createUser(String firstName, String lastName, int age, User.Gender gender, String phoneNumber, String zipCode){
		/*AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");*/
		/*List<User> registeredUsers = (List<User>) userDAO.findAll();
		if(!registeredUsers.isEmpty()){
			for(User u:registeredUsers)	{
				if(u.getFirstName().equalsIgnoreCase(firstName) && u.getLastName().equalsIgnoreCase(lastName))
					return "User already exist in the system. The User Id is "+u.getId();
			}
		}*/
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		dataSource = (DataSource) context.getBean("dataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DROP TABLE Users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Users (" +
                "id INTEGER, firstName VARCHAR(255), lastName VARCHAR(255), age INTEGER, gender VARCHAR(10), phoneNumber VARCHAR(10), zipCode VARCHAR(10))");
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setZipCode(zipCode);
		jdbcTemplate.batchUpdate("INSERT INTO Users (id, firstName, lastName, age, gender, phoneNumber, zipCode) VALUES (1,'Ishan','Dindorkar',25,'Male','9729879137','75067')");
		//userDAO.save(user);
		return "User "+user.getFirstName()+" "+user.getLastName() + " is created successfully in the system. The User Id is "+user.getId();
	}
}
