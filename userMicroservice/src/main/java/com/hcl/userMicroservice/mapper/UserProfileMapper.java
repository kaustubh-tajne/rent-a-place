package com.hcl.userMicroservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.hcl.userMicroservice.dto.MessageDto;
import com.hcl.userMicroservice.dto.ReservationDto;
import com.hcl.userMicroservice.dto.UserProfileDto;
import com.hcl.userMicroservice.model.Message;
import com.hcl.userMicroservice.model.Reservation;
import com.hcl.userMicroservice.model.UserProfile;

public class UserProfileMapper {
	public static List<UserProfileDto> toDto(List<UserProfile> userList) {
		return userList.stream().map(user -> toDto(user)).collect(Collectors.toList());
	}

	public static UserProfileDto toDto(UserProfile user) {
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setId(user.getId());
		userProfileDto.setUsername(user.getUsername());
		userProfileDto.setPassword(user.getPassword());
		userProfileDto.setEmail(user.getEmail());

		userProfileDto.setReservationDtos(ReservationMapper.toDto(user.getReservations()));
		userProfileDto.setMessageDtos(MessageMapper.toDto(user.getMessages()));
		return userProfileDto;
	}

	public static UserProfile toEntity(UserProfileDto userProfileDto) {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(userProfileDto.getId());
		userProfile.setUsername(userProfileDto.getUsername());
		userProfile.setPassword(userProfileDto.getPassword());
		userProfile.setEmail(userProfileDto.getEmail());

		final List<ReservationDto> reservationDtos = userProfileDto.getReservationDtos();
		final List<Reservation> reservationList = ReservationMapper.toEntity(reservationDtos);

		userProfile.setReservations(reservationList);
		reservationList.forEach(reservation -> reservation.setUserProfile(userProfile));
		List<MessageDto> messageDtos = userProfileDto.getMessageDtos();
		List<Message> messageList = MessageMapper.toEntity(messageDtos);

		userProfile.setMessages(messageList);
		messageList.forEach(message -> message.setUserProfile(userProfile));
		return userProfile;
	}
}
