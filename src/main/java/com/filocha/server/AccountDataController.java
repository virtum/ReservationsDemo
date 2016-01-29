package com.filocha.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filocha.hibernate.AccountProvider;
import com.filocha.hibernate.BookModel;
import com.filocha.hibernate.ParkingModel;
import com.filocha.hibernate.TrainingModel;
import com.filocha.hibernate.UserModel;

/**
 * Defines methods to manage http requests received from client associated with
 * account operations
 *
 */
@RestController
public class AccountDataController {

	@Autowired
	private AccountProvider accountProvider;

	@RequestMapping(value = "getUserData", method = RequestMethod.POST)
	public UserModel getUserData(@RequestBody String userEmail) {
		UserModel response = accountProvider.getUserData(userEmail);
		return response;
	}

	@RequestMapping(value = "getUserParkingData", method = RequestMethod.POST)
	public ParkingModel getUserParkingData(@RequestBody String userEmail) {
		ParkingModel response = accountProvider.getUserParkingData(userEmail);

		return response;
	}

	@RequestMapping(value = "getUserBookData", method = RequestMethod.POST)
	public BookModel getUserBookData(@RequestBody String userEmail) {
		BookModel response = accountProvider.getUserBookData(userEmail);

		return response;
	}

	@RequestMapping(value = "getUserTrainingsList", method = RequestMethod.POST)
	public List<TrainingModel> getUserTrainingsList(@RequestBody String userEmail) {
		List<TrainingModel> response = accountProvider.getUserTrainingsList(userEmail);

		return response;
	}
}
