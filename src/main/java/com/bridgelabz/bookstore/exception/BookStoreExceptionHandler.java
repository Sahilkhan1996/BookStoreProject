package com.bridgelabz.bookstore.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstore.dto.ResponseDto;


@ControllerAdvice
public class BookStoreExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errorMessage = errorList.stream().map(objectError -> objectError.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseDto responseDTO = new ResponseDto("Exception found", errorMessage);
		return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookStoreException.class)
	public ResponseEntity<ResponseDto> handleAddressBookException(BookStoreException exception) {
		ResponseDto responseDto = new ResponseDto("Exception caught", exception.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
	}

}
