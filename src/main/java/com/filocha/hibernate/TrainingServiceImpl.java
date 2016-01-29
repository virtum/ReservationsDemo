package com.filocha.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingModel> getTrainingsList() {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			List<TrainingModel> trainingsList = (List<TrainingModel>) s.createQuery("FROM TrainingModel").list();
			return trainingsList;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public TrainingModel getTrainingInfo(int trainingId) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			TrainingModel trainingInfo = new TrainingModel();

			List<TrainingModel> trainingAsList = (List<TrainingModel>) s
					.createQuery("FROM TrainingModel ID WHERE id= " + "'" + trainingId + "'").list();

			for (Iterator<TrainingModel> iterator = trainingAsList.iterator(); iterator.hasNext();) {
				TrainingModel trainingModel = (TrainingModel) iterator.next();
				trainingInfo.setId(trainingModel.getId());
				trainingInfo.setTitle(trainingModel.getTitle());
				trainingInfo.setLecturer(trainingModel.getLecturer());
				trainingInfo.setDescription(trainingModel.getDescription());
				trainingInfo.setSeats(trainingModel.getSeats());
				trainingInfo.setTrainingDate(trainingModel.getTrainingDate());
				trainingInfo.setParticipantsList(trainingModel.getParticipantsList());
			}
			return trainingInfo;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean canReserve(String userEmail, int trainingId) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			List<ParticipantsListModel> participantsList = (List<ParticipantsListModel>) s
					.createQuery("FROM ParticipantsListModel ParticipantEmail WHERE PARTICIPANTEMAIL= " + "'"
							+ userEmail + "'" + "AND TRAININGID= " + "'" + trainingId + "'")
					.list();

			Iterator<ParticipantsListModel> iterator = participantsList.iterator();
			return iterator.hasNext() ? false : true;
		});
	}

	@SuppressWarnings("unchecked")
	public boolean trySignUpForTraining(String userEmail, int trainingId) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			boolean result = false;
			TrainingModel training = new TrainingModel();
			ParticipantsListModel participant = new ParticipantsListModel();

			List<TrainingModel> trainingAsList = (List<TrainingModel>) s
					.createQuery("FROM TrainingModel ID WHERE id= " + "'" + trainingId + "'").list();

			for (Iterator<TrainingModel> iterator = trainingAsList.iterator(); iterator.hasNext();) {
				TrainingModel trainingModel = (TrainingModel) iterator.next();

				List<ParticipantsListModel> participantsList = trainingModel.getParticipantsList();

				if (canReserve(userEmail, trainingId) == false) {
					result = false;
				} else {
					participant.setId(trainingId);
					participant.setParticipantEmail(userEmail);
					participantsList.add(participant);
					saveParticipant(participant);

					training.setId(trainingModel.getId());
					training.setDescription(trainingModel.getDescription());
					training.setLecturer(trainingModel.getLecturer());
					training.setParticipantsList(participantsList);
					training.setSeats(trainingModel.getSeats() - 1);
					training.setTitle(trainingModel.getTitle());
					training.setTrainingDate(trainingModel.getTrainingDate());
					updateTraining(training);

					result = true;
				}
			}
			return result;
		});
	}

	public Void saveParticipant(ParticipantsListModel participant) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.save(participant);

			return null;
		});
	}

	public Void updateTraining(TrainingModel trainingModel) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.merge(trainingModel);

			return null;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Void unsubscribeFromTraining(String userEmail, int trainingId) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.createQuery("DELETE ParticipantsListModel participantEmail WHERE PARTICIPANTEMAIL= " + "'" + userEmail
					+ "'" + "AND TRAININGID= " + "'" + trainingId + "'").executeUpdate();

			List<TrainingModel> list = (List<TrainingModel>) s
					.createQuery("FROM TrainingModel id WHERE ID= " + "'" + trainingId + "'").list();

			for (Iterator<TrainingModel> iterator = list.iterator(); iterator.hasNext();) {
				TrainingModel trainingModel = (TrainingModel) iterator.next();
				TrainingModel training = new TrainingModel();

				training.setId(trainingId);
				training.setDescription(trainingModel.getDescription());
				training.setSeats(trainingModel.getSeats() + 1);
				training.setLecturer(trainingModel.getLecturer());
				training.setParticipantsList(trainingModel.getParticipantsList());
				training.setTitle(trainingModel.getTitle());
				training.setTrainingDate(trainingModel.getTrainingDate());

				updateTraining(training);
			}
			return null;
		});
	}

	@Override
	public int addNewTraining(TrainingModel model) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.save(model);

			return model.getId();
		});
	}

	@Override
	public Void clearTableValues(String modelClassName) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.createQuery("DELETE " + modelClassName).executeUpdate();

			return null;
		});
	}
}
