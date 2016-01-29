package com.filocha.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class AccountProviderImpl implements AccountProvider {

	@Autowired
	TrainingServiceImpl trainingService;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public UserModel getUserData(String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			UserModel result = new UserModel();

			List<UserModel> userToGet = (List<UserModel>) s
					.createQuery("FROM UserModel Email WHERE email = " + "'" + userEmail + "'").list();

			for (Iterator<UserModel> iterator = userToGet.iterator(); iterator.hasNext();) {
				UserModel user = iterator.next();
				result.setFirstName(user.getFirstName());
				result.setLastName(user.getLastName());
				result.setMail(user.getMail());
				result.setPassword(user.getPassword());
			}
			return result;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public BookModel getUserBookData(String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			BookModel result = new BookModel();

			List<BookModel> userBook = (List<BookModel>) s
					.createQuery("FROM BookModel OWNEREMAIL WHERE ownerEmail = " + "'" + userEmail + "'").list();

			for (Iterator<BookModel> iterator = userBook.iterator(); iterator.hasNext();) {
				BookModel bookModel = iterator.next();
				result.setId(bookModel.getId());
				result.setTitle(bookModel.getTitle());
				result.setAuthor(bookModel.getAuthor());
				result.setFree(bookModel.isFree());
				result.setOwnerEmail(bookModel.getOwnerEmail());
			}
			return result;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ParkingModel getUserParkingData(String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			ParkingModel result = new ParkingModel();

			List<ParkingModel> userParking = (List<ParkingModel>) s
					.createQuery(("FROM ParkingModel OWNEREMAIL WHERE ownerEmail = " + "'" + userEmail + "'")).list();

			for (Iterator<ParkingModel> iterator = userParking.iterator(); iterator.hasNext();) {
				ParkingModel parkingModel = iterator.next();
				result.setFree(parkingModel.isFree());
				result.setId(parkingModel.getId());
				result.setOwnerEmail(parkingModel.getOwnerEmail());
				result.setParkingName(parkingModel.getParkingName());
				result.setPlaceNumber(parkingModel.getPlaceNumber());
			}
			return result;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingModel> getUserTrainingsList(String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			List<TrainingModel> trainingsList = new ArrayList<>();

			List<ParticipantsListModel> participant = (List<ParticipantsListModel>) s.createQuery(
					"FROM ParticipantsListModel participantEmal WHERE PARTICIPANTEMAIL= " + "'" + userEmail + "'")
					.list();
			for (Iterator<ParticipantsListModel> iterator = participant.iterator(); iterator.hasNext();) {
				ParticipantsListModel participantsListModel = (ParticipantsListModel) iterator.next();
				if (participantsListModel.getParticipantEmail().equals(userEmail)) {
					trainingsList.add(trainingService.getTrainingInfo(participantsListModel.getTrainingId()));
				}
			}
			return trainingsList;
		});
	}
}
