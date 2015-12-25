/** 
 * Class containing implementation of methods defined in UserDAO interface
 */
package com.egen.codechallenge.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.egen.codechallenge.app.controller.OnlineLibAppConfig;
import com.egen.codechallenge.dao.UserDAO;
import com.egen.codechallenge.model.User;

/**
 * @author IshanD
 *
 */
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private static DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 */
	public UserDAOImpl() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.UserDAO#createTable()
	 */
	@Override
	public void createUserTable() {
		initConf();
		jdbcTemplate.execute("DROP TABLE Users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Users (" +
                "id VARCHAR(255), firstName VARCHAR(255), lastName VARCHAR(255), age INTEGER, gender VARCHAR(10), phoneNumber VARCHAR(10), zipCode VARCHAR(10))");
		
	}
	
	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.UserDAO#save(com.egen.codechallenge.model.User)
	 */
	@Override
	public void save(User user) {
		initConf();
		String insertSQL = "INSERT INTO Users (id, firstName, lastName, age, gender, phoneNumber, zipCode) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(insertSQL, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getId());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				ps.setInt(4, user.getAge());
				ps.setString(5, user.getGender().toString());
				ps.setString(6, user.getPhoneNumber());
				ps.setString(7, user.getZipCode());
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.UserDAO#delete(com.egen.codechallenge.model.User)
	 */
	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.UserDAO#findByFirstName(java.lang.String)
	 */
	@Override
	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.UserDAO#findAll()
	 */
	@Override
	public List<User> findAll() {
		String selectSQL = "SELECT * FROM Users";
		List<User> registeredUsers = new ArrayList<User>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectSQL);
		for(Map row: rows){
			User user = new User();
			user.setId(String.valueOf(row.get("id")));
			user.setFirstName(String.valueOf(row.get("firstName")));
			user.setLastName(String.valueOf(row.get("lastName")));
			user.setAge(Integer.parseInt(String.valueOf(row.get("age"))));
			if(String.valueOf(row.get("gender")).equalsIgnoreCase("Male"))
				user.setGender(User.Gender.MALE);
			else
				user.setGender(User.Gender.FEMALE);
			user.setPhoneNumber(String.valueOf(row.get("phoneNumber")));
			user.setZipCode(String.valueOf(row.get("zipCode")));
			registeredUsers.add(user);
		}
		return registeredUsers;
	}
	
	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.UserDAO#update(com.egen.codechallenge.model.User)
	 */
	@Override
	public void update(User user) {
		initConf();
		String selectSQL = "SELECT * FROM Users";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectSQL);
		for(Map row: rows){
			if(String.valueOf(row.get("id")).equalsIgnoreCase(user.getId())){
				String updateSQL = "UPDATE Users SET firstName = ?, lastName = ?, age = ?, gender = ?, phoneNumber = ?, zipCode = ? where id = ?";
				jdbcTemplate.update(updateSQL, new PreparedStatementSetter(){

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(7, user.getId());
						ps.setString(1, user.getFirstName());
						ps.setString(2, user.getLastName());
						ps.setInt(3, user.getAge());
						ps.setString(4, user.getGender().toString());
						ps.setString(5, user.getPhoneNumber());
						ps.setString(6, user.getZipCode());
					}
				});
			}
		}
	}
	
	/**
	 * 
	 */
	private void initConf() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		dataSource = (DataSource) context.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


}
