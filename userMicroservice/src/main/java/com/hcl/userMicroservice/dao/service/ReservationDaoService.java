package com.hcl.userMicroservice.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.userMicroservice.model.Reservation;
import com.hcl.userMicroservice.repository.ReservationRepository;

@Service
public class ReservationDaoService {

	private final ReservationRepository reservationRepository;

	@Autowired
	public ReservationDaoService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	public Optional<Reservation> getOneById(int id) {
		return reservationRepository.findById(id);
	}

	public Reservation create(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	public Reservation update(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	public boolean delete(int id) {
		reservationRepository.deleteById(id);
		return true;
	}

//	public boolean isExistById(int id) {
//		final Optional<Reservation> optionalReservation = reservationRepository.findById(id);
//		return optionalReservation.isPresent();
//	}
}
