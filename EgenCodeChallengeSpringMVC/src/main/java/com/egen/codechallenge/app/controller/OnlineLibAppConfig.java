/** Configuration class
 * 
 */
package com.egen.codechallenge.app.controller;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.egen.codechallenge.dao.UserDAO;
import com.egen.codechallenge.dao.impl.UserDAOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author IshanD
 *
 */
@Configuration
public class OnlineLibAppConfig {
	
	@Bean(name="userDAO")
	public UserDAO userDAO(){
		return new UserDAOImpl();
	}
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase dataSource = builder
			.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
			.build();
		return dataSource;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    return converter;
	}
}
