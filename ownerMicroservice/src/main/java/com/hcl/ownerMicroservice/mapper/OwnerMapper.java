package com.hcl.ownerMicroservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.hcl.ownerMicroservice.dto.MessageDto;
import com.hcl.ownerMicroservice.dto.OwnerDto;
import com.hcl.ownerMicroservice.dto.PropertyDto;
import com.hcl.ownerMicroservice.model.Message;
import com.hcl.ownerMicroservice.model.Owner;
import com.hcl.ownerMicroservice.model.Property;

public class OwnerMapper {
	public static List<OwnerDto> toDto(List<Owner> ownerList) {
		return ownerList.stream().map(owner -> toDto(owner)).collect(Collectors.toList());
	}

	public static OwnerDto toDto(Owner owner) {
		OwnerDto ownerDto = new OwnerDto();
		ownerDto.setId(owner.getId());
		ownerDto.setUsername(owner.getUsername());
		ownerDto.setPassword(owner.getPassword());
		ownerDto.setEmail(owner.getEmail());
		ownerDto.setPropertyDtos(PropertyMapper.toDto(owner.getProperties()));
		ownerDto.setMessageDtos(MessageMapper.toDto(owner.getMessages()));
		return ownerDto;
	}

	public static Owner toEntity(OwnerDto ownerDto) {
		Owner owner = new Owner();
		owner.setId(ownerDto.getId());
		owner.setUsername(ownerDto.getUsername());
		owner.setPassword(ownerDto.getPassword());
		owner.setEmail(ownerDto.getEmail());

		List<PropertyDto> propertyDtos = ownerDto.getPropertyDtos();
		List<Property> propertyList = PropertyMapper.toEntity(propertyDtos);

		owner.setProperties(propertyList);
		propertyList.forEach(property -> property.setOwnerId(owner));

		List<MessageDto> messageDtos = ownerDto.getMessageDtos();
		List<Message> messageList = MessageMapper.toEntity(messageDtos);

		owner.setMessages(messageList);
		messageList.forEach(message -> message.setOwner(owner));

		return owner;
	}
}
