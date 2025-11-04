package com.javaweb.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponse;

import CustomException.FieldRequiredException;
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleFieldRequiredException(
	        FieldRequiredException ex, WebRequest request) {

		ErrorResponse errorResponseDTO = new ErrorResponse();
	    errorResponseDTO.setError(ex.getMessage());

	    List<String> details = new ArrayList<>();
	    details.add("Check lại name hoặc numberOfBasement đi bởi vì đang bị null đó!");
	    errorResponseDTO.setDetail(details);

	    return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
	}

}
