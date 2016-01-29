package com.filocha.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Test
	public void shouldAddNewUserToDatabase() {
		String userEmail = "a" + new Random().nextInt(1000);

		UserModel model = new UserModel();
		model.setMail(userEmail);
		model.setFirstName("a" + new Random().nextInt(1000));
		model.setLastName("a" + new Random().nextInt(1000));
		model.setPassword("a" + new Random().nextInt(1000));

		service.addNewUser(model);

		boolean expected = service.doesEmailExist(userEmail);

		assertThat(expected, equalTo(true));
	}

	@Test
	public void shouldReturnTrueWhenLogInWasSuccessful() {
		String userEmail = "a" + new Random().nextInt(1000);
		String userPassword = "a" + new Random().nextInt(1000);

		UserModel model = new UserModel();
		model.setMail(userEmail);
		model.setFirstName("a" + new Random().nextInt(1000));
		model.setLastName("a" + new Random().nextInt(1000));
		model.setPassword(userPassword);

		service.addNewUser(model);

		boolean expected = service.logIn(userEmail, userPassword);
		assertThat(expected, equalTo(true));
	}

}
