package com.filocha.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Void addNewUser(UserModel userData) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {

			UserModel newUser = new UserModel();
			newUser.setFirstName(userData.getFirstName());
			newUser.setLastName(userData.getLastName());
			newUser.setMail(userData.getMail());
			newUser.setPassword(userData.getPassword());

			s.save(newUser);

			return null;
		});
	}

	@Override
	public boolean doesEmailExist(String email) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			return
			    s.createQuery("FROM UserModel Email WHERE email = " + "'" + email + "'")
			    .iterate()
			    .hasNext();
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean logIn(String email, String password) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			String emailToCheck = "";
			String passwordToCheck = "";

			List<UserModel> userToCheck = (List<UserModel>) s
					.createQuery("FROM UserModel Email WHERE email = " + "'" + email + "'").list();

			for (Iterator<UserModel> iterator = userToCheck.iterator(); iterator.hasNext();) {
				UserModel user = iterator.next();
				emailToCheck = user.getMail();
				passwordToCheck = user.getPassword();
			}
			boolean statement1 = email.equals(emailToCheck);
			boolean statement2 = password.equals(passwordToCheck);
			boolean statement3 = emailToCheck != "";
			boolean statement4 = password != "";

			if (statement1 && statement2 && statement3 && statement4) {
				return true;
			}
			return false;
		});
	}
}
