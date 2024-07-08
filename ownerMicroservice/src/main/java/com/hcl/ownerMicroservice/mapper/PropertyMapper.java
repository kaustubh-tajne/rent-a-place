package com.hcl.ownerMicroservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.model.Property;

public class PropertyMapper {
	public static List<PropertyDto> toDto(List<Property> properties) {
		return properties.stream().map(property -> toDto(property)).collect(Collectors.toList());
	}

	public static List<Property> toEntity(List<PropertyDto> propertyDtos) {
		return propertyDtos.stream().map(propertyDto -> toEntity(propertyDto)).collect(Collectors.toList());
	}

	public static PropertyDto toDto(Property property) {
		PropertyDto propertyDto = new PropertyDto();
		propertyDto.setId(property.getId());
		propertyDto.setTitle(property.getTitle());
		propertyDto.setFeatures(property.getFeatures());
		propertyDto.setPropertyType(property.getPropertyType());
		propertyDto.setPropertyStatus(property.getPropertyStatus());
		propertyDto.setLocation(property.getLocation());
		propertyDto.setRating(property.getRating());
		propertyDto.setOwnerId(property.getOwnerId().getId());

		return propertyDto;
	}

	public static Property toEntity(PropertyDto propertyDto) {
		Property property = new Property();
		property.setId(propertyDto.getId());
		property.setTitle(propertyDto.getTitle());
		property.setFeatures(propertyDto.getFeatures());
		property.setPropertyType(propertyDto.getPropertyType());
		property.setPropertyStatus(propertyDto.getPropertyStatus());
		property.setLocation(propertyDto.getLocation());
		property.setRating(propertyDto.getRating());

		return property;
	}
}
