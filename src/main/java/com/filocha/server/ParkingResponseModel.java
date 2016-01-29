package com.filocha.server;

public class ParkingResponseModel {
	private String parkingName;
	private int placesTaken;
	private int placesFree;
	private boolean hasAlreadyParkingPlace;
	private int userParkingPlaceNumber;
	private String userEmail;



	public int getUserParkingPlaceNumber() {
		return userParkingPlaceNumber;
	}

	public void setUserParkingPlaceNumber(int userParkingPlaceNumber) {
		this.userParkingPlaceNumber = userParkingPlaceNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public int getPlacesTaken() {
		return placesTaken;
	}

	public void setPlacesTaken(int placesTaken) {
		this.placesTaken = placesTaken;
	}

	public int getPlacesFree() {
		return placesFree;
	}

	public void setPlacesFree(int placesFree) {
		this.placesFree = placesFree;
	}

	public boolean isHasAlreadyParkingPlace() {
		return hasAlreadyParkingPlace;
	}

	public void setHasAlreadyParkingPlace(boolean hasAlreadyParkingPlace) {
		this.hasAlreadyParkingPlace = hasAlreadyParkingPlace;
	}
}
