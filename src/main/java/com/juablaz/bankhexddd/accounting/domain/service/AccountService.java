package com.juablaz.bankhexddd.accounting.domain.service;

import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;

public interface AccountService {

  FullAccountResponseDto find(String accountId);

  String create(String name, String currency);

  void deposit(String accountId, Float value, String currency);

  void withdraw(String accountId, Float value, String currency);
}
