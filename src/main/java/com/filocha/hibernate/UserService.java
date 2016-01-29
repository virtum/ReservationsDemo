package com.filocha.hibernate;

/**
 * Defines methods to manage user operation
 *
 */
public interface UserService {
	/**
	 * Tries to add new user to database
	 * 
	 * @param userData
	 *            information about user
	 */
	Void addNewUser(UserModel userData);

	/**
	 * Checks whether email exist in database or not
	 * 
	 * @param email
	 *            email to check
	 * @return true if email is already in database, otherwise false
	 */
	boolean doesEmailExist(String email);

	/**
	 * Tries to log in user
	 * 
	 * @param email
	 *            user email
	 * @param password
	 *            user password
	 * @return true if user was successfully logged in, otherwise false
	 */
	boolean logIn(String email, String password);

}
