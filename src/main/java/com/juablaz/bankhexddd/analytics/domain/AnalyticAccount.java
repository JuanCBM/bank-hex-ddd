package com.juablaz.bankhexddd.analytics.domain;

import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.util.Collections;
import java.util.List;

public class AnalyticAccount {

  private String id;
  private String currency;
  private List<AnalyticConnection> analyticConnections;

  public AnalyticAccount(String id, String currency,
      List<AnalyticConnection> analyticConnections) {
    this.id = id;
    this.currency = currency;
    this.analyticConnections = analyticConnections;
  }

  public static AnalyticAccount of(FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto) {
    AnalyticAccount account = new AnalyticAccount(
        fullAnalyticAccountResponseDto.getAccountId(),
        fullAnalyticAccountResponseDto.getCurrency(),
        Collections.emptyList()
    );

    return account;
  }

  public void addConnection() {
    this.analyticConnections.add(new AnalyticConnection());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public List<AnalyticConnection> getAnalyticConnections() {
    return analyticConnections;
  }

}
