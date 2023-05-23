package com.example.rest.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(ResponseStatusException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }




    
    /*
     * @ExceptionHandler(ResponseStatusException.class)
     * 
     * @ResponseStatus(value = HttpStatus.NOT_FOUND)
     * public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex,
     * WebRequest request) {
     * ErrorMessage message = new ErrorMessage(
     * HttpStatus.NOT_FOUND.value(),
     * new Date(),
     * ex.getMessage(),
     * request.getDescription(false));
     * 
     * return message;
     * }
     * 
     * @ExceptionHandler(MethodArgumentNotValidException.class)
     * 
     * @ResponseStatus(value = HttpStatus.BAD_REQUEST)
     * public ErrorMessage methodArgumentNotValidException(ResourceNotFoundException
     * ex, WebRequest request) {
     * ErrorMessage message = new ErrorMessage(
     * HttpStatus.NOT_FOUND.value(),
     * new Date(),
     * ex.getMessage(),
     * request.getDescription(false));
     * 
     * return message;
     * }
     * 
     * @ExceptionHandler(Exception.class)
     * 
     * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
     * public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request)
     * {
     * ErrorMessage message = new ErrorMessage(
     * HttpStatus.INTERNAL_SERVER_ERROR.value(),
     * new Date(),
     * ex.getMessage(),
     * request.getDescription(false));
     * 
     * return message;
     * }
     */

}
