/** Configuration class
 * 
 */
package com.egen.codechallenge.app.controller;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author IshanD
 *
 */
@Configuration
public class OnlineLibAppConfig {
	
	/*@Bean(name="userDAO")
	public UserDAO userDAO(){
		return new UserDAOImpl();
	}*/
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase dataSource = builder
			.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
			.build();
		return dataSource;
	}
	
}
