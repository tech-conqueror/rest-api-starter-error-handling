package com.techconqueror.api.rest.starter.error.handling;

import com.techconqueror.api.rest.starter.error.handling.dto.Error;
import com.techconqueror.api.rest.starter.error.handling.dto.Errors;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException exception,
    HttpHeaders headers,
    HttpStatusCode status,
    WebRequest request
  ) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Validation Error");

    Errors errors = new Errors(exception
      .getBindingResult()
      .getFieldErrors()
      .stream()
      .map(fieldError -> new Error(fieldError.getField(), Optional
        .ofNullable(fieldError.getDefaultMessage())
        .orElse(""), Optional.ofNullable(fieldError.getRejectedValue()).orElse("null")
      ))
      .collect(Collectors.toList()));

    problemDetail.setProperty("errors", errors);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail defaultExceptionHandler() {
    return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
