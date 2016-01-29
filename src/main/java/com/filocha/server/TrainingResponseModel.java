package com.filocha.server;

import java.util.Date;

public class TrainingResponseModel {
	private String title;
	private String lecturer;
	private String description;
	private Date trainingDate;
	private int seats;
	private boolean isSignedIn;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getTitle() {
		return title;
	}

	public String getLecturer() {
		return lecturer;
	}

	public String getDescription() {
		return description;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public int getSeats() {
		return seats;
	}

	public boolean isSignedIn() {
		return isSignedIn;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void setSignedIn(boolean isSignedIn) {
		this.isSignedIn = isSignedIn;
	}

}
