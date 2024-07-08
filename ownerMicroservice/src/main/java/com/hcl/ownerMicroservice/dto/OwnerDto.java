package com.hcl.ownerMicroservice.dto;

import java.util.List;


public class OwnerDto {
	private int id;

	private String username;

	private String password;

	private String email;

	private List<PropertyDto> propertyDtos;
	private List<MessageDto> messageDtos;

	public OwnerDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PropertyDto> getPropertyDtos() {
		return propertyDtos;
	}

	public void setPropertyDtos(List<PropertyDto> propertyDtos) {
		this.propertyDtos = propertyDtos;
	}

	public List<MessageDto> getMessageDtos() {
		return messageDtos;
	}

	public void setMessageDtos(List<MessageDto> messageDtos) {
		this.messageDtos = messageDtos;
	}
	
	

}
