/**
 * 
 */
package com.egen.codechallenge.dao;

import java.util.List;

import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */
public interface UserDAO {
	public void createTable();
	public void save(User user);
	public void delete(User user);
	public List<User> findByFirstName(String firstName);
	public List<User> findAll();
}
