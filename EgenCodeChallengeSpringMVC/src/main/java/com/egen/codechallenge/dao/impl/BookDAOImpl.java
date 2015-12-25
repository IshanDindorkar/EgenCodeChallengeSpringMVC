/** 
 * Class containing implementation of methods defined in BookDAO interface
 */
package com.egen.codechallenge.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.egen.codechallenge.app.controller.OnlineLibAppConfig;
import com.egen.codechallenge.dao.BookDAO;
import com.egen.codechallenge.model.Book;

/**
 * @author IshanD
 *
 */
public class BookDAOImpl implements BookDAO {
	
	@Autowired
	private static DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.BookDAO#createBookTable()
	 */
	@Override
	public void createBookTable() {
		initConf();
		jdbcTemplate.execute("DROP TABLE Books IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Books (" +
                "id VARCHAR(255), bookName VARCHAR(255), authors VARCHAR(255), borrowerId VARCHAR(255))");
	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.BookDAO#save(com.egen.codechallenge.model.Book)
	 */
	@Override
	public void save(Book book) {
		initConf();
		String insertSQL = "INSERT INTO Books (id, bookName, authors, borrowerId) VALUES (?,?,?,?)";
		jdbcTemplate.update(insertSQL, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, book.getId());
				ps.setString(2, book.getBookName());
				ps.setString(3, book.getAuthors());
				ps.setString(4, book.getBorrowerId());
			}
		});

	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.BookDAO#update(com.egen.codechallenge.model.Book)
	 */
	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.BookDAO#delete(com.egen.codechallenge.model.Book)
	 */
	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.BookDAO#findByFirstName(java.lang.String)
	 */
	@Override
	public Book findByName(String bookName) {
		String selectBookSQL = "SELECT * FROM Books where bookName = ?";
		Book book = jdbcTemplate.execute(selectBookSQL, new PreparedStatementCallback<Book>() {

			@Override
			public Book doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, bookName);
				Book book = new Book();
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					book.setId(rs.getString(1));
					book.setBookName(rs.getString(2));
					book.setAuthors(rs.getString(3));
					book.setBorrowerId(rs.getString(4));
				}
				return book;
			}
		});
		
		return book;
	}

	/* (non-Javadoc)
	 * @see com.egen.codechallenge.dao.BookDAO#findAll()
	 */
	@Override
	public List<Book> findAll() {
		String selectSQL = "SELECT * FROM Books";
		List<Book> availableBooks = new ArrayList<Book>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectSQL);
		for(Map row: rows){
			Book book = new Book();
			book.setId(String.valueOf(row.get("id")));
			book.setBookName(String.valueOf(row.get("bookName")));
			book.setAuthors(String.valueOf(row.get("authors")));
			availableBooks.add(book);
		}
		return availableBooks;
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
