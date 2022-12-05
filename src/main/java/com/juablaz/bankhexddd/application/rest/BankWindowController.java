package com.juablaz.bankhexddd.application.rest;

import com.juablaz.bankhexddd.application.response.AccountResponseDto;
import com.juablaz.bankhexddd.domain.service.AccountService;

public class BankWindowController implements ForCreatingAccountsOperation, ForExistingAccountsOperation {

  AccountService accountService;

  public BankWindowController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public Long create(String name, String currency) {
    return this.accountService.create(name, currency);
  }

  @Override
  public AccountResponseDto find(Long accountId) {
    return AccountResponseDto.of(this.accountService.find(accountId));
  }

  @Override
  public void deposit(Long accountId, Float amount, String currency) {
    this.accountService.deposit(accountId, amount, currency);
  }

  @Override
  public void withdraw(Long accountId, Float amount, String currency) {
    this.accountService.deposit(accountId, amount, currency);
  }
}
