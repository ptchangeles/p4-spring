package com.revature.exception;

public class CheckingAccountNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckingAccountNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
