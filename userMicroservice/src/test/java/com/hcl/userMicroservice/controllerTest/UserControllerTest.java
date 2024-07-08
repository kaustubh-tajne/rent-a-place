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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.userMicroservice.controller.UserController;
import com.hcl.userMicroservice.dto.UserProfileDto;
import com.hcl.userMicroservice.service.UserProfileService;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	@InjectMocks
	private UserProfileService userProfileService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllUsers() {
		List<UserProfileDto> expectedUsers = new ArrayList<>(); // Add sample user profiles
		when(userProfileService.getAll()).thenReturn(expectedUsers);
		ResponseEntity<List<UserProfileDto>> response = userController.getAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedUsers, response.getBody());
		verify(userProfileService).getAll();
	}

	@Test
	void testGetUserById() {
		int userId = 123;
		UserProfileDto expectedUser = new UserProfileDto(); // Create a sample user profile
		when(userProfileService.getOneById(userId)).thenReturn(expectedUser);
		ResponseEntity<UserProfileDto> response = userController.getOneById(userId);
		if (expectedUser != null) {
			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals(expectedUser, response.getBody());
		} else {
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		}
		verify(userProfileService).getOneById(userId);
	}

}
