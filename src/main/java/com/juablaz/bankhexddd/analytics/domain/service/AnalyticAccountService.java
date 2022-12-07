package com.juablaz.bankhexddd.analytics.domain.service;

public interface AnalyticAccountService {

  void trackNewAccount(String accountId, String currency);

  void trackConnection(String accountId);
}
