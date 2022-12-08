package com.juablaz.bankhexddd.accounting.infraestructure.exception;

import static com.juablaz.bankhexddd.accounting.infraestructure.exception.AccountNotFoundHTTPException.ACCOUNT_NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ACCOUNT_NOT_FOUND)
public class AccountNotFoundHTTPException extends RuntimeException {

  public static final String ACCOUNT_NOT_FOUND = "Account not found";
  private static final long serialVersionUID = -5596141541624573125L;

  public AccountNotFoundHTTPException(String message) {
    super(message);
  }

  public AccountNotFoundHTTPException() {
    super(ACCOUNT_NOT_FOUND);
  }
}
