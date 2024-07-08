package com.hcl.userMicroservice.exceptions;

public class PropertyAlreadyBookedException extends RuntimeException{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertyAlreadyBookedException(String msg)
	{
		super(msg);
	}
}