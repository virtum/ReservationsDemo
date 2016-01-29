package com.filocha.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filocha.hibernate.TrainingModel;
import com.filocha.hibernate.TrainingService;

/**
 * Defines methods to manage http requests received from client associated with
 * training operations
 *
 */
@RestController
public class TrainingDataController {

	@Autowired
	private TrainingService trainingService;

	@RequestMapping(value = "getTrainingsList", method = RequestMethod.GET)
	public List<TrainingModel> getTrainingsList() {
		List<TrainingModel> response = trainingService.getTrainingsList();

		return response;
	}

	@RequestMapping(value = "getTrainingInfo", method = RequestMethod.POST)
	public TrainingResponseModel getTrainingInfo(@RequestBody TrainingRequestModel requestData) {
		TrainingModel model = trainingService.getTrainingInfo(requestData.getId());
		boolean canReserve = trainingService.canReserve(requestData.getUserEmail(), requestData.getId());

		TrainingResponseModel result = new TrainingResponseModel();
		result.setDescription(model.getDescription());
		result.setLecturer(model.getLecturer());
		result.setSeats(model.getSeats());
		result.setSignedIn(!canReserve);
		result.setTitle(model.getTitle());
		result.setTrainingDate(model.getTrainingDate());

		return result;
	}

	@RequestMapping(value = "signUpForTraining", method = RequestMethod.POST)
	public boolean signUpForTraining(@RequestBody TrainingRequestModel requestData) {
		boolean response = trainingService.trySignUpForTraining(requestData.getUserEmail(), requestData.getId());

		return response;
	}

	@RequestMapping(value = "unsubscribeFromTraining", method = RequestMethod.POST)
	public void unsubscribeFromTraining(@RequestBody TrainingRequestModel requestData) {
		trainingService.unsubscribeFromTraining(requestData.getUserEmail(), requestData.getId());

	}

}
