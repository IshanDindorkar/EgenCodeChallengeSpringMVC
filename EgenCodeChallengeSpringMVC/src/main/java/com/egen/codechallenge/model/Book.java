/**  
 * Model class that represents Book in the online Library Management System
 */
package com.egen.codechallenge.model;

import java.util.Arrays;

/**
 * @author IshanD
 *
 */


public class Book {
	
	private long id;
	private String bookName;
	private String[] authors;
	private User borrower;
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
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	public String[] getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	/**
	 * @return the borrower
	 */
	public User getBorrower() {
		return borrower;
	}
	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", " + (bookName != null ? "bookName=" + bookName + ", " : "")
				+ (authors != null ? "authors=" + Arrays.toString(authors) + ", " : "")
				+ (borrower != null ? "borrower=" + borrower : "") + "]";
	}
	
}
