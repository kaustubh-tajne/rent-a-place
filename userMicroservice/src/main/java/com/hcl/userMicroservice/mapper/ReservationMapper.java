package com.hcl.userMicroservice.mapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.model.Reservation;

public class ReservationMapper {

	public static List<ReservationDto> toDto(List<Reservation> reservations) {
		return reservations.stream().map(reservation -> toDto(reservation)).collect(Collectors.toList());
	}

	public static List<Reservation> toEntity(List<ReservationDto> reservationDtos) {
		return reservationDtos.stream().map(reservationDto -> toEntity(reservationDto)).collect(Collectors.toList());
	}
	 public static float dummyPrice(LocalDate start, LocalDate end) {
	        float price = 0;

	        Random random = new Random();
	        float randomValue = random.nextInt(100) ;
	        price = randomValue * 10;
	        return price;
	    }

	public static ReservationDto toDto(Reservation reservations) {
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setId(reservations.getId());
		reservationDto.setCheckInDate(reservations.getCheckInDate());
		reservationDto.setCheckOutDate(reservations.getCheckOutDate());
		reservationDto.setReservationStatus(reservations.getReservationStatus());
		reservationDto.setPrice(reservations.getPrice());
		reservationDto.setUserProfileId(reservations.getUserProfile().getId());
		reservationDto.setPropertyId(reservations.getProperty().getId());

		return reservationDto;
	}

	public static Reservation toEntity(ReservationDto reservationDto) {
		Reservation reservation = new Reservation();
		reservation.setId(reservationDto.getId());
		reservation.setCheckInDate(reservationDto.getCheckInDate());
		reservation.setCheckOutDate(reservationDto.getCheckOutDate());
		reservation.setReservationStatus(reservationDto.getReservationStatus());
		reservation.setPrice(dummyPrice(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate()));
		return reservation;
	}

}
