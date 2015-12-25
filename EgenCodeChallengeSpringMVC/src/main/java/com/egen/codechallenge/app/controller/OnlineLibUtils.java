/** Class containing all utility methods invoked in OnlineLibController class
 * 
 */
package com.egen.codechallenge.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.egen.codechallenge.dao.BookDAO;
import com.egen.codechallenge.dao.UserDAO;
import com.egen.codechallenge.model.Book;
import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */

@SuppressWarnings("resource")
public class OnlineLibUtils {
	
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static BookDAO bookDAO;
	
	static{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		userDAO = (UserDAO)context.getBean("userDAO");
		userDAO.createUserTable();
		bookDAO = (BookDAO)context.getBean("bookDAO");
		bookDAO.createBookTable();
		
	}
	
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
	
	public static void updateUser(String id, String firstName, String lastName, int age, User.Gender gender, String phoneNumber, String zipCode){
		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setZipCode(zipCode);
		userDAO.update(user);
	}
	
	public static String addBook(String bookName, String authors){
		List<Book> availableBooks = (List<Book>) bookDAO.findAll();
		if(!availableBooks.isEmpty()){
			for(Book book:availableBooks)	{
				if(book.getBookName().equalsIgnoreCase(bookName))
					return "Book already exist in the system. The Book Id is "+book.getId();
			}
		}
		
		Book book = new Book();
		book.setBookName(bookName);
		book.setAuthors(authors);
		bookDAO.save(book);
		return "The book has been added successfully to the repository. The id is "+book.getId();
	}
	
	public static List<Book> getAllBooks(){
		return bookDAO.findAll();
	}
	
	public static Book findBookByName(String bookName){
		return bookDAO.findByName(bookName);
	}
	
	public static int checkoutBook(String bookId, String userId){
		User user = userDAO.findById(userId);
		if(user.getFirstName()!=null){
			Book book = bookDAO.findById(bookId);
			if(book.getBookName()!=null){
				if(book.getBorrowerId() == null){
					book.setBorrowerId(userId);
					bookDAO.update(book);
					return 1;
				}	
				else {
					return 2; // book is not available for checkout
				}
			}
			else{
				return -1; // book does not exist
			}
		}
		else{
			return 0; // user does not exist
		}
	}
}
