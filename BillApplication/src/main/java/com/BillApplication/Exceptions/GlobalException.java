package com.BillApplication.Exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionObj> handleResourceNotFoundException(ResourceNotFoundException exp){
		ExceptionObj exObj = new ExceptionObj();
		exObj.setStatusCode(HttpStatus.NOT_FOUND.value());
		exObj.setMessage(exp.getMessage());
		exObj.setTimestamp(System.currentTimeMillis());
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Invalid id - Re-check the id.");
		return new ResponseEntity<ExceptionObj>(exObj,HttpStatus.NOT_FOUND);
	}
}
