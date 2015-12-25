package com.egen.codechallenge.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.codechallenge.model.User;

@RestController
public class OnlineLibController {
	
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public String createUser(@RequestParam(value="firstName") String firstName,
							 @RequestParam(value="lastName") String lastName,
							 @RequestParam(value="age") String age,
							 @RequestParam(value="gender") String gender,
							 @RequestParam(value="phoneNumber") String phoneNumber,
							 @RequestParam(value="zipCode") String zipCode){
		
		if(gender.equalsIgnoreCase("Male"))
			return OnlineLibUtils.createUser(firstName, lastName, Integer.parseInt(age), User.Gender.MALE, phoneNumber, zipCode);
		else if(gender.equalsIgnoreCase("Female"))
			return OnlineLibUtils.createUser(firstName, lastName, Integer.parseInt(age), User.Gender.FEMALE, phoneNumber, zipCode);
		return null;	
	}
}
