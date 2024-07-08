package com.hcl.userMicroservice.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String username;

	private String password;

	private String email;

	@OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<>();

	@OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	public UserProfile() {
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

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


}
