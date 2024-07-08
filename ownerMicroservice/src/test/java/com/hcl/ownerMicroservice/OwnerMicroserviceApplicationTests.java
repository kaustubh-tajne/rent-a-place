package com.hcl.ownerMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.ownerMicroservice.controller.OwnerController;
import com.hcl.ownerMicroservice.controller.PropertyController;
import com.hcl.ownerMicroservice.dao.service.OwnerDaoService;
import com.hcl.ownerMicroservice.dao.service.PropertyDaoService;
import com.hcl.ownerMicroservice.dto.OwnerDto;
import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.model.Owner;
import com.hcl.ownerMicroservice.service.OwnerService;
import com.hcl.ownerMicroservice.service.PropertyService;

import java.util.Collections;
import static org.mockito.Mockito.*;

@SpringBootTest
class OwnerMicroserviceApplicationTests {

	@Mock
	private OwnerDaoService ownerDaoService;

	@Mock
	private PropertyDaoService propertyDaoService;

	@Mock
	private LoadBalancerClient loadBalancerClient;

	@InjectMocks
	private PropertyService propertyService;

	@InjectMocks
	private OwnerService ownerService;
	@InjectMocks
	private OwnerController ownerController;
	@InjectMocks
	private PropertyController propertyController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	void testGetAllOwners() {
		List<Owner> owners = Collections.singletonList(new Owner());
		when(ownerDaoService.getAll()).thenReturn(owners);
		List<OwnerDto> result = ownerService.getAll();
		assertEquals(1, result.size());
	}
	@Test
	void testDeleteOwner() {
		int ownerId = 1;
		when(ownerDaoService.delete(ownerId)).thenReturn(true);
		boolean isDeleted = ownerService.delete(ownerId);
		assertTrue(isDeleted);
		verify(ownerDaoService, times(1)).delete(ownerId);
	}

	@Test
	void testGetAll() {
		List<PropertyDto> properties = Collections.singletonList(new PropertyDto());
		when(propertyService.getAll()).thenReturn(properties);
		ResponseEntity<List<PropertyDto>> response = propertyController.getAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(properties, response.getBody());
	}

	@Test
	void testGetOneById() {
		int propertyId = 1;
		PropertyDto property = new PropertyDto();
		when(propertyService.getOneById(propertyId)).thenReturn(property);
		ResponseEntity<PropertyDto> response = propertyController.getOneById(propertyId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(property, response.getBody());
	}

	@Test
	void testCreateVehicle() {
		PropertyDto newProperty = new PropertyDto();
		when(propertyService.create(any())).thenReturn(new PropertyDto());
		ResponseEntity<PropertyDto> response = propertyController.create(newProperty);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testDeleteVehicle() {
		int propertyId = 1;
		when(propertyService.delete(propertyId)).thenReturn(true);
		ResponseEntity<Void> response = propertyController.delete(propertyId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(propertyService, times(1)).delete(propertyId);
	}
}
