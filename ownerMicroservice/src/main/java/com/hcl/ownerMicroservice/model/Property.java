package com.hcl.ownerMicroservice.model;

import com.hcl.ownerMicroservice.enums.PropertyStatus;
import com.hcl.ownerMicroservice.enums.PropertyType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String title;

	private String features;

	private PropertyType propertyType;

	private PropertyStatus propertyStatus;

	private String location;

	private float rating;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private Owner ownerId;

//	@OneToMany(mappedBy = "property")
//	private List<Message> messages;

	public Property() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public PropertyStatus getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(PropertyStatus propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Owner getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Owner ownerId) {
		this.ownerId = ownerId;
	}

//	public List<Message> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(List<Message> messages) {
//		this.messages = messages;
//	}
}
