package com.hcl.userMicroservice.service;

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

import com.hcl.userMicroservice.dao.service.ReservationDaoService;
import com.hcl.userMicroservice.dao.service.UserProfileDaoService;
import com.hcl.userMicroservice.dto.PropertyDto;
import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.dto.UserProfileDto;
import com.hcl.userMicroservice.enums.ReservationStatus;
import com.hcl.userMicroservice.exceptions.UserNotFoundException;
import com.hcl.userMicroservice.mapper.ReservationMapper;
import com.hcl.userMicroservice.mapper.UserProfileMapper;
import com.hcl.userMicroservice.model.Reservation;
import com.hcl.userMicroservice.model.UserProfile;

@Service
public class UserProfileService {
	private final UserProfileDaoService userProfileDaoService;

	private final LoadBalancerClient loadBalancerClient;

	private final ReservationDaoService reservationDaoService;

	@Autowired
	private RestTemplate restTemplate;

	public UserProfileService(UserProfileDaoService userProfileDaoService, LoadBalancerClient loadBalancerClient,
			ReservationDaoService reservationDaoService) {
		this.userProfileDaoService = userProfileDaoService;
		this.loadBalancerClient = loadBalancerClient;
		this.reservationDaoService = reservationDaoService;
	}

	// to get users list
	public List<UserProfileDto> getAll() {
		List<UserProfile> userList = userProfileDaoService.getAll();
		return UserProfileMapper.toDto(userList);

	}

	// to fetch user by id
	public UserProfileDto getOneById(int id) {
		Optional<UserProfile> userProfile = userProfileDaoService.getOneById(id);
		if (userProfile.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		return UserProfileMapper.toDto(userProfile.get());
	}

	// to register user
	public UserProfileDto create(UserProfileDto userProfileDto) {
		final UserProfile userProfile = UserProfileMapper.toEntity(userProfileDto);

		final UserProfile savedUserProfile = userProfileDaoService.create(userProfile);
		return UserProfileMapper.toDto(savedUserProfile);
	}

	// to update user details
	public UserProfileDto update(UserProfileDto userProfileDto) {
		final UserProfile userProfile = UserProfileMapper.toEntity(userProfileDto);

		final UserProfile savedUserProfile = userProfileDaoService.create(userProfile);
		return UserProfileMapper.toDto(savedUserProfile);
	}

	// to delete user from database
	public boolean delete(int id) {
		return userProfileDaoService.delete(id);
	}

	// to get the properties list
	public List<PropertyDto> getAllProperties() {
		ServiceInstance ownerService = loadBalancerClient.choose("OWNER-SERVICE");
		String baseUrl = ownerService.getUri().toString();
		System.out.println(baseUrl);
		String urlPath = baseUrl + "/api/rentAPLace/v1/properties";

		ResponseEntity<List<PropertyDto>> responseEntity = restTemplate.exchange(urlPath, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PropertyDto>>() {
				});

		responseEntity.getBody().forEach(propertyDto -> System.out.println(propertyDto));

		return responseEntity.getBody();
	}

	public ReservationDto approveProperty(int reservationId) {
		Optional<Reservation> optionalReservation = reservationDaoService.getOneById(reservationId);
		Reservation reservation = optionalReservation.get();

		reservation.setReservationStatus(ReservationStatus.CONFIRMED);
		Reservation updatedReservation = reservationDaoService.update(reservation);

		return ReservationMapper.toDto(updatedReservation);
	}

	public ReservationDto rejectProperty(int reservationId) {
		Optional<Reservation> optionalReservation = reservationDaoService.getOneById(reservationId);
		Reservation reservation = optionalReservation.get();
	System.out.println("########################################################");
		System.out.println(reservation);

		reservation.setReservationStatus(ReservationStatus.REJECT);
		Reservation updatedReservation = reservationDaoService.update(reservation);

		return ReservationMapper.toDto(updatedReservation);
	}

}
