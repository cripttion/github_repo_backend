package com.githubrepo.handler;
public class ResourceNotFoundException extends RuntimeException {
   
	public ResourceNotFoundException(String message) {
        super(message);
    }

}
