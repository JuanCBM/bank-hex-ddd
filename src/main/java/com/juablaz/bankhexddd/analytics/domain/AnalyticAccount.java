package com.juablaz.bankhexddd.analytics.domain;

import com.juablaz.bankhexddd.analytics.domain.response.FullAnalyticAccountResponseDto;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyticAccount {

  private final List<AnalyticConnection> analyticConnections;
  private String id;
  private String currency;

  public AnalyticAccount(String id, String currency, List<AnalyticConnection> analyticConnections) {
    this.id = id;
    this.currency = currency;
    this.analyticConnections = analyticConnections;
  }

  public static AnalyticAccount of(FullAnalyticAccountResponseDto fullAnalyticAccountResponseDto) {
    return new AnalyticAccount(
        fullAnalyticAccountResponseDto.getAccountId(),
        fullAnalyticAccountResponseDto.getCurrency(),
        fullAnalyticAccountResponseDto.getAnnalyticConnections().stream().map(localDateTime ->
            new AnalyticConnection(localDateTime)).collect(Collectors.toList())
    );

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
