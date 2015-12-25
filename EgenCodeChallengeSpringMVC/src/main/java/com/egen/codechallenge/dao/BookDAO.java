/** 
 * Interface that defines CRUD operation for Book instance in the Online Library Management system
 */
package com.egen.codechallenge.dao;

import java.util.List;
import com.egen.codechallenge.model.Book;

/**
 * @author IshanD
 *
 */
public interface BookDAO {
	public void createBookTable();
	public void save(Book book);
	public void update(Book book);
	public void delete(Book book);
	public Book findByName(String bookName);
	public List<Book> findAll();
}
