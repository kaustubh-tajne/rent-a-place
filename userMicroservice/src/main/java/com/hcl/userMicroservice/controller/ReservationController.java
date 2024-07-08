package com.hcl.userMicroservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/rentAPLace/v1/reservations")
//@SecurityRequirement(name = "Authorization")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	// to get the reservation list
	@GetMapping
	public ResponseEntity<List<ReservationDto>> getAll() {

		final List<ReservationDto> result = reservationService.getAll();

		return ResponseEntity.ok(result);
	}

	// to get specific reservation using id
	@GetMapping("/{reservationId}")
	public ResponseEntity<ReservationDto> getOneById(@PathVariable int reservationId) {

		final ReservationDto result = reservationService.getOneById(reservationId);
		if (result == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(result);
	}

	// to do the reservations
	@PostMapping
	public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationDto reservationDto) {

		final ReservationDto result = reservationService.create(reservationDto);
		if (result == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

//	@PutMapping
//	public ResponseEntity<ReservationDto> update(@Valid @RequestBody ReservationDto reservationDto) {
//
//		final ReservationDto result = reservationService.update(reservationDto);
//		if (result == null) {
//			return ResponseEntity.unprocessableEntity().build();
//		}
//		return new ResponseEntity<>(result, HttpStatus.CREATED);
//	}

	// to delete a reservation
	@DeleteMapping("/{reservationId}")
	public ResponseEntity<Void> delete(@PathVariable int reservationId) {
		boolean isDeleted = reservationService.delete(reservationId);
		if (isDeleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
