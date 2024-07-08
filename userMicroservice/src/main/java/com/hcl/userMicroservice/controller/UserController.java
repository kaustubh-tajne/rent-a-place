package com.hcl.userMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.userMicroservice.dto.PropertyDto;
import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.dto.UserProfileDto;
import com.hcl.userMicroservice.service.UserProfileService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/rentAPLace/v1/users")
//@SecurityRequirement(name = "Authorization")
public class UserController {

	private final UserProfileService userProfileService;

	@Autowired
	public UserController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	// to get users list
	@GetMapping
	public ResponseEntity<List<UserProfileDto>> getAll() {
		List<UserProfileDto> users = userProfileService.getAll();
		return ResponseEntity.ok(users);
	}

	// to fetch user by id
	@GetMapping("/{id}")
	public ResponseEntity<UserProfileDto> getOneById(@PathVariable int id) {
		UserProfileDto result = userProfileService.getOneById(id);
		if (result == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(result);
	}

	// to register user
	@PostMapping
	public ResponseEntity<UserProfileDto> create(@RequestBody UserProfileDto userProfileDto) {
		UserProfileDto result = userProfileService.create(userProfileDto);
		if (result == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}

	// to update user details
	@PutMapping
	public ResponseEntity<UserProfileDto> update(@RequestBody UserProfileDto userProfileDto) {
		final UserProfileDto result = userProfileService.update(userProfileDto);
		if (result == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	// to delete user permanently
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		boolean isDeleted = userProfileService.delete(id);
		if (isDeleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	// to get the properties list
	@GetMapping("/properties")
	public ResponseEntity<List<PropertyDto>> getAllProperties() {
		List<PropertyDto> result = userProfileService.getAllProperties();
		return ResponseEntity.ok(result);
	}

	@Hidden
	@PutMapping("/{reservationId}/approve")
	public ResponseEntity<?> approveProperty(@PathVariable int reservationId) {
		ReservationDto result = userProfileService.approveProperty(reservationId);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	@Hidden
	@PutMapping("/{reservationId}/reject")
	public ResponseEntity<?> rejectProperty(@PathVariable int reservationId) {
		ReservationDto result = userProfileService.rejectProperty(reservationId);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

}