package com.juablaz.bankhexddd.accounting.domain.service;

import com.juablaz.bankhexddd.accounting.domain.Account;
import com.juablaz.bankhexddd.accounting.domain.Money;
import com.juablaz.bankhexddd.accounting.domain.exception.IncompatibleCurrencyException;
import com.juablaz.bankhexddd.accounting.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.accounting.domain.request.AccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;

public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public FullAccountResponseDto find(String accountId) {
    return this.accountRepository.findById(accountId);
  }

  @Override
  public String create(String name, String currency) {
    Account account = new Account(name, currency);
    this.accountRepository.save(FullAccountRequestDto.of(account));
    // Eventito
    return account.getId();
  }

  @Override
  public void deposit(String accountId, Float value, String currency)
      throws IncompatibleCurrencyException {
    FullAccountResponseDto fullAccountResponseDto = this.accountRepository.findById(accountId);

    Account account = Account.of(fullAccountResponseDto);
    account.deposit(new Money(value, currency));

    this.accountRepository.update(AccountRequestDto.of(account));
  }

  @Override
  public void withdraw(String accountId, Float value, String currency)
      throws IncompatibleCurrencyException {
    FullAccountResponseDto fullAccountResponseDto = this.accountRepository.findById(accountId);

    Account account = Account.of(fullAccountResponseDto);
    account.withdraw(new Money(value, currency));

    this.accountRepository.update(AccountRequestDto.of(account));
  }
}
