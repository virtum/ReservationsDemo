package com.filocha.hibernate;

import java.util.Date;
import java.util.List;

public class TrainingModel {
	private int id;
	private String title;
	private String lecturer;
	private String description;
	private Date trainingDate;
	private List<ParticipantsListModel> participantsList;
	private int seats;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public List<ParticipantsListModel> getParticipantsList() {
		return participantsList;
	}

	public void setParticipantsList(List<ParticipantsListModel> participantsList) {
		this.participantsList = participantsList;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

}
