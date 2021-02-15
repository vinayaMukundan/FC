package com.example.demo.exception;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.example.demo.airport.AirportNotFoundException;



@ControllerAdvice
@RestController
public class AirportExceptionHandler{
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public final ResponseEntity<ExceptionDetails> customerValidationErrorHandling(MethodArgumentNotValidException ex, WebRequest request) {
		ExceptionDetails errorDetails = new ExceptionDetails(new Date(),"Validation error",ex.getBindingResult().getFieldError().getDefaultMessage());
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);  
	  }
	
	  @ExceptionHandler(ConstraintViolationException.class)
	  public final ResponseEntity<Object> handleConstraintViolationExceptions(ConstraintViolationException ex) {
	    String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
	    ExceptionDetails errorDetails = new ExceptionDetails(new Date(), "Invalid input parameters:", ex.getMessage());
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	
	  @ExceptionHandler(AirportNotFoundException.class)
	  public final ResponseEntity<ExceptionDetails> handleUserNotFoundException(AirportNotFoundException ex, WebRequest request) {
		ExceptionDetails errorDetails = new ExceptionDetails(new Date(), ex.getMessage(),"AIRPORT ID NOT EXIST");
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);  
	  }
	  
	   @ExceptionHandler(Exception.class)
	   public final ResponseEntity<ExceptionDetails> handleAllExceptions(Exception ex, WebRequest request) {
			ExceptionDetails errorDetails = new ExceptionDetails(new Date(), "INTERNAL-SERVER-ERROR",
		      request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  
	  
	//extends ResponseEntityExceptionHandler{
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails errorDetails = new ExceptionDetails(new Date(), "Validation Failed",
		        ex.getBindingResult().toString());
		    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}*/

	/*@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExceptionDetails> handleConstraintViolationException(Exception ex, WebRequest request) {
		ExceptionDetails errorDetails = new ExceptionDetails(new Date(), ex.getMessage(),
	      request.getDescription(false));
	    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	*/
	/*public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    
		Map<String, String> errors = new HashMap<>();
	   
		ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	   
		return errors;
	}*/
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//	    
//		Map<String, String> errors = new HashMap<>();
//	   
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//	   
//		return errors;
//	}
	
}	
