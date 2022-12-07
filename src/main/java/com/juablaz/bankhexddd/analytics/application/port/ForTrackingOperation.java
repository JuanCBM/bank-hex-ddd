package com.juablaz.bankhexddd.analytics.application.port;

import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;

public interface ForTrackingOperation {

  void trackNewAccount(String accountId, String currency);

  void trackConnection(String accountId);

  FullAnalyticAccountResponseDto findAccountsPerCurrency(String currency);

}
