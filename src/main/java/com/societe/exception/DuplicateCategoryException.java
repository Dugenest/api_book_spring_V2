package com.societe.exception;

public class DuplicateCategoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public DuplicateCategoryException(String message) {
	        super(message);
	    }
}