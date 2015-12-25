/**
 * JUnit Test Cases for all service end points defined in REST API for Online Library Management System
 */

package com.egen.codechallenge.test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

/**
 * @author IshanD
 *
 */
public class TestOnlineLibRestAPI {
	
	private final String BASE_URL = "http://localhost:8080";
	
	@Test
	public void testCreateUser(){
		HttpUriRequest request = new HttpPost(BASE_URL+"/createUser?firstName=Ishan&lastName=Dindorkar&age=30&gender=Male&phoneNumber=9729879137&zipCode=75067");
		assertCheck(request);
	}
	
	@Test
	public void testGetAllUsers(){
		HttpUriRequest request = new HttpPost(BASE_URL+"/createUser?firstName=Ishan&lastName=Dindorkar&age=30&gender=Male&phoneNumber=9729879137&zipCode=75067");
		assertCheck(request);
		request = new HttpGet(BASE_URL+"/getAllUsers");
		assertCheck(request);
	}
	
	@Test
	public void testUpdateUser(){
		HttpUriRequest request = new HttpPut(BASE_URL+"/updateUser?id=USER_9a6b18c5-b2cb-43ba-a687-48f34c50822c&firstName=Kunal&lastName=Dindorkar&age=45&gender=Female&phoneNumber=9926450869&zipCode=452007");
		assertCheck(request);
	}
	
	@Test
	public void testAddBook() throws UnsupportedEncodingException{
		HttpUriRequest request = new HttpPost(BASE_URL+"/addBook?bookName=ABC&authors=DEF");
		assertCheck(request);
	}
	
	@Test
	public void testGetAllBooks(){
		HttpUriRequest request = new HttpPost(BASE_URL+"/addBook?bookName=MNO&authors=IOP");
		assertCheck(request);
		request = new HttpGet(BASE_URL+"/getAllBooks");
		assertCheck(request);
	}
	
	@Test
	public void testCheckoutBook(){
		HttpUriRequest request = new HttpPut(BASE_URL+"/checkoutBook?bookId=BOOK_754651b0-dc78-44f5-8607-5270801ae1ea&userId=USER_9a6b18c5-b2cb-43ba-a687-48f34c50822c");
		assertCheck(request);
	}
	
	@Test
	public void testFindBookByName(){
		HttpUriRequest request = new HttpPost(BASE_URL+"/addBook?bookName=MNO&authors=IOP");
		assertCheck(request);
		request = new HttpGet(BASE_URL+"/findBookByName?bookName=MNO");
		assertCheck(request);
	}
	
	/**
	 * @param request
	 */
	private void assertCheck(HttpUriRequest request) {
		try {
			HttpResponse response = new DefaultHttpClient().execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			assertEquals( statusCode, 200 );
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
