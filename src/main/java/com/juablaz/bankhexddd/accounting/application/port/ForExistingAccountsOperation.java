package com.juablaz.bankhexddd.accounting.application.port;

import com.juablaz.bankhexddd.accounting.application.dto.response.AccountResponseDto;
import com.juablaz.bankhexddd.accounting.application.exception.IncompatibleCurrencyHTTPException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ForExistingAccountsOperation {

  AccountResponseDto find(@PathVariable String accountId);

  void deposit(@PathVariable String accountId, @RequestParam Float amount,
      @RequestParam String currency) throws IncompatibleCurrencyHTTPException;

  void withdraw(@PathVariable String accountId, @RequestParam Float amount,
      @RequestParam String currency) throws IncompatibleCurrencyHTTPException;

}
