package com.hcl.userMicroservice.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.userMicroservice.controller.ReservationController;
import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.service.ReservationService;

@SpringBootTest
public class ReservationControllerTest {
	@InjectMocks
	private ReservationService reservationService;
	@InjectMocks
	private ReservationController reservationController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllReservations() {
		List<ReservationDto> expectedReservations = new ArrayList<>(); // Add sample bookings
		when(reservationService.getAll()).thenReturn(expectedReservations);
		ResponseEntity<List<ReservationDto>> response = reservationController.getAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedReservations, response.getBody());
		verify(reservationService).getAll();
	}

	@Test
	void testGetReservationById() {
		int reservationId = 123; // Replace with a valid booking ID
		ReservationDto expectedReservation = new ReservationDto(); // Create a sample booking
		when(reservationService.getOneById(reservationId)).thenReturn(expectedReservation);
		ResponseEntity<ReservationDto> response = reservationController.getOneById(reservationId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedReservation, response.getBody());
		verify(reservationService).getOneById(reservationId);
	}

	@Test
	void testCreateReservation() {
		ReservationDto reservationDto = new ReservationDto(); // Create a sample booking DTO
		when(reservationService.create(reservationDto)).thenReturn(reservationDto);
		ResponseEntity<ReservationDto> response = reservationController.create(reservationDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(reservationDto, response.getBody());
		verify(reservationService).create(reservationDto);
	}

	@Test
	void testDeleteReservation() {
		int reservationId = 123;
		boolean isDeleted = true;
		when(reservationService.delete(reservationId)).thenReturn(isDeleted);
		ResponseEntity<Void> response = reservationController.delete(reservationId);
		if (isDeleted) {
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} else {
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		}
		verify(reservationService).delete(reservationId);
	}
}
