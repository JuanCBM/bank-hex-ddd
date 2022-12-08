package com.juablaz.bankhexddd.analytics.domain.request;

import com.juablaz.bankhexddd.analytics.domain.AnalyticAccount;
import com.juablaz.bankhexddd.analytics.domain.AnalyticConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FullAccountAnalyticRequestDto {

  private String accountId;
  private String currency;
  private List<LocalDateTime> analyticConnections;

  public FullAccountAnalyticRequestDto(String accountId, String currency,
      List<LocalDateTime> analyticConnections) {
    this.accountId = accountId;
    this.currency = currency;
    this.analyticConnections = analyticConnections;
  }

  public FullAccountAnalyticRequestDto() {
  }

  public static FullAccountAnalyticRequestDto of(AnalyticAccount analyticAccount) {
    return new FullAccountAnalyticRequestDto(
        analyticAccount.getId(), analyticAccount.getCurrency(),
        analyticAccount.getAnalyticConnections().stream()
            .map(AnalyticConnection::getStartedAt).collect(Collectors.toList())
    );
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

  public List<LocalDateTime> getAnalyticConnections() {
    return analyticConnections;
  }

}
