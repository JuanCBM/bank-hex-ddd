package com.juablaz.bankhexddd.analytics.application.response;

import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public class AnalyticAccountResponseDto {

  private String accountId;
  private String currency;
  private List<LocalDateTime> annalyticConnections;

  public AnalyticAccountResponseDto(String accountId, String currency,
      List<LocalDateTime> annalyticConnections) {
    this.accountId = accountId;
    this.currency = currency;
    this.annalyticConnections = annalyticConnections;
  }

  public AnalyticAccountResponseDto() {
  }

  public static AnalyticAccountResponseDto of(
      FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto) {
    return new AnalyticAccountResponseDto(fullAnalyticAccountResponseDto.getAccountId(),
        fullAnalyticAccountResponseDto.getCurrency(),
        fullAnalyticAccountResponseDto.getAnnalyticConnections());
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public List<LocalDateTime> getAnnalyticConnections() {
    return annalyticConnections;
  }

  public void setAnnalyticConnections(List<LocalDateTime> annalyticConnections) {
    this.annalyticConnections = annalyticConnections;
  }
}
