package com.hcl.ownerMicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.ownerMicroservice.dao.service.OwnerDaoService;
import com.hcl.ownerMicroservice.dao.service.PropertyDaoService;
import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.enums.PropertyStatus;
import com.hcl.ownerMicroservice.exceptions.OwnerNotFoundException;
import com.hcl.ownerMicroservice.exceptions.PropertyNotFoundException;
import com.hcl.ownerMicroservice.mapper.PropertyMapper;
import com.hcl.ownerMicroservice.model.Owner;
import com.hcl.ownerMicroservice.model.Property;

@Service
public class PropertyService {
	private final PropertyDaoService propertyDaoService;

	private final OwnerDaoService ownerDaoService;

	public PropertyService(PropertyDaoService propertyDaoService, OwnerDaoService ownerDaoService) {
		this.propertyDaoService = propertyDaoService;
		this.ownerDaoService = ownerDaoService;
	}

	// to get all properties
	public List<PropertyDto> getAll() {
		List<Property> propertyList = propertyDaoService.getAll();
		return PropertyMapper.toDto(propertyList);
	}

	// to get specific property by id
	public PropertyDto getOneById(int id) {
		Optional<Property> property = propertyDaoService.getOneById(id);
		System.out.println("HIIIIIIIIIII");
		System.out.println(property);
		if (property.isEmpty()) {
			throw new PropertyNotFoundException("Property not found");
		}
		return PropertyMapper.toDto(property.get());
	}

	// to create property
	public PropertyDto create(PropertyDto propertyDto) {
		if (!ownerDaoService.isExistById(propertyDto.getOwnerId())) {
			throw new OwnerNotFoundException("Owner not found");
		}

		final Optional<Owner> optionalOwner = ownerDaoService.getOneById(propertyDto.getOwnerId());
		final Owner owner = optionalOwner.get();

		final Property property = PropertyMapper.toEntity(propertyDto);

		property.setOwnerId(owner);
		owner.getProperties().add(property);

		final Property savedProperty = propertyDaoService.create(property);
		ownerDaoService.update(owner);
		return PropertyMapper.toDto(savedProperty);
	}

	// update property content
	public PropertyDto update(PropertyDto propertyDto) {
		if (!ownerDaoService.isExistById(propertyDto.getOwnerId())) {
			throw new OwnerNotFoundException("Owner not found");
		}

		final Optional<Owner> optionalOwner = ownerDaoService.getOneById(propertyDto.getOwnerId());
		final Owner owner = optionalOwner.get();

		final Property property = PropertyMapper.toEntity(propertyDto);
		property.setOwnerId(owner);

		final Property updatedProperty = propertyDaoService.update(property);
		return PropertyMapper.toDto(updatedProperty);
	}

	// to delete property from database
	public boolean delete(int id) {
		propertyDaoService.delete(id);
		return true;
	}

	public void changeRejectStatus(int id) {
		System.out.println("Change Status***********************U");
		Optional<Property> optionalProperty = propertyDaoService.getOneById(id);
		final Property property = optionalProperty.get();
		property.setPropertyStatus(PropertyStatus.AVAILABLE);
		propertyDaoService.update(property);
	}
}
