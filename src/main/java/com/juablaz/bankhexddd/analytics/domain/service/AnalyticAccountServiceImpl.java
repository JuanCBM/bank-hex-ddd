package com.juablaz.bankhexddd.analytics.domain.service;

import com.juablaz.bankhexddd.analytics.domain.AnalyticAccount;
import com.juablaz.bankhexddd.analytics.domain.repository.AnalyticAccountRepository;
import com.juablaz.bankhexddd.analytics.domain.request.FullAccountAnalyticRequestDto;
import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;

public class AnalyticAccountServiceImpl implements AnalyticAccountService {

  private final AnalyticAccountRepository analyticAccountRepository;

  public AnalyticAccountServiceImpl(AnalyticAccountRepository analyticAccountRepository) {
    this.analyticAccountRepository = analyticAccountRepository;
  }

  @Override
  public void trackNewAccount(String accountId, String currency) {
    FullAccountAnalyticRequestDto fullAccountAnalyticRequestDto = new FullAccountAnalyticRequestDto(
        accountId, currency);
    this.analyticAccountRepository.trackNewAccount(fullAccountAnalyticRequestDto);
  }

  @Override
  public void trackConnection(String accountId) {
    FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto = analyticAccountRepository.find(
        accountId);

    AnalyticAccount analyticAccount = AnalyticAccount.of(fullAnalyticAccountResponseDto);
    analyticAccount.addConnection();

    this.analyticAccountRepository.update(FullAccountAnalyticRequestDto.of(analyticAccount));

  }
}
