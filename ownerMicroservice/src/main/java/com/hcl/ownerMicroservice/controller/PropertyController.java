package com.hcl.ownerMicroservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.service.PropertyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/rentAPLace/v1/properties")
//@SecurityRequirement(name = "Authorization")
public class PropertyController {

	private final PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	//to get the list of properties
	@GetMapping
	public ResponseEntity<List<PropertyDto>> getAll() {
		final List<PropertyDto> result = propertyService.getAll();
		return ResponseEntity.ok(result);
	}

	//to get a specific property using property id
	@GetMapping("/{propertyId}")
	public ResponseEntity<PropertyDto> getOneById(@PathVariable int propertyId) {
		final PropertyDto result = propertyService.getOneById(propertyId);
		if (result == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(result);
	}

	//to create a property
	@PostMapping
	public ResponseEntity<PropertyDto> create(@RequestBody PropertyDto propertyDto) {
		final PropertyDto result = propertyService.create(propertyDto);
		if (result == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	//to update property details
	@PutMapping
	public ResponseEntity<PropertyDto> update(@RequestBody PropertyDto propertyDto) {
		final PropertyDto result = propertyService.update(propertyDto);
		if (result == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	//to delete a specific property by using its id
	@DeleteMapping("/{propertyId}")
	public ResponseEntity<Void> delete(@PathVariable int propertyId) {
		final boolean isDeleted = propertyService.delete(propertyId);
		if (!isDeleted) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{propertyId}/changeStatus")
	public void changeRejectStatus(@PathVariable int propertyId){
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(propertyId);
		propertyService.changeRejectStatus(propertyId);
	}
}
