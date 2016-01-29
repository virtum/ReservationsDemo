package com.filocha.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filocha.hibernate.ParkingService;

/**
 * Defines methods to manage http requests received from client associated with
 * parking operations
 *
 */
@RestController
public class ParkingDataController {

	@Autowired
	private ParkingService parkingService;

	@RequestMapping(value = "getParginkInfo", method = RequestMethod.POST)
	public ParkingResponseModel getParkingInfo(@RequestBody ParkingRequestModel parkingRequestModel) {
		String userEmail = parkingRequestModel.getUserEmail();
		String parkingName = parkingRequestModel.getParkingName();

		ParkingResponseModel result = parkingService.getParkingInfo(parkingName, userEmail);

		return result;
	}

	@RequestMapping(value = "reserveParkingPlace", method = RequestMethod.POST)
	public boolean reserveParkingPlace(@RequestBody ParkingRequestModel parkingRequestModel) {
		String userEmail = parkingRequestModel.getUserEmail();
		String parkingName = parkingRequestModel.getParkingName();

		boolean result = parkingService.reserveParkingPlace(userEmail, parkingName);
		return result;

	}

	@RequestMapping(value = "leaveParkingPlace", method = RequestMethod.POST)
	public boolean leaveParkingPlace(@RequestBody String userEmail) {
		boolean result = parkingService.leaveParkingPlace(userEmail);
		return result;
	}

}
