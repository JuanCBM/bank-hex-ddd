package com.juablaz.bankhexddd.analytics.infraestructure.repository.h2;

import com.juablaz.bankhexddd.analytics.domain.request.FullAccountAnalyticRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "analytic_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnalyticAccountEntity {

  @Id
  private String accountId;

  private String currency;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AnalyticConnectionEntity> analyticConnections;

  public static AnalyticAccountEntity of(
      FullAccountAnalyticRequestDto fullAccountAnalyticRequestDto) {
    AnalyticAccountEntity analyticAccount = new AnalyticAccountEntity();
    analyticAccount.setAccountId(fullAccountAnalyticRequestDto.getAccountId());
    analyticAccount.setCurrency(fullAccountAnalyticRequestDto.getCurrency());
    analyticAccount.setAnalyticConnections(
        fullAccountAnalyticRequestDto.getAnalyticConnections().stream()
            .map(AnalyticConnectionEntity::new)
            .collect(Collectors.toList()));

    return analyticAccount;
  }
}
