package com.juablaz.bankhexddd.accounting.domain.repository;

import com.juablaz.bankhexddd.accounting.domain.request.AccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;

public interface AccountRepository {

  FullAccountResponseDto findById(String accountId);

  void save(FullAccountRequestDto accountRequestDto);

  void update(AccountRequestDto accountRequestDto);

}
