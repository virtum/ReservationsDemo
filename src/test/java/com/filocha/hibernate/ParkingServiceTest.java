package com.filocha.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.filocha.server.ParkingResponseModel;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class ParkingServiceTest {

	@Autowired
	private ParkingService service;

	@Before
	public void clearTable() {
		service.clearTableValues("ParkingModel");
	}

	@Test
	public void souldReturnParkingData() {
		ParkingModel model = new ParkingModel();
		model.setParkingName("a" + new Random().nextInt(1000));
		model.setFree(true);
		String userEmail = "a" + new Random().nextInt(1000);

		ParkingModel parking = service.addNewParking(model);

		ParkingResponseModel expected = service.getParkingInfo(parking.getParkingName(), userEmail);
		assertThat(expected.getParkingName(), equalTo(expected.getParkingName()));
	}

	@Test
	public void shouldReserveParkingPlace() {
		ParkingModel model = new ParkingModel();
		model.setParkingName("a" + new Random().nextInt(1000));
		model.setId(new Random().nextInt(1000));
		model.setPlaceNumber(new Random().nextInt(1000));
		model.setFree(true);

		String userEmail = "a" + new Random().nextInt(1000);

		ParkingModel parking = service.addNewParking(model);
		service.reserveParkingPlace(userEmail, parking.getParkingName());

		ParkingResponseModel expected = service.getParkingInfo(parking.getParkingName(), userEmail);

		assertThat(expected.getParkingName(), equalTo(parking.getParkingName()));
		assertThat(expected.getUserEmail(), equalTo(userEmail));
		assertThat(expected.getUserParkingPlaceNumber(), equalTo(parking.getPlaceNumber()));
	}

	@Test
	public void shouldLeaveReservedParkingPlace() {
		ParkingModel model = new ParkingModel();
		model.setParkingName("a" + new Random().nextInt(1000));
		model.setId(new Random().nextInt(1000));
		model.setPlaceNumber(new Random().nextInt(1000));
		model.setFree(true);

		String userEmail = "a" + new Random().nextInt(1000);

		ParkingModel parking = service.addNewParking(model);
		service.reserveParkingPlace(userEmail, parking.getParkingName());

		service.leaveParkingPlace(userEmail);
		ParkingResponseModel expected = service.getParkingInfo(parking.getParkingName(), userEmail);

		System.out.println(expected.getUserParkingPlaceNumber());
		assertThat(expected.getParkingName(), equalTo(parking.getParkingName()));
		assertThat(expected.getUserEmail(), equalTo(null));

	}

}
