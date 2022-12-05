package com.juablaz.bankhexddd.application.rest;

import com.juablaz.bankhexddd.application.response.AccountResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/accounts")
public interface ForExistingAccountsOperation {

  @GetMapping("/{accountId}")
  AccountResponseDto find(@PathVariable Long accountId);

  @GetMapping("/{accountId}/deposit")
  void deposit(@PathVariable Long accountId, @RequestParam Float amount, @RequestParam String currency);

  @GetMapping("/{accountId}/withdraw")
  void withdraw(@PathVariable Long accountId, @RequestParam Float amount, @RequestParam String currency);

}
