/**
 * 
 */
package com.egen.codechallenge.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
	 * @see com.egen.codechallenge.dao.UserDAO#save(com.egen.codechallenge.model.User)
	 */
	@Override
	public void save(User user) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		dataSource = (DataSource) context.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
		//jdbcTemplate.batchUpdate("INSERT INTO Users (id, firstName, lastName, age, gender, phoneNumber, zipCode) VALUES (1,'Ishan','Dindorkar',25,'Male','9729879137','75067')");
		String insertSQL = "INSERT INTO Users (id, firstName, lastName, age, gender, phoneNumber, zipCode) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(insertSQL, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getId().toString());
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
	 * @see com.egen.codechallenge.dao.UserDAO#createTable()
	 */
	@Override
	public void createTable() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(OnlineLibAppConfig.class);
		dataSource = (DataSource) context.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DROP TABLE Users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Users (" +
                "id VARCHAR(255), firstName VARCHAR(255), lastName VARCHAR(255), age INTEGER, gender VARCHAR(10), phoneNumber VARCHAR(10), zipCode VARCHAR(10))");
		
	}

}
