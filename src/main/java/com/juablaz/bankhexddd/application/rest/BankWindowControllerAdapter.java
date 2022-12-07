package com.juablaz.bankhexddd.application.rest;

import com.juablaz.bankhexddd.application.port.ForCreatingAccountsOperation;
import com.juablaz.bankhexddd.application.port.ForExistingAccountsOperation;
import com.juablaz.bankhexddd.domain.service.AccountService;
import com.juablaz.bankhexddd.application.response.AccountResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/window/accounts")
public class BankWindowControllerAdapter implements ForCreatingAccountsOperation,
    ForExistingAccountsOperation {

  AccountService accountService;

  public BankWindowControllerAdapter(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  @GetMapping(value = {"", "/"})
  public Long create(@RequestParam String name, @RequestParam String currency) {
    return this.accountService.create(name, currency);
  }

  @Override
  @GetMapping(value = {"/{accountId}"})
  public AccountResponseDto find(@PathVariable Long accountId) {
    return AccountResponseDto.of(this.accountService.find(accountId));
  }

  @Override
  @GetMapping(value = "/{accountId}/deposit")
  public void deposit(@PathVariable Long accountId, @RequestParam Float amount,
      @RequestParam String currency) {
    this.accountService.deposit(accountId, amount, currency);
  }

  @Override
  @GetMapping(value = "/{accountId}/withdraw")
  public void withdraw(@PathVariable Long accountId, @RequestParam Float amount,
      @RequestParam String currency) {
    this.accountService.deposit(accountId, amount, currency);
  }
}
