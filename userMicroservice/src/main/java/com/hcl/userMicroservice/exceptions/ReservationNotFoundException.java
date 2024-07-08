package com.hcl.userMicroservice.exceptions;

public class ReservationNotFoundException extends RuntimeException{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservationNotFoundException(String msg)
	{
		super(msg);
	}
}