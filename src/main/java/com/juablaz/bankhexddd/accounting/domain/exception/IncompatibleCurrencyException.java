package com.juablaz.bankhexddd.accounting.domain.exception;

public class IncompatibleCurrencyException extends RuntimeException {

  public static final String INCOMPATIBLE_CURRENCY = "Incompatible currency";
  private static final long serialVersionUID = -5596141541624573125L;

  public IncompatibleCurrencyException(String message) {
    super(message);
  }

  public IncompatibleCurrencyException() {
    super(INCOMPATIBLE_CURRENCY);
  }
}