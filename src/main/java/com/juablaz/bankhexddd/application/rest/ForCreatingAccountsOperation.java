package com.juablaz.bankhexddd.application.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/accounts")
public interface ForCreatingAccountsOperation {

  @GetMapping("/{accountId}")
  Long create(String name, @RequestParam String currency);

}
