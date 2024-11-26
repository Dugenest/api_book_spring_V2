package com.societe.exception;

public class InvalidAuthorDataException extends RuntimeException {
	
	 private static final long serialVersionUID = 1L;

	 public InvalidAuthorDataException(String message) {
	        super(message);
	    }

}
