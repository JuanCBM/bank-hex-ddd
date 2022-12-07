package com.juablaz.bankhexddd.accounting.domain.repository;

import com.juablaz.bankhexddd.accounting.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.accounting.domain.response.FullAccountResponseDto;

public interface AccountRepository {

  FullAccountResponseDto findById(String accountId);

  void save(FullAccountRequestDto fullAccountRequestDto);

  void update(FullAccountRequestDto fullAccountRequestDto);

}
