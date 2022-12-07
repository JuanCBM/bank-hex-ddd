package com.juablaz.bankhexddd.application.port;

import com.juablaz.bankhexddd.application.response.AccountResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ForExistingAccountsOperation {

  AccountResponseDto find(@PathVariable Long accountId);

  void deposit(@PathVariable Long accountId, @RequestParam Float amount,
      @RequestParam String currency);

  void withdraw(@PathVariable Long accountId, @RequestParam Float amount,
      @RequestParam String currency);

}
