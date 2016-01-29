package com.filocha.server;

import com.filocha.hibernate.BookModel;
import com.filocha.hibernate.ParkingModel;
import com.filocha.hibernate.UserModel;

public interface AccountData {

	UserModel getUserData(String userEmail);

	ParkingModel getUserParkingData(String userEmail);

	BookModel getUserBookData(String userEmail);

}