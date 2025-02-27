package com.techconqueror.api.rest.starter.error.handling.dto;

public record Error(
  String field,
  String reason,
  Object rejectedValue
) {}
