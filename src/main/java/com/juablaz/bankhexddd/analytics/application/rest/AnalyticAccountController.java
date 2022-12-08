package com.juablaz.bankhexddd.analytics.application.rest;

import com.juablaz.bankhexddd.analytics.application.port.ForTrackingOperation;
import com.juablaz.bankhexddd.analytics.application.response.AnalyticAccountResponseDto;
import com.juablaz.bankhexddd.analytics.domain.service.AnalyticAccountService;
import java.util.List;
import java.util.stream.Collectors;
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
  @GetMapping("/{accountId}/track")
  public void trackNewAccount(@PathVariable String accountId, @RequestParam String currency) {
    this.analyticAccountService.trackNewAccount(accountId, currency);
  }

  @Override
  @GetMapping("/{accountId}")
  public AnalyticAccountResponseDto findByAccountId(@PathVariable String accountId) {
    return AnalyticAccountResponseDto.of(this.analyticAccountService.findByAccountId(accountId));
  }

  @Override
  @GetMapping("/{currency}/list")
  public List<AnalyticAccountResponseDto> findAccountsPerCurrency(@PathVariable String currency) {

    return this.analyticAccountService.findAccountsPerCurrency(currency).stream()
        .map(fullAnalyticAccountResponseDto -> AnalyticAccountResponseDto.of(
            fullAnalyticAccountResponseDto))
        .collect(Collectors.toList());
  }
}
