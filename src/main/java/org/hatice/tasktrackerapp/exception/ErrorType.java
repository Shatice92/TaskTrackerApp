package org.hatice.tasktrackerapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	USER_NOTFOUND(1001, "User not found", HttpStatus.NOT_FOUND),
	TASK_NOTFOUND(2001, "Task not found", HttpStatus.NOT_FOUND),
	TASKLIST_EMPTY(2002, "Tasklist is Empty", HttpStatus.NOT_FOUND),
	VALIDATION_ERROR(400,"Validation Errors, Please check validation rules",HttpStatus.BAD_REQUEST),
	INTERNAL_SERVER_ERROR(500,"Server Error, Try Again Later",HttpStatus.INTERNAL_SERVER_ERROR),
	USERLIST_IS_EMPTY(1002, "Userlist is Empty", HttpStatus.NOT_FOUND);
	
	int code;
	String message;
	HttpStatus httpStatus;
}