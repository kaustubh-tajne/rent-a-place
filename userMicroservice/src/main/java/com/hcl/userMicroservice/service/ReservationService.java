package com.hcl.userMicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.userMicroservice.dao.service.PropertyDaoService;
import com.hcl.userMicroservice.dao.service.ReservationDaoService;
import com.hcl.userMicroservice.dao.service.UserProfileDaoService;
import com.hcl.userMicroservice.dto.PropertyDto;
import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.enums.PropertyStatus;
import com.hcl.userMicroservice.exceptions.PropertyAlreadyBookedException;
import com.hcl.userMicroservice.exceptions.ReservationNotFoundException;
import com.hcl.userMicroservice.mapper.PropertyMapper;
import com.hcl.userMicroservice.mapper.ReservationMapper;
import com.hcl.userMicroservice.model.Property;
import com.hcl.userMicroservice.model.Reservation;
import com.hcl.userMicroservice.model.UserProfile;

@Service
public class ReservationService {
	private final ReservationDaoService reservationDaoService;

	private final UserProfileDaoService userProfileDaoService;

	private final PropertyDaoService propertyDaoService;

	private final LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	public ReservationService(ReservationDaoService reservationDaoService, UserProfileDaoService userProfileDaoService,
			PropertyDaoService propertyDaoService, LoadBalancerClient loadBalancerClient) {
		this.reservationDaoService = reservationDaoService;
		this.userProfileDaoService = userProfileDaoService;
		this.propertyDaoService = propertyDaoService;
		this.loadBalancerClient = loadBalancerClient;
	}

	// to get the reservation list
	public List<ReservationDto> getAll() {
		List<Reservation> reservationList = reservationDaoService.getAll();
		return ReservationMapper.toDto(reservationList);

	}

	// to get specific reservation using id
	public ReservationDto getOneById(int id) {
		Optional<Reservation> reservation = reservationDaoService.getOneById(id);
		if (reservation.isEmpty()) {
			throw new ReservationNotFoundException("Reservation not found");
		}
		return ReservationMapper.toDto(reservation.get());
	}

	// to do the reservations
	public ReservationDto create(ReservationDto reservationDto) {
		System.out.println("1");
		System.out.println(reservationDto);
		PropertyDto propertyDto = getPropertyById(reservationDto.getPropertyId());
		Property property = PropertyMapper.toEntity(propertyDto);
		System.out.println(property);

		if (property.getPropertyStatus().equals(PropertyStatus.NOT_AVAILABLE)) {
			throw new PropertyAlreadyBookedException("Property Already Booked");
		}

		Optional<UserProfile> optionalUserProfile = userProfileDaoService.getOneById(reservationDto.getUserProfileId());
		UserProfile userProfile = optionalUserProfile.get();
		System.out.println(userProfile);

		Reservation reservation = ReservationMapper.toEntity(reservationDto);

		reservation.setUserProfile(userProfile);
		reservation.setProperty(property);

		userProfile.getReservations().add(reservation);
		propertyDaoService.create(property);

		Reservation savedReservation = reservationDaoService.create(reservation);
		System.out.println(savedReservation);
		userProfileDaoService.update(userProfile);
		changeStatusOfProperty(reservationDto.getPropertyId());
		return ReservationMapper.toDto(savedReservation);
	}

	// function to get property by using id to make reservations
	public PropertyDto getPropertyById(int propertyId) {
		ServiceInstance ownerService = loadBalancerClient.choose("OWNER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println(baseUrl);
		String urlPath = baseUrl + "/api/rentAPLace/v1/properties/" + propertyId;
		System.out.println("Url Path"+ urlPath);
		PropertyDto propertyDto = null;
		try {
			ResponseEntity<PropertyDto> propertyDtoResponseEntity = restTemplate.getForEntity(urlPath, PropertyDto.class);
			System.out.println(propertyDtoResponseEntity);
			System.out.println(propertyDtoResponseEntity.getBody());
			PropertyDto propertyDtoResp = propertyDtoResponseEntity.getBody();
			propertyDto = new PropertyDto();
			propertyDto.setId(propertyDtoResp.getId());
			propertyDto.setTitle(propertyDtoResp.getTitle());
			propertyDto.setFeatures(propertyDtoResp.getFeatures());
			propertyDto.setPropertyType(propertyDtoResp.getPropertyType());
			propertyDto.setPropertyStatus(propertyDtoResp.getPropertyStatus());
			propertyDto.setLocation(propertyDtoResp.getLocation());
			propertyDto.setRating(propertyDtoResp.getRating());
		} catch (Exception e) {
			System.out.println(e);
		}
		

		return propertyDto;
	}

//	public ReservationDto update(ReservationDto reservationDto) {
//		if (!userProfileDaoService.isExistById(reservationDto.getUserProfileId())) {
//			throw new RuntimeException("User Not Found");
//		}
//		if (!propertyDaoService.isExistById(reservationDto.getPropertyId())) {
//			throw new RuntimeException("Property Not Found");
//		}
//
//		final Optional<UserProfile> optionalUserProfile = userProfileDaoService
//				.getOneById(reservationDto.getUserProfileId());
//		final UserProfile userProfile = optionalUserProfile.get();
//
//		final Optional<Property> optionalProperty = propertyDaoService.getOneById(reservationDto.getPropertyId());
//		final Property property = optionalProperty.get();
//
//		final Reservation reservation = ReservationMapper.toEntity(reservationDto);
//
//		reservation.setUserProfile(userProfile);
//		reservation.setProperty(property);
//
//		final Reservation updatedReservation = reservationDaoService.update(reservation);
//		return ReservationMapper.toDto(updatedReservation);
//	}

	// function to change the status of property if reservation is done
	public PropertyDto changeStatusOfProperty(int propertyId) {
		ServiceInstance ownerService = loadBalancerClient.choose("OWNER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println(baseUrl);
		String urlPath = baseUrl + "/api/rentAPLace/v1/owner/property/" + propertyId + "/booked";

		ResponseEntity<PropertyDto> propertyDtoResponseEntity = restTemplate.getForEntity(urlPath, PropertyDto.class);
		return propertyDtoResponseEntity.getBody();
	}

	// to delete a reservation
	public boolean delete(int id) {
		Optional<Reservation> reservation = reservationDaoService.getOneById(id);
//		System.out.println(reservation.get().getProperty().getId());
		int propertyId = reservation.get().getProperty().getId();
		System.out.println("propertyId Id");
		System.out.println(propertyId);
		ServiceInstance ownerService = loadBalancerClient.choose("OWNER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println("base url is: "+baseUrl);
		String urlPath = baseUrl + "/api/rentAPLace/v1/properties/" + propertyId+"/changeStatus";
		System.out.println("url path is: "+urlPath);
		restTemplate.getForEntity(urlPath, null);
		reservationDaoService.delete(id);
		
		return true;
	}

}
