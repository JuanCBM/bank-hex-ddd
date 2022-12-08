package com.juablaz.bankhexddd.analytics.domain.event;

public class AccountCreatedEvent {

  private String accountId;
  private String currency;


  public AccountCreatedEvent(String accountId, String currency) {
    this.accountId = accountId;
    this.currency = currency;

  }

  public AccountCreatedEvent() {
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
