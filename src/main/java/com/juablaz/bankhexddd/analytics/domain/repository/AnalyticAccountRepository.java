package com.juablaz.bankhexddd.analytics.domain.repository;

import com.juablaz.bankhexddd.analytics.domain.request.FullAccountAnalyticRequestDto;
import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.util.List;

public interface AnalyticAccountRepository {

  List<FullAnalyticAccountResponseDto> findAccountsPerCurrency(String currency);

  void update(FullAccountAnalyticRequestDto fullAccountRequestDto);

  FullAnalyticAccountResponseDto find(String accountId);

  void create(FullAccountAnalyticRequestDto fullAccountRequestDto);

}
