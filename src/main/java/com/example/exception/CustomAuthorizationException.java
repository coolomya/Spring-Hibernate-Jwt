package com.example.exception;
public class CustomAuthorizationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomAuthorizationException(String message) {
        super(message);
    }
}