package com.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	Long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %l" ,resourceName , fieldName , fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
