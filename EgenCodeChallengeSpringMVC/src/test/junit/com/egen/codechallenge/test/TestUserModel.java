/**
 * JUnit test cases for validation rules defined for creating a valid User instance
 */

package com.egen.codechallenge.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */
public class TestUserModel {
	
	private Logger log = Logger.getLogger(TestUserModel.class);
	
	@Test
	public void testUserCreation(){
		User user = new User();
		log.info(user.getId().toString());
	}
	
	@Test
	public void testAgeValidation(){
		User user = new User();
		user.setAge(-1);
		
	}
	
	@Test
	public void testPhoneNumberValidation(){
		User user = new User();
		user.setPhoneNumber("0");
	}
}
