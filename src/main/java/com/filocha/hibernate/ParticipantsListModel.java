package com.filocha.hibernate;

public class ParticipantsListModel {
	private int id;
	private String participantEmail;
	private int trainingId;
	private int idx;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getId() {
		return id;
	}

	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}
}
