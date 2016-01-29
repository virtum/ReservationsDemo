package com.filocha.hibernate;

import java.util.List;

/**
 * Defines operations to manage list of defined trainings.
 */
public interface TrainingService {

	/**
	 * Fetches information about trainings from database
	 * 
	 * @return list of trainings in database
	 */
	List<TrainingModel> getTrainingsList();

	/**
	 * Fetches training model for given id
	 * 
	 * @param trainingId
	 *            training id to show what training method looks for
	 * @return training model or training with null values, if the training
	 *         does not exist.
	 */
	TrainingModel getTrainingInfo(int trainingId);

	/**
	 * Checks if user can reserve a training,
	 * 
	 * @param userEmail
	 *            user email to check if is already signed up
	 * @param trainingId
	 *            id of training to check if user is already signed up
	 * @return true if the user has a reservation otherwise false
	 */
	boolean canReserve(String userEmail, int trainingId);

	/**
	 * Tries to save user for a training
	 * 
	 * @param userEmail
	 *            user email which identifies the user.
	 * @param trainingId
	 *            training id
	 * @return true when the user was successfully added to training's
	 *         participants list otherwise false
	 */
	boolean trySignUpForTraining(String userEmail, int trainingId);

	/**
	 * Unsubscribes the user from the training
	 * 
	 * @param userEmail
	 *            user id
	 * @param trainingId
	 *            training id
	 */
	Void unsubscribeFromTraining(String userEmail, int trainingId);

	/**
	 * This method is for testing usage only, it adds new training to database
	 *
	 * The model should already contain id set to unique value, because id is
	 * not generated by database.
	 * 
	 * @param model
	 *            model
	 * @return id for newly created model.
	 */
	int addNewTraining(TrainingModel model);

	/**
	 * This method is for testing usage only, it delete values for given table
	 * name
	 * 
	 * @param modelClassName
	 *            name of table in database
	 */
	public Void clearTableValues(String modelClassName);

}