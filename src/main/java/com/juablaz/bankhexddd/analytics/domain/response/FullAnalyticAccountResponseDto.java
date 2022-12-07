package com.juablaz.bankhexddd.analytics.domain.response;

public class FullAnalyticAccountResponseDto {

  private String accountId;
  private String currency;

  public FullAnalyticAccountResponseDto(String accountId, String currency) {
    this.accountId = accountId;
    this.currency = currency;
  }

  public FullAnalyticAccountResponseDto() {
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
