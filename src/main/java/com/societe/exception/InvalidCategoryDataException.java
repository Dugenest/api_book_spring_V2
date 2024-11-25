package com.societe.exception;

public class InvalidCategoryDataException extends RuntimeException {
	
	 private static final long serialVersionUID = 1L;

	 public InvalidCategoryDataException(String message) {
	        super(message);
	    }

}
