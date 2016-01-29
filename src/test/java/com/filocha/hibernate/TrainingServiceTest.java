package com.filocha.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class TrainingServiceTest {

	@Autowired
	private TrainingService service;

	@Before
	public void clearDatabase() {
		service.clearTableValues("TrainingModel");
		service.clearTableValues("ParticipantsListModel");
	}

	@Test
	public void shouldReturnAllTrainings() {
		TrainingModel model1 = new TrainingModel();
		TrainingModel model2 = new TrainingModel();

		model1.setId(new Random().nextInt(10000));
		model2.setId(new Random().nextInt(10000));

		int model1Id = service.addNewTraining(model1);
		int model2Id = service.addNewTraining(model2);
		Assume.assumeThat(model1Id, not(equalTo(0)));
		Assume.assumeThat(model1Id, not(equalTo(0)));

		List<TrainingModel> trainings = service.getTrainingsList();
		List<Integer> given = trainings.stream().map(o -> o.getId()).collect(Collectors.toList());

		assertThat(given, hasItems(model1Id, model2Id));
	}

	@Test
	public void shouldReturnModelWithNullValues() {
		TrainingModel training = service.getTrainingInfo(0);
		assertThat(training.getTitle(), equalTo(null));
	}

	@Test
	public void shouldReturnTrueWhenEmailIsNull() {
		TrainingModel model = new TrainingModel();
		model.setId(new Random().nextInt(10000));

		int trainingId = service.addNewTraining(model);

		boolean given = service.canReserve(null, trainingId);

		assertThat(given, equalTo(true));
	}

	@Test
	public void shouldReturnTrainingData() {
		TrainingModel model = new TrainingModel();
		model.setId(new Random().nextInt(10000));
		int trainingId = service.addNewTraining(model);

		TrainingModel given = service.getTrainingInfo(trainingId);

		assertThat(given.getId(), equalTo(trainingId));
	}

	@Test
	public void shouldSignUpForTraining() {
		TrainingModel model = new TrainingModel();
		model.setId(new Random().nextInt(10000));
		int trainingId = service.addNewTraining(model);

		String userEmail = "a" + new Random().nextInt(10000);

		service.trySignUpForTraining(userEmail, trainingId);

		TrainingModel trainig = service.getTrainingInfo(trainingId);
		List<String> expected = trainig.getParticipantsList().stream().map(o -> o.getParticipantEmail())
				.collect(Collectors.toList());

		assertThat(expected, hasItem(userEmail));
	}

	@Test
	public void shouldUnsubscribeSavedUserFromTraining() {
		TrainingModel model = new TrainingModel();
		model.setId(new Random().nextInt(10000));
		int trainingId = service.addNewTraining(model);

		String userEmail = "a" + new Random().nextInt(10000);
		service.trySignUpForTraining(userEmail, trainingId);

		TrainingModel training = service.getTrainingInfo(trainingId);
		List<String> given = training.getParticipantsList().stream().map(o -> o.getParticipantEmail())
				.collect(Collectors.toList());

		assertThat(given, hasItem(userEmail));

		service.unsubscribeFromTraining(userEmail, trainingId);

		TrainingModel changedTraining = service.getTrainingInfo(trainingId);
		List<String> expected = changedTraining.getParticipantsList().stream().map(o -> o.getParticipantEmail())
				.collect(Collectors.toList());

		assertThat(expected, not(hasItem(userEmail)));
	}

	@Test
	public void shouldReturnFalseIfUserHasAlreadyReservedTraining() {
		TrainingModel model = new TrainingModel();
		model.setId(new Random().nextInt(10000));
		int trainingId = service.addNewTraining(model);

		String userEmail = "a" + new Random().nextInt(10000);

		boolean expected1 = service.canReserve(userEmail, trainingId);
		assertThat(expected1, equalTo(true));

		service.trySignUpForTraining(userEmail, trainingId);

		boolean expected2 = service.canReserve(userEmail, trainingId);
		assertThat(expected2, equalTo(false));
	}
}