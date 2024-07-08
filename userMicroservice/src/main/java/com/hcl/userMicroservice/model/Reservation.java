package com.hcl.userMicroservice.model;

import com.hcl.userMicroservice.enums.ReservationStatus;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	private ReservationStatus ReservationStatus;

	private float price;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserProfile userProfile;

	@ManyToOne
	@JoinColumn(name = "property_id", nullable = false)
	private Property property;

	public Reservation() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public ReservationStatus getReservationStatus() {
		return ReservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		ReservationStatus = reservationStatus;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", ReservationStatus=" + ReservationStatus + ", price=" + price + ", userProfile=" + userProfile
				+ ", property=" + property + "]";
	}

}
