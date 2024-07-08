package com.hcl.ownerMicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.ownerMicroservice.dao.service.OwnerDaoService;
import com.hcl.ownerMicroservice.dao.service.PropertyDaoService;
import com.hcl.ownerMicroservice.dto.OwnerDto;
import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.dto.ReservationDto;
import com.hcl.ownerMicroservice.enums.PropertyStatus;
import com.hcl.ownerMicroservice.exceptions.OwnerNotFoundException;
import com.hcl.ownerMicroservice.exceptions.PropertyNotFoundException;
import com.hcl.ownerMicroservice.mapper.OwnerMapper;
import com.hcl.ownerMicroservice.mapper.PropertyMapper;
import com.hcl.ownerMicroservice.model.Owner;
import com.hcl.ownerMicroservice.model.Property;

@Service
public class OwnerService {

	private final OwnerDaoService ownerDaoService;

	private final PropertyDaoService propertyDaoService;

	private final LoadBalancerClient loadBalancerClient;
	
	private final PropertyService propertyService;

	@Autowired
	private RestTemplate restTemplate;

	public OwnerService(OwnerDaoService ownerDaoService, PropertyDaoService propertyDaoService,
			LoadBalancerClient loadBalancerClient, PropertyService propertyService) {
		this.ownerDaoService = ownerDaoService;
		this.propertyDaoService = propertyDaoService;
		this.loadBalancerClient = loadBalancerClient;
		this.propertyService = propertyService;
	}

	// to get owner list
	public List<OwnerDto> getAll() {
		List<Owner> ownerList = ownerDaoService.getAll();
		return OwnerMapper.toDto(ownerList);

	}

	// to get owner by id
	public OwnerDto getOneById(int id) {
		Optional<Owner> owner = ownerDaoService.getOneById(id);
		if (owner.isEmpty()) {
			throw new OwnerNotFoundException("Owner does not found..please check again..");
		}
		return OwnerMapper.toDto(owner.get());
	}

	// to register owner
	public OwnerDto create(OwnerDto ownerDto) {
		final Owner owner = OwnerMapper.toEntity(ownerDto);

		final Owner savedOwner = ownerDaoService.create(owner);
		return OwnerMapper.toDto(savedOwner);
	}

	// to update owner details
	public OwnerDto update(OwnerDto ownerDto) {
		final Owner owner = OwnerMapper.toEntity(ownerDto);

		final Owner savedOwner = ownerDaoService.create(owner);
		return OwnerMapper.toDto(savedOwner);
	}

	// to delete owner from database
	public boolean delete(int id) {
		return ownerDaoService.delete(id);

	}

	public PropertyDto bookProperty(int propertyId) {
		if (!propertyDaoService.isExistById(propertyId)) {
			throw new PropertyNotFoundException("Property not found");
		}

		Optional<Property> optionalProperty = propertyDaoService.getOneById(propertyId);
		Property property = optionalProperty.get();

		property.setPropertyStatus(PropertyStatus.NOT_AVAILABLE);

		Property updateProperty = propertyDaoService.update(property);

		return PropertyMapper.toDto(updateProperty);
	}

	// to get all the reservations list
	public List<ReservationDto> getAllReservations() {
		ServiceInstance ownerService = loadBalancerClient.choose("USER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println(baseUrl);

		String urlPath = baseUrl + "/api/rentAPLace/v1/reservations";

		ResponseEntity<List<ReservationDto>> responseEntity = restTemplate.exchange(urlPath, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ReservationDto>>() {
				});

		responseEntity.getBody().forEach(reservationDto -> System.out.println(reservationDto));

		return responseEntity.getBody();
	}

	// to approve reservation by owner
	public ReservationDto approveReservation(int reservationId) {
		ServiceInstance ownerService = loadBalancerClient.choose("USER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println(baseUrl);

		String urlPath = baseUrl + "/api/rentAPLace/v1/users/" + reservationId + "/approve";

		ResponseEntity<ReservationDto> responseEntity = restTemplate.exchange(urlPath, HttpMethod.PUT, null,
				new ParameterizedTypeReference<ReservationDto>() {
				});

		return responseEntity.getBody();
	}

	// to reject reservation by owner
	public ReservationDto rejectReservation(int reservationId) {
		List<ReservationDto> list = getAllReservations();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(list);
		int propertyId = 0;
		
		for(ReservationDto r: list) {
			if (r.getId() == reservationId) {
				propertyId = r.getPropertyId();
			}
		}
		
		propertyService.changeRejectStatus(propertyId);
		ServiceInstance ownerService = loadBalancerClient.choose("USER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println(baseUrl);

		String urlPath = baseUrl + "/api/rentAPLace/v1/users/" + reservationId + "/reject";

		ResponseEntity<ReservationDto> responseEntity = restTemplate.exchange(urlPath, HttpMethod.PUT, null,
				new ParameterizedTypeReference<ReservationDto>() {
				});

		return responseEntity.getBody();
	}
}
