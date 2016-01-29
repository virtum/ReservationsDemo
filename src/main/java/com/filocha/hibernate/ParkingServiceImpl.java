package com.filocha.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filocha.server.ParkingResponseModel;

@Component
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public ParkingResponseModel getParkingInfo(String parkingName, String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			ParkingResponseModel result = new ParkingResponseModel();
			int free = 0;
			int taken = 0;
			boolean hasPlace = false;

			List<ParkingModel> parkingList = (List<ParkingModel>) s
					.createQuery("FROM ParkingModel PARKINGNAME WHERE parkingName = " + "'" + parkingName + "'").list();

			for (Iterator<ParkingModel> iterator = parkingList.iterator(); iterator.hasNext();) {
				ParkingModel parkingPlace = iterator.next();
				if (parkingPlace.isFree() == true) {
					free++;
				} else {
					taken++;
				}
			}
			List<ParkingModel> parkingPlaceOwner = (List<ParkingModel>) s
					.createQuery("FROM ParkingModel PARKINGNAME WHERE ownerEmail = " + "'" + userEmail + "'").list();

			for (Iterator<ParkingModel> iterator = parkingPlaceOwner.iterator(); iterator.hasNext();) {
				ParkingModel parking = iterator.next();
				if (userEmail.equals(parking.getOwnerEmail())) {
					hasPlace = true;
					result.setUserParkingPlaceNumber(parking.getPlaceNumber());
					result.setUserEmail(parking.getOwnerEmail());
				}
			}
			result.setHasAlreadyParkingPlace(hasPlace);
			result.setParkingName(parkingName);
			result.setPlacesFree(free);
			result.setPlacesTaken(taken);

			return result;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean reserveParkingPlace(String userEmail, String parkingName) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			boolean result = false;
			boolean hasPlace = false;

			List<ParkingModel> hasParkingPlaceList = (List<ParkingModel>) s.createQuery("FROM ParkingModel PARKINGNAME")
					.list();

			for (Iterator<ParkingModel> iterator = hasParkingPlaceList.iterator(); iterator.hasNext();) {
				ParkingModel parking = iterator.next();

				String mail = parking.getOwnerEmail();
				if (mail != null && parking.getOwnerEmail().equals(userEmail)) {
					hasPlace = true;
				}
			}
			List<ParkingModel> freeParkingPlacesList = (List<ParkingModel>) s
					.createQuery("FROM ParkingModel PARKINGNAME WHERE parkingName = " + "'" + parkingName + "'"
							+ "AND isFree = " + "'" + true + "'")
					.list();

			if (hasPlace == false) {
				outerLoop: for (Iterator<ParkingModel> iterator = freeParkingPlacesList.iterator(); iterator
						.hasNext();) {
					ParkingModel parking = iterator.next();
					boolean isFree = parking.isFree();

					if (isFree == true) {
						ParkingModel parkingModel = new ParkingModel();

						parkingModel.setId(parking.getId());
						parkingModel.setFree(false);
						parkingModel.setOwnerEmail(userEmail);
						parkingModel.setParkingName(parkingName);
						parkingModel.setPlaceNumber(parking.getPlaceNumber());
						updateParkingPlace(parkingModel);

						result = true;

						break outerLoop;
					}
				}
			}
			return result;
		});
	}

	public Void updateParkingPlace(ParkingModel parkingModel) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.update(parkingModel);
			return null;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean leaveParkingPlace(String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			boolean placeLeft = false;

			List<ParkingModel> parkingList1 = (List<ParkingModel>) s
					.createQuery("FROM ParkingModel PARKINGNAME WHERE ownerEmail = " + "'" + userEmail + "'").list();

			for (Iterator<ParkingModel> iterator = parkingList1.iterator(); iterator.hasNext();) {
				ParkingModel parking = iterator.next();
				if (userEmail.equals(parking.getOwnerEmail())) {
					ParkingModel parkingModel = new ParkingModel();

					parkingModel.setId(parking.getId());
					parkingModel.setFree(true);
					parkingModel.setOwnerEmail(null);
					parkingModel.setParkingName(parking.getParkingName());
					parkingModel.setPlaceNumber(parking.getPlaceNumber());

					updateParkingPlace(parkingModel);

					placeLeft = true;
				}
			}
			return placeLeft;
		});
	}

	@Override
	public ParkingModel addNewParking(ParkingModel model) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {

			s.save(model);

			return model;
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
