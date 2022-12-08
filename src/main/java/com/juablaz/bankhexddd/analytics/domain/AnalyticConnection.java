package com.juablaz.bankhexddd.analytics.domain;

import java.time.LocalDateTime;

public class AnalyticConnection {

  private final LocalDateTime startedAt;

  public AnalyticConnection(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public AnalyticConnection() {
    this.startedAt = LocalDateTime.now();
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

}
