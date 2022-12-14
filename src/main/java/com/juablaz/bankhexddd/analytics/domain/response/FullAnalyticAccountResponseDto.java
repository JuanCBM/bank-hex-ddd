package com.juablaz.bankhexddd.analytics.domain.response;

import com.juablaz.bankhexddd.analytics.domain.AnalyticAccount;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FullAnalyticAccountResponseDto {

  private String accountId;
  private String currency;
  private List<LocalDateTime> annalyticConnections;

  public FullAnalyticAccountResponseDto(String accountId, String currency,
      List<LocalDateTime> annalyticConnections) {
    this.accountId = accountId;
    this.currency = currency;
    this.annalyticConnections = annalyticConnections;
  }

  public FullAnalyticAccountResponseDto() {
  }

  public static FullAnalyticAccountResponseDto of(AnalyticAccount analyticAccount) {
    return new FullAnalyticAccountResponseDto
        (analyticAccount.getId(), analyticAccount.getCurrency(),
            analyticAccount.getAnalyticConnections().stream()
                .map(analyticConnectionEntity -> analyticConnectionEntity.getStartedAt()).collect(
                    Collectors.toList()));
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
