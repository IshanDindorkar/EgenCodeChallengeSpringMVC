/**  
 * Model class that represents User in the online Library Management System
 */
package com.egen.codechallenge.model;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * @author IshanD
 *
 */


public class User {
	private final UUID id = UUID.randomUUID();
	public enum Gender{
		MALE,
		FEMALE
	}
	private String firstName;
	private String lastName;
	private int age;
	private Gender gender;
	private String phoneNumber;
	private String zipCode;
	
	private Logger log = Logger.getLogger(User.class);

	/**
	 * Default Constructor
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		if(age > 0){
			this.age = age;
		} 
		else {
			log.error("Invalid Age. Please enter valid value for Age");
			throw new NumberFormatException();
		}	
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("\\d{10}");
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.matches()) {
			this.phoneNumber = phoneNumber;
		}	
		else {
		   	log.error("Invalid Phone Number. Please enter 10-digit positive number");
		   	throw new NumberFormatException();
		}   
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Logger log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", " + (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "") + "age=" + age + ", "
				+ (gender != null ? "gender=" + gender + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
				+ (zipCode != null ? "zipCode=" + zipCode + ", " : "") + (log != null ? "log=" + log : "") + "]";
	}
	
	
}
