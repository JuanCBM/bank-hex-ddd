package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "analytic_account")
@Data
@Entity
@NoArgsConstructor
public class AnalyticAccountEntity {

  @Id
  public String accountId;

  public String currency;

}
