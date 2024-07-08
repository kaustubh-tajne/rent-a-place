package com.hcl.userMicroservice.mapper;

import com.hcl.userMicroservice.dto.PropertyDto;
import com.hcl.userMicroservice.model.Property;

public class PropertyMapper {

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
