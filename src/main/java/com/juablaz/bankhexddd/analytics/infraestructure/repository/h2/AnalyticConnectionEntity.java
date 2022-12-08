package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "analytic_connection")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnalyticConnectionEntity {

  @Id
  private String id;

  private LocalDateTime startedAt;

  public AnalyticConnectionEntity(LocalDateTime startedAt) {
    this.id = UUID.randomUUID().toString();
    this.startedAt = startedAt;
  }
}
