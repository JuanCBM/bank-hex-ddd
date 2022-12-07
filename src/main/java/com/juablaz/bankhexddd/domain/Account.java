package com.juablaz.bankhexddd.domain;

import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;
import org.springframework.util.Assert;

public class Account {

  private Long id;
  private String name;
  private Money balance;

  public Account() {

  }

  public Account(String name, String currency) {
    this.name = name;
    this.balance = new Money(Float.NaN, currency);
  }

  public static Account of(FullAccountResponseDto fullAccountResponseDto) {
    Account account = new Account();
    account.setId(fullAccountResponseDto.getId());
    account.setName(fullAccountResponseDto.getName());
    account.setBalance(
        new Money(fullAccountResponseDto.getValue(), fullAccountResponseDto.getCurrency()));

    return account;
  }

  public void withdraw(Money amount) {
    Assert.isTrue(amount.sameCurrency(this.balance), Money.INCOMPATIBLE_CURRENCY);

    this.balance = this.balance.subtract(amount);
  }

  public void deposit(Money amount) {
    Assert.isTrue(amount.sameCurrency(this.balance), Money.INCOMPATIBLE_CURRENCY);
    this.balance = this.balance.add(amount);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Money getBalance() {
    return balance;
  }

  public void setBalance(Money balance) {
    this.balance = balance;
  }
}
