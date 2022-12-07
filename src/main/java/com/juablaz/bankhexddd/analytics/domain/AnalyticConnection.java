package com.juablaz.bankhexddd.analytics.domain;

import java.time.LocalDateTime;

public class AnalyticConnection {

  private LocalDateTime startedAt;

  public AnalyticConnection(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public AnalyticConnection() {
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }
}
