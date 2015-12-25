package com.egen.codechallenge.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.codechallenge.model.Book;
import com.egen.codechallenge.model.User;

@RestController
public class OnlineLibController {
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST, produces = {"text/plain","application/JSON"})
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
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.GET, produces = {"application/JSON"})
	public List<User> getAllUsers(){
		return OnlineLibUtils.getAllUsers();
		
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.PUT, produces = {"text/plain","application/JSON"})
	public String updateUser(@RequestParam(value="id") String id,
							 @RequestParam(value="firstName") String firstName,
							 @RequestParam(value="lastName") String lastName,
							 @RequestParam(value="age") String age,
							 @RequestParam(value="gender") String gender,
							 @RequestParam(value="phoneNumber") String phoneNumber,
							 @RequestParam(value="zipCode") String zipCode){
		
		if(gender.equalsIgnoreCase("Male"))
			OnlineLibUtils.updateUser(id, firstName, lastName, Integer.parseInt(age), User.Gender.MALE, phoneNumber, zipCode);
		else if(gender.equalsIgnoreCase("Female"))
			OnlineLibUtils.updateUser(id, firstName, lastName, Integer.parseInt(age), User.Gender.FEMALE, phoneNumber, zipCode);
		return "User details updated";	
	}
	
	@RequestMapping(value="/addBook",method=RequestMethod.POST, produces = {"text/plain","application/JSON"})
	public String addBook(@RequestParam(value="bookName") String bookName,
					      @RequestParam(value="authors") String authors){
		
		return OnlineLibUtils.addBook(bookName, authors);
	}
	
	@RequestMapping(value="/getAllBooks", method=RequestMethod.GET, produces = {"application/JSON"})
	public List<Book> getAllBooks(){
		return OnlineLibUtils.getAllBooks();
	}
	
	@RequestMapping(value="/findBookByName", method=RequestMethod.GET, produces = {"application/JSON"})
	public Book findBookByName(@RequestParam(value="bookName") String bookName){
		return OnlineLibUtils.findBookByName(bookName);
	}
	
	@RequestMapping(value="/checkoutBook",method=RequestMethod.PUT, produces = {"text/plain","application/JSON"})
	public String checkoutBook(@RequestParam(value="bookId") String bookId,
							   @RequestParam(value="userId") String userId){
		int status = OnlineLibUtils.checkoutBook(bookId, userId);
		if(status == 1)
			return "Book checked out successfully";
		else if(status == -1)
			return "Book does not exist in the system";
		else if(status == 0)
			return "User does not exist in the system";
		else
			return "Book is not available for checkout";
	}
}
