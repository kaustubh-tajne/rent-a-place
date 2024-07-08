package com.hcl.userMicroservice.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.userMicroservice.model.Property;
import com.hcl.userMicroservice.repository.PropertyRepository;

@Service
public class PropertyDaoService {

	private final PropertyRepository propertyRepository;

	@Autowired
	public PropertyDaoService(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	public List<Property> getAll() {
		return propertyRepository.findAll();
	}

	public Optional<Property> getOneById(int id) {
		return propertyRepository.findById(id);
	}

	public Property create(Property property) {
		return propertyRepository.save(property);
	}

	public Property update(Property property) {
		return propertyRepository.save(property);
	}

	public boolean delete(int id) {
		propertyRepository.deleteById(id);
		return true;
	}

	public boolean isExistById(int propertyId) {
		final Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
		return optionalProperty.isPresent();
	}
}
