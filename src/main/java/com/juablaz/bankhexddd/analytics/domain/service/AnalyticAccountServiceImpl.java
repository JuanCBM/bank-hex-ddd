package com.juablaz.bankhexddd.analytics.domain.service;

import com.juablaz.bankhexddd.analytics.domain.AnalyticAccount;
import com.juablaz.bankhexddd.analytics.domain.repository.AnalyticAccountRepository;
import com.juablaz.bankhexddd.analytics.domain.request.FullAccountAnalyticRequestDto;
import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyticAccountServiceImpl implements AnalyticAccountService {

  private final AnalyticAccountRepository analyticAccountRepository;

  public AnalyticAccountServiceImpl(AnalyticAccountRepository analyticAccountRepository) {
    this.analyticAccountRepository = analyticAccountRepository;
  }

  @Override
  public void trackNewAccount(String accountId, String currency) {
    FullAccountAnalyticRequestDto fullAccountAnalyticRequestDto = new FullAccountAnalyticRequestDto(
        accountId, currency, new ArrayList<>());
    this.analyticAccountRepository.create(fullAccountAnalyticRequestDto);
  }

  @Override
  public List<FullAnalyticAccountResponseDto> findAccountsPerCurrency(String currency) {
    List<FullAnalyticAccountResponseDto> fullAnalyticAccountResponseDtos = analyticAccountRepository.findAccountsPerCurrency(
        currency);

    return fullAnalyticAccountResponseDtos.stream().map(
            this::createNewConnection)
        .collect(
            Collectors.toList());
  }

  @Override
  public FullAnalyticAccountResponseDto findByAccountId(String accountId) {
    FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto = analyticAccountRepository.find(
        accountId);

    return createNewConnection(fullAnalyticAccountResponseDto);
  }

  private FullAnalyticAccountResponseDto createNewConnection(
      FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto) {
    AnalyticAccount analyticAccount = AnalyticAccount.of(fullAnalyticAccountResponseDto);
    analyticAccount.addConnection();

    this.analyticAccountRepository.update(FullAccountAnalyticRequestDto.of(analyticAccount));

    return FullAnalyticAccountResponseDto.of(analyticAccount);
  }

}
