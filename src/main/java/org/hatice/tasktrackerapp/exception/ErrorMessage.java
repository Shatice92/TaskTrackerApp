package org.hatice.tasktrackerapp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ErrorMessage {
	Integer code;
	String message;
	Boolean success;
	List<String> fields;
}