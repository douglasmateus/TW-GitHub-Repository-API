package com.thoughtworks.repos.exception;

/**
 * EntityNotFoundException throws an exception to inform that the resource does not exist
 * @author douglas.mateus
 * 
 */
public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7664041172222540937L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
