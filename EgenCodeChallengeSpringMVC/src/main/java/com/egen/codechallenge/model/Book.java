/**  
 * Model class that represents Book in the online Library Management System
 */
package com.egen.codechallenge.model;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author IshanD
 *
 */


public class Book {
	
	private String id = "BOOK_"+UUID.randomUUID().toString();
	private String bookName;
	private String authors;
	private String borrowerId;
	/**
	 * Default Constructor
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**
	 * @return the borrower
	 */
	public String getBorrowerId() {
		return borrowerId;
	}
	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", " + (bookName != null ? "bookName=" + bookName + ", " : "")
				+ (authors != null ? "authors=" + authors + ", " : "")
				+ (borrowerId != null ? "borrower=" + borrowerId : "") + "]";
	}
	
}
