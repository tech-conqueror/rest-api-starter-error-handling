package com.techconqueror.api.rest.starter.error.handling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ResourceNotFoundException extends ErrorResponseException {

  public ResourceNotFoundException(String resourceType, String resourceId) {
    super(
      HttpStatus.NOT_FOUND, ProblemDetail
        .forStatusAndDetail(
          HttpStatus.NOT_FOUND, String.format("%s with ID %s not found", resourceType, resourceId)
        ), null
    );
  }
}
