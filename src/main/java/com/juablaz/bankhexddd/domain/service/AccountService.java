package com.juablaz.bankhexddd.domain.service;

import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;

public interface AccountService {

  FullAccountResponseDto find(Long accountId);

  Long create(String name, String currency);

  void deposit(Long accountId, Float value, String currency);

  void withdraw(Long accountId, Float value, String currency);
}
