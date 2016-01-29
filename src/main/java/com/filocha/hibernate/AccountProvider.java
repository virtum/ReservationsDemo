package com.filocha.hibernate;

import java.util.List;

/**
 * Defines methods to manage account operations
 *
 */
public interface AccountProvider {
	/**
	 * Fetches information about given user from database
	 * 
	 * @param userEmail
	 *            user email
	 * @return user model with information about user
	 */
	UserModel getUserData(String userEmail);

	/**
	 * Fetches information about given user's book from database
	 * 
	 * @param userEmail
	 *            user email
	 * @return book model with information about user's book
	 */
	BookModel getUserBookData(String userEmail);

	/**
	 * Fetches information about given user's parking place from database
	 * 
	 * @param userEmail
	 *            user email
	 * @return parking model with information about user's parking place
	 */
	ParkingModel getUserParkingData(String userEmail);

	/**
	 * Fetches information about given user's trainings from database
	 * 
	 * @param userEmail
	 *            user email
	 * @return trainig model with information about user's trainings
	 */
	List<TrainingModel> getUserTrainingsList(String userEmail);
}
