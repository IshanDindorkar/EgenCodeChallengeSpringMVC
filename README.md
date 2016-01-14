# EgenCodeChallenge

Model classes -
1.  Book
2.  User

Data Access Object Interfaces defining CRUD operations -
1.  BookDAO
2.  UserDAO

Data Access Object Implementation classes -
1.  BookDAOImpl
2.  UserDAOImpl

Controller class containing business logic for all REST Service endpoints -
OnlineLibController

Entry point to the Online Library System application -
OnlineLibApplication

Test classes -
1.  TestOnlineLibRestAPI - Contains JUnit test cases to validate all REST service endpoints
2.  TestUserModel - Contains JUnit test cases for User related validation rules like age should be a non-zero positive integer and phone number should be of 10 digits
