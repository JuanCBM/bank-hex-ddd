package com.juablaz.bankhexddd.analytics.infraestructure.exception;

import static com.juablaz.bankhexddd.analytics.infraestructure.exception.AnalyticAccountNotFoundException.ANALYTIC_ACCOUNT_NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ANALYTIC_ACCOUNT_NOT_FOUND)
public class AnalyticAccountNotFoundException extends RuntimeException {

  public static final String ANALYTIC_ACCOUNT_NOT_FOUND = "Analytic account not found";
  private static final long serialVersionUID = -5596141541624573125L;

  public AnalyticAccountNotFoundException(String message) {
    super(message);
  }

  public AnalyticAccountNotFoundException() {
    super(ANALYTIC_ACCOUNT_NOT_FOUND);
  }
}
