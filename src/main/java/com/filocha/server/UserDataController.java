package com.filocha.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filocha.hibernate.UserModel;
import com.filocha.hibernate.UserService;

/**
 * Defines methods to manage http requests received from client associated with
 * user operations
 *
 */
@RestController
public class UserDataController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "addNewUser", method = RequestMethod.POST)
	public void addNewUser(@RequestBody UserModel userData) {
		userService.addNewUser(userData);
	}

	@RequestMapping(value = "doesEmailExist", method = RequestMethod.POST)
	public boolean doesEmailExist(@RequestBody UserModel userData) {
		boolean result = userService.doesEmailExist(userData.getMail());

		return result;
	}

	@RequestMapping(value = "logIn", method = RequestMethod.POST)
	public LoginResponseModel logIn(@RequestBody UserModel userData) {
		boolean result = userService.logIn(userData.getMail(), userData.getPassword());
		LoginResponseModel response = new LoginResponseModel();
		if (result == true) {
			response.setUserEmail(userData.getMail());
		}
		response.setLoginSuccess(result);

		return response;
	}
}
