package com.juablaz.bankhexddd.accounting.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncompatibleCurrencyHTTPException extends Exception {

  private static final long serialVersionUID = -5596141541624573125L;

  public IncompatibleCurrencyHTTPException(String message) {
    super(message);
  }

}