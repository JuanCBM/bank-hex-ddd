package com.juablaz.bankhexddd.analytics.application.port;

import com.juablaz.bankhexddd.analytics.application.response.AnalyticAccountResponseDto;
import java.util.List;

public interface ForTrackingOperation {

  void trackNewAccount(String accountId, String currency);

  AnalyticAccountResponseDto findByAccountId(String accountId);

  List<AnalyticAccountResponseDto> findAccountsPerCurrency(String currency);

}
