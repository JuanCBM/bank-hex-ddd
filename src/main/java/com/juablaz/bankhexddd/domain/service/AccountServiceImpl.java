package com.juablaz.bankhexddd.domain.service;

import com.juablaz.bankhexddd.domain.Account;
import com.juablaz.bankhexddd.domain.Money;
import com.juablaz.bankhexddd.domain.repository.AccountRepository;
import com.juablaz.bankhexddd.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;

public class AccountServiceImpl implements AccountService {

  private AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public FullAccountResponseDto find(Long accountId) {
    return this.accountRepository.findById(accountId);
  }

  @Override
  public Long create(String name, String currency) {
    Account account = new Account(name, currency);
    return this.accountRepository.save(FullAccountRequestDto.of(account));
  }

  @Override
  public void deposit(Long accountId, Float value, String currency) {
    FullAccountResponseDto fullAccountResponseDto = this.accountRepository.findById(accountId);

    Account account = Account.of(fullAccountResponseDto);
    account.deposit(new Money(value, currency));

    this.accountRepository.update(FullAccountRequestDto.of(account));
  }

  @Override
  public void withdraw(Long accountId, Float value, String currency) {
    FullAccountResponseDto fullAccountResponseDto = this.accountRepository.findById(accountId);

    Account account = Account.of(fullAccountResponseDto);
    account.withdraw(new Money(value, currency));

    this.accountRepository.update(FullAccountRequestDto.of(account));
  }
}
