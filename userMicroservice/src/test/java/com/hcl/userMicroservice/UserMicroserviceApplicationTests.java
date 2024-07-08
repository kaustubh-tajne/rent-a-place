package com.hcl.userMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import com.hcl.userMicroservice.controller.ReservationController;
import com.hcl.userMicroservice.controller.UserController;
import com.hcl.userMicroservice.dao.service.ReservationDaoService;
import com.hcl.userMicroservice.dao.service.UserProfileDaoService;
import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.dto.UserProfileDto;
import com.hcl.userMicroservice.service.ReservationService;
import com.hcl.userMicroservice.service.UserProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

@SpringBootTest
class UserMicroserviceApplicationTests {
//
//	@Mock
//	private UserProfileDaoService userProfileDaoService;
//
//	@Mock
//	private ReservationDaoService reservationDaoService;
//
//	@Mock
//	private LoadBalancerClient loadBalancerClient;
//
//	@InjectMocks
//	private ReservationService reservationService;
//
//	@InjectMocks
//	private UserProfileService userProfileService;
//
//	@InjectMocks
//	private UserController userController;
//	@InjectMocks
//	private ReservationController reservationController;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	void testGetAllUsers() {
//		List<UserProfileDto> expectedUsers = new ArrayList<>(); // Add sample user profiles
//		when(userProfileService.getAll()).thenReturn(expectedUsers);
//		ResponseEntity<List<UserProfileDto>> response = userController.getAll();
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(expectedUsers, response.getBody());
//		verify(userProfileService).getAll();
//	}
//
//	@Test
//	void testGetUserById() {
//		int userId = 123;
//		UserProfileDto expectedUser = new UserProfileDto(); // Create a sample user profile
//		when(userProfileService.getOneById(userId)).thenReturn(expectedUser);
//		ResponseEntity<UserProfileDto> response = userController.getOneById(userId);
//		if (expectedUser != null) {
//			assertEquals(HttpStatus.OK, response.getStatusCode());
//			assertEquals(expectedUser, response.getBody());
//		} else {
//			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//		}
//		verify(userProfileService).getOneById(userId);
//	}
//
//	@Test
//	void testGetAllReservations() {
//		List<ReservationDto> expectedReservations = new ArrayList<>(); // Add sample bookings
//		when(reservationService.getAll()).thenReturn(expectedReservations);
//		ResponseEntity<List<ReservationDto>> response = reservationController.getAll();
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(expectedReservations, response.getBody());
//		verify(reservationService).getAll();
//	}
//
//	@Test
//	void testGetReservationById() {
//		int reservationId = 123; // Replace with a valid booking ID
//		ReservationDto expectedReservation = new ReservationDto(); // Create a sample booking
//		when(reservationService.getOneById(reservationId)).thenReturn(expectedReservation);
//		ResponseEntity<ReservationDto> response = reservationController.getOneById(reservationId);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(expectedReservation, response.getBody());
//		verify(reservationService).getOneById(reservationId);
//	}
//
//	@Test
//	void testCreateReservation() {
//		ReservationDto reservationDto = new ReservationDto(); // Create a sample booking DTO
//		when(reservationService.create(reservationDto)).thenReturn(reservationDto);
//		ResponseEntity<ReservationDto> response = reservationController.create(reservationDto);
//		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//		assertEquals(reservationDto, response.getBody());
//		verify(reservationService).create(reservationDto);
//	}
//
//	@Test
//	void testDeleteReservation() {
//		int reservationId = 123;
//		boolean isDeleted = true;
//		when(reservationService.delete(reservationId)).thenReturn(isDeleted);
//		ResponseEntity<Void> response = reservationController.delete(reservationId);
//		if (isDeleted) {
//			assertEquals(HttpStatus.OK, response.getStatusCode());
//		} else {
//			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//		}
//		verify(reservationService).delete(reservationId);
//	}
	@Test
	void contextLoads() {
	}

}
