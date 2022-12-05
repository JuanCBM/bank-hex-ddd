package com.juablaz.bankhexddd.domain.repository;

import com.juablaz.bankhexddd.domain.request.FullAccountRequestDto;
import com.juablaz.bankhexddd.domain.response.FullAccountResponseDto;

public interface AccountRepository {

  FullAccountResponseDto findById(Long accountId);

  Long save(FullAccountRequestDto fullAccountRequestDto);

  void update(FullAccountRequestDto fullAccountRequestDto);

}
