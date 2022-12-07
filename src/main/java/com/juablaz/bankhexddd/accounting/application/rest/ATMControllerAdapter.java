package com.juablaz.bankhexddd.accounting.application.rest;

import com.juablaz.bankhexddd.accounting.application.dto.response.AccountResponseDto;
import com.juablaz.bankhexddd.accounting.application.port.ForExistingAccountsOperation;
import com.juablaz.bankhexddd.accounting.domain.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atm/accounts")
public class ATMControllerAdapter implements ForExistingAccountsOperation {

  AccountService accountService;

  public ATMControllerAdapter(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  @GetMapping("/{accountId}")
  public AccountResponseDto find(@PathVariable String accountId) {
    return AccountResponseDto.of(this.accountService.find(accountId));
  }

  @Override
  @GetMapping(value = "/{accountId}/deposit")
  public void deposit(@PathVariable String accountId, @RequestParam Float amount,
      @RequestParam String currency) {
    this.accountService.deposit(accountId, amount, currency);
  }

  @Override
  @GetMapping(value = "/{accountId}/withdraw")
  public void withdraw(@PathVariable String accountId, @RequestParam Float amount,
      @RequestParam String currency) {
    this.accountService.withdraw(accountId, amount, currency);
  }
}
