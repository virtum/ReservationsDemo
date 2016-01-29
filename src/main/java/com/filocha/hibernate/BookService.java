package com.filocha.hibernate;

import java.util.List;

/**
 * Defines methods to manage book operations
 *
 */
public interface BookService {

	/**
	 * Fetches information about books from database
	 * 
	 * @return list of books
	 */
	List<BookModel> getBooksList();

	/**
	 * Fetches book model for given id
	 * 
	 * @param bookId
	 *            book id
	 * @return book model or book with null values, if the book does not exist.
	 */
	BookModel getBookInfo(int bookId);

	/**
	 * Tries to save book for a user
	 * 
	 * @param bookId
	 *            book id
	 * @param userEmail
	 *            user email
	 * @return true if reservation was successful, otherwise false
	 */
	boolean reserveBook(int bookId, String userEmail);

	/**
	 * Tries to return earlier saved user's book
	 * 
	 * @param userEmail
	 *            user email
	 * @return true if book was returned successfully, otherwise false
	 */
	boolean returnBook(String userEmail);

	/**
	 * This method is for testing usage only, it adds new book to database
	 *
	 * The model should already contain id set to unique value, because id is
	 * not generated by database.
	 * 
	 * @param model
	 *            model
	 * @return id for newly created model.
	 */
	int addNewBook(BookModel model);

	/**
	 * This method is for testing usage only, it delete values for given table
	 * name
	 * 
	 * @param modelClassName
	 *            name of table in database
	 */
	public Void clearTableValues(String modelClassName);
}