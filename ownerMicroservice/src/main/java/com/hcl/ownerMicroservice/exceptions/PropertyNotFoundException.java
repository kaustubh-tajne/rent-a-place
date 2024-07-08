package com.hcl.ownerMicroservice.exceptions;

public class PropertyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertyNotFoundException(String message) {
		super(message);
	}
}
