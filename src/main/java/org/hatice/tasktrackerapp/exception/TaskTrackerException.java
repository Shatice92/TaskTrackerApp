package org.hatice.tasktrackerapp.exception;

public class TaskTrackerException extends RuntimeException {
	private ErrorType errorType;
	
	public TaskTrackerException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
	public ErrorType getErrorType() {
		return errorType;
	}
}