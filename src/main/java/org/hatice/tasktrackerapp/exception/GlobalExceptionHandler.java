package org.hatice.tasktrackerapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType, List<String> fields) {
		log.error("All errors occur at :" + errorType.getMessage() + fields);
		return new ResponseEntity<>(ErrorMessage.builder().code(errorType.getCode()).message(errorType.getMessage())
		                                        .success(false).fields(fields).build(), errorType.getHttpStatus());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> runtimeExceptionHandler(RuntimeException e) {
		return createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR, List.of(e.getMessage()));
	}
	
	@ExceptionHandler(TaskTrackerException.class)
	public ResponseEntity<ErrorMessage> TaskTrackerException(TaskTrackerException e) {
		return createResponseEntity(e.getErrorType(), List.of(e.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> validationExceptionHandler(MethodArgumentNotValidException e) {
		List<String> fieldErrors = new ArrayList<>();
		e.getBindingResult().getFieldErrors().forEach(fieldError -> {
			fieldErrors.add(fieldError.getField() + " Validation Error: " + fieldError.getDefaultMessage());
		});
		
		return createResponseEntity(ErrorType.VALIDATION_ERROR, fieldErrors);
	}
	
	
}