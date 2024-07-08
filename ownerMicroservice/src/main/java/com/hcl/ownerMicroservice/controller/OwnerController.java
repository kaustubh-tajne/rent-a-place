package com.hcl.ownerMicroservice.controller;

import java.util.List;

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

import com.hcl.ownerMicroservice.dto.OwnerDto;
import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.dto.ReservationDto;
import com.hcl.ownerMicroservice.service.OwnerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/rentAPLace/v1/owner")
//@SecurityRequirement(name = "Authorization")
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	//to get list of owners registered till the time
	@GetMapping
	public ResponseEntity<List<OwnerDto>> getAll() {
		List<OwnerDto> users = ownerService.getAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	//to get a specific owner using the owner id
	@GetMapping("/{id}")
	public ResponseEntity<OwnerDto> getOneById(@PathVariable int id) {
		OwnerDto owner = ownerService.getOneById(id);
		if (owner == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(owner, HttpStatus.OK);
	}

	// to register the owner
	@PostMapping
	public ResponseEntity<OwnerDto> create(@RequestBody OwnerDto ownerDto) {
		OwnerDto owner = ownerService.create(ownerDto);
		if (owner == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(owner, HttpStatus.CREATED);
	}

	//to update owner details
	@PutMapping
	public ResponseEntity<OwnerDto> update(@RequestBody OwnerDto ownerDto) {
		final OwnerDto result = ownerService.update(ownerDto);
		if (result == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	//to delete owner from database
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		boolean isDeleted = ownerService.delete(id);
		if (isDeleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	//////////////////////////////////

	@GetMapping("/property/{propertyId}/booked")
	public ResponseEntity<PropertyDto> bookProperty(@PathVariable int propertyId) {
		PropertyDto result = ownerService.bookProperty(propertyId);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	//to get the list of reservations made 
	@GetMapping("/reservations")
	public ResponseEntity<List<ReservationDto>> getAllReservations() {
		List<ReservationDto> result = ownerService.getAllReservations();
		return ResponseEntity.ok(result);
	}

	//to approve a reservation made by user
	@PostMapping("/{reservationId}/approve")
	public ResponseEntity<?> approveReservation(@PathVariable int reservationId) {
		ReservationDto result = ownerService.approveReservation(reservationId);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	//to reject a reservation made by user
	@PostMapping("/{reservationId}/reject")
	public ResponseEntity<?> rejectReservation(@PathVariable int reservationId) {
		ReservationDto result = ownerService.rejectReservation(reservationId);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
}
