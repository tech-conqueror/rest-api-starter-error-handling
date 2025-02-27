package com.techconqueror.api.rest.starter.error.handling.dto;

import java.util.ArrayList;
import java.util.Collection;

public class Errors extends ArrayList<Error> {

  public Errors(Collection<? extends Error> c) {
    super(c);
  }
}
