package com.juablaz.bankhexddd.analytics.domain.service;

import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.util.List;

public interface AnalyticAccountService {

  void trackNewAccount(String accountId, String currency);

  List<FullAnalyticAccountResponseDto> findAccountsPerCurrency(String currency);

  FullAnalyticAccountResponseDto findByAccountId(String accountId);
}
