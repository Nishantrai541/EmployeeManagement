package com.leaveManagement.leaveManagement.globalException;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaveManagement.leaveManagement.response.globalException.GlobalExceptionResponse;

import feign.FeignException;




@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalExceptionResponse> exception(Exception ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<GlobalExceptionResponse> nullPointerException(NullPointerException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<GlobalExceptionResponse> runtimeException(RuntimeException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<GlobalExceptionResponse> numberFormatException(NumberFormatException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<GlobalExceptionResponse> illegalArgumentException(IllegalArgumentException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<GlobalExceptionResponse> iOException(IOException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(NoSuchFieldException.class)
	public ResponseEntity<GlobalExceptionResponse> noSuchFieldException(NoSuchFieldException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<GlobalExceptionResponse> arithmeticException(ArithmeticException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public ResponseEntity<GlobalExceptionResponse> arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(ClassNotFoundException.class)
	public ResponseEntity<GlobalExceptionResponse> classNotFoundException(ClassNotFoundException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<GlobalExceptionResponse> fileNotFoundException(FileNotFoundException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<GlobalExceptionResponse> interruptedException(InterruptedException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<GlobalExceptionResponse> stringIndexOutOfBoundsException(StringIndexOutOfBoundsException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<GlobalExceptionResponse> illegalStateException(IllegalStateException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(NoSuchMethodException.class)
	public ResponseEntity<GlobalExceptionResponse> noSuchMethodException(NoSuchMethodException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<GlobalExceptionResponse> sqlException(SQLException ex) {

		GlobalExceptionResponse response = new GlobalExceptionResponse(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	   @ExceptionHandler(FeignException.class)
	    public ResponseEntity<String> handleFeignException(FeignException e) {
		   String message = extractMessageFromFeignException(e);
	        if (e.status() == HttpStatus.NOT_FOUND.value()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	        }
	        return ResponseEntity.status(e.status()).body(message);
	    }
	   
	   
	   private String extractMessageFromFeignException(FeignException e) {
		    try {
		        // Convert the response body into a Map or custom class
		        ObjectMapper objectMapper = new ObjectMapper();
		        String responseBody = e.contentUTF8();
		        Map<String, Object> errorResponse = objectMapper.readValue(responseBody,new TypeReference<Map<String, Object>>(){});
		        return errorResponse.get("message").toString();
		    } catch (Exception ex) {
		        // Handle parsing errors
		        return "Unable to extract message";
		    }
	   }


}
