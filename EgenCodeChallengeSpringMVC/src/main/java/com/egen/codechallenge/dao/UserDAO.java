/** 
 * Interface that defines CRUD operations for User instance in the Online Library Management System
 */
package com.egen.codechallenge.dao;

import java.util.List;

import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */
public interface UserDAO {
	public void createUserTable();
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public List<User> findByFirstName(String firstName);
	public User findById(String id);
	public List<User> findAll();
}
