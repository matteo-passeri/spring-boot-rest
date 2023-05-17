

package com.example.rest;

class OrderNotFoundException extends RuntimeException {

    OrderNotFoundException(Long id) {
    super("Could not find order " + id);
  }
}