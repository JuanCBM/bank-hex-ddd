package com.juablaz.bankhexddd.accounting.domain;

import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;
import java.util.UUID;
import org.springframework.util.Assert;

public class Account {

  private String id;
  private String name;
  private Money balance;

  public Account() {

  }

  public Account(String name, String currency) {
    this.id = UUID.randomUUID().toString();
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
