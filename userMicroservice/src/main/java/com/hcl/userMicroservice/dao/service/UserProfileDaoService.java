package com.hcl.userMicroservice.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.userMicroservice.model.UserProfile;
import com.hcl.userMicroservice.repository.UserProfileRepository;

@Service
public class UserProfileDaoService {

	private final UserProfileRepository userProfileRepository;

	public UserProfileDaoService(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	public boolean isExistById(int id) {
		final Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
		return optionalUserProfile.isPresent();
	}

	public List<UserProfile> getAll() {
		return userProfileRepository.findAll();
	}

	public Optional<UserProfile> getOneById(int id) {
		return userProfileRepository.findById(id);
	}

	public UserProfile create(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	public UserProfile update(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	public boolean delete(int id) {
		userProfileRepository.deleteById(id);
		return true;
	}
}
