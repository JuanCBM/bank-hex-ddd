package com.juablaz.bankhexddd.application.rest;

import com.juablaz.bankhexddd.application.response.AccountResponseDto;
import com.juablaz.bankhexddd.domain.service.AccountService;

public class ATMController implements ForExistingAccountsOperation {

  AccountService accountService;

  public ATMController(AccountService accountService) {
    this.accountService = accountService;
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
