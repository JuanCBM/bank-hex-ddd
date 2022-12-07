package com.juablaz.bankhexddd.analytics.application.rest;

import com.juablaz.bankhexddd.analytics.application.port.ForTrackingOperation;
import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import com.juablaz.bankhexddd.analytics.domain.service.AnalyticAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atm/analytics")
public class AnalyticAccountController implements ForTrackingOperation {

  AnalyticAccountService analyticAccountService;

  public AnalyticAccountController(AnalyticAccountService analyticAccountService) {
    this.analyticAccountService = analyticAccountService;
  }

  @Override
  @GetMapping("/{accountId}")
  public void trackNewAccount(@PathVariable String accountId, @RequestParam String currency) {
    this.analyticAccountService.trackNewAccount(accountId, currency);
  }

  @Override
  @GetMapping("/{accountId}/track")
  public void trackConnection(@PathVariable String accountId) {
    this.analyticAccountService.trackConnection(accountId);
  }

  @Override
  @GetMapping("/{currency}/list")
  public FullAnalyticAccountResponseDto findAccountsPerCurrency(@PathVariable String currency) {
    return null;
  }
}
