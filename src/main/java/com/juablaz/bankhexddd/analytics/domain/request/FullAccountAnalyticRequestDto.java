package com.juablaz.bankhexddd.analytics.domain.request;

import com.juablaz.bankhexddd.analytics.domain.AnalyticAccount;

public class FullAccountAnalyticRequestDto {

  private String accountId;
  private String currency;

  public FullAccountAnalyticRequestDto(String accountId, String currency) {
    this.accountId = accountId;
    this.currency = currency;
  }

  public FullAccountAnalyticRequestDto() {
  }

  public static FullAccountAnalyticRequestDto of(AnalyticAccount analyticAccount) {
    FullAccountAnalyticRequestDto fullAccountAnalyticRequestDto = new FullAccountAnalyticRequestDto(
        analyticAccount.getId(), analyticAccount.getCurrency()
    );

    return fullAccountAnalyticRequestDto;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
