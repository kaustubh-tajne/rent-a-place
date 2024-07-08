package com.hcl.ownerMicroservice.dto;

import java.time.LocalDate;

import com.hcl.ownerMicroservice.enums.ReservationStatus;

public class ReservationDto {
	private int id;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	private ReservationStatus reservationStatus;

	private float price;

	private int userProfileId;

	private int propertyId;

	public ReservationDto() {
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
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(int userProfileId) {
		this.userProfileId = userProfileId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	@Override
	public String toString() {
		return "ReservationDto [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", reservationStatus=" + reservationStatus + ", price=" + price + ", userProfileId=" + userProfileId
				+ ", propertyId=" + propertyId + "]";
	}

}
