package com.juablaz.bankhexddd.accounting.application.rest;

import com.juablaz.bankhexddd.accounting.application.dto.response.AccountResponseDto;
import com.juablaz.bankhexddd.accounting.application.exception.IncompatibleCurrencyHTTPException;
import com.juablaz.bankhexddd.accounting.application.port.ForCreatingAccountsOperation;
import com.juablaz.bankhexddd.accounting.application.port.ForExistingAccountsOperation;
import com.juablaz.bankhexddd.accounting.domain.exception.IncompatibleCurrencyException;
import com.juablaz.bankhexddd.accounting.domain.service.AccountService;
import com.juablaz.bankhexddd.analytics.domain.event.AccountCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
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
  ApplicationEventPublisher applicationEventPublisher;

  public BankWindowControllerAdapter(AccountService accountService,
      ApplicationEventPublisher applicationEventPublisher) {
    this.accountService = accountService;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  // Viola CQS, se podría dar la carga de generar el UUID al Controller y no dejarla en el Servicio
  // Mandándola como parámetro.
  // No es preocupante.
  @Override
  @GetMapping(value = {"", "/"})
  public String create(@RequestParam String name, @RequestParam String currency) {
    String accountId = this.accountService.create(name, currency);

    applicationEventPublisher.publishEvent(new AccountCreatedEvent(accountId, currency));

    return accountId;
  }

  @Override
  @GetMapping(value = {"/{accountId}"})
  public AccountResponseDto find(@PathVariable String accountId) {
    return AccountResponseDto.of(this.accountService.find(accountId));
  }

  @Override
  @GetMapping(value = "/{accountId}/deposit")
  public void deposit(@PathVariable String accountId, @RequestParam Float amount,
      @RequestParam String currency) throws IncompatibleCurrencyHTTPException {
    try {
      this.accountService.deposit(accountId, amount, currency);
    } catch (IncompatibleCurrencyException e) {
      throw new IncompatibleCurrencyHTTPException(e.getMessage());
    }

  }

  @Override
  @GetMapping(value = "/{accountId}/withdraw")
  public void withdraw(@PathVariable String accountId, @RequestParam Float amount,
      @RequestParam String currency) throws IncompatibleCurrencyHTTPException {
    try {
      this.accountService.withdraw(accountId, amount, currency);
    } catch (IncompatibleCurrencyException e) {
      throw new IncompatibleCurrencyHTTPException(e.getMessage());
    }
  }
}
