package com.example.rest.exception;

public class ResourceNotFoundException extends RuntimeException {
  
    public ResourceNotFoundException(Long id) {
      super("Could not find id: " + id);
    }
  }